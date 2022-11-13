import java.awt.*;

public class ProcessingThread extends Thread {

    int y;
    int width;
    ImageProcessing imageProcessing;

    public ProcessingThread (int y, int width, ImageProcessing imageProcessing) {
        this.y = y;
        this.width = width;
        this.imageProcessing = imageProcessing;
    }

    public void run(){
        System.out.println("start thread");
        for (int i = 0; i < width; i++) {

            int currentRGB = imageProcessing.getPixel(i, y);
            int a = 0xFF & (currentRGB >> 24);
            int r = 0xFF & (currentRGB >> 16);
            int g = 0xFF & (currentRGB >> 8);
            int b = 0xFF & currentRGB;
            r = 255 - r;
            g = 255 - g;
            b = 255 - b;
            int newRgb = (a << 24) + (r << 16) + (g << 8) + b;
            imageProcessing.setPixel(i, y, newRgb);
        }
        System.out.println("stop thread");
    }

}
