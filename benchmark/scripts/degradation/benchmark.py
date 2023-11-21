import subprocess
import os
from os import path
import shutil
import signal
import sys

sys.path.append(path.dirname(path.dirname(path.abspath(__file__))))

from util import benchmark, options


if __name__ == "__main__":
  run_sat, run_maxsat_one, run_maxsat_all, run_maxsat_part, run_maxsat_part_auto, timeout, repeat, model, from_file = options()

  problems = ["10_2_1", "20_3_1", "26_2_1", "26_2_2"]
  sat_files = None
  maxsat_files = list(map(lambda x: "maxsat_" + x + ".als", problems))

  benchmark(problems, sat_files, maxsat_files, run_maxsat_one, run_maxsat_all,
            run_maxsat_part, run_maxsat_part_auto, timeout, repeat, from_file)
