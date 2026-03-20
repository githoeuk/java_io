package sever;

import java.util.Vector;

public class ChatRoomSimulator {

    // 공유 자원
    private static Vector<String> userList = new Vector<>(); // 스레드에 안전한 arraylist = vector
    public static void main(String[] args) throws InterruptedException {

        // ArrayList와 사용법 동일
//        Vector<String> list = new Vector<>();
//        list.add("철수");
//        list.add("영희");
//        list.remove("영희");
//        list.get(0);
//        list.size();
//        list.contains("영희");
//
//        for(String name : list){
//            System.out.println(name);
//        }

        System.out.println("=== 채팅방 접속자 시뮬레이션 ===");

        // if 3명이 동싱에 접속
        // 람다 표현식
        // Thread t1 = new Thread(() -> {});
        Thread t1 = new Thread(new Runnable() {
            @Override
            public void run() {
                userList.add("철수");
                System.out.println("[접속] 철수 | 현재 " + userList.size() + "명");

            }
        }); // end of thread

        Thread t2 = new Thread(new Runnable() {
            @Override
            public void run() {
                userList.add("영희");
                System.out.println("[접속] 영희 | 현재 " + userList.size() + "명");

            }
        }); // end of thread

        Thread t3 = new Thread(new Runnable() {
            @Override
            public void run() {
                userList.add("민준");
                System.out.println("[접속] 민준 | 현재 " + userList.size() + "명");

            }
        }); // end of thread

        // 스레드는 순차적으로 실행되지 않을 수 있다.
        t1.start();
        t2.start();
        t3.start();

        // join()명령어를 통해 메인 스레드와의 순서를 정할 수 있다.
//        t1.join();
//        t2.join();
//        t3.join();

        System.out.println("최종 접속자 : " + userList);

        // "영희 퇴장"
        userList.remove("영희");
        System.out.println("영희 퇴장 후 : " + userList);

    } // end of main
} // end of class
