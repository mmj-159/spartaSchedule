package com.example.schedule.dto;

import com.example.schedule.entity.Schedule;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.Date;

@Getter
@AllArgsConstructor
public class ScheduleResponseDto {
    private Long id;
    private String title;
    private String contents;
    private  String author;
    private String password;
    private Date created_at;
    private Date updated_at;

    public ScheduleResponseDto(Schedule schedule){
        this.id = schedule.getId();
        this.password = schedule.getPassword();
        this.author = schedule.getAuthor();
        this.title = schedule.getTitle();
        this.contents = schedule.getContents();
    }

}
