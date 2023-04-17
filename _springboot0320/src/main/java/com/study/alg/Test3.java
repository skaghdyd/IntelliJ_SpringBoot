package com.study.alg;

public class Test3 {
    public static void main(String[] args) {
        int[] a = {10, 3, 1, 4, 2};
        for (int i = 0; i < a.length - 1; i++) {
            int min = a[i]; //임시 최소값
            int k = i; //임시 최소값의 인덱스

            //최소값 찾기
            for (int j = i + 1; j < a.length; j++) {
                if (min > a[j]) {
                    min = a[j]; //최소값 변경
                    k = j; //최소값 인덱스 변경
                }
            }

            //교환 알고리즘(swap) : 최소값 앞으로 이동
            int tmp = a[i];
            a[i] = a[k];
            a[k] = tmp;
        }

        for (int i : a) {
            System.out.println(i);
        }
    }
}
