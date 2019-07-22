import colorChanger.ChangeColor;

import java.io.File;
import java.io.IOException;

public class Main {
    public static void main(String[] args) throws IOException {
        File file = new File("D:\\Inp.jpg");
        ChangeColor cc = new ChangeColor(file, "D:\\Out.jpg");
        cc.setColor("gray");
    }
}
