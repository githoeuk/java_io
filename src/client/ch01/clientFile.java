package client.ch01;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.Socket;
import java.net.UnknownHostException;

// 소켓 통신을 위한 클라이언트 측 코드
public class clientFile {
    public static void main(String[] args) {

        // 클라이언트는 접근할 서버 컴퓨터에 IP 주소와 포트 번호를 알고 있어야 한다.
        try (Socket socket = new Socket("localhost", 5000)) {
            // new Socket() 선언한 순간부터 호출
            //(Socket socket = new Socket("localhost", 5000)
            // 위 코드가 실행되는 순간 내부적으로 이미 서버 컴퓨터와 연결을 시도 한 상태

            PrintWriter writer = new PrintWriter(socket.getOutputStream(),true);
            // writer.write("Hello ~ Sever~~"); // 개행 문자까지 보내야 정상 전송
            //writer.write("Hello ~ Sever~~" + "\n"); // 개행 문자까지 보내야 정상 전송

            writer.println("클라이언트측 메시지.");


        } catch (UnknownHostException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }


    } //end of main
}
