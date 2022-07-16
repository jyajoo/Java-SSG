package com.ll.exam;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;

import static org.junit.jupiter.api.Assertions.*;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
class WiseSayingControllerTest {

    @BeforeAll
    public void beforeAll() {
        App.setMode("test");
    }

    @BeforeEach
    public void beforeEach() {
        Util.file.deleteDir(App.getBaseDir());
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