package com.example.memoto.dto;

import com.example.memoto.model.Memo;

import java.time.LocalDateTime;

public class MemoDTO {
    private Long id;
    private String title;
    private String content;
    private LocalDateTime createdOn;
    private String username;

    // default constuctor and getters and setters are important for jackson...

    public MemoDTO() {}

    public MemoDTO(Memo memo) {
        this.id = memo.getId();
        this.title = memo.getTitle();
        this.content = memo.getContent();
        this.createdOn = memo.getCreatedOn() != null && !memo.getCreatedOn().equals("") ? memo.getCreatedOn() : LocalDateTime.now();
        this.username = memo.getUser() != null ? memo.getUser().getUsername() : "Unknown Username";
    }

    public Long getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public String getContent() {
        return content;
    }

    public LocalDateTime getCreatedOn() {
        return createdOn;
    }

    public String getUsername() {
        return username;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public void setCreatedOn(LocalDateTime createdOn) {
        this.createdOn = createdOn;
    }

    public void setUsername(String username) {
        this.username = username;
    }

}