package client.ch02;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.net.UnknownHostException;

public class ClientFile {
    public static void main(String[] args) throws IOException {

        try (Socket socket = new Socket("localhost", 5001)) {
            // Socket socket = new Socket("localhost", 5001) 생성 순간
            // 서버측과 연결 된 상태

            // 쓰는 스트림 (클라이언트 --> 서버)
            PrintWriter writer = new PrintWriter(socket.getOutputStream(),true);

            // 읽는 스트림 (서버 --> 클라이언트)
            BufferedReader reader = new
                    BufferedReader(new InputStreamReader(socket.getInputStream()));

            // 기능 실행
            // 클라이언트가 서버에 보냄
            writer.println("클라이언트 : 서버 측 정상 수신  ");

            // 서버에서 보낸 응답 수신
            String response = reader.readLine();
            System.out.println(" 서버 측 응답 : " + response);


        } catch (UnknownHostException e){
            System.out.println("서버측을 알 수 없습니다 " + e.getMessage());
        } catch (IOException e){
            throw new RuntimeException(e);
        }

    } // end of main
}
