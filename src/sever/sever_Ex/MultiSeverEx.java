package sever.sever_Ex;

import io.ch17.BufferedFileReader;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Vector;

public class MultiSeverEx {

    private static final int PORT = 5000;
    private static Vector<PrintWriter> clientWriterList = new Vector<>();
    // Vector 사용 이유
    // 연결된 모든 클라이언트들의
    // 모든 클라이언트들의 메세지(클라이언트들의 출력 스트림)을 출력 및 접속자 수 관리

    public static void main(String[] args) {
        // 1단계 : 클라이언트 접속 준비
        System.out.println("서버 시작...");
        try (ServerSocket serverSocket = new ServerSocket(PORT)) {
            System.out.println("클라이언트 응답 대기 중....");
            // 클라이언트들이 올때마다 받을 수 있게 항상 대기하도록 함
            while (true) {
                // 1. 클라이언트 대기 중
                // 4. 다시 클라이언트 대기
                Socket clientSocket = serverSocket.accept();
                // 2. 클라이언트 접속 시 - 스레드 생성 및 실행
                new ClientHandler(clientSocket).start();
                // 3. 클라이언트 접속 알림
                System.out.println("클라이언트 접속");
                System.out.println("현재 접속자 : " + clientWriterList.size() + "명");
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    } // end of main

    // 2단계 : 클라이언트들과의 통신 - 내부 클래스로 설계
    // 각 통신은 스레드를 통해 작동함으로 스레드를 상속
    private static class ClientHandler extends Thread {
        private Socket socket;      // 사용 이유 - 스트림과의 연결
        private PrintWriter out;    // 사용 이유 - 클라이언트들에게 메시지 전송
        private BufferedReader in;  // 사용 이유 - 클라이언트 메세지 수신 및 입력
        private BufferedInputStream bis;

        // 생성자 작성
        public ClientHandler(Socket socket) {
            this.socket = socket; // 외부에서 클라이언트들의 포트 번호 수신
        }

        // 읽기,쓰기 스레드
        @Override
        public void run() {
            try {
                // 클라이언트 송신 내용 수신
                in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
                // 서버에서 내용 송신
                out = new PrintWriter(socket.getOutputStream(), true);

                // 추후 접속자 명수 확인 , 방송을 위해 Vector에 저장
                clientWriterList.add(out);

                String message;
                while ((message = in.readLine()) != null) {
                    String[] filter = message.split("//");
                    if(filter[0].equals("message")){
                        // 서버로 전송된 클라이언트들의 메세제
                        System.out.println("클라이언트 메세지");
                        bis = new BufferedInputStream(socket.getInputStream());
                        System.out.println(filter[1]);
                        // 서버가 받은 메시지를 각 클라이언트들에게 다시 송신
                        broadcast(message);
                    }else if(filter[0].equals("file")){

                    }


                } // end of while
            } catch (Exception e) {
                System.out.println("누군가 퇴장하였습니다. ");
            }finally {
                // 클라이언트가 강제 종료 및 exit 요청 시
                // 서버에서 관리하고 있는 Vector 안에 출력 스트림 제거해줘야 한다.
                clientWriterList.remove(out); // 퇴장한 클라이언트 printWrite 주소값을 찾아 삭제
                try{
                    if(socket != null){
                        socket.close(); // 메모리 누수 방지
                    }
                }catch (IOException e){
                    System.out.println("클라이언트 퇴장 | 현재 접속자 : " + clientWriterList.size());
                }
            } // end of try_finally

        } // end of run

        // 방송 : 서버가 받은 메세지를 클라이언트들에게 보여주는 것
        private void broadcast(String message) {
            for (PrintWriter writer : clientWriterList) { // 클라이언트들에게 개별로 전송
                writer.println(message); // 전송할 내용
            }
        } // end if broadcast

    } // end of clientHandler

} // end of class
