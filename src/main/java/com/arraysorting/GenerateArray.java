package com.arraysorting;

public class GenerateArray {
    public static int[] randArray(int arraySize, int maxValue) {
        int[] randArr = new int[arraySize];
        for (int i = 0; i < arraySize; i++) {
            randArr[i] = (int) (Math.random() * maxValue);
        }
        return randArr;
    }
}
