package client.ch05;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

public abstract class AbstractClient {

    private String name;
    private Socket socket;
    private PrintWriter writerStream;
    private BufferedReader readerStream;
    private BufferedReader keyboardReader;
    private boolean flag = true;

    public AbstractClient(String name) {
        this.name = name;
    }

    // 스레드 run()아님
    // 실행 흐름 정의 (자식 클래스에서 재정의 불가) final
    public final void run() {
        try {
            // 순서가 중요함
            connectToSever(); // 서버 연걸
            setupStreams(); // 스트림 연결
            startCommunication(); // 스레드 생성
        } catch (Exception e) {
            e.printStackTrace(); // 스택 구조로 예외를 추척하게 하는 부분을 출력
        }finally {
            // 최종에는 socket.close() 처리
            cleanUp();
        }

    } // end of run

    // AbstractClient 상속 받은 자식 클래스는
    // 무조건 connectToSever()라는 메서드를 재정의 해야 함
    protected abstract void connectToSever() throws IOException;

    private void setupStreams() throws IOException {
        writerStream = new PrintWriter(socket.getOutputStream(), true);
        readerStream = new BufferedReader(new InputStreamReader(socket.getInputStream()));
        keyboardReader = new BufferedReader(new InputStreamReader(System.in));
    }

    private void startCommunication() throws InterruptedException {
        Thread readThread = new Thread(new Runnable() {
            @Override
            public void run() {
                // 스레드 안에 오류는 안에서 해결
                try {
                    String msg;
                    while ((msg = readerStream.readLine()) != null) {
                        System.out.println("수신한 메시지 ");
                        System.out.println(msg);
                        if(!flag){
                            break;
                        }

                    } // end of while
                } catch (Exception e) {
                    System.out.println("서버와의 연결이 끊겼습니다.");
                }
            }
        }); // end of readThread


        // 키보드에서 값을 받아서 소켓으로 서버에 메세지 전송
        Thread writeThread = new Thread(new Runnable() {
            @Override
            public void run() {

                try {

                    String inputmsg;
                    System.out.println("보낼 메시지를 입력하세요 ");
                    while ((inputmsg = keyboardReader.readLine()) != null) {
                        writerStream.println("[" + name + "]" + inputmsg);
                        // 종료 구문
                        if("exit".equalsIgnoreCase(inputmsg)){
                            flag = false;
                            break;
                        }

                    }

                } catch (Exception e) {
                    System.out.println("전송 중 오류 발생");
                }


            }
        }); // end of writeThread

        readThread.start();
        writeThread.start();

        // 예외메세지 메서드 시그니처에 추가했음
        readThread.join();
        writeThread.join();

    } // end of startCommunication

    // 외부에서 메서드를 통해서 주소값을 주입 받음
    protected void setSocket(Socket socket) {
        this.socket = socket;
    }

    private void cleanUp() {
        try {
            if(socket!= null){
                socket.close();
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

} // end of class
