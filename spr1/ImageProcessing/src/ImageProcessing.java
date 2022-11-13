import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class ImageProcessing {

    BufferedImage inputImage;
    BufferedImage outputImage;

    String outputPath;

    public ImageProcessing (String inputPath, String outputPath) {
        try {
            File inputFile = new File(inputPath);
            inputImage = ImageIO.read(inputFile);
            this.outputPath = outputPath;
            outputImage = new BufferedImage(inputImage.getWidth(), inputImage.getHeight(), BufferedImage.TYPE_INT_ARGB);

        } catch (IOException ex) {
            System.out.println("Couldn't read image");
        }
    }

    public synchronized int getPixel(int x, int y) {
        return inputImage.getRGB(x, y);
    }

    public int getHeight(){
        return inputImage.getHeight();
    }

    public int getWidth(){
        return inputImage.getWidth();
    }

    public synchronized void setPixel(int x, int y, int rgb){
        outputImage.setRGB(x,y,rgb);
    }

    public void writeFile(){
        try{
            File outputFile = new File(outputPath);
            if(ImageIO.write(outputImage, "png", outputFile)){
                System.out.println("finished writting");
            }
            else {
                System.out.println("Nothing happened");
            }

        }
        catch (IOException ex){
            System.out.println("Problem writing file");
        }

    }

}
