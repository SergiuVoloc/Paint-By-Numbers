
import org.opencv.core.*;
import org.opencv.imgproc.Imgproc;

import java.io.File;
import java.io.IOException;
import java.awt.image.BufferedImage;
import javax.imageio.ImageIO;

public class Main {
    public static void main(String[] args) {

        // For storing image in RAM
        BufferedImage image = null;

        File inputFile = null;


        // READ IMAGE
        try
        {
            inputFile  = new File("src/images/img.jpg"); //image file path

            // Reading input file
            image = ImageIO.read(inputFile);

            System.out.println("Reading complete.");
        }
        catch(IOException e)
        {
            System.out.println("Error: "+e);
        }

        //get image width and height
        int width = image.getWidth();
        int height = image.getHeight();


         /* get pixel value (the arguments in the getRGB method
         denotes the  cordinates of the image from which the
         pixel values need to be extracted) */
         int p = image.getRGB(0,0);


          /* We, have seen that the components of pixel occupy
           8 bits. To get the bits we have to first right shift
           the 32 bits of the pixels by bit position(such as 24
           in case of alpha) and then bitwise ADD it with 0xFF.
           0xFF is the hexadecimal representation of the decimal
           value 255.  */

        // get alpha
        int a = (p>>24) & 0xff;

        // get red
        int r = (p>>16) & 0xff;

        // get green
        int g = (p>>8) & 0xff;

        // get blue
        int b = p & 0xff;



        // for simplicity we will set the ARGB value to 255, 100, 150 and 200 respectively.
        a = 255;
        r = 255;
        g = 200;
        b = 255;


        //set the pixel value
        p = (a<<24) | (r<<16) | (g<<8) | b;
        image.setRGB(0, 0, p);


        // WRITE IMAGE
        try
        {
            // Output file path
            File outputFile = new File("src/images/out.jpg");

            // Writing to file taking type and path as
            ImageIO.write(image, "jpg", outputFile);

            System.out.println("Writing complete.");
        }
        catch(IOException e)
        {
            System.out.println("Error: "+e);
        }


    }
}
