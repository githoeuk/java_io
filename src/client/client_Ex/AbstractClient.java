package client.client_Ex;

import io.ch17.BufferedFileReader;

import java.io.*;
import java.net.Socket;

public abstract class AbstractClient {

    private String name;
    private Socket socket;
    private PrintWriter writer;
    private BufferedReader reader;
    private BufferedReader keyboardReader;
    private BufferedWriter bw;
    private boolean flag = true;

    // 생성자 생성
    public AbstractClient(String name) {
        this.name = name;
    }

    // thread 메서드 아님 / 사용자가 생성한 메서드
    public final void run() {
        try {
            connectToSever();       // 서버 연결
            setupStream();          // 스트림 연결
            startCommunication();   // 통신 스레드 생성
        } catch (Exception e) {
            e.printStackTrace(); // 스택 구조로 예외를 추적
        } finally {
            // socket.close()
            cleanUp();
        }
    } // end of run

    protected abstract void connectToSever() throws IOException;

    // 스트림 세팅
    private void setupStream() throws IOException {
        writer = new PrintWriter(socket.getOutputStream(), true);
        reader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
        keyboardReader = new BufferedReader(new InputStreamReader(System.in));

    }

    // 통신 세팅
    private void startCommunication() throws InterruptedException {
        // 읽기 스레드
        Thread readThread = new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    String msg;
                    while (((msg = reader.readLine()) != null)) { // 블로킹
                        System.out.println("서버 메시지 ");
                        System.out.println(msg);
                        if (flag == false) {
                            System.out.println("연결을 종료합니다.");
                            break;
                        }
                    } // end of while
                } catch (Exception e) {
                    System.out.println("서버와의 연결이 끊겼습니다.");
                }

            }
        }); // end of readThread

        // 쓰기 스레드
        Thread writeThread = new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    String choice;
                    System.out.print("보낼 메시지 : ");
                    while (((choice = keyboardReader.readLine()) != null)) { // 블로킹
                        System.out.print("보낼 내용 1. 문자 2. 파일 (종료 : exit) ");
                        if ("1".equals(choice)){
                            writer.println("message//");
                            String sendMsg;
                            while ((sendMsg = keyboardReader.readLine())!= null){
                                writer.println("[ " + name + " ] : " + sendMsg);
                            }
                        }else if ("2".equals(choice)){
                            writer.println("file//");
                            String sendMsg;
                            while ((sendMsg = keyboardReader.readLine())!= null){
                                bw = new BufferedWriter(new FileWriter(sendMsg));
                            }
                        }
                        if ("exit".equalsIgnoreCase(choice)) {
                            System.out.println("연결를 종료합니다. ");
                            flag = false;
                            break;
                        }
                    } // end of while
                } catch (Exception e) {
                    System.out.println("전송 오류 발생");
                }
            }
        }); // end of writeThread

        readThread.start();
        writeThread.start();
        // 예외메세지 -> 메서드 시그니처에 추가
        readThread.join();
        writeThread.join();

    } // end of startCommunication

    protected void setSocket(Socket socket) {
        this.socket = socket;
    }

    private void cleanUp() {
        try {
            if (socket != null) {
                socket.close();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    } // end of cleanUp


} // end of class
