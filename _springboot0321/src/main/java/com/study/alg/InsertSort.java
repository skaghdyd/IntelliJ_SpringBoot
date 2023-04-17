package com.study.alg;

public class InsertSort {
    public static void main(String[] args) {
        int[] a = {10, 3, 1, 4, 2};
        for (int i = 1; i < a.length; i++) {
            int tmp = a[i];
            int ins = 0;
            for (int j = i - 1; j >= 0; j--) {
                if (a[j] > tmp) {
                    a[j + 1] = a[j];
                } else {
                    ins=j+1;
                    break;
                }
                for (int x : a) {
                    System.out.print(x+" , ");
                }
                System.out.println();
            }
            a[ins] = tmp;
        }
        for (int x : a) {
            System.out.print(x+" , ");
        }
    }
}
