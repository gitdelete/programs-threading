package com.aman.dynamicprogramming;

import java.util.Arrays;

public class SumMaxContinuousWindow_Kadane {

    private static int[] ar = {2,-5,3,-1,-1,-3,5,10,3,-32,4,3,12,-10,-1,3,4,-3,12};

    public static void main(String[] args) {
        int maxSum=0;
        int sum=0;
        int startIndex=0; int endIndex=0;
        for(int a= 0 ; a < ar.length; a++){
            sum=sum+ar[a];
            if(sum>maxSum){
                endIndex=a;
                maxSum=sum;
            }
            if(sum<0){
                sum=0;
            }
        }
        System.out.println(maxSum);
        System.out.println(endIndex);
    }


}
