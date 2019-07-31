import handler.Handler;

import java.io.IOException;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) throws IOException {
        System.out.println("Welcome!\nplease choose one of the options below:\n1) change color of an image\n2) choose an image to face detection\n3) choose an image to encrypt it");
        String ans, yn;
        Scanner scanner = new Scanner(System.in);
        while (true) {
            ans = scanner.nextLine();
            switch (ans) {
                case "1":
                    Handler.setColor();
                    System.out.println("\nplease choose one of the options below:\n1) change color of an image\n2) choose an image to face detection\n3) choose an image to encrypt it");
                    break;
                case "2":
                    Handler.faceDetector();
                    System.out.println("\nplease choose one of the options below:\n1) change color of an image\n2) choose an image to face detection\n3) choose an image to encrypt it");
                    break;
                case "3":
                    Handler.imageEncryption();
                    System.out.println("do you wanna create decrypted image too?(y / n)");
                    yn = scanner.nextLine();
                    if (yn.equals("y") || yn.equals("Y"))
                        Handler.imageDecryption();
                    System.out.println("\nplease choose one of the options below:\n1) change color of an image\n2) choose an image to face detection\n3) choose an image to encrypt it");
                    break;
                default:
                    System.out.println("ops! you missed... terminated...");
                    System.exit(0);
            }
        }
    }
}