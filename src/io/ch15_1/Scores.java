package io.ch15_1;

import java.io.*;
import java.util.Scanner;

public class Scores {



    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.print("과목이 몇 개인지 입력하세요 : ");
        int count = sc.nextInt();

        score_output(count,sc);

        score_input(count);

    } // end of main

    private static void score_output(int count , Scanner sc){

        System.out.print("과목 별 점수를 입력하세요 : ");
        for(int i = 1; i == count; i++) {
            System.out.print("과목 "+ i + " :" );
            int scores = sc.nextInt();

            try (FileOutputStream fos = new FileOutputStream("Score_board.txt")) {
                    fos.write(scores);
                System.out.println(" ");

                } catch (Exception e) {
                throw new RuntimeException(e);
            }
        } // end of for
        System.out.println("저장 완료");

    } // end of score_input


    private static void score_input(int count){
        System.out.println("평균 점수 : ");
        try (FileInputStream fin = new FileInputStream("Score_board.txt")) {
                int data;
                int avg = 0;
                int fourcount = 0;
                while ((data = fin.read()) != -1){

                    if(fourcount/4 == 0){
                        data *= 10;
                    }
                    avg += data;
                    if (fourcount == count){
                        System.out.println(avg / count);
                    }
                }


        } catch (Exception e) {
            throw new RuntimeException(e);
        }

    }

}
