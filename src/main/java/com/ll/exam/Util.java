package com.ll.exam;


import java.io.*;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;

public class Util {
    public static class json{
        public static Map<String, Object> jsonToMapFromFile(String path) {
            String json = file.readFromFile(path, "");

            if (json.isEmpty()) {
                return null;
            }

            final String[] jsonBits = json
                    .replaceAll("\\{", "")
                    .replaceAll("\\}", "")
                    .split(",");

            final List<Object> bits = Stream.of(jsonBits)
                    .map(String::trim)
                    .flatMap(bit -> Arrays.stream(bit.split(":")))
                    .map(String::trim)
                    .map(s -> s.startsWith("\"") ? s.substring(1, s.length() - 1) : Integer.parseInt(s))
                    .collect(Collectors.toList());

            Map<String, Object> map = IntStream
                    .range(0, bits.size() / 2)
                    .mapToObj(i -> Pair.of((String) bits.get(i * 2), bits.get(i * 2 + 1)))
                    .collect(Collectors.toMap(p -> p.getKey(), p -> p.getValue(), (key1, key2) -> key1, LinkedHashMap::new));

            return map;
        }

    }

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
                String body = "";

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
