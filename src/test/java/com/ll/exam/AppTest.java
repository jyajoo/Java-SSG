package com.ll.exam;

import org.junit.jupiter.api.Test;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.Scanner;

import static org.junit.jupiter.api.Assertions.*;

class AppTest {

    @Test
    public void 테스트_실험() {
        int rs = 10 + 20;
        assertEquals(30, rs);
    }

    @Test
    public void 테스트_스캐너(){
        Scanner sc = TestUtil.genScanner("안녕");

        String cmd = sc.nextLine().trim();
        assertEquals("안녕", cmd);
    }

    @Test
    public void 출력을_모니터에_하지_않고_문자열로_얻기() {
        ByteArrayOutputStream output = TestUtil.setOutToByteArray();
        System.out.print("안녕");
        String rs = output.toString();
        TestUtil.clearSetOutToByteArray(output);
    }

    @Test
    public void 문자열을_파일에_저장() throws IOException {
        Util.file.mkdir("test_data");
        Util.file.saveToFile("test_data/1.txt", "안녕");

        String body = Util.file.readFromFile("test_data/1.txt");
        assertEquals("안녕", body);
    }

    @Test
    public void 프로그램_시작시_타이틀_출력_그리고_종료() {
        String rs = AppTestRunner.run("종료");

        assertTrue(rs.contains("== 명언 SSG =="));
        assertTrue(rs.contains("명령)"));
    }

    @Test
    public void 등록_출력_테스트() {
        String rs = AppTestRunner.run("""
                등록
                삶이 있는 한 희망은 있다.
                키케로
                종료
                """);

        assertTrue(rs.contains("명언 : "));
        assertTrue(rs.contains("작가 : "));

    }

    @Test
    public void 등록시_번호출력() {
        String rs = AppTestRunner.run("""
                등록
                삶이 있는 한 희망은 있다.
                키케로
                등록
                나에게 불가능이란 없다.
                나폴레옹
                종료
                """);

        assertTrue(rs.contains("1번 명언이 등록되었습니다."));
        assertTrue(rs.contains("2번 명언이 등록되었습니다."));
    }

    @Test
    public void 목록확인() {
        String rs = AppTestRunner.run("""
                등록
                삶이 있는 한 희망은 있다.
                키케로
                등록
                나에게 불가능이란 없다.
                나폴레옹
                목록
                종료
                """);
        assertTrue(rs.contains("번호 / 작가 / 명언"));
        assertTrue(rs.contains("-----------------------"));
        assertTrue(rs.contains("2 / 나폴레옹 / 나에게 불가능이란 없다"));
        assertTrue(rs.contains("1 / 키케로 / 삶이 있는 한 희망은 있다."));
    }

    @Test
    public void 삭제() {
        String rs = AppTestRunner.run("""
                등록
                삶이 있는 한 희망은 있다.
                키케로
                등록
                나에게 불가능이란 없다.
                나폴레옹
                삭제?id=1
                목록
                삭제?id=1
                종료
                """);

        assertTrue(rs.contains("1번 명인이 삭제되었습니다."));
        assertTrue(rs.contains("2 / 나폴레옹 / 나에게 불가능이란 없다."));

        assertFalse(rs.contains("1 / 키케로 / 삶이 있는 한 희망은 있다."));
        assertTrue(rs.contains("1번 명언은 존재하지 않습니다."));
    }

    @Test
    public void 수정() {
        String rs = AppTestRunner.run("""
                등록
                삶이 있는 한 희망은 있다.
                키케로
                등록
                나에게 불가능이란 없다
                나폴레옹
                목록
                수정?id=1
                나에게 불가능이란 없다.
                나폴레옹
                목록
                종료
                """);

        assertTrue(rs.contains("1 / 나폴레옹 / 나에게 불가능이란 없다"));
        assertTrue(rs.contains("1 / 나폴레옹 / 나에게 불가능이란 없다."));
    }
}