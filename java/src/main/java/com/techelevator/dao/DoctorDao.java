package com.techelevator.dao;

import com.techelevator.model.Doctor;
import com.techelevator.model.User;

import java.util.List;

public interface DoctorDao {

    List<Doctor> findAll();

    Doctor getDoctorById(int doctorId);

    int findIdByDoctorLastName (String doctorLastName);

    void create(Doctor doctor);

    void updateDoctor (int doctorId, Doctor doctor);

    void deleteDoctorById(int doctorId);

    Integer getMaxId();

    Integer getDoctorIdByUserId(Integer userId);
}

