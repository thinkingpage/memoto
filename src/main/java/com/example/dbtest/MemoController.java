package com.example.dbtest;

import com.example.dbtest.model.Memo;
import com.example.dbtest.repository.MemoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.time.Instant;
import java.util.List;

@RestController
public class MemoController {

    @Autowired
    MemoRepository memoRepository;

    // kein mapping für index nötig. resources > static > index.html reicht.
    @GetMapping("/getmemos")
    public List<Memo> findAll() {
        return memoRepository.findAll();
    }

//    @GetMapping("/getmemobyid/{id}")
//    public Memo getById(@PathVariable long id) {
//        return memoRepository.getReferenceById(id);
//    }

    // https://docs.spring.io/spring-data/relational/reference/repositories/core-concepts.html
    // https://docs.spring.io/spring-data/jpa/reference/jpa/getting-started.html
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

    @GetMapping("/deletememobyid/{id}")
    public String deleteById(@PathVariable long id) {
        memoRepository.deleteById(id);
        System.out.println("memo has been deleted");
        return "delete successfully";
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
