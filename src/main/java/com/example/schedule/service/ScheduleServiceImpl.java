package com.example.schedule.service;

import com.example.schedule.dto.ScheduleRequestDto;
import com.example.schedule.dto.ScheduleResponseDto;
import com.example.schedule.entity.Schedule;
import com.example.schedule.repository.ScheduleRepository;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@Service
public class ScheduleServiceImpl implements ScheduleService{

    private final ScheduleRepository scheduleRepository;

    public ScheduleServiceImpl(ScheduleRepository scheduleRepository) {
        this.scheduleRepository = scheduleRepository;
    }

    @Override
    public ScheduleResponseDto createSchedule(ScheduleRequestDto dto) {

        //요청받은 데이터로 Schedule 객체 생성
        Schedule schedule = new Schedule(dto.getTitle(), dto.getContents(), dto.getAuthor(), dto.getPassword(), dto.getCreated_at());
        return scheduleRepository.createSchedule(schedule);
    }

    @Override
    public List<ScheduleResponseDto> findAllSchedule() {

        return scheduleRepository.findAllSchedule();

    }

    @Override
    public ScheduleResponseDto findScheduleById(Long id) {

        Schedule scheduleById = scheduleRepository.findScheduleById(id);

        if (scheduleById == null) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND,"Does not exist id = " + id);
        }
        return new ScheduleResponseDto(scheduleById);
    }

    @Override
    public ScheduleResponseDto updateSchedule(Long id, String title, String contents, String author, String password) {

        Schedule scheduleById = scheduleRepository.findScheduleById(id);

        if (scheduleById == null) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND,"Does not exist id = " + id);
        }

        if (title == null || contents == null || author == null || password == null){
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "The title and contents are required values.");
        }

        //scheduleById.update(title,contents,author,password,upated_at);

        return new ScheduleResponseDto(scheduleById);
    }

    @Override
    public ScheduleResponseDto updateTitle(Long id, String title, String contents, String author, String password) {
        Schedule scheduleById = scheduleRepository.findScheduleById(id);

        if (scheduleById == null) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND,"Does not exist id = " + id);
        }

        if (title == null || contents != null || author == null || password == null){
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "The title and contents are required values.");
        }

        //scheduleById.updateTitle(title,author,password);

        return new ScheduleResponseDto(scheduleById);
    }

    @Override
    public void deleteSchedule(Long id) {
        Schedule scheduleById = scheduleRepository.findScheduleById(id);

        if (scheduleById == null) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND,"Does not exist id = " + id);
        }

        scheduleRepository.deleteSchedule(id);
    }
}
