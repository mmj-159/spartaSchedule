package com.example.schedule.repository;

import com.example.schedule.dto.ScheduleResponseDto;
import com.example.schedule.entity.Schedule;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;
import org.springframework.stereotype.Repository;

import javax.sql.DataSource;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.*;

@Repository
public class JdbcTemplateScheduleRepository implements ScheduleRepository{

    private final JdbcTemplate jdbcTemplate;

    public JdbcTemplateScheduleRepository(DataSource dataSource) {
        this.jdbcTemplate = new JdbcTemplate(dataSource);
    }

    @Override
    public ScheduleResponseDto createSchedule(Schedule schedule) {
        // Insert Query 직접 작성하지 않아도 됨
        SimpleJdbcInsert jdbcInsert = new SimpleJdbcInsert(jdbcTemplate);
        jdbcInsert.withTableName("schedule").usingGeneratedKeyColumns("id");

        // created_at 값 설정 (현재 날짜, java.sql.Date 형식)
        java.sql.Date created_at = java.sql.Date.valueOf(LocalDate.now());

        Map<String, Object> parameters = new HashMap<>();
        parameters.put("author", schedule.getAuthor());     // 1. author
        parameters.put("title", schedule.getTitle());       // 2. title
        parameters.put("contents", schedule.getContents()); // 3. contents
        parameters.put("password", schedule.getPassword()); // 4. password
        parameters.put("created_at", created_at);           // 5. created_at
        parameters.put("updated_at", null);                 // 6. updated_at (초기에는 null)

        // 저장 후 생성된 Key값 반환
        Number key = jdbcInsert.executeAndReturnKey(new MapSqlParameterSource(parameters));

        return new ScheduleResponseDto(
                key.longValue(),
                schedule.getAuthor(),
                schedule.getTitle(),
                schedule.getContents(),
                schedule.getPassword(),
                created_at,
                null
        );}

    @Override
    public List<ScheduleResponseDto> findAllSchedule() {
        return jdbcTemplate.query("select * from schedule", scheduleRowMapper());
    }

    @Override
    public Optional<Schedule> findScheduleById(Long id) {
        List<Schedule> result = jdbcTemplate.query("SELECT * FROM schedule WHERE id = ?", scheduleRowMapperV2(), id);
        return result.isEmpty() ? Optional.empty() : Optional.of(result.get(0));
    }

    @Override
    public int updateSchedule(Long id, String author, String title, String contents, String password) {
        java.sql.Date updated_at = java.sql.Date.valueOf(LocalDate.now());
        return jdbcTemplate.update("update schedule set author = ?, title = ?, contents =?, updated_at = ? where id = ? ", author, title, contents, updated_at,id);
    }

    @Override
    public int updateTitle(Long id, String author, String title, String password) {
        java.sql.Date updated_at = java.sql.Date.valueOf(LocalDate.now());
        return jdbcTemplate.update("update schedule set author = ?, title = ?, updated_at = ? where id = ?", author, title, updated_at,id);
    }

    @Override
    public int updateContents(Long id, String author, String contents, String password) {
        java.sql.Date updated_at = java.sql.Date.valueOf(LocalDate.now());
        return jdbcTemplate.update("update schedule set author = ?, contents = ?, updated_at = ? where id = ?", author, contents, updated_at,id);
    }


    @Override
    public int deleteSchedule(Long id) {
        return jdbcTemplate.update("delete from schedule where id = ?", id);
    }




    private RowMapper<ScheduleResponseDto> scheduleRowMapper() {
        return new RowMapper<ScheduleResponseDto>() {
            @Override
            public ScheduleResponseDto mapRow(ResultSet rs, int rowNum) throws SQLException {
                return new ScheduleResponseDto(
                        rs.getLong("id"),
                        rs.getString("author"),
                        rs.getString("title"),
                        rs.getString("contents"),
                        rs.getString("password"),
                        rs.getDate("created_at"),
                        rs.getDate("updated_at")
                );
            }
        };
    }


    private RowMapper<Schedule> scheduleRowMapperV2(){
        return new RowMapper<Schedule>() {
            @Override
            public Schedule mapRow(ResultSet rs, int rowNum) throws SQLException {
                return new Schedule(
                        rs.getLong("id"),
                        rs.getString("author"),
                        rs.getString("title"),
                        rs.getString("contents"),
                        rs.getString("password"),
                        rs.getDate("created_at"),
                        rs.getDate("updated_at")
                );
            }
        };
    }
}
