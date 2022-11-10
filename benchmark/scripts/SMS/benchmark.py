import subprocess
import os
from os import path
import shutil
import signal
import sys

import math
from random import randint
from random import random

sys.path.append(path.dirname(path.dirname(path.abspath(__file__))))

from util import benchmark, options


def generate(num_tasks, max_deadline, max_process_time, max_frags, max_slack, max_dep):
  sms_gen = [
    "./sms-gen",
    str(num_tasks),
    str(max_deadline),
    str(max_process_time),
    str(max_frags),
    str(max_slack),
    str(max_dep),
    str(randint(0, 100_000_000))
  ]
  out = subprocess.check_output(sms_gen, text=True).strip()
  out = out.splitlines()
  
  # Tasks
  frags_sig = []
  frags = []
  r = []
  d = []
  first = []
  final = []
  deps = []
  # Task fragments
  c = []
  prev = []
  for i in range(num_tasks):
    line = out[i+1].split(' ')
    num_frag = int(line[3])
    frags_sig.append(','.join([f'T{i}_{j}' for j in range(num_frag)]))
    frags.append([f'T{i} -> T{i}_{j}' for j in range(num_frag)])
    r.append(line[0])
    d.append(line[2])
    first.append(f'T{i}_0')
    final.append(f'T{i}_{num_frag-1}')
    deps.append([f'T{i} -> T{int(j)-1}' for j in out[num_tasks+i+1].split(' ')[1:]])
    c.append([f'T{i}_{j} -> {line[4:][j]}' for j in range(len(line[4:]))])
    prev.append([f'T{i}_{j} -> T{i}_{j-1}' for j in range(1, num_frag)])
  
  frags_str = ' +\n    '.join([' + '.join(f) for f in frags])
  deps_str = ' +\n    '.join([' + '.join(d) for d in deps if len(d) > 0])
  frags_sig_str = '\n'.join([f'one sig {f} extends Frag {{}}' for f in frags_sig])
  c_str = ' +\n    '.join([' + '.join(i) for i in c])
  prev_str = ' +\n    '.join([' + '.join(p) for p in prev if len(p) > 0])

  max_actual_deadline = max(map(lambda x: int(x), d))
  int_size = math.ceil(math.log2(max_actual_deadline + max_process_time)) + 1
  
  als = f"""
abstract sig Task {{
  frags: set Frag,
  r: Int,
  d: Int,
  first: Frag,
  final: Frag,
  deps: set Task
}}
sig Completed in Task {{}}

one sig {','.join([f'T{i}' for i in range(num_tasks)])} extends Task {{}}
fact {{
  frags =
    {frags_str}

  r = {' + '.join([f'T{i} -> {r[i]}' for i in range(num_tasks)])}

  d = {' + '.join([f'T{i} -> {d[i]}' for i in range(num_tasks)])}

  first = {' + '.join([f'T{i} -> {first[i]}' for i in range(num_tasks)])}

  final = {' + '.join([f'T{i} -> {final[i]}' for i in range(num_tasks)])}

  deps =
    {deps_str}
}}

abstract sig Frag {{
  s: Int,
  c: Int,
  prev: lone Frag
}} {{
  s < {max_actual_deadline}
}}
{frags_sig_str}
fact {{
  c =
    {c_str}

  prev =
    {prev_str}
}}

pred StartAfterRelease {{
  all t: Completed | t.first.s >= t.r
}}

pred StartAfterPrevFrag {{
  all t: Completed, f1, f2: t.frags | f1 -> f2 in prev implies
    f1.s >= plus[f2.s, f2.c]
}}

pred SingleFrag {{
  all disj t1, t2: Completed, f1: t1.frags, f2: t2.frags |
    f2.s >= plus[f1.s, f1.c] or f1.s >= plus[f2.s, f2.c]
}}

pred TaskDep {{
  all t1: Completed, t2: t1.deps {{
    t1.first.s >= plus[t2.final.s, t2.final.c]
    t2 in Completed
  }}
}}

pred Deadline {{
  all t: Completed | t.d >= plus[t.final.s, t.final.c]
}}"""
  
  sat = als + f"""
run {{
  StartAfterRelease
  StartAfterPrevFrag
  SingleFrag
  TaskDep
  Deadline
  some Completed
}} for {int_size} Int
"""
  maxsat = als + f"""
run {{
  StartAfterRelease
  StartAfterPrevFrag
  SingleFrag
  TaskDep
  Deadline
  maxsome Completed
}} for {int_size} Int
"""

  return sat, maxsat


def run(outpath, run_sat=False, run_maxsat_one=False, run_maxsat_all=False, run_maxsat_part=False,
        run_maxsat_part_auto=False, timeout=180, repeat=5):
  params = [
    # (30, 20, 6, 3, 3, 2),
    (34, 20, 6, 3, 3, 2),
    (38, 20, 6, 3, 3, 2),
    (42, 20, 6, 3, 3, 2),
    (46, 20, 6, 3, 3, 2),
    (50, 20, 6, 3, 3, 2),
    (52, 20, 6, 3, 3, 2),
  ]
  problems = []
  maxsat_files = []
  sat_files = []

  for num_tasks, max_deadline, max_process_time, max_frags, max_slack, max_dep in params:
    problem = f"{num_tasks}_{max_deadline}_{max_process_time}_{max_frags}_{max_slack}_{max_dep}"
    problems.append(problem)

    sat, maxsat = generate(num_tasks, max_deadline, max_process_time, max_frags, max_slack, max_dep)
    
    sat_filename = path.join(outpath, f"sat_{problem}.als")
    sat_files.append(sat_filename)
    with open(sat_filename, "w") as f:
      f.write(sat)

    maxsat_filename = path.join(outpath, f"maxsat_{problem}.als")
    maxsat_files.append(maxsat_filename)
    with open(maxsat_filename, "w") as f:
      f.write(maxsat)
  
  sat_files = sat_files if run_sat else None
  benchmark(problems, sat_files, maxsat_files, run_maxsat_one, run_maxsat_all,
            run_maxsat_part, run_maxsat_part_auto, timeout, repeat)


def run_models(modelpath, run_sat=False, run_maxsat_one=False, run_maxsat_all=False, run_maxsat_part=False,
               run_maxsat_part_auto=False, timeout=180, repeat=5):
  models = filter(lambda x: x.startswith("maxsat") and x.endswith(".als"), os.listdir(modelpath))
  problems = []
  maxsat_files = []
  sat_files = []

  for m in models:
    problems.append(m[len("maxsat_"):-len(".als")])
    maxsat_files.append(path.join(modelpath, m))
    sat_files.append(path.join(modelpath, m.replace("maxsat", "sat")))
  
  sat_files = sat_files if run_sat else None
  benchmark(problems, sat_files, maxsat_files, run_maxsat_one, run_maxsat_all,
            run_maxsat_part, run_maxsat_part_auto, timeout, repeat)


if __name__ == "__main__":
  run_sat, run_maxsat_one, run_maxsat_all, run_maxsat_part, run_maxsat_part_auto, timeout, repeat, model, from_file = options()

  if model is None:
    outpath = path.join(os.getcwd(), "out")
    if path.exists(outpath):
      shutil.rmtree(outpath)
    os.mkdir(outpath)
    run(outpath, run_sat, run_maxsat_one, run_maxsat_all, run_maxsat_part, run_maxsat_part_auto, timeout, repeat)
  else:
    run_models(model, run_sat, run_maxsat_one, run_maxsat_all, run_maxsat_part, run_maxsat_part_auto, timeout, repeat)
