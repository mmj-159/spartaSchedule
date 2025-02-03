package com.example.schedule.controller;

import com.example.schedule.dto.ScheduleRequestDto;
import com.example.schedule.dto.ScheduleResponseDto;
import com.example.schedule.service.ScheduleService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.*;

@RestController
@RequestMapping("/schedules")
public class ScheduleController {

    private final ScheduleService scheduleService;

    public ScheduleController(ScheduleService scheduleService) {
        this.scheduleService = scheduleService;
    }

    @PostMapping
    public ResponseEntity<ScheduleResponseDto> createSchedule(@RequestBody ScheduleRequestDto dto) {
     return new ResponseEntity<>(scheduleService.createSchedule(dto), HttpStatus.CREATED);
    }

    @GetMapping
    public List<ScheduleResponseDto> findAllSchedule(){
        return scheduleService.findAllSchedule();
    }

    @GetMapping("/{id}")
    public ResponseEntity<ScheduleResponseDto> findScheduleById(@PathVariable Long id){

        return new ResponseEntity<>(scheduleService.findScheduleById(id), HttpStatus.OK);

    }


      @PutMapping("/{id}")
     public ResponseEntity<ScheduleResponseDto> updateScheduleById(
            @PathVariable Long id,
            @RequestBody ScheduleRequestDto dto
      ) {
        return  new ResponseEntity<>(scheduleService.updateSchedule(id, dto.getAuthor(), dto.getTitle(),dto.getContents(), dto.getPassword()), HttpStatus.OK);
      }

    @PatchMapping("/{id}/title")
    public ResponseEntity<ScheduleResponseDto> updateTitle(
            @PathVariable Long id,
            @RequestBody ScheduleRequestDto dto
    ) {

        return new ResponseEntity<>(scheduleService.updateTitle(id, dto.getAuthor(), dto.getTitle(),dto.getContents(),dto.getPassword()),HttpStatus.OK);
    }

    @PatchMapping("/{id}/contents")
    public ResponseEntity<ScheduleResponseDto> updateContents(
            @PathVariable Long id,
            @RequestBody ScheduleRequestDto dto
    ) {

        return new ResponseEntity<>(scheduleService.updateContents(id, dto.getAuthor(), dto.getTitle(),dto.getContents(),dto.getPassword()),HttpStatus.OK);
    }

     @DeleteMapping("/{id}")
     public ResponseEntity<Void> deleteSchedule(@PathVariable Long id){

        scheduleService.deleteSchedule(id);

        return new ResponseEntity<>(HttpStatus.OK);
    }

}
