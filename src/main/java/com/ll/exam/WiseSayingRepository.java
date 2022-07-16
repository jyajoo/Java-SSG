package com.ll.exam;

import java.util.ArrayList;
import java.util.List;

public class WiseSayingRepository {
    private WiseSayingTable wiseSayingTable;

    public WiseSayingRepository() {
        wiseSayingTable = new WiseSayingTable();
    }

    public WiseSaying write(String content, String author){
        return wiseSayingTable.save(content, author);
    }

    public List<WiseSaying> findAll() {
        return wiseSayingTable.findAll();
    }

    public WiseSaying findById(int id) {
        return wiseSayingTable.findById(id);
    }

    public boolean modify(WiseSaying wiseSaying, String content, String author) {
        return wiseSayingTable.save(wiseSaying, content, author);
    }

    public boolean remove(WiseSaying wiseSaying) {
        return wiseSayingTable.removeById(wiseSaying.id);
    }
}
