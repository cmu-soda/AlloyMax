import subprocess
import os
from os import path
import shutil
import signal
import sys

from random import randint
from random import random

sys.path.append(path.dirname(path.dirname(path.abspath(__file__))))

from util import benchmark, options

def generate(tags, persons, max_tags, num_tables, min_persons, max_persons):
  person_tags = []
  for x in range(0, persons):
    x_tags = []
    value = randint(0, max_tags)
    t=0
    for y in range (0, tags):
      if t == max_tags:
        break
      v = random()
      if v <= float(value / tags):
        x_tags.append("P" + str(x) + "->" + "T" + str(y))
        t = t + 1
    if len(x_tags) > 0:
      person_tags.append("+".join(x_tags))
  person_tags = "+\n    ".join(person_tags)
  
  als = f"""
abstract sig Person {{
  tags: set Tag
}}
one sig {",".join(["P" + str(i) for i in range(persons)])} extends Person {{}}

abstract sig Tag {{}}
one sig {",".join(["T" + str(i) for i in range(tags)])} extends Tag {{}}

fact {{
  tags = {person_tags}
}}

sig Table {{
  seat: set Person,
}} {{
  #seat < {max_persons + 1}
  #seat > {min_persons - 1}
}}

fact {{
  all p: Person | one seat.p
}}
"""
  
  sat = als + f"""
run {{}} for exactly {num_tables} Table, 5 int
"""

  table_based = als + f"""
run {{
  // table-based
  all t: Table | softno t.seat.tags
}} for exactly {num_tables} Table, 5 int
"""

  tag_based = als + f"""
run {{
	// tag-based
	all t: Tag | softno seat.tags.t
}} for exactly {num_tables} Table, 5 int
"""
  
  return sat, table_based, tag_based


def run(outpath, run_sat=False, run_maxsat_one=False, run_maxsat_all=False, run_maxsat_part=False,
        run_maxsat_part_auto=False, timeout=180, repeat=5):
  params = [
    (7, 28, 6),
    (7, 30, 6),
    (8, 30, 7),
    (8, 32, 7),
    # (9, 34, 8),
  ]
  min_p = 3
  max_p = 7

  problems = []
  table_files = []
  tag_files = []
  sat_files = []

  for tag, p, tab in params:
    problem = f"{tag}_{p}_{tag}_{tab}_{min_p}_{max_p}"
    problems.append(problem)

    sat, table_based, tag_based = generate(tag, p, tag, tab, min_p, max_p)

    sat_filename = path.join(outpath, f"sat_{problem}.als")
    sat_files.append(sat_filename)
    with open(sat_filename, "w") as f:
      f.write(sat)

    table_filename = path.join(outpath, f"table_{problem}.als")
    table_files.append(table_filename)
    with open(table_filename, "w") as f:
      f.write(table_based)
    
    tag_filename = path.join(outpath, f"tag_{problem}.als")
    tag_files.append(tag_filename)
    with open(tag_filename, "w") as f:
      f.write(tag_based)
  
  if run_sat:
    benchmark(problems, sat_files, timeout=timeout, repeat=repeat)
  # print("====================\nTable-based\n====================")
  benchmark(problems, None, table_files, run_maxsat_one, run_maxsat_all,
            run_maxsat_part, run_maxsat_part_auto, timeout, repeat)
  # print("====================\nTag-based\n====================")
  # benchmark(problems, None, tag_files, run_maxsat_one, run_maxsat_all,
  #           run_maxsat_part, run_maxsat_part_auto, timeout, repeat)


def run_models(modelpath, run_sat=False, run_maxsat_one=False, run_maxsat_all=False, run_maxsat_part=False,
               run_maxsat_part_auto=False, timeout=180, repeat=5):
  models = filter(lambda x: x.startswith("sat") and x.endswith(".als"), os.listdir(modelpath))
  problems = []
  table_files = []
  tag_files = []
  sat_files = []

  for m in models:
    problems.append(m[len("sat_"):-len(".als")])
    sat_files.append(path.join(modelpath, m))
    table_files.append(path.join(modelpath, m.replace("sat", "table")))
    tag_files.append(path.join(modelpath, m.replace("sat", "tag")))
  
  if run_sat:
    benchmark(problems, sat_files, timeout=timeout, repeat=repeat)
  # print("====================\nTable-based\n====================")
  benchmark(problems, None, table_files, run_maxsat_one, run_maxsat_all,
            run_maxsat_part, run_maxsat_part_auto, timeout, repeat)
  # print("====================\nTag-based\n====================")
  # benchmark(problems, None, tag_files, run_maxsat_one, run_maxsat_all,
  #           run_maxsat_part, run_maxsat_part_auto, timeout, repeat)


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
