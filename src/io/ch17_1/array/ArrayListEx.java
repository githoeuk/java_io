package io.ch17_1.array;

import java.util.ArrayList;

public class ArrayListEx {

    public static void main(String[] args) {
        // ArrayLis 는 배열에 불폄함을 해결해 주는 클래스 이다.
        ArrayList <String> list = new ArrayList<>(); // 배열처럼 미리 크기를 지정할 필요가 없다.

        // 추가 하는 기능
        list.add("사과");     // 내부적으로 [0] 인덱스 자동 저장
        list.add("바나나");   // 내부적으로 [1] 인덱스 자동 저장
        list.add("딸기");     // 내부적으로 [2] 인덱스 자동 저장

        // 조회 하는  기능
        System.out.println(list.get(0));
        System.out.println(list.get(1));
        System.out.println(list.getFirst());
        System.out.println(list.getLast());

        // 삭제 하는 기능
//        list.remove("바나나");
//        list.remove("사과");
//        list.remove("딸기");
//        list.remove("딸기11111111"); // 없는 데이터라면 그냥 실행해버림 -> 예외 안 던짐
        //list.removeAll();
        list.clear(); // 리스트 내용 전체 삭제

        // 편의 기능
        System.out.println("현재 arrayList의 길이 " + list.size());
        System.out.println(list.contains("사과"));
        System.out.println(list.isEmpty());



    } // end of main
} // end of class
