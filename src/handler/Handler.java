package handler;

import imageProcessing.colorChanger.ChangeColor;
import imageProcessing.faceDetection.FaceDetection;
import imageProcessing.imageCrypto.ImageCrypto;

import java.io.IOException;
import java.util.Scanner;

public abstract class Handler {
    private static ChangeColor changeColor;
    private static FaceDetection faceDetection;
    private static ImageCrypto imageCrypto;
    private static Scanner scanner = new Scanner(System.in);
    private static String imageFile;

    private Handler() {

    }

    private static void initChangeColor() throws IOException {
        System.out.println("enter paths and roots with double slash(//) and suffix(.txt, .jpg, ...)");
        System.out.println("enter path of source image:");
        imageFile = scanner.nextLine();
        System.out.println("enter path of output image:");
        String outputImagePath = scanner.nextLine();
        System.out.println("enter the color(blue, green, red, gray, sepia, negative):");
        String color = scanner.nextLine();
        changeColor = new ChangeColor(imageFile, outputImagePath, color);
    }

    private static void initFaceDetection() {
        System.out.println("enter paths and roots with double slash(//) and suffix(.txt, .jpg, ...)");
        System.out.println("enter path of source image:");
        String imageInput = scanner.nextLine();
        System.out.println("enter path of face detected image:");
        String imageOutput = scanner.nextLine();
        faceDetection = new FaceDetection(imageInput, imageOutput);
    }

    private static void initCrypto() throws IOException {
        System.out.println("enter paths and roots with double slash(//) and suffix(.txt, .jpg, ...)");
        System.out.println("enter path of source image:");
        imageFile = scanner.nextLine();
        System.out.println("enter path of encrypted image");
        String outputEncryptedPath = scanner.nextLine();
        System.out.println("enter path of decrypted image");
        String outputDecryptedPath = scanner.nextLine();
        imageCrypto = new ImageCrypto(imageFile, outputEncryptedPath, outputDecryptedPath);
    }

    public static void setColor() throws IOException {
        initChangeColor();
        changeColor.setColor();
    }

    public static void faceDetector() {
        initFaceDetection();
        faceDetection.faceDetector();
    }

    public static void imageEncryption() throws IOException {
        initCrypto();
        imageCrypto.imageEncryption();
    }

    public static void imageDecryption() throws IOException {
        System.out.println("notice: you have to call image encryption first to avoid exceptions");
        initCrypto();
        imageCrypto.imageDecryption();
    }
}
