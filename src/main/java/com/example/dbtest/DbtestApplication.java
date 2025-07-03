package com.example.dbtest;

import org.slf4j.ILoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.EventListener;

import java.util.List;

@SpringBootApplication
public class DbtestApplication {

    @Autowired
    private MemoRepository repository;

    @EventListener(ApplicationReadyEvent.class)
    public void runAfterStartup() {
        List allMemos = this.repository.findAll();
        System.out.println("Number of Memo's: " + allMemos.size());

        MemoEntity memo = new MemoEntity();
        memo.setText("First Memo ever");
        this.repository.save(memo);
    }



    public static void main(String[] args) {
        SpringApplication.run(DbtestApplication.class, args);
    }
}
