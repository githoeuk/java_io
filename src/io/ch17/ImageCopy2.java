package io.ch17;

import java.io.BufferedInputStream;
import java.io.FileInputStream;
import java.io.FileOutputStream;

public class ImageCopy {

    // abc.png 파일을 읽어서 -> abc2.png 파일을 만들어 봐라

    public static void main(String[] args) {

        long start = System.currentTimeMillis();

        try (
                FileInputStream in = new FileInputStream("popo.png");
                FileOutputStream out = new FileOutputStream("popo2.png")) {

                int inData;
                int outData;

               while((inData = in.read()) != -1)  {
                out.write(inData);
                out.flush();
            }
            System.out.println("저장됨");
        } catch (Exception e) {
            throw new RuntimeException(e);
        }

        long end = System.currentTimeMillis();

        System.out.println("걸린 시간 : " + (end-start));

    } // end of main
} // end of class
