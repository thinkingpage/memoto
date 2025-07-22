package com.example.dbtest;

import com.example.dbtest.model.Memo;
import com.example.dbtest.repository.MemoRepository;
import com.example.dbtest.service.MemoService;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import org.apache.coyote.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;


// https://docs.spring.io/spring-data/relational/reference/repositories/core-concepts.html
// https://docs.spring.io/spring-data/jpa/reference/jpa/getting-started.html

@RestController
public class MemoController {


    @Autowired
    MemoService memoService;

    @PersistenceContext
    private EntityManager entityManager;

    @GetMapping("/memos")
    public ResponseEntity<List<Memo>> findAllMemos() {
        List<Memo> memos = memoService.findAllMemos();
        return new ResponseEntity<>(memos, HttpStatus.OK);
    }

    @GetMapping("/memos/{id}")
    public ResponseEntity<Memo> findMemoById(@PathVariable long id) {
        return new ResponseEntity<>(memoService.findMemoById(id), HttpStatus.OK);
    }

    @DeleteMapping("/memos/{id}")
    public ResponseEntity<Void> deleteById(@PathVariable long id) {
        memoService.deleteMemoById(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }


    @PostMapping("/memos")
    public void addMemo(@RequestBody Memo memo) {
        memoService.addMemo(memo);
    }


    @GetMapping("/find10firstmemos")
    public ResponseEntity<List<Memo>> find10firstmemos() {
        return new ResponseEntity<>(memoService.find10FirstMemos(), HttpStatus.OK);
    }

    @GetMapping("/find")
    public ResponseEntity<List<Memo>> find(
            @RequestParam(name="title") String title,
            @RequestParam(name="year") int year
    ) {
        return new ResponseEntity<>(memoService.findByTitleContaining(title, year), HttpStatus.OK);
    }

    @GetMapping("/test")
    public ResponseEntity<List<Memo>> finde(
            @RequestParam(name="title") String title,
            @RequestParam(name="year") int year
    ) {
        return new ResponseEntity<>(memoService.finde(title, year), HttpStatus.OK);
    }
}
