package com.ll.exam;

import java.io.*;
import java.util.Scanner;

public class TestUtil {

    /*
    문자열을 입력으로 하는 Scanner 생성
     */
    public static Scanner genScanner(String input) {
        InputStream in = new ByteArrayInputStream(input.getBytes());

        return new Scanner(in);
    }

    /*
    System.out이 특정 바이트스트림을 가리키도록 함
     */
    public static ByteArrayOutputStream setOutToByteArray() {
        final ByteArrayOutputStream output = new ByteArrayOutputStream();
        System.setOut(new PrintStream(output));

        return output;
    }

    /*
    System.out이 다시 제자리로 돌아가도록 함
     */
    public static void clearSetOutToByteArray(ByteArrayOutputStream output) {
        System.setOut(new PrintStream(new FileOutputStream(FileDescriptor.out)));
        try {
            output.close();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
