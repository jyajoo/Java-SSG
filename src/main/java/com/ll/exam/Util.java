package com.ll.exam;


import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Comparator;
import java.util.stream.Stream;

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
        public static String readFromFile(String path, String defaultValue) {
            try (RandomAccessFile reader = new RandomAccessFile(path, "r")) {
                String body = " ";

                String line;
                while ((line = reader.readLine()) != null) {
                    body += new String(line.getBytes(StandardCharsets.ISO_8859_1), StandardCharsets.UTF_8);
                }
                return body.trim();
            } catch (FileNotFoundException e) {
                return defaultValue;
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }

        /*
        디렉토리 삭제
         */
        public static void deleteDir(String path) {
            Path rootPath = Paths.get(path);
            try (Stream<Path> walk = Files.walk(rootPath)) {
                walk.sorted(Comparator.reverseOrder())
                        .map(Path::toFile)
                        .forEach(File::delete);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
    }
}
