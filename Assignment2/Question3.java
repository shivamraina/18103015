package com.shivam;

import java.util.Scanner;

public class Question3 {

    public static void sort(String[] arr) {
        int n = arr.length;
        for(int i=0;i<n-1;i++) {
            for(int j=i+1;j<n;j++) {
                if(arr[i].compareTo(arr[j]) > 0) {
                    String temp = arr[i];
                    arr[i] = arr[j];
                    arr[j] = temp;
                }
            }
        }
    }

    public static void main (String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.println("Enter Size of Array");
        int n = Integer.parseInt(sc.nextLine());
        String[] arr = new String[n];
        System.out.println("Input Array elements/ Enter separated only");
        for (int i=0;i<n;i++) {
            arr[i] = sc.nextLine();
        }
        sort(arr);
        for (int i=0;i<n;i++) {
            System.out.println(arr[i]);
        }
    }
}