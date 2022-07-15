package com.ll.exam;

import org.junit.jupiter.api.Test;

import java.io.File;

import static org.junit.jupiter.api.Assertions.*;

class WiseSayingTableTest {

    @Test
    public void 등록_출력() {
        WiseSayingTable wiseSayingTable = new WiseSayingTable("test_data");

        wiseSayingTable.save("삶이 있는 한 희망은 있다.", "키케로");
        assertTrue(new File("test_data/wise_saying/1.json").exists());

        wiseSayingTable.save("나에게 불가능이란 없다.", "나폴레옹");
        assertTrue(new File("test_data/wise_saying/2.json").exists());
    }
}