package com.example.schedule.repository;

import com.example.schedule.dto.ScheduleResponseDto;
import com.example.schedule.entity.Schedule;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;
import org.springframework.stereotype.Repository;

import javax.sql.DataSource;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Repository
public class JdbcTemplateScheduleRepository implements ScheduleRepository{

    private final JdbcTemplate jdbcTemplate;

    public JdbcTemplateScheduleRepository(DataSource dataSource) {
        this.jdbcTemplate = new JdbcTemplate(dataSource);
    }

    @Override
    public ScheduleResponseDto createSchedule(Schedule schedule) {
        //Insert Query 직접 작성 안해도 됨
        SimpleJdbcInsert jdbcInsert = new SimpleJdbcInsert(jdbcTemplate);
        jdbcInsert.withTableName("schedule").usingGeneratedKeyColumns("id");

        Map<String, Object> parameters = new HashMap<>();
        parameters.put("title", schedule.getTitle());
        parameters.put("contents", schedule.getContents());
        parameters.put("author", schedule.getAuthor());
        parameters.put("password", schedule.getPassword());
        // created_at 값 설정 (현재 날짜, java.sql.Date 형식)
        java.sql.Date created_at = java.sql.Date.valueOf(LocalDate.now());
        parameters.put("created_at", created_at);

        // updated_at은 생성 시에는 null (초기화 필요 없음)
        parameters.put("updated_at", null);

        //저장 후 생성된 Key값 Number 타입으로 반환하는 메서드
        Number key = jdbcInsert.executeAndReturnKey(new MapSqlParameterSource(parameters));

        return new ScheduleResponseDto(key.longValue(), schedule.getTitle(), schedule.getContents(), schedule.getAuthor(),schedule.getPassword(),created_at,null);
    }

    @Override
    public List<ScheduleResponseDto> findAllSchedule() {
        return null;
    }

    @Override
    public Schedule findScheduleById(Long id) {
        return null;
    }

    @Override
    public void deleteSchedule(Long id) {

    }
}
