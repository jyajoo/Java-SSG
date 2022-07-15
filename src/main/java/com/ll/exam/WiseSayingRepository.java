package com.ll.exam;

import java.util.ArrayList;
import java.util.List;

public class WiseSayingRepository {
    private int wiseSayingLastId;
    private List<WiseSaying> wiseSayingList;

    public WiseSayingRepository() {
        wiseSayingLastId = 0;
        wiseSayingList = new ArrayList<>();
    }

    public WiseSaying write(String content, String author){
        WiseSaying wiseSaying = new WiseSaying(++wiseSayingLastId, content, author);
        wiseSayingList.add(wiseSaying);
        return wiseSaying;
    }

    public List<WiseSaying> list() {
        return wiseSayingList;
    }

    public WiseSaying findById(int id) {
        for (WiseSaying wiseSaying : wiseSayingList) {
            if (wiseSaying.id == id) {
                return wiseSaying;
            }
        }
        return null;
    }

    public boolean modify(WiseSaying wiseSaying, String content, String author) {
        wiseSaying.content = content;
        wiseSaying.author = author;
        return true;
    }

    public boolean remove(WiseSaying wiseSaying) {
        wiseSayingList.remove(wiseSaying);
        return true;
    }
}
