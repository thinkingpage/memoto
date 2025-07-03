package com.example.dbtest;

import java.util.Arrays;
import java.util.concurrent.atomic.AtomicLong;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class MemoController {

    private static final String template = "%s";
    private final AtomicLong counter = new AtomicLong();


    @GetMapping("/memo")
    public Memo memo(@RequestParam(value = "text", defaultValue = "---") String textInput) {
       return new Memo(counter.incrementAndGet(), String.format(template, textInput));
    }

}