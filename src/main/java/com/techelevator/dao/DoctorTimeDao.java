package com.techelevator.dao;

import com.techelevator.model.DoctorTime;

import java.sql.Time;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

public interface DoctorTimeDao {


    DoctorTime getDoctorTimeByTimeId (int doctorTimeId);

    List<DoctorTime> getAllDoctorTime();

    LocalTime getStartTimeByDoctorIdDate (int doctorId, LocalDate officeDate);

    LocalTime getEndTimeByDoctorIdDate (int doctorId, LocalDate officeDate);

    void changeStartTimeAndEndTimeByDoctorId(int doctorId, Time startTime, Time endTime);

    void createStartTime(DoctorTime doctorTime);

    void updateStartTime(int doctorTimeId, DoctorTime doctorTime);

    void deleteStartTime(int doctorTimeId);

    void createEndTime(DoctorTime doctorTime);

    void updateEndTime(int doctorTimeId, DoctorTime doctorTime);

    void deleteEndTime(int doctorTimeId);
}

