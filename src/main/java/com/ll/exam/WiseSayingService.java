package com.ll.exam;

import java.util.ArrayList;
import java.util.List;

public class WiseSayingService {

    private int wiseSayingLastId;
    private List<WiseSaying> wiseSayingList;

    public WiseSayingService() {
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

    public void modify(WiseSaying wiseSaying, String content, String author) {
        wiseSaying.content = content;
        wiseSaying.author = author;
    }

    public void remove(WiseSaying wiseSaying) {
        wiseSayingList.remove(wiseSaying);
    }
}
