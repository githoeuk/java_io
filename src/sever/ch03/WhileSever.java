package sever.ch03;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;

public class WhileSever {
    public static void main(String[] args) {

        try (ServerSocket serverSocketsocket = new ServerSocket(5000)) {

            System.out.println("클라이언트 연결 요청을 기다림....");
            Socket clientSocket = serverSocketsocket.accept();
            System.out.println("====== 서버 실행 ========");

            // 소켓과 연결 된 스트림
            BufferedReader reader = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
            PrintWriter writer = new PrintWriter(clientSocket.getOutputStream(),true);

            // 키보드와 연결할 스트림
            BufferedReader keyboardReader = new BufferedReader(new InputStreamReader(System.in));

            String line;
            while ( (line = reader.readLine()) != null ){ // 블로킹
                // "exit"가 날라오면 정지를 시킴
                if ("exit".equalsIgnoreCase(line)){ // 대소문자 구분없이 잡음
                    System.out.println("클라이언트 측에서 대화를 중단하였습니다. ");
                    break;
                } // end of if
                System.out.println("클라이언트 > " + line);
                // 서버 측 컴퓨터의 키보드에서 값을 받아서 클라이언트 측으로 전송
                System.out.print("서버 입력 > ");
                // *************************
                // 서버에서(클라이언트에서) 응답을 받아야만 메세지를 보낼 수 있음
                // *************************
                String severMsg = keyboardReader.readLine(); // 블로킹 상태( 콘솔에 글자를 입력해야 함 )
                // 클라이언트와 연결 된 소켓 출력 스트림을 활용해서 내용을 전달.
                writer.println(severMsg);
                // 서버측에서 서버 종료 --> exit
                if("exit".equalsIgnoreCase(severMsg)){
                    System.out.println("대화를 종료합니다.");
                    break;
                } // end of if

            } // end of while

        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    } // end of main
} // end of class
