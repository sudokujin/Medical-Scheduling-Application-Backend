package com.techelevator.dao;



import com.techelevator.model.DoctorTime;
import org.springframework.beans.NullValueInNestedPathException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.rowset.SqlRowSet;
import org.springframework.stereotype.Component;

import java.sql.Time;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

@Component
public class JdbcDoctorTimeDao implements DoctorTimeDao{

    private JdbcTemplate jdbcTemplate;

    public JdbcDoctorTimeDao(JdbcTemplate jdbcTemplate){
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public DoctorTime getDoctorTimeByTimeId(int doctorTimeId){
        DoctorTime doctorTime = null;
        String sql = "SELECT * " + "FROM doctor_time " + "WHERE doctor_time_id = ?" ;
        SqlRowSet results = jdbcTemplate.queryForRowSet(sql, doctorTimeId);

        if(results.next()){
            doctorTime = mapRowToDoctorTime(results);
        }
        return doctorTime;
    }

    @Override
    public List<DoctorTime> getAllDoctorTime() {
        List<DoctorTime> doctorTimes = new ArrayList<>();
        String sql = "SELECT * FROM doctor_time";
        SqlRowSet results = jdbcTemplate.queryForRowSet(sql);
        try {
            while (results.next()) {
                doctorTimes.add(mapRowToDoctorTime(results));
            }
        }   catch (NullValueInNestedPathException | EmptyResultDataAccessException e) {
            throw new RuntimeException("No time schedules found found");
        }

        return doctorTimes;

    }

    @Override
    public LocalTime getStartTimeByDoctorIdDate(int doctorId, LocalDate officeDate) {
        LocalTime startTime = null;
        String sql = "SELECT start_time FROM doctor_time JOIN doctor ON doctor_time.doctor_id=doctor.doctor_id WHERE doctor.doctor_id = ? AND doctor_time.office_date = ?;" ;
        startTime = jdbcTemplate.queryForObject(sql, LocalTime.class, new Object[] {doctorId, officeDate});

        return startTime;
    }

    @Override
    public LocalTime getEndTimeByDoctorIdDate(int doctorId, LocalDate officeDate) {
        LocalTime endTime = null;
        String sql = "SELECT end_time FROM doctor_time JOIN doctor ON doctor_time.doctor_id=doctor.doctor_id WHERE doctor.doctor_id = ? AND doctor_time.office_date = ?;" ;
        endTime = jdbcTemplate.queryForObject(sql, LocalTime.class, new Object[]{doctorId, officeDate});

        return endTime;
    }
    @Override                                          //int or Time? ..I think should be time
    public void changeStartTimeAndEndTimeByDoctorId(int doctorId, Time startTime, Time endTime) {
        String sql = "UPDATE public.doctor_time\n" +
                "\tSET start_time=?, end_time=?\n" +
                "\tWHERE doctor_id=?;";
        jdbcTemplate.update(sql, doctorId, startTime);
    }

    @Override
    public void createStartTime(DoctorTime doctorTime) {
        String sql = "INSERT INTO doctor_time(doctor_id, office_date, start_time, end_time) VALUES (?, ?, ?, ?);";
        jdbcTemplate.update(sql, doctorTime.getDoctorId(), doctorTime.getOfficeDate(), doctorTime.getStart_time(), doctorTime.getEnd_time());
    }


    @Override
    public void updateStartTime(int doctorTimeId, DoctorTime doctorTime) {
        String sql = "UPDATE doctor_time SET doctor_time_id=?, doctor_id=?, office_date=?, start_time=?, end_time=? WHERE doctor_time_id=?;";
        jdbcTemplate.update(sql,doctorTime, doctorTimeId);

    }

    @Override
    public void deleteStartTime(int doctorTimeId) {
        String sql = "DELETE FROM doctor_time WHERE doctor_time_id=?;";
        jdbcTemplate.update(sql,doctorTimeId);
    }

    @Override
    public void createEndTime(DoctorTime doctorTime) {
        String sql = "INSERT INTO doctor_time(doctor_id, office_date, start_time, end_time) VALUES (?, ?, ?, ?);";
        jdbcTemplate.update(sql, doctorTime.getDoctorId(), doctorTime.getOfficeDate(), doctorTime.getStart_time(), doctorTime.getEnd_time());
    }


    @Override
    public void updateEndTime(int doctorTimeId, DoctorTime doctorTime) {
        String sql = "UPDATE doctor_time SET doctor_time_id=?, doctor_id=?, office_date=?, start_time=?, end_time=? WHERE doctor_time_id=?;";
        jdbcTemplate.update(sql,doctorTime, doctorTimeId);

    }

    @Override
    public void deleteEndTime(int doctorTimeId) {
        String sql = "DELETE FROM doctor_time WHERE doctor_time_id=?;";
        jdbcTemplate.update(sql,doctorTimeId);
    }



    private DoctorTime mapRowToDoctorTime(SqlRowSet results) {
        DoctorTime doctorTime = new DoctorTime();
        doctorTime.setDoctorTimeId(results.getInt("doctor_time_id"));
        doctorTime.setDoctorId(results.getInt("doctor_id"));
        doctorTime.setOfficeDate(LocalDate.parse(results.getString("office_date")));
        doctorTime.setStart_time(results.getTime("start_time").toLocalTime());
        doctorTime.setEnd_time(results.getTime("end_time").toLocalTime());

        return doctorTime;
    }


}
