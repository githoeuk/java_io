package io.ch16;

import java.io.FileReader;
import java.io.FileWriter;
import java.io.InputStreamReader;

public class FileStreamUserInput {

    public static void main(String[] args) {
        writeUserInputToFile("user_input.txt");
        readFromFile("user_input.txt");
    } // end of main

    public static void writeUserInputToFile(String fileName) {
        // FileWriter
        /*
            키보드 입력 -> InputStreamReader(System.in) (바이트 -> 문자 변환)
            파일에 쓰기 -> FileWriter(filename)         (문자 기반 파일 출력)
         */

        try (InputStreamReader reader = new InputStreamReader(System.in);
             FileWriter writer = new FileWriter(fileName, true)) {

            System.out.println("텍스트를 입력하세요  (종료 : CTRL + D) ");
            // 1. 사용자가 입력한 값을 받자 - 키보드에서
            int charCode;
            while ((charCode = reader.read()) != -1) {
                writer.write(charCode); // charCode에 키보드 입력 내용 저장
                // 문자 하나 받을 때 마다 즉시 파일에 저장
                writer.flush(); // [] <-- 임시 메모리공간에 저장되어 있는 내용 비우기
            }
            System.out.println(fileName + "에 텍스트를 모두 저장 함 ");

        } catch (Exception e) {
            throw new RuntimeException(e);
        }

    } // end of writeUserInputToFile

    // 직접 코드 작성해보기
    // 파일에서 텍스트를 읽는 메서드를 직접 구현해보기
    private static void readFromFile(String fileName) {
        // .. 파일에 내용을 문자 기반으로 읽어서 콘솔창에 출력

        try (FileReader fileReader = new FileReader(fileName)) {
            System.out.println("-- 저장된 내용 출력 --");
            int charCode;
            while ((charCode = fileReader.read()) != -1){
                System.out.print((char) charCode);
            }

        }catch (Exception e) {
            throw new RuntimeException(e);
        }

    }

}
