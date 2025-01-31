package com.example.schedule.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@AllArgsConstructor
public class Schedule {

    @Setter
    private Long id;
    private String title;
    private String contents;
    private  String author;
    private String password;

    public Schedule(String title, String contents, String author, String password){
        this.title = title;
        this.contents = contents;
        this.author = author;
        this.password = password;
    }

    public void update(String title, String contents, String author, String password){
        this.title = title;
        this.contents = contents;
        this.author = author;
        this.password = password;
    }

    public void updateTitle(String title, String author, String password){
        this.title = title;
        this.author = author;
        this.password = password;
    }

}
