package client.ch03;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.net.UnknownHostException;

public class MultiTreadClient {

    public static void main(String[] args) {
        try (Socket socket = new Socket("localhost", 5000)) {

            // 소켓에 연결할 입력, 출력 스트림
            PrintWriter writer = new PrintWriter(socket.getOutputStream(), true);
            BufferedReader reader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            // 클라이언트에서 키보드에서 값을 입력 받을 스트림이 필요.
            BufferedReader keyboardReader = new BufferedReader(new InputStreamReader(System.in));

            // 읽기 쓰레드 (서버측에서 값을 받을 수 있도록 처리)
            Thread readThread = new Thread(new Runnable() {
                @Override
                public void run() {
                    try {
                        String serveMessage;
                        while ((serveMessage = reader.readLine())!= null){
                                if ("exit".equalsIgnoreCase(serveMessage)){
                                    System.out.println("서버가 종료되었습니다");
                                } // end of if
                            System.out.println("서버 : " + serveMessage);

                        } // end of while
                    } catch (IOException e) {
                        System.out.println("서버측과 연결이 끊겼습니다.");
                    }
                }
            }); // end of readThread

            // 쓰기 스레드 생성 (클라이언트 측 키보드에서 값을 받아서 서버측으로 전송 )
            Thread writeThread = new Thread(new Runnable() {
                @Override
                public void run() {
                    try {
                        String clientMessage;
                        while ((clientMessage = keyboardReader.readLine()) != null){
                            writer.println(clientMessage);
                            if ("exit".equalsIgnoreCase(clientMessage)){
                                System.out.println("클라이언트가 종료했습니다.");
                                break;
                            }
                        } // end of while
                    } catch (IOException e) {
                        System.out.println("메세지 전송 중 오류 발생 ");
                    }
                }
            });  // end of writeThread


            readThread.start();
            writeThread.start();

            readThread.join();
            writeThread.join();

        } catch (UnknownHostException e) {
            throw new RuntimeException(e);
        } catch (IOException | InterruptedException e) {
            throw new RuntimeException(e);
        }


    } // end of main
}
