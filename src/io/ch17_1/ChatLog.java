package io.ch17_1;

import java.io.*;

public class ChatLog {

    public static void main(String[] args) {

        System.out.println("==== 채팅 로그 저장소 ====");

        System.out.println("이름과 메시지를 입력하세요. (종료 : 빈 줄 입력)");
        System.out.println("형식 : 이름>메시지 예) 홍길동>안녕하세요 ");

        // System.in (바이트) -> InputStreamReader ( 문자 변환) -> BufferedReader(버퍼 + readLine())
        //FileWriter("파일 명") -> BufferedWriter()

        try (BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
             BufferedWriter bw = new BufferedWriter(new FileWriter("chat_log.txt", true))
        ) {
            String line;
            while((line = br.readLine())!=null){
                bw.write(line);
                bw.newLine();
                bw.flush();  /// 입력할 때마다 즉시 파일에 저장
            }

            System.out.println("채팅 로그가 저장되었습니다 ");

        } catch (Exception e) {
            throw new RuntimeException(e);
        }


    } // end of main

} // end of class
