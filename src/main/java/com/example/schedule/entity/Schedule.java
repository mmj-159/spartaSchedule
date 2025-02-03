package com.example.schedule.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.Date;

@Getter
@AllArgsConstructor
public class Schedule {

    private Long id;
    private  String author;
    private String title;
    private String contents;
    private String password;
    private Date created_at;
    private Date updated_at;

    public Schedule( String author, String title, String contents, String password, Date created_at){
        this.author = author;
        this.title = title;
        this.contents = contents;
        this.password = password;
        this.created_at = created_at;
    }


    public void update(String author, String title, String contents, String password, Date updated_at){
        this.author = author;
        this.title = title;
        this.contents = contents;
        this.password = password;
        this.updated_at = updated_at;
    }

    public void updateTitle(String author,String title,  String password, Date updated_at){
        this.author = author;
        this.title = title;
        this.password = password;
        this.updated_at = updated_at;
    }

    public void updateContents( String author, String contents, String password, Date updated_at){
        this.author = author;
        this.contents = contents;
        this.password = password;
        this.updated_at = updated_at;
    }

}
