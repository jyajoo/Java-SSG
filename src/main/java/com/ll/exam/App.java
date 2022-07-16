package com.ll.exam;

import java.util.Scanner;

public class App {

    private final Scanner sc;
    private static String mode = "prod";

    public App(Scanner sc) {
        this.sc = sc;
    }

    public static String getBaseDir() {
        return mode + "_data";
    }

    public static String setMode(String mode) {
        return mode = mode;
    }

    public void run() {
        System.out.println("== 명언 SSG ==");

        WiseSayingController wiseSayingController = new WiseSayingController(sc);

        outer:
        while (true) {
            System.out.print("명령) ");
            String cmd = sc.nextLine().trim();
            Rq rq = new Rq(cmd);

            switch (rq.getPath()) {
                case "등록":
                    wiseSayingController.write();
                    break;
                case "목록":
                    wiseSayingController.list();
                    break;
                case "삭제":
                    wiseSayingController.remove(rq);
                    break;
                case "수정":
                    wiseSayingController.modify(rq);
                    break;
                case "빌드":
                    wiseSayingController.build();
                    break;
                case "종료":
                    break outer;
            }
        }
        sc.close();
    }
}
