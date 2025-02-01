package com.example.schedule.dto;

import lombok.Getter;

import java.util.Date;

@Getter
public class ScheduleRequestDto {
    private String title;
    private String contents;
    private  String author;
    private String password;
    private Date created_at;
    private Date updated_at;
}
