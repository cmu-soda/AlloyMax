# Requirements

## Hardware
AlloyMax does not require any special hardware components to run. However, some problems in our benchmark may require a lot of memory to solve. Therefore, we suggest using a machine with at least **16GB** memory.

## Operating System
AlloyMax requires Java version >= 1.8. Although AlloyMax can run on both Windows and Linux machines, some of the back-end Sat/MaxSAT solvers can only run on Linux. Specifically, we use OpenWBO as the MaxSAT solver in our paper which can only run on Linux. **Therefore, we suggest using a Linux machine to reproduce our results.**

## Environment
AlloyMax requires Java version >= 1.8 to run. In addition, our benchmark scripts require Python 3.8 and numpy to run. The program has been tested under,
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

## Docker
In addition, we provide a Docker image to try out AlloyMax. See INSTALL.md