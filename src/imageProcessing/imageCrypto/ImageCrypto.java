package imageProcessing.imageCrypto;

import AES.aes.AES;

import javax.imageio.ImageIO;
import java.awt.image.*;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;

/**
 * @author Baha2r
 **/

public class ImageCrypto extends AES {
    private static byte[] imageInBytes;
    private static byte[] enc;
    private static byte[] dec;

    //constructor parameters
    private String outputEncryptedPath;
    private String outputDecryptedPath;
    //constructor parameters

    private BufferedImage imageFile;

    /**
     * constructor
     *
     * @param inputImageFile      path of original image file
     * @param outputEncryptedPath path of encrypted image file
     * @param outputDecryptedPath path of decrypted image file
     **/
    public ImageCrypto(String inputImageFile, String outputEncryptedPath, String outputDecryptedPath) throws IOException {
        super();
        this.outputEncryptedPath = outputEncryptedPath;
        this.outputDecryptedPath = outputDecryptedPath;
        File image = new File(inputImageFile);
        this.imageFile = ImageIO.read(image);
    }

//    /**
//     * constructor
//     *
//     * @param inputImageFile path of original image file
//     **/
//    public ImageCrypto(String inputImageFile) throws IOException {
//        super();
//        File image = new File(inputImageFile);
//        this.imageFile = ImageIO.read(image);
//
//    }

    /**
     * method to encrypt image file and save in denote path
     **/
    public void imageEncryption() throws IOException {
        System.out.println("encryption started...");
        originalImageToByteArray();
        enc = encryption(imageInBytes);
        encryptedByteArrayToImage();
        System.out.println("encryption ended...");
    }


//    /**
//     * method to encrypt image file and save in denote path
//     **/
//    public void imageEncryption(String outputEncryptedPath) throws IOException {
//        System.out.println("encryption started...");
//        ByteArrayOutputStream byteImage = new ByteArrayOutputStream();
//        ImageIO.write(imageFile, "jpg", byteImage);
//        byteImage.flush();
//        byte[] imageInBytes = byteImage.toByteArray();
//        byteImage.close();
//        enc = encryption(imageInBytes);
//        File output = new File(outputEncryptedPath);
//        int w = (int) (getWidth() / 5.7);
//        int h = (int) (getHeight() / 5.7);
//        int type = getType();
//        BufferedImage encryptedImageFile = new BufferedImage(w, h, type);
//        SampleModel sampleModel = encryptedImageFile.getSampleModel().createCompatibleSampleModel(w, h);
//        DataBuffer dataBuffer = new DataBufferByte(enc, enc.length);
//        WritableRaster writableRaster = Raster.createWritableRaster(sampleModel, dataBuffer, null);
//        encryptedImageFile.setData(writableRaster);
//        ImageIO.write(encryptedImageFile, "jpg", output);
//        System.out.println("encryption ended...");
//    }

//    /**
//     * method to decrypt image file and save in denote path
//     **/
//    public void imageDecryption(String outputDecryptedPath) throws IOException {
//        System.out.println("decryption started...");
//        dec = decryption(enc);
//        File output = new File(outputDecryptedPath);
//        ByteArrayInputStream decryptedImageInByte = new ByteArrayInputStream(dec);
//        BufferedImage decryptedImageFile = ImageIO.read(decryptedImageInByte);
//        ImageIO.write(decryptedImageFile, "jpg", output);
//        System.out.println("decryption ended...");
//    }

    /**
     * method to decrypt image file and save in denote path
     **/
    public void imageDecryption() throws IOException {
        System.out.println("decryption started...");
        dec = decryption(enc);
        decryptedByteArrayToImage();
        System.out.println("decryption ended...");
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

    /**
     * convert image file to byte array
     **/
    private void originalImageToByteArray() throws IOException {
        ByteArrayOutputStream byteImage = new ByteArrayOutputStream();
        ImageIO.write(imageFile, "jpg", byteImage);
        byteImage.flush();
        imageInBytes = byteImage.toByteArray();
        byteImage.close();
    }

    /**
     * convert encrypted byte array to image file
     **/
    private void encryptedByteArrayToImage() throws IOException {
        File output = new File(outputEncryptedPath);
        int w = (int) (getWidth() / 5.7);
        int h = (int) (getHeight() / 5.7);
        int type = getType();
        BufferedImage encryptedImageFile = new BufferedImage(w, h, type);
        SampleModel sampleModel = encryptedImageFile.getSampleModel().createCompatibleSampleModel(w, h);
        DataBuffer dataBuffer = new DataBufferByte(enc, enc.length);
        WritableRaster writableRaster = Raster.createWritableRaster(sampleModel, dataBuffer, null);
        encryptedImageFile.setData(writableRaster);
        ImageIO.write(encryptedImageFile, "jpg", output);
    }

    /**
     * convert decrypted byte array to image file
     **/
    private void decryptedByteArrayToImage() throws IOException {
        File output = new File(outputDecryptedPath);
        ByteArrayInputStream decryptedImageInByte = new ByteArrayInputStream(dec);
        BufferedImage decryptedImageFile = ImageIO.read(decryptedImageInByte);
        ImageIO.write(decryptedImageFile, "jpg", output);
    }
}