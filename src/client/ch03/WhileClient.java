package client.ch03;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.net.UnknownHostException;

public class WhileClient {

    public static void main(String[] args) {
        try (Socket socket = new Socket("192.168.4.101", 5000)) {

            // 소켓에 연결할 입력, 출력 스트림
            PrintWriter writer = new PrintWriter(socket.getOutputStream(), true);
            BufferedReader reader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            // 클라이언트에서 키보드에서 값을 입력 받을 스트림이 필요.
            BufferedReader keyboardReader = new BufferedReader(new InputStreamReader(System.in));

            String line;
            while (true) {
                // 메시지 전송
                System.out.print("클라이언트 입력 > ");
                String input = keyboardReader.readLine(); // 블로킹 상태
                writer.println(input);
                // 클라이언트 종료 요청
                if ("exit".equalsIgnoreCase(input)) {
                    System.out.println("대화를 종료합니다.");
                    break;
                }
                // 서버 측에서 보낸 메세지를 받아 클라이언트 콘솔창에 출력
                String response = reader.readLine();
                // 서버측 종료 요청
                if (response.equalsIgnoreCase("exit")) {
                    System.out.println("서버측에서 대화를 중단하였습니다.");
                    break;
                }
                System.out.println("서버 응답 : " + response);
            } // end of while

        } catch (UnknownHostException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }


    } // end of main
}
