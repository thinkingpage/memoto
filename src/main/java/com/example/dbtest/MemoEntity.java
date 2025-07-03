package com.example.dbtest;

import jakarta.persistence.*;
import org.springframework.data.annotation.CreatedDate;
import java.util.Date;

@Entity
@Table(name = "memo")
public class MemoEntity {

    @Id
    @GeneratedValue
    private long id;

    @Column(name = "text", nullable = false)
    private String text;

    @Column(name = "created_at", nullable = false)
    @CreatedDate
    private Date created_at;

    public void setText(String text) {
        this.text = text;
    }

    public String getText() {
        return text;
    }
}