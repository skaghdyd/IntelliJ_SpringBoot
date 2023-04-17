package com.study.alg;

import java.sql.SQLOutput;

public class Test {
    public static void main(String[] args) {
        int[] a = {1, 2, 4, 5, 10};
        int searchVal = 4;
        int findId = -1;
        int left = 0;
        int right = a.length - 1;
        while (left <= right) {
            int middle = (int) ((left + right) / 2);
            if (a[middle] == searchVal) {
                findId = middle;
                break;
            } else if (a[middle] < searchVal) {
                left = middle + 1;
            } else {
                right = middle - 1;
            }
        }
        System.out.printf("%s %d\n", "findId =", findId);

    }
}
