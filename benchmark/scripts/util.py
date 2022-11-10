import subprocess
import os
from os import path
import shutil
import signal
import sys
import time


def run_sat(sat, timeout=60, from_file=False):
  if from_file:
    raise Exception("'from_file' option is not implemented yet.")

  cmd = [
    "java",
    "-Xms8192k",
    "-Xmx8192m",
    "-Djava.library.path=../../lib/open-wbo",
    "-cp",
    "../../bin/org.alloytools.alloy.dist.jar",
    "edu.mit.csail.sdg.alloy4whole.BenchmarkMain",
    "-sat=" + sat
  ]

  trans_time = "N/A"
  solve_time = "N/A"
  num_inst = "N/A"
  with subprocess.Popen(cmd, stdout=subprocess.PIPE, stderr=subprocess.STDOUT, text=True, preexec_fn=os.setsid) as proc:
    try:
      out = proc.communicate(timeout=timeout)[0]
      total_time = 0
      for line in out.strip().split("\n"):
        if line.startswith("Translation time: "):
          trans_time = int(line[len("Translation time: "):])
        elif line.startswith("Total time: "):
          total_time = int(line[len("Total time: "):])
        elif line.startswith("Enumeration number: "):
          num_inst = line[len("Enumeration number: "):]
      if total_time > 0:
        solve_time = total_time - trans_time
      if trans_time == "N/A" or solve_time == "N/A" or num_inst == "N/A":
        print("Error when reading output, might be environment issues, please try the Docker image.")
        print("Output from AlloyMax:")
        print(out)
        exit(-1)
    except subprocess.TimeoutExpired:
      trans_time = "T/O"
      solve_time = "T/O"
      num_inst = "T/O"
      os.killpg(proc.pid, signal.SIGINT)
      out = proc.communicate()[0]
      for line in out.strip().split("\n"):
        if line.startswith("Translation time: "):
          trans_time = line[len("Translation time: "):]
        elif line.startswith("Enumeration number: "):
          num_inst = line[len("Enumeration number: "):]
    except KeyboardInterrupt as e:
      os.killpg(proc.pid, signal.SIGKILL)
      proc.communicate()
      cleantmp()
      raise e
  
  cleantmp()
  return f"{trans_time},{solve_time},{num_inst}"


def run_maxsat(maxsat, timeout=60, partition=False, auto=False, from_file=False):
  assert(not auto or partition)
  if from_file:
    return run_maxsat_from_file(maxsat, timeout, partition, auto)

  cmd = [
    "java",
    "-Xms8192k",
    "-Xmx8192m",
    "-Djava.library.path=../../lib/open-wbo",
    "-cp",
    "../../bin/org.alloytools.alloy.dist.jar",
    "edu.mit.csail.sdg.alloy4whole.BenchmarkMain",
    "-maxsat=" + maxsat
  ]
  if partition:
    cmd.append("-p")
  if auto:
    cmd.append("-auto")

  trans_time = "N/A"
  solve_time = "N/A"
  sat = "N/A"
  with subprocess.Popen(cmd, stdout=subprocess.PIPE, stderr=subprocess.STDOUT, text=True, preexec_fn=os.setsid) as proc:
    try:
      out = proc.communicate(timeout=timeout)[0]
      total_time = 0
      for line in out.strip().split("\n"):
        if line.startswith("Translation time: "):
          trans_time = int(line[len("Translation time: "):])
        elif line.startswith("Total time: "):
          total_time = int(line[len("Total time: "):])
        elif line.startswith("Solved: "):
          sat = line[len("Solved: "):]
      if total_time > 0:
        solve_time = total_time - trans_time
      if trans_time == "N/A" or solve_time == "N/A" or sat == "N/A":
        print("Error when reading output, might be environment issues, please try the Docker image.")
        print("Output from AlloyMax:")
        print(out)
        exit(-1)
    except subprocess.TimeoutExpired:
      trans_time = "T/O"
      solve_time = "T/O"
      sat = "T/O"
      os.killpg(proc.pid, signal.SIGINT)
      out = proc.communicate()[0]
      for line in out.strip().split("\n"):
        if line.startswith("Translation time: "):
          trans_time = line[len("Translation time: "):]
          break
    except KeyboardInterrupt as e:
      os.killpg(proc.pid, signal.SIGKILL)
      proc.communicate()
      cleantmp()
      raise e
  
  cleantmp()
  return f"{trans_time},{solve_time},{sat}"


def run_maxsat_from_file(maxsat, timeout=60, partition=False, auto=False):
  cmd = [
    "java",
    "-Xms8192k",
    "-Xmx8192m",
    "-Djava.library.path=../../lib/open-wbo",
    "-cp",
    "../../bin/org.alloytools.alloy.dist.jar",
    "edu.mit.csail.sdg.alloy4whole.BenchmarkMain",
    "-maxsat=" + maxsat,
    "-file-only"
  ]
  if partition:
    cmd.append("-p")
  if auto:
    cmd.append("-auto")

  cnf = None
  trans_time = "N/A"
  solve_time = "N/A"
  sat = "N/A"
  try:
    out = subprocess.check_output(cmd, text=True, timeout=timeout)
    for line in out.strip().split("\n"):
      if line.startswith("Translation time: "):
        trans_time = int(line[len("Translation time: "):])
      elif line.startswith("CNF File: "):
        cnf = line[len("CNF File: "):]
  except subprocess.TimeoutExpired:
    trans_time = "T/O"
    solve_time = "T/O"
    sat = "T/O"
    return f"{trans_time},{solve_time},{sat}"
  
  if cnf is not None:
    if partition:
      if auto:
        openwbo = [
          "../../lib/open-wbo/open-wbo",
          "-formula=0",
          "-algorithm=3",
          cnf
        ]
      else:
        openwbo = [
          "../../lib/open-wbo/open-wbo",
          "-formula=2",
          "-algorithm=4",
          cnf
        ]
    else:
      openwbo = [
        "../../lib/open-wbo/open-wbo",
        "-formula=0",
        "-algorithm=2",
        cnf
      ]

    timeout = max(timeout - round(trans_time / 1000), 1)
    start_time = time.time()
    with subprocess.Popen(openwbo, stdout=subprocess.PIPE, stderr=subprocess.STDOUT, text=True, preexec_fn=os.setsid) as proc:
      try:
        out = proc.communicate(timeout=timeout)[0]
        solve_time = round((time.time() - start_time) * 1000)
        for line in out.strip().split("\n"):
          if line.startswith("s OPTIMUM FOUND"):
            sat = "SAT"
          elif line.startswith("s UNSATISFIABLE"):
            sat = "UNSAT"
        if sat == "N/A":
          print("Error when reading output, might be environment issues, please try the Docker image.")
          print("Output from AlloyMax:")
          print(out)
          exit(-1)
      except subprocess.TimeoutExpired:
        solve_time = "T/O"
        sat = "T/O"
        os.killpg(proc.pid, signal.SIGINT)
        out = proc.communicate()[0]
      except KeyboardInterrupt as e:
        os.killpg(proc.pid, signal.SIGKILL)
        proc.communicate()
        cleantmp()
        raise e

  cleantmp()
  return f"{trans_time},{solve_time},{sat}"


def run_maxsat_all(maxsat, timeout=60, from_file=False):
  cmd = [
    "java",
    "-Xms8192k",
    "-Xmx8192m",
    "-Djava.library.path=../../lib/open-wbo",
    "-cp",
    "../../bin/org.alloytools.alloy.dist.jar",
    "edu.mit.csail.sdg.alloy4whole.BenchmarkMain",
    "-maxsat=" + maxsat,
    "-all-opt"
  ]

  cnf = None
  trans_time = "N/A"
  solve_time = "N/A"
  num_inst = "N/A"
  try:
    out = subprocess.check_output(cmd, text=True, timeout=timeout)
    for line in out.strip().split("\n"):
      if line.startswith("Translation time: "):
        trans_time = int(line[len("Translation time: "):])
      elif line.startswith("CNF File: "):
        cnf = line[len("CNF File: "):]
  except subprocess.TimeoutExpired:
    trans_time = "T/O"
    solve_time = "T/O"
    num_inst = "T/O"
    return f"{trans_time},{solve_time},{num_inst}"
  
  if cnf is not None:
    openwbo = [
      "../../lib/open-wbo/open-wbo",
      "-formula=0",
      "-algorithm=2",
      "-all-opt",
      cnf
    ]

    timeout = max(timeout - round(trans_time / 1000), 1)
    start_time = time.time()
    with subprocess.Popen(openwbo, stdout=subprocess.PIPE, stderr=subprocess.STDOUT, text=True, preexec_fn=os.setsid) as proc:
      try:
        out = proc.communicate(timeout=timeout)[0]
        solve_time = round((time.time() - start_time) * 1000)
        for line in out.strip().split("\n"):
          if line.startswith("c Optimal Solutions: "):
            num_inst = line[len("c Optimal Solutions: "):]
            break
        if num_inst == "N/A":
          print("Error when reading output, might be environment issues, please try the Docker image.")
          print("Output from AlloyMax:")
          print(out)
          exit(-1)
      except subprocess.TimeoutExpired:
        solve_time = "T/O"
        num_inst = "T/O"
        os.killpg(proc.pid, signal.SIGINT)
        out = proc.communicate()[0]
        for line in out.strip().split("\n"):
          if line.startswith("c Optimal Solutions: "):
            num_inst = line[len("c Optimal Solutions: "):]
            break
      except KeyboardInterrupt as e:
        os.killpg(proc.pid, signal.SIGKILL)
        proc.communicate()
        cleantmp()
        raise e

  cleantmp()
  return f"{trans_time},{solve_time},{num_inst}"


def benchmark(problems, sat_files=None, maxsat_files=None, maxsat_one=False, maxsat_all=False,
              maxsat_part=False, maxsat_part_auto=False, timeout=180, repeat=5, from_file=False):
  header = "problem"
  if maxsat_files is not None:
    if maxsat_one:
      header += ",maxsat_trans,maxsat_solve,maxsat_result"
    if maxsat_all:
      header += ",all_opt_trans,all_opt_solve,#opt_inst"
    if maxsat_part:
      header += ",part_trans,part_solve,part_result"
    if maxsat_part_auto:
      header += ",auto_part_trans,auto_part_solve,auto_part_result"
  if sat_files is not None:
    header += ",sat_trans,sat_solve,#inst"
  
  print(header)
  for i in range(len(problems)):
    for _ in range(repeat):
      results = problems[i]
      if maxsat_files is not None:
        if maxsat_one:
          results += "," + run_maxsat(maxsat_files[i], timeout=timeout, from_file=from_file)
        if maxsat_all:
          results += "," + run_maxsat_all(maxsat_files[i], timeout=timeout, from_file=from_file)
        if maxsat_part:
          results += "," + run_maxsat(maxsat_files[i], timeout=timeout, partition=True, from_file=from_file)
        if maxsat_part_auto:
          results += "," + run_maxsat(maxsat_files[i], timeout=timeout, partition=True, auto=True, from_file=from_file)
      if sat_files is not None:
        results += "," + run_sat(sat_files[i], timeout=timeout, from_file=from_file)
      print(results)


def options():
  run_sat = False
  run_maxsat_one = False
  run_maxsat_all = False
  run_maxsat_part = False
  run_maxsat_part_auto = False
  timeout = 180
  repeat = 5
  model = None
  from_file = False

  if len(sys.argv) < 2:
    print("Usage: benchmark.py")
    print("\t-sat\t\t\tEnumerate all solutions by using SAT")
    print("\t-maxsat\t\t\tFind one optimal solution")
    print("\t-maxsat_all\t\tFind all optimal solutions")
    print("\t-maxsat_part\t\tFind one optimal solution by using Max-SAT with user partitioning")
    print("\t-maxsat_part_auto\tFind one optimal solution by using auto partitioning")
    print("\t-t=<timeout> in seconds")
    print("\t-r=<repeat>\t\tNumber of times to repeat the run")
    print("\t-m=<model path>")
    print("\t-from_file\t\tGenerate the CNF/WCNF file and then call the solver")
    exit(0)
  else:
    for arg in sys.argv[1:]:
      if arg == "-sat":
        run_sat = True
      elif arg == "-maxsat":
        run_maxsat_one = True
      elif arg == "-maxsat_all":
        run_maxsat_all = True
      elif arg == "-maxsat_part":
        run_maxsat_part = True
      elif arg == "-maxsat_part_auto":
        run_maxsat_part_auto = True
      elif arg == "-from_file":
        from_file = True
      elif arg.startswith("-t="):
        timeout = int(arg[len("-t="):])
      elif arg.startswith("-r="):
        repeat = int(arg[len("-r="):])
      elif arg.startswith("-m="):
        model = arg[len("-m="):]
  
  return run_sat, run_maxsat_one, run_maxsat_all, run_maxsat_part, run_maxsat_part_auto, timeout, repeat, model, from_file


def alloy_star_options():
  timeout = 180
  repeat = 5
  model = None

  if len(sys.argv) < 2:
    print("Usage: benchmark.py")
    print("\t-t=<timeout> in seconds")
    print("\t-r=<repeat> number of time to repeat the run")
    print("\t-m=<model path>")
    exit(0)
  else:
    for arg in sys.argv[1:]:
      if arg.startswith("-t="):
        timeout = int(arg[len("-t="):])
      elif arg.startswith("-r="):
        repeat = int(arg[len("-r="):])
      elif arg.startswith("-m="):
        model = arg[len("-m="):]
  
  return timeout, repeat, model


def benchmark_alloy_star(problems, sat_files, timeout=180, repeat=5):
  print("problem,trans,solve,result")
  for i in range(len(problems)):
    for _ in range(repeat):
      results = problems[i] + "," + run_alloy_star(sat_files[i], timeout)
      print(results)


def run_alloy_star(sat_file, timeout=60):
  cmd = [
    "java",
    "-Xms8192k",
    "-Xmx8192m",
    "-Djava.library.path=../../lib",
    "-jar",
    "../../bin/hola-cli.jar",
    sat_file
  ]

  trans_time = "N/A"
  solve_time = "N/A"
  sat = "N/A"
  with subprocess.Popen(cmd, stdout=subprocess.PIPE, stderr=subprocess.STDOUT, text=True, preexec_fn=os.setsid) as proc:
    try:
      out = proc.communicate(timeout=timeout)[0]
      total_time = 0
      for line in out.strip().split("\n"):
        if line.startswith("Translation time: "):
          trans_time = int(line[len("Translation time: "):])
        elif line.startswith("Total time: "):
          total_time = int(line[len("Total time: "):])
        elif line.startswith("Solved: "):
          sat = line[len("Solved: "):]
      if total_time > 0:
        solve_time = total_time - trans_time
      if trans_time == "N/A" or solve_time == "N/A" or sat == "N/A":
        print("Error when reading output from Alloy*:")
        print(out)
        exit(-1)
    except subprocess.TimeoutExpired:
      trans_time = "T/O"
      solve_time = "T/O"
      sat = "T/O"
      os.killpg(proc.pid, signal.SIGINT)
      out = proc.communicate()[0]
      for line in out.strip().split("\n"):
        if line.startswith("Translation time: "):
          trans_time = line[len("Translation time: "):]
          break
    except KeyboardInterrupt as e:
      os.killpg(proc.pid, signal.SIGKILL)
      proc.communicate()
      cleantmp()
      raise e
  
  cleantmp()
  return f"{trans_time},{solve_time},{sat}"


def cleantmp():
  # FIXME: this is wrong when the user open two benchmarks at the same time, this will
  # delete the tmp files generated for the other benchmark
  tmps = filter(lambda x: x.startswith("kodkod") or x.endswith("cnf"), os.listdir("/tmp"))
  for t in tmps:
    t = path.join("/tmp", t)
    os.remove(t)


def collect_stat(problems, files):
  print("problem,#vars,#clauses")
  for i in range(len(problems)):
    cmd = [
      "java",
      "-Xms8192k",
      "-Xmx8192m",
      "-Djava.library.path=../../lib/open-wbo",
      "-cp",
      "../../bin/org.alloytools.alloy.dist.jar",
      "edu.mit.csail.sdg.alloy4whole.BenchmarkMain",
      "-maxsat=" + files[i],
      "-file-only"
    ]

    num_vars = "N/A"
    num_clauses = "N/A"
    out = subprocess.check_output(cmd, text=True)
    for line in out.strip().split("\n"):
      if line.startswith("CNF generated. "):
        els = line[len("CNF generated. "):].split(", ")
        num_vars = els[1][len("Total variables: "):]
        num_clauses = els[2][len("Total clauses: "):]
        break

    print(f"{problems[i]},{num_vars},{num_clauses}")
