package com.ll.exam;

import java.util.ArrayList;
import java.util.List;

public class WiseSayingService {

    private WiseSayingRepository wiseSayingRepository;

    public WiseSayingService() {
        wiseSayingRepository = new WiseSayingRepository();
    }

    public WiseSaying write(String content, String author){
        return wiseSayingRepository.write(content, author);
    }

    public List<WiseSaying> list() {
        return wiseSayingRepository.list();
    }

    public WiseSaying findById(int id) {
        return wiseSayingRepository.findById(id);
    }

    public boolean modify(WiseSaying wiseSaying, String content, String author) {
        return wiseSayingRepository.modify(wiseSaying, content, author);
    }

    public boolean remove(WiseSaying wiseSaying) {
        return wiseSayingRepository.remove(wiseSaying);
    }
}
