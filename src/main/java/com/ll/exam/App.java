package com.ll.exam;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class App {

    private Scanner sc;

    public App(Scanner sc) {
        this.sc = sc;
    }

    public void run() {
        System.out.println("== 명언 SSG ==");

        int wiseSayingLastId = 0;
        List<WiseSaying> wiseSayingList = new ArrayList<>();

        outer:
        while (true) {
            System.out.print("명령) ");
            String cmd = sc.nextLine().trim();

            switch (cmd) {
                case "등록":
                    System.out.print("명언 : ");
                    String content = sc.nextLine().trim();
                    System.out.print("작가 : ");
                    String author = sc.nextLine().trim();
                    int id = ++wiseSayingLastId;

                    wiseSayingList.add(new WiseSaying(id, content, author));

                    System.out.println(id + "번 명언이 등록되었습니다.");
                    break;

                case "목록":
                    System.out.println("번호 / 작가 / 명언");
                    System.out.println("-----------------------");
                    for (int i = wiseSayingList.size() - 1; i >= 0; i--) {
                        WiseSaying wiseSaying = wiseSayingList.get(i);
                        System.out.printf("%d / %s / %s\n", wiseSaying.id, wiseSaying.author, wiseSaying.content);
                    }

                case "종료":
                    break outer;
            }
        }

        sc.close();
    }
}
