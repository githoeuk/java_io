package io.ch17_1.array;

import java.util.ArrayList;
import java.util.Objects;

public class ArrayListEx2 {

    // 정수 , 실수 , 불리언 , 사용자 정의 객체를 담을 수 있는 ArrayList 각각 만들어서 사용해 보기





    public static void main(String[] args) {



        ArrayList<String> sList = new ArrayList<>();
        ArrayList<Integer> iList = new ArrayList<>();
        ArrayList<Float> fList = new ArrayList<>();
        ArrayList<Boolean> bList = new ArrayList<>();
        ArrayList<Sample> SampleList = new ArrayList<>();


        sList.add("String");
        iList.add(1);
        fList.add(1.1F);
        bList.add(false);
        System.out.println(iList.contains(1));


    } // end of main

    public class Sample{

    }

} // end of class
