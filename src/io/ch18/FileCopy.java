package io.ch18;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

public class FileCopy {
    public static void main(String[] args) throws FileNotFoundException {

        // 파일 경로 지정
        String sourceFilePath = "employees.zip";
        String destinationFilePath = "employess_copy.zip";

        // 소요 시간 측정 시작
        // 현재 시간을 나노초(10먹분의 1초) 단위로 변환 from 1970 ~
        long startTime = System.nanoTime();

        // 파일 복사 기능
        try (
                FileInputStream fis = new FileInputStream(sourceFilePath);
                FileOutputStream fos = new FileOutputStream(destinationFilePath)
        ) {
            // employees.zip 에서 1바이트 씩 읽어서
            // employees_copy.zip 에 1바이트 씩 쓰기
            int data;
            while ((data = fis.read()) != -1) {
                fos.write(data);
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
