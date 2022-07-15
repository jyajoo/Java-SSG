package com.ll.exam;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.File;

import static org.junit.jupiter.api.Assertions.*;

class WiseSayingTableTest {

    private WiseSayingTable wiseSayingTable;

    public WiseSayingTableTest() {
        wiseSayingTable = new WiseSayingTable("test_data");
    }

    @BeforeEach
    public void beforeEach() {
        Util.file.deleteDir("test_data");
        wiseSayingTable.save("삶이 있는 한 희망은 있다.", "키케로");
        wiseSayingTable.save("나에게 불가능이란 없다.", "나폴레옹");
    }

    @Test
    public void 저장() {
        int newId = wiseSayingTable.getLastId() + 1;
        wiseSayingTable.save("자유가 아니면 죽음을 달라.", "패트릭 헨리");
        assertTrue(new File("test_data/wise_saying/%d.json".formatted(newId)).exists());
    }
}