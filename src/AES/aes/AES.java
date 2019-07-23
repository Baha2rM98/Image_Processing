package AES.aes;

import javax.crypto.Cipher;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.nio.charset.StandardCharsets;
import java.util.Base64;

/**
 * @author Baha2r
 **/

public abstract class AES {

    private final String ALGORITHM = "AES/CBC/PKCS5PADDING";

    //give to this iv file path
    private final File ivPath = new File("C:\\Users\\Baha2r\\IdeaProjects\\Image Processing\\src\\AES\\assets", "iv");

    //give to this k file path
    private final File kPath = new File("C:\\Users\\Baha2r\\IdeaProjects\\Image Processing\\src\\AES\\assets", "k");

    private SecretKeySpec KEY;
    private IvParameterSpec IV;

    /**
     * This constructor is used for preparing Key and ivVector thad are already exist
     **/
    protected AES() throws IOException {
        ivPath.setReadOnly();
        kPath.setReadOnly();
        IV = new IvParameterSpec(ivReader().getBytes(StandardCharsets.UTF_8));
        KEY = new SecretKeySpec(keyReader().getBytes(StandardCharsets.UTF_8), "AES");
    }

    private String ivReader() throws IOException {
        return readInnerBinaryFile(ivPath);
    }

    private String keyReader() throws IOException {
        return readInnerBinaryFile(kPath);
    }

    private String readInnerBinaryFile(File file) throws IOException {
        FileInputStream fis = new FileInputStream(file);
        ObjectInputStream ois = new ObjectInputStream(fis);
        final int size = ois.available();
        byte[] bytes = new byte[size];
        for (int i = 0; i < size; i++) {
            bytes[i] = ois.readByte();
        }
        byte[] buff = CharacterHider.show(bytes);
        ois.close();
        fis.close();
        return new String(buff);
    }

    protected byte[] encryption(byte[] plainText) {
        try {
            Cipher cipher = Cipher.getInstance(ALGORITHM);
            cipher.init(Cipher.ENCRYPT_MODE, KEY, IV);
            byte[] encrypted = cipher.doFinal(plainText);
            return Base64.getEncoder().encode(encrypted);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    protected byte[] decryption(byte[] cipherText) {
        try {
            Cipher cipher = Cipher.getInstance(ALGORITHM);
            cipher.init(Cipher.DECRYPT_MODE, KEY, IV);
            return cipher.doFinal(Base64.getDecoder().decode(cipherText));
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
}