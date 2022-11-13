

public class Main {
    public static void main(String[] args) {

        ImageProcessing imageProcessing = new ImageProcessing("files/input.jpg", "files/output.jpg");
        try {
            for (int i = 0; i < imageProcessing.getHeight(); i++) {
                ProcessingThread pt = new ProcessingThread(i, imageProcessing.getWidth(), imageProcessing);
                pt.start();
                pt.join();
            }
        }catch (InterruptedException ex){
            System.out.println("Threads interrupted");
        }

        imageProcessing.writeFile();

    }
}