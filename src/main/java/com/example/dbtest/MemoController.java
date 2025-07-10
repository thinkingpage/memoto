package com.example.dbtest;

import com.example.dbtest.model.Memo;
import com.example.dbtest.repository.MemoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class MemoController {

    @Autowired
    MemoRepository memoRepository;

    @GetMapping("/getmemos")
    public List<Memo> findAll() {
        return memoRepository.findAll();
    };

    @PostMapping("/addmemo")
    public void addMemo(@RequestBody Memo memo) {
        memoRepository.save(memo);
    }
}
