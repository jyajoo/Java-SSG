package com.ll.exam;


import java.io.File;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;
import java.nio.charset.StandardCharsets;

public class Util {
    public static class file {

        /*
        파일 저장
        https://www.baeldung.com/java-write-to-file#write-with-filechannel
         */
        public static void saveToFile(String path, String body){
            try (RandomAccessFile stream = new RandomAccessFile(path, "rw");
                 FileChannel channel = stream.getChannel()) {
                byte[] strBytes = body.getBytes();
                ByteBuffer buffer = ByteBuffer.allocate(strBytes.length);
                buffer.put(strBytes);
                buffer.flip();
                channel.write(buffer);
            } catch (IOException e) {

            }
        }

        /*
        디렉토리 생성
         */
        public static void mkdir(String path) {
            new File(path).mkdirs();
        }

        /*
        파일 읽기
         */
        public static String readFromFile(String path) throws IOException {
            RandomAccessFile reader = new RandomAccessFile(path, "r");
            StringBuilder sb = new StringBuilder();
            String line;

            boolean isFirst = true;

            while ((line = reader.readLine()) != null) {
                if (!isFirst) {   // 첫 줄이 아니라면, \n 처리.
                    sb.append("\n");
                }
                sb.append(new String(line.getBytes(StandardCharsets.ISO_8859_1), StandardCharsets.UTF_8));
                isFirst = false;
            }

            return sb.toString();
        }
    }
}
