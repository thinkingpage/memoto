package com.example.dbtest;

import com.example.dbtest.model.Memo;
import com.example.dbtest.repository.MemoRepository;
import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.time.Instant;
import java.util.List;
import java.util.Optional;

@RestController
public class MemoController {

    @Autowired
    MemoRepository memoRepository;

    @GetMapping("/getmemos")
    public List<Memo> findAll() {
        return memoRepository.findAll();
    }

//    @GetMapping("/getmemobyid/{id}")
//    public Memo getById(@PathVariable long id) {
//        return memoRepository.getReferenceById(id);
//    }

    @GetMapping("/getmemobyid/{id}")
    public Memo getById(@PathVariable long id) {
        Memo memo = memoRepository.getReferenceById(id);
        String memoTitle = memo.getTitle();
        String memoContent = memo.getContent();
        Instant memoDate = memo.getCreatedOn();

        System.out.println("memoTitle: " + memoTitle);
        System.out.println("memoContent: " + memoContent);
        System.out.println("memoDate: " + memoDate);

        System.out.println(memo);

        return memoRepository.getReferenceById(id);
    }

    @PostMapping("/addmemo")
    public void addMemo(@RequestBody Memo memo) {
        memoRepository.save(memo);
    }

    // Aufgaben von Trello --
    // Nutzung: Auf der Startseite immer die aktuellsten 10 anzeigen und Lazy Loading bei den darauf folgenden?
    @GetMapping("/find10firstmemos")
    public List<Memo> find10firstmemos() {
        return memoRepository.findFirst10ByOrderByCreatedOnDesc();
        // Limiter informieren !

    }

}
