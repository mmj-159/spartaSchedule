package com.example.schedule.service;

import com.example.schedule.dto.ScheduleRequestDto;
import com.example.schedule.dto.ScheduleResponseDto;

import java.util.List;

public interface ScheduleService {
    ScheduleResponseDto createSchedule(ScheduleRequestDto dto);

    List<ScheduleResponseDto> findAllSchedule();

    ScheduleResponseDto findScheduleById(Long id);

    ScheduleResponseDto updateSchedule(Long id, String author, String title, String contents, String password);

    ScheduleResponseDto updateTitle(Long id, String author, String title, String contents, String password);

    ScheduleResponseDto updateContents(Long id, String author, String title, String contents, String password);

    void deleteSchedule(Long id);
}
