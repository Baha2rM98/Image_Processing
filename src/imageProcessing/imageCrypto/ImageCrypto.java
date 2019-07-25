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
import java.util.Arrays;

public class ImageCrypto extends AES {
    private static byte[] imageInBytes;
    private static byte[] enc;
    private static byte[] dec;
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
        originalImageToByteArray();
        enc = encryption(imageInBytes);
        encryptedByteArrayToImage();
        System.out.println("encryption ended...");
    }

    public void imageDecryption() throws IOException {
        dec = decryption(enc);
        decryptedByteArrayToImage();
    }

    private int getHeight() {
        return imageFile.getHeight();
    }

    private int getWidth() {
        return imageFile.getWidth();
    }

    private int getType() {
        return imageFile.getType();
    }

    private void originalImageToByteArray() throws IOException {
        ByteArrayOutputStream byteImage = new ByteArrayOutputStream();
        ImageIO.write(imageFile, "jpg", byteImage);
        byteImage.flush();
        imageInBytes = byteImage.toByteArray();
        byteImage.close();
    }

    private void encryptedByteArrayToImage() throws IOException {
        File output = new File(outputEncryptedPath);
        int w = getWidth();
        int h = getHeight();
        int type = getType();
        BufferedImage encryptedImageFile = new BufferedImage(w / 10, h / 10, type);
        encryptedImageFile.setData(Raster.createRaster(encryptedImageFile.getSampleModel(), new DataBufferByte(enc, enc.length), new Point(0, 0)));
        ImageIO.write(encryptedImageFile, "jpg", output);
    }

    private void decryptedByteArrayToImage() throws IOException {
        File output = new File(outputDecryptedPath);
        int w = getWidth();
        int h = getHeight();
        int type = getType();
        BufferedImage decryptedImageFile = new BufferedImage(w / 10, h / 10, type);
        decryptedImageFile.setData(Raster.createRaster(decryptedImageFile.getSampleModel(), new DataBufferByte(dec, dec.length), new Point(0, 0)));
        ImageIO.write(decryptedImageFile, "jpg", output);
    }

//    public void show() {
//        if (Arrays.equals(dec, imageInBytes))
//            System.out.println(true);
//        else System.out.println(false);
//    }
}
