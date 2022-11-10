[![DOI](https://zenodo.org/badge/326310778.svg)](https://zenodo.org/badge/latestdoi/326310778)

# AlloyMax Benchmark
This is the reproduction package of the benchmarks used for the work *AlloyMax: Bringing Maximum Satisfaction to Relational Specifications,* which will appear in FSE 2021. This package contains AlloyMax executable, the necessary libraries, the models used in the paper, and the scripts for running the benchmark.

## System Requirements
AlloyMax requires Java version >= 1.8. Although AlloyMax can run on both Windows and Linux machines, some of the back-end Sat/MaxSAT solvers can only run on Linux. Specifically, we use OpenWBO (to support partitioning) as the MaxSAT solver in our paper, which can only run on Linux. **Therefore, we suggest using a Linux machine to reproduce our results.**

Also, some models requires a large memory. **We suggest using a machine with at least 16GB memory.** We ran the benchmarks on a machine with 24GB memory.

In addition, the benchmark scripts require Python 3.8 and numpy. The program has been tested under the following versions of the software:
```
Java:
openjdk version "11.0.8" 2020-07-14
OpenJDK Runtime Environment (build 11.0.8+10-post-Ubuntu-0ubuntu120.04)
OpenJDK 64-Bit Server VM (build 11.0.8+10-post-Ubuntu-0ubuntu120.04, mixed mode, sharing)
```
```
Python 3.8.5
numpy  1.19.4
```

## Install Instruction
1. Clone this repository to your local machine.
```
cd <download directory>
git clone https://github.com/SteveZhangBit/alloy-maxsat-benchmark.git
```

2. Run AlloyMax
```
java -jar bin/org.alloytools.alloy.dist.jar
```
You should be able to see the AlloyMax GUI show up on the screen.

## Docker Image
In addition, we provide a Docker image. In order to build the Docker image:
```
cd <download directory>/docker
docker build -t alloymax .
```
The Docker image takes 2.0GB disk space. Then, to run the docker image:
```
docker run -p 5900:5900 -it --rm alloymax
```
The package is downloaded at the root ```/``` directory. You can test the benchmarks directly from the command-line interface.

*Note for Mac/Windows Users: Docker runs on a virtual machine on Mac/Windows. Thus, the memory is limited to a given number in the setting (2GB by default). You should change it to a larger number (e.g., 16Gb) to avoid memory issues.*

### VNC for GUI
You can also run AlloyMax in GUI mode through VNC. In order to connect to the Docker container through VNC, you should install [TigerVNC viewer](https://tigervnc.org/) on your **host** machine: e.g., on Ubuntu
```
sudo apt-get install tigervnc-viewer
```
Other systems can follow the instruction on the website of TigerVNC.

To access the container through VNC, first, launch the VNC server from the Docker container:
```
/startup-vnc.sh
```

Then, to connect to the VNC server from your host machine:
```
vncviewer 127.0.0.1
```

## Try out AlloyMax (Example in Section 2)
1. Open AlloyMax in GUI
    ```
    java -jar bin/org.alloytools.alloy.dist.jar
    ```

2. Open ```scripts/course/course_schedule.als```
3. Generate any valid schedule:

    3.1 Select: *Options -> Solver -> SAT4J*.

    3.2 Select: *Execute -> Run AnySchedule*.

    3.3 Click on the **Instance** on the right panel to open the visualizer.

    3.4 In the visualizer, select *Theme -> Load Theme -> course/course.thm* for simpler visualization.
4. Generate schedule that maximizes Alice's interests:

    4.1 Select: *Options -> Solver -> OpenWBO*.

        Note: Right now, OpenWBO only works on amd64 machines. Although SAT4JMax is cross-platform and works for Course problems, it does not work for some other problems (e.g., problems with softno).

    4.2 Select: *Execute -> Run MaxInterests1*.

    4.3 Repeat 3.3 and 3.4 for visualization.
5. Generate schedule with hard constraints on time preferences.

    5.1 Select: *Execute -> Run WithPrefer*. It should show UNSAT.
6. Generate schedule with soft time preferences.

    6.1 Uncomment Case 4.

    6.2 Select: *Execute -> Run WithSoftPrefer*.

        Note: comment it out if not using it, it may affect other problems.
7. Generate schedule with priorities on goals.

    7.1 Uncomment Case 5.

    7.2 Select: *Execute -> Run WithSoftPreferAndPrior*.

        Note: comment it out if not using it, it may affect other problems.

## Reproduce the benchmark results in the paper (Section 6)
We can use the ```benchmark.py``` script to run the benchmark. The script allows different options to enable different solving mode. Running ```python benchmark.py``` should display:
```
Usage: benchmark.py
	-sat			Enumerate all solutions by using SAT
	-maxsat			Find one optimal solution
	-maxsat_all		Find all optimal solutions
	-maxsat_part		Find one optimal solution by using Max-SAT with spec partitioning
	-maxsat_part_auto	Find one optimal solution by using auto partitioning
	-t=<timeout> in seconds
	-r=<repeat>		Number of times to repeat the run
	-m=<model path>
	-from_file		Generate the CNF/WCNF file and then call the solver
```
Under the folder for each benchmark problem, you can use the ```benchmark.py``` and ```benchmark_alloy_star.py``` script to reproduce the results from the paper. In additition, the ```results``` folder under each problem folder contains the benchmark results from our run.

*Note: Alloy generates tmp files to solve problems. You could clean your /tmp directory after running the benchmark.*

*Note: The following benchmark problems may take a long time to complete.*

### Course Scheduling (Section 6.2.1)
```
cd <path>/<to>/<package>/scripts/course

# run AlloyMax benchmark:
python benchmark.py -maxsat -maxsat_part -maxsat_part_auto -t=1800 -r=1 -m=models_20210203/
```
The output should be like:
```
problem,maxsat_trans,maxsat_solve,maxsat_result,part_trans,part_solve,part_result,auto_part_trans,auto_part_solve,auto_part_result
30_40_3_6,9401,1039,SAT,9644,312,SAT,8765,236,SAT
...
```
where ```problem``` refers to the problem name, ```maxsat_trans``` refers to the translation time of the problem, ```maxsat_solve``` refers to the actual solving time of the MaxSAT solver, ```part_trans``` and ```part_solve``` refer to the translation time and solving time with spec-based partitioning, and ```auto_part_trans``` and ```auto_part_solve``` refer to the translation time and solving time with auto-partitioning. All times are in ms.

```
# run Alloy* benchmark:
python benchmark_alloy_star.py -t=1800 -r=1 -m=alloy_star_models/
```
Similarly, the output should be like:
```
problem,trans,solve,result
30_40_3_6,12106,116018,SAT
...
```
where ```trans``` refers to the translation time and ```solve``` refers to the actual solving time of the backend solver.

*Note: The time unit in the output is milliseconds.*

### CheckMate (Section 6.2.2)
```
cd <path>/<to>/<package>/scripts/checkmate

# run AlloyMax benchmark:
python benchmark.py -maxsat -maxsat_part -maxsat_part_auto -t=1800 -r=1
```

### Degradation (Section 6.2.3)
```
cd <path>/<to>/<package>/scripts/degradation

# run AlloyMax benchmark:
python benchmark.py -maxsat -maxsat_part -maxsat_part_auto -t=1800 -r=1 -from_file
```
It's better to run this benchmark by using the ```from_file``` mode; otherwise, it may have memory problems.

### Wedding Seating (Section 6.2.4)
```
cd <path>/<to>/<package>/scripts/seating

# run AlloyMax benchmark:
python benchmark.py -maxsat -maxsat_part -maxsat_part_auto -t=1800 -r=1 -m=models_20210203/

# run Alloy* benchmark:
python benchmark_alloy_star.py -t=1800 -r=1 -m=alloy_star_models/
```

### Single Machine Scheduling (Section 6.2.5)
```
cd <path>/<to>/<package>/scripts/SMS

# run AlloyMax benchmark:
python benchmark.py -maxsat -maxsat_part -maxsat_part_auto -t=1800 -r=1 -m=models_20210203/
```

#### Run Alloy* Problems for SMS in GUI
*Known issue:* Although we set the same Alloy* solving options, the SMS problems can be solved from the GUI mode but in CLI mode they will have StackOverflow errors. Thus, we manually run the problems in GUI mode to collect the results. See the following commands:

1. Start Alloy* in GUI mode
```
java -jar bin/hola-0.3_2019-03-23.jar
```
2. (Optinal) Change the solver to Glucose or Minisat (which only works on Linux/Mac).

    Select *Options -> Solver -> Glucose/MiniSat*.
3. Open ```<path>/<to>/<package>/scripts/SMS/alloy_star_models/<problem>.als```
4. Click *Execute* to solve the problem.

### Running all benchmarks
You can also use the ```run.sh``` script to run all the benchmark problems at once.
```
cd <path>/<to>/<package>/scripts
sh run.sh
```
The result of each benchmark run can be found in one_result_max.csv, one_result_maxsat_part.csv (for spec-based partitioning), and one_result_maxsat_part_auto.cvs (for auto-partitioning) in the respective directories.

## AlloyMax Source Code
You can also access the source code of AlloyMax from: https://github.com/SteveZhangBit/org.alloytools.alloy/tree/maxsat

This repository is a fork from the original Alloy project. Our modification is on branch ```maxsat```. You can follow the build instruction for Alloy to build AlloyMax.
