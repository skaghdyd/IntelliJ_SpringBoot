package com.study.alg;

public class Test {
    public static void main(String[] args) {
        System.out.println("===================================");

        int a = 10;
        int b = 20;
        System.out.printf("A=%d B=%d\n", a, b);

        int temp = a;
        a = b;
        b = temp;
        System.out.printf("A=%d B=%d\n", a, b);
    }
}
