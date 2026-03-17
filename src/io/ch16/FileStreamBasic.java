package io.ch16;

import java.io.FileReader;
import java.io.FileWriter;

public class FileStreamBasic {
    public static void main(String[] args) {

        writeToFile("Basic_output.txt");
        System.out.println("---------------------");
        readFromFile("Basic_output.txt");
    } // end of main

    // 파일의 텍스트를 쓰는 메서드 ( 문자 기반 스트림 사용 )
    public static void writeToFile(String fileName) {
        /*
           FileWriter 문자 기반 출력 스트림
           FileOutputStream과 달리 write(String)이 가능하다.
           getBytes() 변환이 필요가 없음
         */
        // append 설정 하지 않으면 기본값은 false이다. (덮어쓰기)
        try (FileWriter writer = new FileWriter(fileName)) {
            String text = "자바 문자 기반 스트림 예제\n";
            //text.getBytes(); --> 필요 없음
            writer.write(text);
            writer.write("추가 문자열 기록 ");
            writer.flush(); // 생략 가능함
            System.out.println("파일에 텍스트를 정상 기록하였습니다.");
        } catch (Exception e) {
            System.err.println("파일 쓰기 중 오류 발생 : " + e.getMessage());
        }
    }

    public static void readFromFile(String fileName) {
        /*
            FileReader는 문자 기반 입력 스트림
            read()는 한 문자씩 읽어 유니코드 값(정수)를 반환한다.
            FileInputStream과 사용법은 같지만 한글이 깨지지 않는다
         */
        try (FileReader reader = new FileReader(fileName)) {
            int charCode;
            while ((charCode = reader.read()) != -1) {
                System.out.print((char) charCode);
            }
        } catch (Exception e) {
            System.out.println("해당하는 파일이 없습니다. " + e.getMessage());
        }
    }

} // end of class
