package io.ch17_1.array;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.util.ArrayList;
import java.util.Scanner;

public class TodoList2 {

    private static String CHECK = "V";

    public static void main(String[] args) {

        Scanner scan = new Scanner(System.in);
        System.out.println("===== To-Do List =====");
        System.out.println("1. 할일 추가");
        System.out.println("2. 전체 목록 보기");
        System.out.println("3. 완료 처리");
        System.out.println("4. 미완료 목록만 보기");
        System.out.println("5. 완료 취소하기");
        System.out.print("선택: ");
        String choice = scan.nextLine();
        if (choice.equals("1")) {
            addTask(scan);
        } else if (choice.equals("2")) {
            showAll();
        } else if (choice.equals("3")) {
            successTask(scan);
        } else if (choice.equals("4")) {
            searchNone();
        } else if (choice.equals("5")) {
            System.out.print("취소할 작업을 입력하세요: ");
            String cancelTask = scan.nextLine();
            cancel(cancelTask);
        }

        scan.close();
    } // end of main
    // 할 일 추가

    private static void addTask(Scanner sc) {
        System.out.println("해야할 일 을 입력하세요 : ");
        String task = sc.nextLine();

        try (BufferedWriter bw = new BufferedWriter(new FileWriter("todo2.txt"))) {
            bw.write("[ ]" + task);
            bw.newLine();
            System.out.println("추가 됐습니다. " + task);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    } // end of addTask

    private static void showAll(){
        System.out.println("=== ToDoList 전체 목록 === ");
        try (BufferedReader bw = new BufferedReader(new FileReader("todo2.txt"));) {
            int count = 0;
            String line;
            while ((line = bw.readLine()) != null){
                count++;
                System.out.println(count +"번쨰 : "+ line);
            }

        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    } // end of showAll()

    private static void successTask(Scanner sc) {
        System.out.println("완료한 일을 입력해주세요 : ");
        String task = sc.nextLine();
        String tempList;
        int count = 0;
        ArrayList<String> line = new ArrayList<>();

        try (BufferedReader br = new BufferedReader(new FileReader("todo2.txt"))) {
            BufferedWriter bw = new BufferedWriter(new FileWriter("todo2.txt"));
            while ((tempList = br.readLine()) != null) {
                if (tempList.contains(task)) {
                    // replace를 통해 목록 찾은 목록(tempList) 수정하기
                    line.add(tempList.replace("[ ]", "[" + CHECK + " ] "));
                    count++;
                } else {
                    line.add(tempList);
                }
            }
            if (count == 0) {
                System.out.println("목록에 업무가 존재하지 않습니다. ");
            } else {
                System.out.println("완료 처리되었습니다");
            }
            // 목록 업데이트 하기
            for (String updateList : line) {
                bw.write(updateList);
                bw.newLine();
                bw.flush();
            }

        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    } // end of successTask

    private static void searchNone(){
        System.out.println("미완료 된 목록입니다.");
        try (BufferedReader br = new BufferedReader(new FileReader("todo2.txt"))) {
            String line;
            int count = 0;
            while((line = br.readLine()) != null){
                if (!line.startsWith("[" + CHECK + "}")){
                    System.out.println(line);
                    count++;
                }
            }
            if(count == 0){
                System.out.println(" 미완료 목록이 없습니다.");
            }else{
                System.out.println(" 미완료 목록이 " + count + "개 있습니다.");
            }

        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    private static void cancel(String task){
        try (BufferedReader br = new BufferedReader(new FileReader("todo2.txt"))) {
            ArrayList<String> todo = new ArrayList<>();
            String list;

            while ((list = br.readLine())!= null){
                if (list.contains("[ ]" + task)){
                    todo.add("[ ]" + task);
                }else {
                    todo.add(list);
                }
            }
            BufferedWriter bw = new BufferedWriter(new FileWriter("todo2.txt"));
            for(int i = 0; i < todo.size(); i++){
                bw.write(todo.get(i));
                bw.newLine();
                bw.flush();
            }
            System.out.println("작업 취소가 완료 되었습니다.");


        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
} // end of class