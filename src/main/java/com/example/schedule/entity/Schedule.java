package com.example.schedule.entity;

import com.example.schedule.dto.ScheduleRequestDto;
import com.example.schedule.dto.ScheduleResponseDto;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class Schedule {
    private Long id;
    private String title;
    private String contents;
    private  String author;
    private String password;

    public void update(ScheduleRequestDto dto){
        this.title = dto.getTitle();
        this.contents = dto.getContents();
        this.author = dto.getAuthor();
        this.password = dto.getPassword();
    }

    public void updateTitle(ScheduleRequestDto dto){
        this.title = dto.getTitle();
        this.author = dto.getAuthor();
        this.password = dto.getPassword();
    }

}
