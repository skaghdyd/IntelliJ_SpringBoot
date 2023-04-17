package com.study.alg;

public class QuickSort {
    public static void main(String[] args) {
        int[] nums = {10, 3, 1, 9, 7, 8, 6, 2, 4, 5};
        quickSort(nums, 0, nums.length - 1);
        System.out.println("Quick sort = ");
        for (int i : nums) {
            System.out.printf("%d, ", i);
        }
        System.out.println();
    }

    public static void quickSort(int[] arr, int left, int right) {
        if (left < right) {
            int pivotIndex = partition(arr, left, right);
            quickSort(arr, left, pivotIndex - 1);
            quickSort(arr, pivotIndex, right);
        }
    }

    public static int partition(int[] arr, int left, int right) {
        System.out.print("시작합니다 = ");
        for (int i : arr) {
            System.out.printf("%d, ", i);
        }
        System.out.println();
        System.out.print("left = " + left + ", right = " + right);
        System.out.println();
        int pivot = arr[(left + right) / 2];
        while (left <= right) {
            while (arr[left] < pivot) left++;
            while (arr[right] > pivot) right--;
            if (left <= right) {
                int temp = arr[left];
                arr[left] = arr[right];
                arr[right] = temp;
                left++;
                right--;
            }
            System.out.print("Quick sort = ");
            for (int i : arr) {
                System.out.printf("%d, ", i);
            }
            System.out.println();
            System.out.print("left = " + left + ", right = " + right);
            System.out.println();
        }
        return left;
    }
}
