package com.example.dbtest.model;


import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
@Table(name="memo")
public class Memo {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private String title;
    private String text;
}
