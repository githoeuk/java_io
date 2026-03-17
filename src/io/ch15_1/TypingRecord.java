package io.ch15_1;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.util.Scanner;

public class TypingRecord {

    static Scanner sc = new Scanner(System.in);

    public static void main(String[] args) {


        System.out.println("=== 타자 연습 기록기 ===");
        System.out.println("1. 문장 저장 ");
        System.out.println("2. 기록 보기");
        System.out.print("선택 : ");
        String choice = sc.nextLine();

        if (choice.equals("1")) {
            saveRecord(sc);
            sc.close(); // 메모리 누수 방지
        } else if (choice.equals("2")) {
            printRecord();
        }
    } // end of main

    private static void printRecord() {
        System.out.println("\n ====== 저장된 기록 =====");
        StringBuilder sb = new StringBuilder();

        try (FileInputStream fin = new FileInputStream("typing_record.txt")) {
            int data;
            int i = 0;
            while ((data = fin.read()) != -1) {
                if ((char) data == '\n') {
                    i++;
                    System.out.print(" " + i + "번 기록");
                }
                System.out.print((char) data);

            }

            System.out.println("총 " + i + "개의 기록이 있습니다.");
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    private static void saveRecord(Scanner sc) {
        System.out.println("연습한 문장을 입력하세요 : ");
        String input = sc.nextLine();

        try (FileOutputStream fos = new FileOutputStream("typing_record.txt", true)) {
            fos.write(input.getBytes());
            // 줄바꿈 추가
            fos.write("\n".getBytes());
            System.out.println("저장 완료");


        } catch (Exception e) {
            throw new RuntimeException(e);
        }


    }
} // end of class
