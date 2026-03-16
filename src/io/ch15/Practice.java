package io.ch15;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

public class Practice {
    public static void main(String[] args) {


        String data = "Hello welcome to java world";

        try (FileOutputStream fos = new FileOutputStream("practice.txt", false)) {
            byte[] dataBytes = data.getBytes();
            fos.write(dataBytes);

        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    } // end of main
} // end of class
