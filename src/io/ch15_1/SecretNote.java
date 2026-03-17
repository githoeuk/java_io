package io.ch15_1;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.util.Scanner;

public class SecretNote {

    public static void main(String[] args) {

        // 1. 키보드에서 값을 받아야 한다. -> inputstream
        // 2. 파일에다가 키보드에서 입력 받은 값을 저장 시켜야 한다. -> outputsteram
        // 2.1 단 --> 내용을 살짝 변경해서 저장해야 한다.(아스키코드 값에 +3)

        // 1단계
        Scanner sc = new Scanner(System.in);
        System.out.println(" 암호 생성 ");
        System.out.println(" 1. 메모 암호화  저장");
        System.out.println(" 2. 메모 복호화  읽기");
        System.out.println(" 선택 : ");
        String choice = sc.nextLine();
        if (choice.equals("1")){
            saveMemo(sc);
        } else if (choice.equals("2")) {
            showMemo(sc);
        }


    } // end of main

    private static void showMemo(Scanner sc){
        System.out.println("\n=== 복호화된 암호 ===");
        try (FileInputStream fis = new FileInputStream("secret.txt")) {
            // 한 바이트 씩 개수만큼 읽어서 콘솔에 출력하시오
            // fis.read() <- (힌트) 한 바이트 읽어 주는 메서드
            System.out.print("복호화 키 : ");
            int outputKey = sc.nextInt();
            int data;
            while ((data = fis.read()) != -1){
                // 문자표 [ h] --> 72 [][]
                System.out.print( (char) (data - outputKey));
            }

            //System.out.println((char) fis.read() -3);

        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    private static void saveMemo(Scanner sc) {
        System.out.println("==== 암호 생성 ====");
        System.out.print("저장할 암호를 입력하세요 : ");
        String input = sc.nextLine();
        System.out.print("암호화 키 : ");
        int inputKey = sc.nextInt();
        // 2단계
        try (FileOutputStream fos = new FileOutputStream("secret.txt")) {
            //write - 바이트 타입만 받음
            byte[] original = input.getBytes();
            //배열의 크기만 선언한 상태
            byte[] encrypted = new byte[original.length];

            for (int i = 0; i < original.length; i++) {
                encrypted[i] = (byte) (original[i] + inputKey); // original[i] + 3 -> int형 ->byte형변환
            }

            // 데이터를 암호화 한 후 파일에 쓰기
            fos.write(encrypted);
            // fos.flush(); -> fos.close() 호출 시 자동 호출


        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    } //  end of saveMemo


} // end of class
