package imageProcessing.colorChanger;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

/**
 * @author Baha2r
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

    public ChangeColor(String imageFile, String outPath) throws IOException {
        File file = new File(imageFile);
        this.image = ImageIO.read(file);
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
        boolean isBlue = color.equals("blue") || color.equals("BLUE") || color.equals("Blue");
        if (isBlue) {
            blueChanger();
            System.out.println("color changed to blue!");
        }
        boolean isGreen = color.equals("green") || color.equals("GREEN") || color.equals("Green");
        if (isGreen) {
            greenChanger();
            System.out.println("color changed to green!");
        }
        boolean isSepia = color.equals("sepia") || color.equals("SEPIA") || color.equals("Sepia");
        if (isSepia) {
            sepiaChanger();
            System.out.println("color changed to sepia!");
        }
        if (!(isGray) && !(isNegative) && !(isRed) && !(isBlue) && !(isGreen) && !(isSepia)) {
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

    private void blueChanger() throws IOException {
        int width = getWidth();
        int height = getHeight();
        for (int i = 0; i < width; i++) {
            for (int j = 0; j < height; j++) {
                int pos = image.getRGB(i, j);
                int alpha = (pos >> 24) & 0xff;
                int red = 0;
                int green = 0;
                int blue = pos & 0xff;
                pos = (alpha << 24 | red << 16 | green << 8 | blue);
                image.setRGB(i, j, pos);
            }
        }
        generateImage(image);
    }

    private void greenChanger() throws IOException {
        int width = getWidth();
        int height = getHeight();
        for (int i = 0; i < width; i++) {
            for (int j = 0; j < height; j++) {
                int pos = image.getRGB(i, j);
                int alpha = (pos >> 24) & 0xff;
                int red = 0;
                int green = (pos >> 8) & 0xff;
                int blue = 0;
                pos = (alpha << 24 | red << 16 | green << 8 | blue);
                image.setRGB(i, j, pos);
            }
        }
        generateImage(image);
    }

    private void sepiaChanger() throws IOException {
        int width = getWidth();
        int height = getHeight();
        final double const_1 = 0.393;
        final double const_2 = 0.769;
        final double const_3 = 0.189;
        final double const_4 = 0.349;
        final double const_5 = 0.686;
        final double const_6 = 0.168;
        final double const_7 = 0.272;
        final double const_8 = 0.534;
        final double const_9 = 0.131;
        for (int i = 0; i < width; i++) {
            for (int j = 0; j < height; j++) {
                int pos = image.getRGB(i, j);
                int alpha = (pos >> 24) & 0xff;
                int red = (pos >> 16) & 0xff;
                int green = (pos >> 8) & 0xff;
                int blue = pos & 0xff;
                int newRed = (int) ((red * const_1) + (green * const_2) + (blue * const_3));
                int newGreen = (int) ((red * const_4) + (green * const_5) + (blue * const_6));
                int newBlue = (int) ((red * const_7) + (green * const_8) + (blue * const_9));
                if (newRed > 255)
                    red = 255;
                else red = newRed;
                if (newGreen > 255)
                    green = 255;
                else green = newGreen;
                if (newBlue > 255)
                    blue = 255;
                else blue = newBlue;
                pos = (alpha << 24 | red << 16 | green << 8 | blue);
                image.setRGB(i, j, pos);
            }
        }
        generateImage(image);
    }
}