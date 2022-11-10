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

  problems = ["flush_reload", "meltdown", "spectre"]
  sat_files = list(map(lambda x: x + ".als", problems))
  maxsat_files = list(map(lambda x: x + "_maxsat.als", problems))

  sat_files = sat_files if run_sat else None
  benchmark(problems, sat_files, maxsat_files, run_maxsat_one, run_maxsat_all,
            run_maxsat_part, run_maxsat_part_auto, timeout, repeat)
