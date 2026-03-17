package io.ch17;

import java.awt.color.ICC_ColorSpace;
import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;

public class ByteBufferedKeyboardConsole {

    /*
        보조 기반 스트림에 대해 알아보자
        기반 스트림이 있어야 사용할 수 있다.
     */

    public static void main(String[] args) {

        // System.in
        // System.out

        try(
            BufferedInputStream bis = new BufferedInputStream(System.in);
            BufferedOutputStream bos = new BufferedOutputStream(System.out);){

            byte[] buffer =new byte[1024]; // 한 번에 1024 바이트씩 읽을 버퍼
            int bytesRead;

            while((bytesRead = bis.read(buffer)) != -1){
                bos.write(buffer,0,bytesRead); // 읽은 만큼만 쓴다.
            }

        } catch (Exception e){
            throw new RuntimeException(e);
        }



    } // end of main
}
