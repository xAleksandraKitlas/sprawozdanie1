package com.company;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;

public class ParallelJulia extends Thread {
    final static int N = 4096;

    final static int CUTOFF = 100;
    static int[][] set = new int[N][N];
    public static void main(String[] args) throws Exception {

        long startTime = System.currentTimeMillis();

        ParallelJulia thread0 = new ParallelJulia(0);
        ParallelJulia thread1 = new ParallelJulia(1);
        ParallelJulia thread2 = new ParallelJulia(2);
        ParallelJulia thread3 = new ParallelJulia(3);
        thread0.start();
        thread1.start();
        thread2.start();
        thread3.start();

        thread0.join();
        thread1.join();
        thread2.join();
        thread3.join();

        long endTime = System.currentTimeMillis();
        System.out.println("Obliczenia zakończone w czasie " + (endTime - startTime) + " millisekund");

        BufferedImage img = new BufferedImage(N, N, BufferedImage.TYPE_INT_ARGB);
        for (int i = 0; i < N; i++) { for (int j = 0; j < N; j++) {
            int k = set[i][j];
            float level;
            if (k < CUTOFF) {
                level = (float) k / CUTOFF;
            }
            else {
                level = 0; }
            Color c = new Color(0, 0, level);
            img.setRGB(i, j, c.getRGB()); }
        }

        ImageIO.write(img, "PNG", new File("Julia.png")); }
    int me;

    public ParallelJulia(int me) {
        this.me = me;
    }

    public void run() {
        int begin = 0, end = 0;
        if (me == 0) {
            begin = 0;
            end = (N / 4) * 1;
        }
        else if (me == 1) {
            begin = (N / 4) * 1;
            end = (N / 4) * 2; }
        else if (me == 2) {
            begin = (N / 4) * 2;
            end = (N / 4) * 3;
        }
        else if (me == 3) {
            begin = (N / 4) * 3;
            end = N; }

        for (int i = begin; i < end; i++) {
            for (int j = 0; j < N; j++) {
                double cr = (4.0 * i - 2 * N) / N;
                double ci = (4.0 * j - 2 * N) / N;
                double zr = cr, zi = ci;
                int k = 0;
                while (k < CUTOFF && zr * zr + zi * zi < 4.0) {
                    double xtemp =  zi * zi -zr * zr;
                    zr = 2 * zr * zi + cr;
                    zi = xtemp + ci;
                    k++; }
                set[i][j] = k; }
        }
    }
}
