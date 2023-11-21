import os
import sys
from os import path

sys.path.append(path.dirname(path.dirname(path.abspath(__file__))))
from util import benchmark_alloy_star, alloy_star_options

def run_models(modelpath, timeout=180, repeat=5):
  models = filter(lambda x: x.startswith("sat") and x.endswith(".als"), os.listdir(modelpath))
  models = sorted(models)
  problems = []
  sat_files = []

  for m in models:
    problems.append(m[len("sat_"):-len(".als")])
    sat_files.append(path.join(modelpath, m))

  benchmark_alloy_star(problems, sat_files, timeout, repeat)


if __name__ == "__main__":
  timeout, repeat, model = alloy_star_options()
  run_models(model, timeout=timeout, repeat=repeat)
