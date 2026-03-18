package io.ch17_1.array;

import java.util.ArrayList;

public class ArrayListEx3 {
    public static void main(String[] args) {

        // 1. 정수를 담은 리스트
        ArrayList<Integer> intList = new ArrayList<>();
        intList.add(10);
        intList.add(20);
        intList.add(30);
        System.out.println("정수 리스트 : " + intList);

//        Book book = new Book();
//        book.title = "자바";
//        System.out.println(book);

        ArrayList<Double> doubleArrayList = new ArrayList<>();
        doubleArrayList.add(1.0);
        doubleArrayList.add(1.1);
        doubleArrayList.add(1.2);
        System.out.println(doubleArrayList);

        // 3. 불리언 담을 리스트
        ArrayList<Boolean> booleanList = new ArrayList<>();
        booleanList.add(true);
        booleanList.add(false);

        // 사용자 정의 객체를 담은 리스트
        ArrayList<Book> bookList = new ArrayList<>();
        bookList.add(new Book("자바책"));
        bookList.add(new Book("RDMS책"));

        // bookList.get(0) ==> 주소값.title
        System.out.println(bookList.get(0).title);
        System.out.println(bookList.get(1).title);
        try {
            System.out.println(bookList.get(2).title);
        } catch (Exception e) {
            e.printStackTrace(); // sout
        }

        System.out.println("프로그램 정상 종료 ");

    } // end of main
} // end of class


class Book{
    String title;

    public Book(String title) {
        this.title = title;
    }

    @Override
    public String toString() {
        return "{" + " title = " + title + " } ";
    }
}