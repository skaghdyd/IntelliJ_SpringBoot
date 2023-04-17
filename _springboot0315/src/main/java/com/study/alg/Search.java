package com.study.alg;

public class Search {
    public static void main(String[] args){
        int[] a = {10,3,1,4,2};
        int searchValue = 4;
        int findId = -1;
        for(int i=0; i<a.length; i++){
            if(a[i]==searchValue){
                findId = i;
                break;
            }
        }
        System.out.println("검색한 인덱스 : " + findId);
    }
}
