package com.arraysorting;

public class StatTracking {
    public static long[] recordTimeAndMemory(Runnable sortingMethod) {
        Runtime runtime = Runtime.getRuntime();

        // Force garbage collection to have more consistent results
        runtime.gc();

        long startMemory = runtime.totalMemory() - runtime.freeMemory();
        long startTime = System.nanoTime();

        sortingMethod.run();

        long timeElapsed = System.nanoTime() - startTime;
        long endMemory = runtime.totalMemory() - runtime.freeMemory();
        long memoryUsed = endMemory - startMemory;

        return new long[]{timeElapsed, memoryUsed};
    }
}
