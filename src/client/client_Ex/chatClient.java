package client.client_Ex;

import java.io.IOException;
import java.net.Socket;
import java.util.Scanner;

public class chatClient extends AbstractClient{


    public chatClient(String name) {
        //부모 클래스에 사용자 정의 생성자가 있다면
        // 자식 클래스 생성자는 가장 먼저 부모의 사용자 정의 생성자를 호출해야 한다.
        super(name);
    }

    // 서버에 연결
    @Override
    protected void connectToSever() throws IOException {
        // 부모 클래스 멤버 변수 socket에 객체의 주소값을 할당하지 않으면
        // nullPointException 발생
        // 서버 연결
        setSocket(new Socket("localhost",5000));
    }

    // 실행하기
    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);
        System.out.print("이름을 입력하세요 : ");
        String name = sc.nextLine();

        new chatClient(name).run();

    }

}
