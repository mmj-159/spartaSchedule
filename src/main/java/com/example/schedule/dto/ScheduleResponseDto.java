package com.example.schedule.dto;

import com.example.schedule.entity.Schedule;
import lombok.Getter;

@Getter
public class ScheduleResponseDto {
    private Long id;
    private String title;
    private String contents;
    private  String author;
    private String password;

    public ScheduleResponseDto(Schedule schedule){
        this.id = schedule.getId();
        this.password = schedule.getPassword();
        this.author = schedule.getAuthor();
        this.title = schedule.getTitle();
        this.contents = schedule.getContents();
    }

}
