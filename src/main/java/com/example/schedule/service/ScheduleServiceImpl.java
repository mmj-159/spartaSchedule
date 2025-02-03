package com.example.schedule.service;

import com.example.schedule.dto.ScheduleRequestDto;
import com.example.schedule.dto.ScheduleResponseDto;
import com.example.schedule.entity.Schedule;
import com.example.schedule.repository.ScheduleRepository;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.Optional;

@Service
public class ScheduleServiceImpl implements ScheduleService{

    private final ScheduleRepository scheduleRepository;

    public ScheduleServiceImpl(ScheduleRepository scheduleRepository) {
        this.scheduleRepository = scheduleRepository;
    }

    @Override
    public ScheduleResponseDto createSchedule(ScheduleRequestDto dto) {

        //요청받은 데이터로 Schedule 객체 생성
        Schedule schedule = new Schedule(dto.getAuthor(), dto.getTitle(), dto.getContents(), dto.getPassword(), dto.getCreated_at());
        return scheduleRepository.createSchedule(schedule);
    }

    @Override
    public List<ScheduleResponseDto> findAllSchedule() {

        return scheduleRepository.findAllSchedule();

    }

    @Override
    public ScheduleResponseDto findScheduleById(Long id) {

        Optional<Schedule> scheduleById = scheduleRepository.findScheduleById(id);

        if (scheduleById.isEmpty()) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND,"Does not exist id = " + id);
        }
        return new ScheduleResponseDto(scheduleById.get());
    }

    @Transactional
    @Override
    public ScheduleResponseDto updateSchedule(Long id, String author, String title, String contents, String password) {
        if (title == null || contents == null || author == null || password == null){
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "The title and contents are required values.");
        }

        int updatedRow= scheduleRepository.updateSchedule(id, author, title, contents, password);

        if (updatedRow == 0) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND,"Does not exist id = " + id);
        }
        Optional<Schedule> optionalSchedule = scheduleRepository.findScheduleById(id);

        return new ScheduleResponseDto(optionalSchedule.get());
    }

    @Override
    public ScheduleResponseDto updateTitle(Long id, String author, String title, String contents,  String password) {

        int updateRow = scheduleRepository.updateTitle(id, author, title, password);

        if (title == null || contents != null || author == null || password == null){
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "The title is required values.");
        }

        if (updateRow == 0) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND,"Does not exist id = " + id);
        }

        Optional<Schedule> optionalSchedule = scheduleRepository.findScheduleById(id);

        return new ScheduleResponseDto(optionalSchedule.get());
    }

    @Override
    public ScheduleResponseDto updateContents(Long id, String author, String title, String contents, String password) {
        int updateRow = scheduleRepository.updateContents(id, author, contents, password);

        if (title != null || contents == null || author == null || password == null){
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "The contents are required values.");
        }

        if (updateRow == 0) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND,"Does not exist id = " + id);
        }

        Optional<Schedule> optionalSchedule = scheduleRepository.findScheduleById(id);

        return new ScheduleResponseDto(optionalSchedule.get());

    }

    @Override
    public void deleteSchedule(Long id) {
        int deleteRow = scheduleRepository.deleteSchedule(id);
        if (deleteRow == 0) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND,"Does not exist id = " + id);
        }
    }
}
