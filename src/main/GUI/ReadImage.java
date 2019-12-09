package GUI;

import logic.ReadJson;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.net.URL;

public class ReadImage {

    private static BufferedImage readImage(String nameOfFile){

        try {
            URL url = ReadImage.class.getResource(nameOfFile);
            File file = new File(url.getPath());
            BufferedImage image = ImageIO.read(file);
            return image;
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    public static BufferedImage getGrassBufferedImage(){
        return readImage("bush.png");
    }
    public static BufferedImage getAnimalBufferedImage(){
        return readImage("animal.png");
    }
}
