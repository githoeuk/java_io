package io.ch18;

import java.io.*;

public class FileCopyBuffered {
    public static void main(String[] args) throws FileNotFoundException {

        // 파일 경로 지정
        String sourceFilePath = "employees.zip";
        String destinationFilePath = "employess_copy.zip";

        // 소요 시간 측정 시작
        // 현재 시간을 나노초(10먹분의 1초) 단위로 변환 from 1970 ~
        long startTime = System.nanoTime();

        // 파일 복사 기능 - 빠른 버전
        try (
                FileInputStream fis = new FileInputStream(sourceFilePath);
                FileOutputStream fos = new FileOutputStream(destinationFilePath);
                BufferedInputStream bfis = new BufferedInputStream(fis);
                BufferedOutputStream bfos = new BufferedOutputStream(fos)
        ) {
            // employees.zip 에서 1바이트 씩 읽어서 -> 버퍼에 크기를 직접 지정해줄 수 있음 .
            // 1바이트 1000개 = 1kb(1024)
            byte[] bytes = new byte[1024]; // 2kb 씩 읽을 버퍼 배열

            int data;
            while ((data = bfis.read(bytes)) != -1) {
                bfos.write(bytes, 0 , data); // 읽은 만큼 쓴다.(버퍼배열 만큼 읽고 쓴다)
            }
            System.out.println(" 파일 복사 완료 ");

        } catch (IOException e) {
            throw new RuntimeException(e);
        }


        long endTime = System.nanoTime();
        long duration = endTime - startTime;
        double seconds = duration / 1_000_000_000.0; // 나노 '초' ->> '초' 변환
        System.out.println("나노 초 값(nano_seconds) : " + duration);
        System.out.println("초 값(seconds)  : " + seconds);
    } // end of main
} // end of class
