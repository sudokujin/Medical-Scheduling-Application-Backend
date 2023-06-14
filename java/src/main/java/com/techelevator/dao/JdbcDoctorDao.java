package com.techelevator.dao;


import com.techelevator.model.Doctor;
import org.springframework.beans.NullValueInNestedPathException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.rowset.SqlRowSet;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;


@Component
public class JdbcDoctorDao implements DoctorDao{

    private final JdbcTemplate jdbcTemplate;


    public JdbcDoctorDao(JdbcTemplate jdbcTemplate){
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public Doctor getDoctorById(int doctorId){
        Doctor doctor = null;
        String sql = "select * FROM doctor WHERE doctor_id = ?";
        SqlRowSet results = jdbcTemplate.queryForRowSet(sql, doctorId);

        if(results.next()){
            doctor = mapRowToDoctor(results);
        }
        return doctor;
    }

    @Override
    public List <Doctor> findAll() {
        List<Doctor> doctors = new ArrayList<>();
        String sql = "SELECT * FROM doctor;";
        try {
        SqlRowSet result = jdbcTemplate.queryForRowSet(sql);
        while(result.next()) {
            doctors.add(mapRowToDoctor(result));
        }
            } catch (NullValueInNestedPathException | EmptyResultDataAccessException e) {
                throw new RuntimeException("No doctor found");
            }
        return doctors;
    }

    @Override
    public int findIdByDoctorLastName(String doctorLastName) {
        if (doctorLastName == null) throw new IllegalArgumentException("Last name cannot be null");

        int doctorId;
        try {
            doctorId = jdbcTemplate.queryForObject("SELECT doctor_id FROM doctor WHERE last_name = ?", int.class, doctorLastName);
        } catch (NullPointerException | EmptyResultDataAccessException e) {
            throw new UsernameNotFoundException("User " + doctorLastName + " was not found.");
        }
        return doctorId;
        }

    @Override
    public void create(Doctor doctor) {
        String sql = "INSERT INTO doctor (user_id, first_name,last_name,specialty,suite_number, costperhour, phone_number) " +
                "VALUES (?, ?, ?, ?, ?, ?, ?);" ;
        jdbcTemplate.update(sql, doctor.getUserId(), doctor.getFirstName(), doctor.getLastName(), doctor.getSpecialty(), doctor.getSuiteNumber(),doctor.getCostPerHour(), doctor.getPhoneNumber());

        }


    @Override
    public void updateDoctor(int doctorId, Doctor doctor) {
        String sql = "UPDATE public.doctor\n" +
                "\tSET user_id=?, first_name=?, last_name=?, specialty=?, suite_number=?, costperhour=?, phone_number=?\n" +
                "\tWHERE doctor_id=?;";
        jdbcTemplate.update(sql,doctor.getUserId(), doctor.getFirstName(),doctor.getLastName(),doctor.getSpecialty(),
                doctor.getSuiteNumber(), doctor.getCostPerHour(), doctor.getPhoneNumber(),doctor.getDoctorId());

    }

    @Override
    public void deleteDoctorById(int doctorId) {
        String deleteDoctorById = "delete from doctor where doctor_id = ?;";
        jdbcTemplate.update(deleteDoctorById, doctorId);
    }

    @Override
    public Integer getMaxId() {
        String sql = "SELECT(MAX(user_id)) FROM users;";
        return jdbcTemplate.queryForObject(sql, Integer.class);
    }

    @Override
    public Integer getDoctorIdByUserId(Integer userId) {
        String sql = "SELECT doctor_id FROM doctor WHERE user_id = ?;";
        return jdbcTemplate.queryForObject(sql, int.class, userId);
    }



    private Doctor mapRowToDoctor(SqlRowSet results) {
        Doctor doctor = new Doctor();
        doctor.setDoctorId(results.getInt("doctor_id"));
        doctor.setUserId(results.getInt("user_id"));
        doctor.setFirstName(results.getString("first_name"));
        doctor.setLastName(results.getString("last_name"));
        doctor.setSpecialty(results.getString("specialty"));
        doctor.setSuiteNumber(results.getInt("suite_number"));
        doctor.setCostPerHour(results.getInt("costperhour"));
        doctor.setPhoneNumber(results.getString("phone_number"));
        return doctor;

    }


}
