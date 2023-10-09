package com.techelevator.model;

import java.sql.Time;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.Date;

public class DoctorTime {
    private int doctorTimeId;
    private int doctorId;
    private LocalDate officeDate;
    private LocalTime start_time;
    private LocalTime end_time;

    public DoctorTime() {

    }
    public int getDoctorTimeId() {
        return doctorTimeId;
    }

    public void setDoctorTimeId(int doctorTimeId) {
        this.doctorTimeId = doctorTimeId;
    }

    public int getDoctorId() {
        return doctorId;
    }

    public void setDoctorId(int doctorId) {
        this.doctorId = doctorId;
    }

    public LocalDate getOfficeDate() {
        return officeDate;
    }

    public void setOfficeDate(LocalDate officeDate) {
        this.officeDate = officeDate;
    }

    public LocalTime getStart_time() {
        return start_time;
    }

    public void setStart_time(LocalTime start_time) {
        this.start_time = start_time;
    }

    public LocalTime getEnd_time() {
        return end_time;
    }

    public void setEnd_time(LocalTime end_time) {
        this.end_time = end_time;
    }

}
