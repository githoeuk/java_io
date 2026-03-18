package io.ch17;

import java.io.*;

public class ImageCopy3 {

    // abc.png 파일을 읽어서 -> abc2.png 파일을 만들어 봐라

    public static void main(String[] args) {

        String sourceFile = "popo.png";
        String destFile = "C:\\_work_java\\popo123.png";
        long startTime = System.currentTimeMillis();

        try (FileInputStream fis = new FileInputStream(sourceFile);
             FileOutputStream fos = new FileOutputStream(destFile)) {

            int data;
            while ((data = fis.read()) != -1){
                fos.write(data);
        }

            long endTime = System.currentTimeMillis();
            System.out.println("파일 복사 완료 ");
            System.out.println((endTime - startTime));

    } catch(
    FileNotFoundException e)

    {
        throw new RuntimeException(e);
    } catch(
    IOException e)

    {
        throw new RuntimeException(e);
    }


} // end of main
} // end of class
