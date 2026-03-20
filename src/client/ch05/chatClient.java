package client.ch05;

import client.ch04.ChatClient;

import java.io.IOException;
import java.net.Socket;
import java.util.Scanner;

public class chatClient extends AbstractClient{

    public chatClient(String name) {
        // 부모 클래스에 사용자 정의 생성자가 있다면
        // 자식 클래스 생성자에서 가장 먼저 부모 생성자를 호출 해야 한다.
        super(name);
    }

    // 서버에 연결 방법만 직접 구현
    @Override
    protected void connectToSever() throws IOException {
        // 부모 클래스 멤버 변수인 socket 에 객체의 주소값을 할당 하지 않으면
        // nullPointException 발생
        setSocket(new Socket("localhost",5000)); // 함축
        // Socket socket = new Socket("localhost",5000); // 이게 뭔소리지?
        // setSocket(socket);
    }

    // 실행하기
    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);
        System.out.print("이름을 입력하세요 : ");
        String name = sc.nextLine();

        new chatClient(name).run();

    } // end of main

} // end of class
