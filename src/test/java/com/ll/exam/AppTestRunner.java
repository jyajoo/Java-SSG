package com.ll.exam;

import java.io.ByteArrayOutputStream;
import java.util.Scanner;

public class AppTestRunner {
    public static String run(String input) {
        /*
        문자열을 입력으로 하는 Scanner 생성
         */
        Scanner sc = TestUtil.genScanner(input);

        /*
        System.out이 특정 바이트스트림을 가리키도록 함
         */
        ByteArrayOutputStream output = TestUtil.setOutToByteArray();
        new App(sc).run();

        String rs = output.toString();

        /*
        System.out이 다시 제자리로 돌아가도록 함
         */
        TestUtil.clearSetOutToByteArray(output);
        return rs;
    }
}
