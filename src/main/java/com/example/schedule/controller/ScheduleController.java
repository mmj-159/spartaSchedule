package com.example.schedule.controller;

import com.example.schedule.dto.ScheduleRequestDto;
import com.example.schedule.dto.ScheduleResponseDto;
import com.example.schedule.entity.Schedule;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.*;

@RestController
@RequestMapping("/schedules")
public class ScheduleController {

    private final Map<Long, Schedule> scheduleList = new HashMap<>();

    @PostMapping
    public ResponseEntity<ScheduleResponseDto> createSchedule(@RequestBody ScheduleRequestDto dto) {

     Long scheduleId = scheduleList.isEmpty() ? 1 : Collections.max(scheduleList.keySet()) +1;
     Schedule schedule = new Schedule(scheduleId, dto.getTitle(), dto.getContents(), dto.getAuthor(), dto.getPassword());
     scheduleList.put(scheduleId,schedule);

     return new ResponseEntity<>(new ScheduleResponseDto(schedule), HttpStatus.CREATED);
    }


    @GetMapping
    public List<ScheduleResponseDto> findAllSchedule() {

        List<ScheduleResponseDto> responseList = new ArrayList<>();

        for (Schedule schedule : scheduleList.values()){
            ScheduleResponseDto responseDto = new ScheduleResponseDto(schedule);
            responseList.add(responseDto);
        }

        return responseList;
    }


    @GetMapping("/{id}")
    public ResponseEntity<ScheduleResponseDto> findScheduleById(@PathVariable Long id){
        Schedule schedule = scheduleList.get(id);

        if (schedule == null){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        return new ResponseEntity<>(new ScheduleResponseDto(schedule), HttpStatus.OK);
    }


    @PutMapping("/{id}")
    public ResponseEntity<ScheduleResponseDto> updateScheduleById(
            @PathVariable Long id,
            @RequestBody ScheduleRequestDto dto)
    {
        Schedule schedule = scheduleList.get(id);

        if (schedule == null){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        if (dto.getTitle() == null || dto.getContents() == null || dto.getAuthor() == null || dto.getPassword() == null ){
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }

        schedule.update(dto);
        return new ResponseEntity<>(new ScheduleResponseDto(schedule), HttpStatus.OK);
    }

    @PatchMapping("/{id}")
    public ResponseEntity<ScheduleResponseDto> updateTitle(
            @PathVariable Long id,
            @RequestBody ScheduleRequestDto dto
    ) {
        Schedule schedule = scheduleList.get(id);

        if (schedule == null){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        if (dto.getTitle() == null || dto.getContents() != null || dto.getAuthor() == null || dto.getPassword() == null ){
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        schedule.updateTitle(dto);

        return new ResponseEntity<>(new ScheduleResponseDto(schedule), HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteSchedule(@PathVariable Long id){

        if (scheduleList.containsKey(id)){
            scheduleList.remove(id);

            return new ResponseEntity<>(HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

}
