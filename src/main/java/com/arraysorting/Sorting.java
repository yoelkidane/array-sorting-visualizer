package com.arraysorting;

public class Sorting {
    public static void insertionSort(int[] array) {
        for (int current = 1; current < array.length; current++) {
            int key = array[current];
            int compare = current - 1;
            while (compare >= 0 && array[compare] > key) {
                array[compare + 1] = array[compare];
                compare--;
            }
            array[compare + 1] = key;
        }
    }

    // Heap Sort
    public static void heapSort(int[] array) {
        int arrLength = array.length;

        // Build a max heap (so elements are ordered properly)
        for (int parentIndex = arrLength / 2 - 1; parentIndex >= 0; parentIndex--) {
            heapify(array, arrLength, parentIndex);
        }

        // Remove from the max heap
        for (int lastIndex = arrLength - 1; lastIndex >= 0; lastIndex--) {
            int tempValue = array[0];
            array[0] = array[lastIndex];
            array[lastIndex] = tempValue;

            // Heapify to maintain heap property
            heapify(array, lastIndex, 0);
        }
    }

    // Checks if subtree (all children) are in heap order
    private static void heapify(int[] array, int heapSize, int parentIndex) {
        int largestIndex = parentIndex; // largest value =  root
        int leftChildIndex = 2 * parentIndex + 1;
        int rightChildIndex = 2 * parentIndex + 2;

        // Left child exists and is greater than current largest
        if (leftChildIndex < heapSize && array[leftChildIndex] > array[largestIndex]) {
            largestIndex = leftChildIndex;
        }

        // Right child exists and is greater than current largest
        if (rightChildIndex < heapSize && array[rightChildIndex] > array[largestIndex]) {
            largestIndex = rightChildIndex;
        }

        // When largest is not at the root, fix heap order
        if (largestIndex != parentIndex) {
            int temp = array[parentIndex];
            array[parentIndex] = array[largestIndex];
            array[largestIndex] = temp;
            heapify(array, heapSize, largestIndex); // recursive allows to fix whole array
        }
    }

    // Merge Sort
    public static void mergeSort(int[] array, int leftIndex, int rightIndex) {
        if (leftIndex < rightIndex) {
            int middleIndex = leftIndex + (rightIndex - leftIndex) / 2;

            //Sort left half
            mergeSort(array, leftIndex, middleIndex);
            //Sort right half
            mergeSort(array, middleIndex + 1, rightIndex);

            // Merge the two halves
            merge(array, leftIndex, middleIndex, rightIndex);
        }
    }

    // Merges two sorted subarrays into one sorted array
    private static void merge(int[] array, int leftIndex, int middleIndex, int rightIndex) {
        int leftSubArrayLength = middleIndex - leftIndex + 1; // size of left subarray
        int rightSubArrayLength = rightIndex - middleIndex; // size of right subarray

        // Create temporary arrays for left and right subarrays
        int[] leftSubArray = new int[leftSubArrayLength];
        int[] rightSubArray = new int[rightSubArrayLength];

        // Copy data to left and right subarrays
        for (int i = 0; i < leftSubArrayLength; i++) {
            leftSubArray[i] = array[leftIndex + i];
        }
        for (int i = 0; i < rightSubArrayLength; i++) {
            rightSubArray[i] = array[middleIndex + 1 + i];
        }

        int leftPointer = 0, rightPointer = 0, mergedPointer = leftIndex; // pointers for subarrays

        // Merge the temporary arrays back into the main array
        while (leftPointer < leftSubArrayLength && rightPointer < rightSubArrayLength) {
            if (leftSubArray[leftPointer] <= rightSubArray[rightPointer]) {
                array[mergedPointer] = leftSubArray[leftPointer];
                leftPointer++;
            } else {
                array[mergedPointer] = rightSubArray[rightPointer];
                rightPointer++;
            }
            mergedPointer++;
        }

        // Copy any remaining elements of the left subarray
        while (leftPointer < leftSubArrayLength) {
            array[mergedPointer] = leftSubArray[leftPointer];
            leftPointer++;
            mergedPointer++;
        }

        // Copy any remaining elements of the right subarray
        while (rightPointer < rightSubArrayLength) {
            array[mergedPointer] = rightSubArray[rightPointer];
            rightPointer++;
            mergedPointer++;
        }
    }

    // Quick Sort with cutoff for Insertion Sort
    public static void quickSort(int[] array, int lowIndex, int highIndex, int cutoffValue) {
        // Use insert sort when the size is small enough
        if (lowIndex < highIndex) {
            if (highIndex - lowIndex <= cutoffValue) {
                insertionSortSegment(array, lowIndex, highIndex);
            }
            else {
                int pivotIndex = partition(array, lowIndex, highIndex);

                // Recursively quick sort left and right partitions
                quickSort(array, lowIndex, pivotIndex - 1, cutoffValue);
                quickSort(array, pivotIndex + 1, highIndex, cutoffValue);
            }
        }
    }

    // Sorts the smaller segments of array using insert sort logic
    private static void insertionSortSegment(int[] array, int lowIndex, int highIndex) {
        for (int i = lowIndex + 1; i <= highIndex; i++) {
            int keyValue = array[i];
            int comparisonIndex = i - 1;
            while (comparisonIndex >= lowIndex && array[comparisonIndex] > keyValue) {
                array[comparisonIndex + 1] = array[comparisonIndex];
                comparisonIndex--;
            }
            array[comparisonIndex + 1] = keyValue;
        }
    }

    private static int partition(int[] array, int lowIndex, int highIndex) {
        // Choose the last element as the pivot
        int pivotValue = array[highIndex];
        int i = (lowIndex - 1);

        // Rearrange where i is lower than pivot, and j is higher
        for (int j = lowIndex; j < highIndex; j++) {
            if (array[j] <= pivotValue) {
                i++;
                // swap elements at i and j
                int temp = array[i];
                array[i] = array[j];
                array[j] = temp;
            }
        }
        // Place pivot into finalized position and return its index
        int temp = array[i + 1];
        array[i + 1] = array[highIndex];
        array[highIndex] = temp;

        return i + 1;
    }
}
