package com.arraysorting;

public class Main {
    public static void main(String[] args) {
        int[] sizes = {100, 500, 1000, 2500, 5000, 7500, 10000, 20000};
        int[] cutoffValues = {0, 10, 50, 200};

        long[][] times = new long[sizes.length][5 + cutoffValues.length];
        long[][] memoryUsed = new long[sizes.length][5 + cutoffValues.length];

        System.out.printf("%-30s%-20s%s%n", "Algorithm", "Time (ns)", "Memory Used (bytes)");

        for (int sizeIndex = 0; sizeIndex < sizes.length; sizeIndex++) {
            int size = sizes[sizeIndex];
            int[] originalArray = com.arraysorting.GenerateArray.randArray(size, 10000);
            System.out.println("\nArray Size: " + size);

            // Insertion Sort
            long[] result = com.arraysorting.StatTracking.recordTimeAndMemory(() -> {
                int[] arrayCopy = originalArray.clone();
                com.arraysorting.Sorting.insertionSort(arrayCopy);
            });
            times[sizeIndex][0] = result[0];
            memoryUsed[sizeIndex][0] = result[1];
            System.out.printf("%-30s%-20d%d%n", "Insertion Sort", result[0], result[1]);


            // Heap Sort
            result = com.arraysorting.StatTracking.recordTimeAndMemory(() -> {
                int[] arrayCopy = originalArray.clone();
                com.arraysorting.Sorting.heapSort(arrayCopy);
            });
            times[sizeIndex][1] = result[0];
            memoryUsed[sizeIndex][1] = result[1];
            System.out.printf("%-30s%-20d%d%n", "Heap Sort", result[0], result[1]);

            // Merge Sort
            result = com.arraysorting.StatTracking.recordTimeAndMemory(() -> {
                int[] arrayCopy = originalArray.clone();
                com.arraysorting.Sorting.mergeSort(arrayCopy, 0, arrayCopy.length - 1);
            });
            times[sizeIndex][2] = result[0];
            memoryUsed[sizeIndex][2] = result[1];
            System.out.printf("%-30s%-20d%d%n", "Merge Sort", result[0], result[1]);

            // Quick Sort with cutoffs
            for (int cutoffIndex = 0; cutoffIndex < cutoffValues.length; cutoffIndex++) {
                int cutoff = cutoffValues[cutoffIndex];
                result = com.arraysorting.StatTracking.recordTimeAndMemory(() -> {
                    int[] arrayCopy = originalArray.clone();
                    com.arraysorting.Sorting.quickSort(arrayCopy, 0, arrayCopy.length - 1, cutoff);
                });
                times[sizeIndex][3 + cutoffIndex] = result[0];
                memoryUsed[sizeIndex][3 + cutoffIndex] = result[1];
                System.out.printf("%-30s%-20d%d%n", "Quick Sort (Cutoff " + cutoff + ")", result[0], result[1]);
            }
        }

        System.out.println();

        // Generate graphs
        com.arraysorting.Graphing.generateGraph(sizes, times, "Sorting Algorithm Times", "Time (ns)", "sorting_times.png");
        com.arraysorting.Graphing.generateGraph(sizes, memoryUsed, "Sorting Algorithm Memory Usage", "Memory (bytes)", "sorting_memory.png");
    }
}
