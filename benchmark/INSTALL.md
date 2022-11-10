# Install Instruction
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
You can follow the instruction below to run the course scheduling problem in Section 2 of the paper.

1. Open AlloyMax in GUI
    ```
    java -jar bin/org.alloytools.alloy.dist.jar
    ```

2. Open ```scripts/course/course_schedule.als```
3. Generate any valid schedule:

    3.1 Select: *Options -> Solver -> SAT4J*.

    3.2 Select: *Execute -> Run AnySchedule*.

    3.3 Click on the **Instance** on the right panel to open the visualizer.

    3.4 In the visualizer, select *Theme -> Load Theme -> course/course.thm* for simpler visualization. Then, you should see a similar model as it in Figure 2 of the paper.
