package io.ch17;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.FileInputStream;
import java.io.FileOutputStream;

public class ImageCopy2 {

    // abc.png 파일을 읽어서 -> abc2.png 파일을 만들어 봐라

    public static void main(String[] args) {

        long start = System.currentTimeMillis();

        try (
                BufferedInputStream bis = new BufferedInputStream(new FileInputStream("popo.png"));
                BufferedOutputStream bos = new BufferedOutputStream(new FileOutputStream("popo3.png"))) {

                int inData;
                int outData;

               while((inData = bis.read()) != -1)  {
                   bos.write(inData);
                   bos.flush();
            }
            System.out.println("저장됨");
        } catch (Exception e) {
            throw new RuntimeException(e);
        }

        long end = System.currentTimeMillis();

        System.out.println("걸린 시간 : " + (end-start));

    } // end of main
} // end of class
