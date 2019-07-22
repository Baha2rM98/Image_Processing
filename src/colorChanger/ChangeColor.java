package colorChanger;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

/**
 * A class to change color of a picture
 **/

public class ChangeColor {
    private BufferedImage image;
    private String outPath;

    /**
     * constructor
     *
     * @param imageFile File that represent the picture
     * @param outPath   path of the produced picture
     **/

    public ChangeColor(File imageFile, String outPath) throws IOException {
        this.image = ImageIO.read(imageFile);
        this.outPath = outPath;
    }

    /**
     * setColor method
     *
     * @param color color of the new picture
     * @throws IOException throw exception if something wrong happens
     **/

    public void setColor(String color) throws IOException {
        boolean isGray = color.equals("gray") || color.equals("GRAY") || color.equals("Gray");
        if (isGray) {
            grayChanger();
            System.out.println("color changed to gray!");
        }
        boolean isNegative = color.equals("negative") || color.equals("NEGATIVE") || color.equals("Negative");
        if (isNegative) {
            negativeChanger();
            System.out.println("color changed to negative!");
        }
        boolean isRed = color.equals("red") || color.equals("RED") || color.equals("Red");
        if (isRed) {
            redChanger();
            System.out.println("color changed to red!");
        }
        if (!(isGray) && !(isNegative) && !(isRed)) {
            System.err.println("This color is not defined!");
        }
    }

    private int getWidth() {
        return image.getWidth();
    }

    private int getHeight() {
        return image.getHeight();
    }

    private void generateImage(BufferedImage outImageFile) throws IOException {
        File outFile = new File(outPath);
        ImageIO.write(outImageFile, "jpg", outFile);
    }

    private void grayChanger() throws IOException {
        int width = getWidth();
        int height = getHeight();
        for (int i = 0; i < width; i++) {
            for (int j = 0; j < height; j++) {
                int pos = image.getRGB(i, j);
                int alpha = (pos >> 24) & 0xff;
                int red = (pos >> 16) & 0xff;
                int green = (pos >> 8) & 0xff;
                int blue = pos & 0xff;
                int average = (red + blue + green) / 3;
                pos = (alpha << 24 | average << 16 | average << 8 | average);
                image.setRGB(i, j, pos);
            }
        }
        generateImage(image);
    }

    private void negativeChanger() throws IOException {
        int width = getWidth();
        int height = getHeight();
        for (int i = 0; i < width; i++) {
            for (int j = 0; j < height; j++) {
                int pos = image.getRGB(i, j);
                int alpha = (pos >> 24) & 0xff;
                int red = (pos >> 16) & 0xff;
                int green = (pos >> 8) & 0xff;
                int blue = pos & 0xff;
                red = 255 - red;
                green = 255 - green;
                blue = 255 - blue;
                pos = (alpha << 24) | (red << 16) | (green << 8) | blue;
                image.setRGB(i, j, pos);
            }
        }
        generateImage(image);
    }

    private void redChanger() throws IOException {
        int width = getWidth();
        int height = getHeight();
        for (int i = 0; i < width; i++) {
            for (int j = 0; j < height; j++) {
                int pos = image.getRGB(i, j);
                int alpha = (pos >> 24) & 0xff;
                int red = (pos >> 16) & 0xff;
                int green = 0;
                int blue = 0;
                pos = (alpha << 24) | (red << 16) | (green << 8) | blue;
                image.setRGB(i, j, pos);
            }
        }
        generateImage(image);
    }
}