package imageProcessing.imageCrypto;

import AES.aes.AES;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.awt.image.DataBufferByte;
import java.awt.image.Raster;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;

public class ImageCrypto extends AES {
    private static byte[] imageInBytes;
    private static byte[] enc;
    private static byte[] dec;
    private final double DIVIDED_VALUE = 4.05;
    private String outputEncryptedPath;
    private String outputDecryptedPath;
    private BufferedImage imageFile;

    public ImageCrypto(String inputImageFile, String outputEncryptedPath, String outputDecryptedPath) throws IOException {
        super();
        this.outputEncryptedPath = outputEncryptedPath;
        this.outputDecryptedPath = outputDecryptedPath;
        File image = new File(inputImageFile);
        this.imageFile = ImageIO.read(image);
    }

    public void imageEncryption() throws IOException {
        System.out.println("encryption started...");
        originalImage2ByteArray();
        enc = encryption(imageInBytes);
        encryptedByteArray2Image();
        System.out.println("encryption ended...");
    }


    private int getHeight() {
        return (int) (imageFile.getHeight() / DIVIDED_VALUE);
    }

    private int getWidth() {
        return (int) (imageFile.getWidth() / DIVIDED_VALUE);
    }

    private int getType() {
        return imageFile.getType();
    }

    private void originalImage2ByteArray() throws IOException {
        ByteArrayOutputStream byteImage = new ByteArrayOutputStream();
        ImageIO.write(imageFile, "jpg", byteImage);
        byteImage.flush();
        imageInBytes = byteImage.toByteArray();
        byteImage.close();
    }

    private void encryptedByteArray2Image() throws IOException {
        File output = new File(outputEncryptedPath);
        int w = getWidth();
        int h = getHeight();
        int type = getType();
        BufferedImage encryptedImage = new BufferedImage(w, h, type);
        encryptedImage.setData(Raster.createRaster(encryptedImage.getSampleModel(), new DataBufferByte(enc, enc.length), new Point()));
        ImageIO.write(encryptedImage, "jpg", output);
    }
}
