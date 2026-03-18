package io.ch17;

import java.io.*;

public class CharBufferedKeyboardConsole {

    public static void main(String[] args) throws IOException {
        /*
            스트림 체인 구조

            [키보드] -> System.in( = InputStream)
            InputStreamReader(브릿지)
            // BufferedRead -> (버퍼 + readLine()추가 ) - 한 줄 씩 읽을 수 있음

            [ 프로그램 ] ---> 콘솔
            BufferedWrite ( 버퍼 + newLine() 추가 )
            System.out(PrintWriter) -> 콘솔창에 출력

         */

        // BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        InputStreamReader isr = new InputStreamReader(System.in);
        BufferedReader br = new BufferedReader(isr); //체인 구조

        // br.read() / br.readLine()

        // BufferedWriter bw = new BufferedWriter(new PrintWriter(System.out));
        PrintWriter pw = new PrintWriter(System.out);
        BufferedWriter bw = new BufferedWriter(pw);

        // 콘솔창에 종료 명령어 ( ctrl + D )
        System.out.println(" 텍스트를 입력하세요 (종료 : 빈 줄 입력 )");

        String line;
        while ((line = br.readLine()) != null) {
            bw.write("받은 값 출력 : " + line);
            bw.newLine(); // 운영체제에 맞는 줄바꿈 자동 삽입
            // \n <-- 리눅스 , mac  , \r \n <--- 윈도우
            bw.flush(); // 버퍼에 남은 데이터 즉시 출력
        }

    } // end of main
} // end of class
