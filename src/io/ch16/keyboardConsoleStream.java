package io.ch16;

import java.io.InputStreamReader;
import java.io.PrintWriter;

public class keyboardConsoleStream {
    /*
        바이트 단위 스트림 이름 형태 : inputStream , OutputStream,
        문자 기반스트림 이름 형태 : xxxReader , xxx Writer
     */

    public static void main(String[] args) {

        /*
            InputStreamReader 의 read()는 하나의 문자를 읽어서
            유니코드(정수값)로 반환합니다.
            PrintWriter 는 문자 기반의 출력 스트림
            system.out은 콘솔 출력
         */
        // 키보드에서 값을 읽어보자 ---
        try (InputStreamReader reader = new InputStreamReader(System.in)) {

            PrintWriter writer = new PrintWriter(System.out, true);
            System.out.println("텍스트를 입력하세요 (종료는 CTRL + D)");
            System.out.println("-------------------------------------");
            int charCode;
            // 개행문자로 종료 처리
            while ((charCode = reader.read()) != -1){
                writer.print((char)charCode);
            }
            writer.flush(); // 버퍼에 남은 데이터를 즉시 출력
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    } // end of main
}
