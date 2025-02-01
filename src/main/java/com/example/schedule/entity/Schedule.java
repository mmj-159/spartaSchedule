package com.example.schedule.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.Date;

@Getter
@AllArgsConstructor
public class Schedule {

    private Long id;
    private String title;
    private String contents;
    private  String author;
    private String password;
    private Date created_at;
    private Date updated_at;

    public Schedule(String title, String contents, String author, String password, Date created_at){
        this.title = title;
        this.contents = contents;
        this.author = author;
        this.password = password;
        this.created_at = created_at;
    }

    public void update(String title, String contents, String author, String password, Date updated_at){
        this.title = title;
        this.contents = contents;
        this.author = author;
        this.password = password;
        this.updated_at = updated_at;
    }

    public void updateTitle(String title, String author, String password, Date updated_at){
        this.title = title;
        this.author = author;
        this.password = password;
        this.updated_at = updated_at;
    }

}
