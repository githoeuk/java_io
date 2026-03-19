package sever.ch03;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;

public class MultiThreadSever {
    public static void main(String[] args) {

        try (ServerSocket serverSocket = new ServerSocket(5000)) {

            System.out.println("클라이언트 연결 요청을 기다림....");
            Socket clientSocket = serverSocket.accept();
            System.out.println("====== 서버 실행 ========");

            // 소켓과 연결 된 스트림
            BufferedReader reader = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
            PrintWriter writer = new PrintWriter(clientSocket.getOutputStream(), true);

            // 키보드와 연결할 스트림
            BufferedReader keyboardReader = new BufferedReader(new InputStreamReader(System.in));

            //읽기 쓰레드 : 클라이언트 메세지를 계속 수신

            Thread readTread = new Thread(new Runnable() {
                @Override
                public void run() {
                    try {
                        String clientMessage;
                        while ((clientMessage = reader.readLine()) != null) { // 블로킹
                            if ("exit".equalsIgnoreCase(clientMessage)) {
                                System.out.println("클라이언트가 종료했습니다.");
                                break;
                            } // end of if
                            System.out.println("클라이언트 메세지 : " + clientMessage);

                        } // end of while

                    } catch (IOException e) {
                        System.err.println("클라이언트와 연결이 끊겼습니다.");
                    }

                }
            }); // end of readThread

            // 쓰기 스레드 : 키보드 입력을 받아 클라이언트에게 전송
            Thread writeThread = new Thread(new Runnable() {
                @Override
                public void run() {
                    try {
                        String severMessage;
                        while ((severMessage = keyboardReader.readLine()) != null) {
                            writer.println(">>>" + severMessage); // "\n:까지 포함해서 보냄
                            if ("exit".equalsIgnoreCase(severMessage)) {
                                System.out.println("서버가 종료했습니다");
                                break;
                            } // end of if
                        } // end of while
                    } catch (IOException e) {
                        throw new RuntimeException(e);
                    }
                }
            }); // end of writeThread


            readTread.start();      // 읽기 쓰레드 시작
            writeThread.start();    // 쓰기 스레드 시작

            readTread.join();   // 읽기 스레드가 종료까지 대기
            writeThread.join(); // 쓰기 스레드가 종료까지 대기

            /*

                Thread.sleep() = N 초 동안 대기 상태라면
                join() = 저 스레드가 동작을 그만할때까지 대기한다.
                join() = 이 스레드가 끝날 때 까지 기다려 줘 라는 의미이다.

                join() 이 없으면
                main 스레드가 바로 try 블록을 벗어나면
                -> 서버가 종료됨(소켓 자동 close())
                -> 아직 실행 중인 readThread, writeThread가 닫힌 소켓으로 통신 시도 -> 오류 발생

             */

        } catch (IOException | InterruptedException e) {
            throw new RuntimeException(e);
        }

    } // end of main
} // end of class
