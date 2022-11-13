package com.company;

import java.util.Random;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.atomic.AtomicInteger;

public class CircleMonteCarlo {
    AtomicInteger nAtomSuccess;
    int nThrows;
    double value;
    double r;

    public  CircleMonteCarlo(int i, int r){
        this.nAtomSuccess = new AtomicInteger(0);
        this.nThrows = i;
        this.value = 0;
        this.r = r;
    }

    class MonteCarlo implements Runnable {
        double r;
        MonteCarlo(double r){
            this.r = r;
        }
        @Override
        public void run() {
            Random rand = new Random();
            double x = (-r) + (r - (-r)) * rand.nextDouble();
            double y = (-r) + (r - (-r)) * rand.nextDouble();
            if(Math.pow(x,2) + Math.pow(y,2) <= Math.pow(r, 2)){
                nAtomSuccess.incrementAndGet();
            }
        }
    }

    public double getArea() {
        int nProcessors = Runtime.getRuntime().availableProcessors();
        ExecutorService executor = Executors.newWorkStealingPool(nProcessors);
        for (int i = 1; i <= nThrows; i++) {
            Runnable worker = new MonteCarlo(r);
            executor.execute(worker);
        }
        executor.shutdown();
        while (!executor.isTerminated()) {
        }
        value = Math.pow(2*r,2) * nAtomSuccess.get()/nThrows;
        return value;
    }

}
