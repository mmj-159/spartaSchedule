package com.example.schedule.repository;

import com.example.schedule.dto.ScheduleResponseDto;
import com.example.schedule.entity.Schedule;

import java.util.List;
import java.util.Optional;

public interface ScheduleRepository {
    ScheduleResponseDto createSchedule(Schedule schedule);

    List<ScheduleResponseDto> findAllSchedule();

    Optional<Schedule> findScheduleById(Long id);

    int updateSchedule(Long id, String author, String title, String contents, String password);

    int updateTitle(Long id, String author, String title, String password);

    int updateContents(Long id, String author, String contents, String password);

    int deleteSchedule(Long id);

}
