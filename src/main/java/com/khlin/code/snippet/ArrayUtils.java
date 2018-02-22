package com.khlin.code.snippet;

import java.util.Arrays;

public class ArrayUtils {

    /**
     * 反转数组，在原数组上操作
     *
     * @param array
     */
    public static void reverse(int[] array) {
        int size = array.length;
        for (int i = 0; i <= (size - 1) / 2; i++) {
            int temp = array[i];
            array[i] = array[size - 1 - i];
            array[size - 1 - i] = temp;
        }
    }

    /**
     * 反转数组，在原数组上不操作，返回一个副本
     * @param array
     * @return
     */
    public static int[] reverseCopy(int[] array) {
        int[] copy = new int[array.length];
        System.arraycopy(array, 0, copy, 0, array.length);
        reverse(copy);
        return copy;
    }
}
