package sever.ch01;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;

public class ServerFile {
    public static void main(String[] args) {

        // 소켓 통신을 하기 위한 서버측 테스트 코드1

        // (내 IP주소는 당연히 알고 있음 )
        // 포트 번호를 열고 클라이언트에 연결 요청을 기다리는 서버 소켓
        // IP : 192.168.4.12
        try (ServerSocket serverSocket = new ServerSocket(5000)) {
            //accept() = 클라이언트가 연결할 때까지 이 줄에서 멈춤(블로킹 상태 )
            Socket clientSocket = serverSocket.accept();
            // 1.코드가 아래로 안 내려감(블로킹)
            System.out.println("클라이언트가 연결되었습니다."); // 블로킹 해제 연결됨을 의미
            // 2. 소켓 객체가 생성되면 (accept()) 이 소켓이 클라이언트 소켓과 연결되어 있는 소켓이다.

            // I/O 단원에서 배운 체인 그대로
            InputStream input = clientSocket.getInputStream(); // 클라이언트가 보낸 데이터를 받을 수 있음
            BufferedReader br = new BufferedReader(new InputStreamReader(input));
            // (new InputStreamReader(input)) = 브릿지 (InputStreamReader - 문자단위 (input - 바이트 단위 ) )

            // 클라이언트가 보낸 한 줄을 읽음
            String message = br.readLine();
            // 내 서버측 콘솔창에 출력
            System.out.println("클라이언트 메세지 : " + message);

            clientSocket.close();

        } catch (IOException e) {
            System.out.println("오류 발생 : 포트 5000 이미 사용 중이거나 연결에 실패했습니다.");
        }
    } // end fo main
}
