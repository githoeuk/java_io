package io.ch14;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

public class MyFileInputStream2 {

    public static void main(String[] args) {

        // 주의 a.txt파일에서 바이트 단위로 데이터를 읽어서 콘솔에 출력할 예정
        // 한글이 있다면 깨짐 발생

        try (FileInputStream in = new FileInputStream("C:\\_work_java\\java_oop3\\src\\a.txt") {
        }) {

            // 파일에서 더 이상 읽을 데이터가 없다면 -1을 반환한다.
            int readData; // 한 바이트씩 데이터를 읽을 메모리 공간 선언
            while((readData = in.read()) != -1 ){
                System.out.print((char) readData);
            }

            // try 블록이 끝나면 in.close()가 자동으로 호출된다.( 실수를 방지)
            // try - with - resource 구문을 사용한다.


        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }


    } // end of main
} // end of class
