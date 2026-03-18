package io.ch17_1;

import javax.swing.*;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.util.ArrayList;
import java.util.Scanner;

public class TodoList {

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);
        boolean flag = true;

        while(flag) {
            System.out.println("=== To do 리스트 ===");
            System.out.println(" 1. 할 일 추가 ");
            System.out.println(" 2. 전체 목록 보기 ");
            System.out.println(" 3. 완료 처리 ");
            System.out.println(" 4. 미완료 목록 보기 ");
            System.out.println(" 5. 완료 취소  ");
            System.out.println(" 0. 종료  ");
            System.out.print(" 선택 : ");
            String choice = sc.nextLine();

            if (choice.equals("1")) {
                addTask(sc);
            } else if (choice.equals("2")) {
                showAll();
            } else if (choice.equals("3")) {
                successTask(sc);
            } else if (choice.equals("4")) {
                searchNone();
            } else if (choice.equals("5")) {
                returnList(sc);
            } else if(choice.equals("0")) {
                flag = false;
            }else {
                System.out.println("다시 입력하세요 ");
            }
        }
        sc.close();
    } // end of main

    // 5번
    private static void returnList(Scanner sc) {
        System.out.println("변경할 일을 정확히 입력해주세요 : ");
        String task = sc.nextLine();
        String tempList;
        int count = 0;
        ArrayList<String> line = new ArrayList<>();

        try (BufferedReader br = new BufferedReader(new FileReader("todo.txt"))) {
            while ((tempList = br.readLine()) != null) {
                if (tempList.contains(task)) {
                    line.add(tempList.replace("[V]", "[ ]"));
                    count++;
                } else {
                    line.add(tempList);
                }
            }
            if (count == 0) {
                System.out.println("목록에 업무가 존재하지 않습니다.");
            } else {
                System.out.println("완료 처리 되었습니다 ");
            }

        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        try (BufferedWriter bw = new BufferedWriter(new FileWriter("todo.txt"))) {
            for (String updateList : line) {
                bw.write(updateList);
                bw.newLine();
                bw.flush();
            }

        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    // 4번
    private static void searchNone() {
        System.out.println("미완료 목록 입니다");
        try (BufferedReader br = new BufferedReader(new FileReader("todo.txt"))) {
            String line;
            int count = 0;
            while ((line = br.readLine()) != null) {
                if (!line.startsWith("[V]")) {
                    System.out.println(line);
                    count++;
                } else if(!line.startsWith("[v]")) {
                        System.out.println(line);
                        count++;

                    }
            }

            if (count == 0) {
                System.out.println("미완료 목록 입니다");
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }

    }
    // 3번
    private static void successTask(Scanner sc) {
        System.out.println("완료할 할 일을 정확히 입력해주세요");
        String task = sc.nextLine();
        String tempList;
        int count = 0;
        ArrayList<String> line = new ArrayList<>();

        try (BufferedReader br = new BufferedReader(new FileReader("todo.txt"))) {
            while ((tempList = br.readLine()) != null) {
                if (tempList.contains(task)) {
                    line.add(tempList.replace("[ ]", "[V]"));
                    count++;
                } else {
                    line.add(tempList);
                }
            }
            if (count == 0) {
                System.out.println("목록에 업무가 존재하지 않습니다.");
            } else {
                System.out.println("완료 처리 되었습니다 ");
            }

        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        try (BufferedWriter bw = new BufferedWriter(new FileWriter("todo.txt"))) {
            for (String updateList : line) {
                bw.write(updateList);
                bw.newLine();
                bw.flush();
            }

        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
    // 2번
    private static void showAll() {
        System.out.println("=== ToDoList 전체 목록 === ");
        try (BufferedReader bw = new BufferedReader(new FileReader("todo.txt"));) {
            ArrayList<Integer> arrCount = new ArrayList<>();
            int count = 0;
            String line;
            while ((line = bw.readLine()) != null) {
                arrCount.add(count++);
                System.out.println(arrCount.get(count - 1) + 1 + "번쨰 : " + line);
            } // end of while
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
    // 1번
    private static void addTask(Scanner sc) {
        System.out.print("추가할 할 일을 입력하세요 : ");
        String task = sc.nextLine();
        // " [ ] 할 일 내용 " 형식으로 지정
        // [ ] 미완료 상태
        try (BufferedWriter bw = new BufferedWriter(new FileWriter("todo.txt", true))) {
            bw.write("[ ] " + task);
            bw.newLine();
            System.out.println("추가되었습니다. : " + task);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

} // end of class
