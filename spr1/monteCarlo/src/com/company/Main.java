package com.company;

public class Main {

    public static void main(String[] args) {
        CircleMonteCarlo area = new CircleMonteCarlo(100000, 2);
        long startTime = System.currentTimeMillis();
        double value = area.getArea();
        long stopTime = System.currentTimeMillis();
        double realArea = Math.PI * Math.pow(2, 2);
        System.out.println("Approx value:" + value);
        System.out.println("Difference to manually computed value of area : " + (Math.abs(value - realArea)));
        System.out.println("Error: " + (Math.abs(value - realArea)) / realArea * 100 + " %");
        System.out.println("Time Duration: " + (stopTime - startTime) + "ms");
    }
}
