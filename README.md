# Array Sorting Visualizer
A Java program visualizing sorting algorithm performance with dynamic graph generation using JFreeChart.


## Overview

The Array Sorting Visualizer is a Java application designed to visualize the performance of various sorting algorithms. It dynamically generates graphs to show the execution time and memory usage of different sorting methods. This project provides an educational tool for understanding how sorting algorithms behave with different array sizes.



## Overview

Random Array Generation
- Create arrays with random values for testing.

Multiple Sorting Algorithms:
- Insertion Sort, Heap Sort, Merge Sort, Quick Sort (various cutoffs)

Performance Metrics:
- Execution time (in nanoseconds), Memory usage (in bytes)

Dynamic Graph Generation:
- Execution time graphs, Memory usage graphs


## Installation
### Prerequisites
- Git
- Java 8 or higher
- Apache Maven

### Steps

1. **Clone the repository**
```
git clone https://github.com/yoelkidane/array-sorting-visualizer.git
cd array-sorting-visualizer
```

2. **Compile the project**: Use Maven to compile the project. This will properly download the necessary dependencies for compilation.
```bash
mvn compile
```

  
3. **Run the Application**: Execute the main calss using Maven
```bash
mvn exec:java -Dexec.mainClass="com.arraysorting.Main"
 ```


## Usage
**Console Output:** The console will display the execution time and memory usage of each sorting algorithm for the various array sizes.

**Generated Graphs:** The application will create and store PNG's of graphs related the output in the project's root directly. Look for files `sorting_times.png` and `sorting_memory.png`.
