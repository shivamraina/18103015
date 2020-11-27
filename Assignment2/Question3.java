package com.shivam;

import java.util.Scanner;

public class Main {

    static int compare(String a, String b) {
        for(int i=0;i<a.length() && i<b.length(); i++) {
            if(a.charAt(i) != b.charAt(i)) {
                return a.charAt(i) - b.charAt(i);
            }
        }
        return a.length() - b.length();
    }

    static void sort(String[] strings) {
        // type of bubble sort
        for(int j=1;j< strings.length;j++) {
            for (int i = 0; i < strings.length - j; i++) {
                if (compare(strings[i], strings[i + 1]) > 0) {
                    String temp = strings[i];
                    strings[i] = strings[i + 1];
                    strings[i + 1] = temp;
                }
            }
        }
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.println("Enter Size of String Array");
        int n = Integer.parseInt(sc.nextLine());
        String[] strings = new String[n];
        System.out.println("Input Array elements/ Enter separated only");
        for(int i=0;i<n;i++) {
            strings[i] = sc.nextLine();
        }
        sort(strings);
        for(int i=0;i<n;i++) {
            System.out.println(strings[i]);
        }
    }
}