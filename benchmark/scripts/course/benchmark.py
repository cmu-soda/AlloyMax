import subprocess
import os
from os import path
import shutil
import signal
import sys
import random
import numpy as np

sys.path.append(path.dirname(path.dirname(path.abspath(__file__))))

from util import benchmark, options


def generate(num_courses, num_stu, max_core, max_interests):
  lec_types = [
    ["MonAM", "WedAM"],
    ["MonAM", "WedAM", "FriPM"],
    ["MonPM", "WedPM"],
    ["TueAM", "ThuAM"],
    ["TuePM", "TuePM"]
  ]

  courses = []
  students = []

  # generate courses
  for i in range(num_courses):
    courses.append(lec_types[np.random.randint(len(lec_types))])

  # generate students cores and interests
  for _ in range(num_stu):
    # generate cores
    cores = []
    tmp = list(range(num_courses))
    np.random.shuffle(tmp)

    t = set()
    for i in range(np.random.randint(max_core+1)):
      found = False
      for c in tmp[i:]:
        if set(courses[c]) & t == set():
          found = True
          cores.append(c)
          t = t | set(courses[c])
          break
      if not found:
        break
    
    # generate interests
    np.random.shuffle(tmp)
    if len(cores) > 1:
      interests = [cores[np.random.randint(len(cores))]]
    else:
      interests = []
      for c in tmp:
        if set(courses[c]) & t == set():
          interests.append(c)
          break
    for i in range(np.random.randint(max_interests)):
      interests.append(tmp[i+1])
    
    if len(cores) > 0:
      cores = " + ".join(["C" + str(i) for i in cores])
    else:
      cores = "none"
    interests = " + ".join(["C" + str(i) for i in interests])
    students.append((cores, interests))
  
  courses = list(map(lambda e: " + ".join(["C" + str(e[0]) + " -> " + l for l in e[1]]), enumerate(courses)))
  courses_str = " +\n    ".join(courses)
  student_str = "\n".join([
    f"one sig S{i} extends Student {{}} {{\n  core = {students[i][0]}\n  interests = {students[i][1]}\n}}"
    for i in range(len(students))
  ])
  
  als = f"""
abstract sig Day {{}}
one sig Mon, Tue, Wed, Thu, Fri extends Day {{}}

abstract sig Time {{}}
one sig AM, PM extends Time {{}}

abstract sig Course {{
  lectures: set Lecture
}}
one sig {",".join(["C" + str(i) for i in range(num_courses)])} extends Course {{}}

fact {{
  lectures = {courses_str}
}}

abstract sig Lecture {{
  day: one Day,
  time: one Time
}}
one sig MonAM, MonPM, TueAM, TuePM, WedAM, WedPM,
        ThuAM, ThuPM, FriAM, FriPM extends Lecture {{}}

fact {{
  day = MonAM -> Mon + MonPM -> Mon +
    TueAM -> Tue +TuePM -> Tue +
    WedAM -> Wed + WedPM -> Wed +
    ThuAM -> Thu + ThuPM -> Thu +
    FriAM -> Fri + FriPM -> Fri
  time = MonAM -> AM + MonPM -> PM +
    TueAM -> AM +TuePM -> PM +
    WedAM -> AM + WedPM -> PM +
    ThuAM -> AM + ThuPM -> PM +
    FriAM -> AM + FriPM -> PM
}}

abstract sig Student {{
  core: set Course,
  interests: set Course,
  courses: set Course
}}

{student_str}

pred conflict[c1, c2: Course] {{
  some l1, l2: Lecture {{
    l1 in c1.lectures
    l2 in c2.lectures
    l1.day = l2.day
    l1.time = l2.time
  }}
}}

pred validSchedule[courses: Student -> Course] {{
  all stu: Student {{
    #stu.courses > 2
    stu.core in stu.courses
    all disj c1, c2: stu.courses | not conflict[c1, c2]
  }}
}}

"""

  sat = als + "run AnySchedule {\n  validSchedule[courses]\n  all stu: Student | some stu.interests & stu.courses\n}"
  maxsat = als + "run MaxInterests1 {\n  validSchedule[courses]\n  all stu: Student | maxsome stu.interests & stu.courses\n}"
  return sat, maxsat


def run(outpath, run_sat=False, run_maxsat_one=False, run_maxsat_all=False, run_maxsat_part=False,
        run_maxsat_part_auto=False, timeout=180, repeat=5):
  max_core = 3
  max_interests = 6
  params = [
    (30, 40),
    (40, 50),
    (50, 60),
    (60, 70),
    (70, 80),
    (80, 90),
    (90, 100)
  ]
  problems = []
  maxsat_files = []
  sat_files = []

  for num_courses, num_stu in params:
    problem = f"{num_courses}_{num_stu}_{max_core}_{max_interests}"
    problems.append(problem)

    sat, maxsat = generate(num_courses, num_stu, max_core, max_interests)

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
