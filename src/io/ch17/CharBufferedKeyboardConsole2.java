package io.ch17;

import java.io.*;

public class CharBufferedKeyboardConsole2 {

    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        System.out.println("텍스트를 입력하세요 (종료 : 빈 줄 입력 )");

        String line;
        while((line = br.readLine()) != null){
            bw.write("받은 값 출력 : " + line);
            bw.newLine();
            bw.flush();
        }

    } // end of main
} // end of class
