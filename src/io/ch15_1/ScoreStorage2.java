package io.ch15_1;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.util.Scanner;

public class ScoreStorage2 {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        saveScore(sc);
        printScore();


    } // end of main

    private static void printScore() {
        try (FileInputStream fin = new FileInputStream("SCORES.txt")) {
            System.out.println("점수 분석 총점 / 평균 ");
            StringBuffer sb = new StringBuffer();
            int data;
            while ((data = fin.read()) != -1){
                sb.append((char)  data);
            }
            String[] parts = sb.toString().trim().split(" ");
            int total = 0;
            for(String part : parts){
                total += Integer.parseInt(part);
            }
            System.out.println("총 점 : " + total);
            System.out.println("학생들 통합 평균 : " + (double)total/ parts.length);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    private static void saveScore(Scanner sc) {
        System.out.println("학생 수를 입력하세요 : ");
        int count =Integer.parseInt(sc.nextLine());

        StringBuilder sb = new StringBuilder();
        for(int i= 0; i < count; i++){
            System.out.println(i + "번째 학생 점수");
            String score = sc.nextLine();
            sb.append(score);
            sb.append(" ");
        }
        System.out.println(sb.toString());

        try (FileOutputStream fos = new FileOutputStream("SCORES.txt")) {
            fos.write(sb.toString().getBytes());
            System.out.println(" 저장 완료 ");
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

} // end of class
