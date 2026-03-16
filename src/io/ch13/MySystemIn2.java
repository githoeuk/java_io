package io.ch13;

import java.io.IOException;

/*
    표준 입출력이란?
    자바에서 표준 입출력은 프로그램과 사용자간에 기본적인 데이터 교환 방법을 제공

 */
public class MySystemIn2 {

    public static void main(String[] args) {
        System.out.println("알파벳 하나를 쓰고 enter 키를 누르세요 : ");
        int i;

        try {
         // enter(' \n ') 가 입력될 때 까지 반복해서 읽기
            while( (i = System.in.read()) != '\n'){
                System.out.println(" i + " + i);
                // InputStream은 바이트 단위로 데이터를읽어 오기 때문에
                // 문자로 표현하려면 형 변환 해야 한다.
                System.out.println("문자로 표현 : " + (char)i);
                // enter까지 입력을 받음
            }

        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    } // end of main
} // end of class
