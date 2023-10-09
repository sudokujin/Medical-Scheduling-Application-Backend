package com.techelevator.controller;


import com.techelevator.dao.AppointmentDao;
import com.techelevator.dao.DoctorTimeDao;
import com.techelevator.model.Appointment;
import com.techelevator.model.DoctorTime;
import org.springframework.web.bind.annotation.*;

import java.sql.Time;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;


import java.util.Date;
import java.util.List;
import java.util.stream.Stream;

@CrossOrigin
@RequestMapping("/time")
@RestController
public class DoctorTimeController {
    private final DoctorTimeDao doctorTimeDao;
    private final AppointmentDao appointmentDao;


    public DoctorTimeController(DoctorTimeDao doctorTimeDao, AppointmentDao appointmentDao) {
        this.doctorTimeDao = doctorTimeDao;
        this.appointmentDao = appointmentDao;
    }
//
//    @GetMapping("/{id}")
//    public DoctorTime getTimeByDoctorId (@PathVariable Integer id) {
//        return doctorTimeDao.getDoctorTimeByDoctorId(id);
//    }

    @GetMapping("")
    public List<DoctorTime> getAllDoctorTimes() {
        return doctorTimeDao.getAllDoctorTime();
    }

    @DeleteMapping("delete/start/{id}")
    public void deleteStartTimeByDoctorId(@PathVariable Integer id) {
        doctorTimeDao.deleteStartTime(id);
    }

    @DeleteMapping("delete/end/{id}")
    public void deleteEndTimeByDoctorId(@PathVariable Integer id) {
        doctorTimeDao.deleteEndTime(id);
    }

    @PutMapping("update/start/{id}")
    public void updateStartTimeByDoctorId(@PathVariable Integer id, DoctorTime doctorTime) {
        doctorTimeDao.updateStartTime(id, doctorTime);
    }

    @PutMapping("update/end/{id}")
    public void updateEndTimeByDoctorId(@PathVariable Integer id, DoctorTime doctorTime) {
        doctorTimeDao.updateEndTime(id, doctorTime);
    }

    @PostMapping("create/start/")
    public void createStartTime(DoctorTime doctorTime) {
        doctorTimeDao.createStartTime(doctorTime);
    }

    @PostMapping("create/end/")
    public void createEndTime(DoctorTime doctorTime) {
        doctorTimeDao.createEndTime(doctorTime);
    }

    @PostMapping("update/startandend/{id}")
    public void updateBothStartAndEndTimeByDoctorId(@PathVariable Integer id, Time startTime, Time endTime) {
        doctorTimeDao.changeStartTimeAndEndTimeByDoctorId(id, startTime, endTime);
    }


//    @GetMapping("/222")
//    public String getTime() throws ParseException {
//        String dateString = "2023-10-19";
//        DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
//        Date date = null;
//
//        date = dateFormat.parse(dateString);
//
//        return doctorTimeDao.getStartTimeByDoctorIdDate(5, date).toString();

//        SimpleDateFormat formatter = new SimpleDateFormat("YYYY-MM-DD", Locale.ENGLISH);
//        Date date = formatter.parse("2023-10-10");
//        System.out.println(date);
//        return doctorTimeDao.getEndTimeByDoctorIdDate(1, date).toString();
//    }
//}

    @GetMapping("/array/{id}/{date}")
    public ArrayList<String> blah(@PathVariable Integer id, @PathVariable String date) throws ParseException {
//        id = 5;
        String dateString = "1776-07-04";
        LocalDate customDate = LocalDate.parse(dateString);
        LocalDate dateToPassIn = LocalDate.parse(date);
        LocalTime first;
        LocalTime second;
        ArrayList<String> arr = new ArrayList<>();
        String breakTime = "12:00";
        String breakTimeTwo = "12:30";

        ArrayList<String> breakTimes = new ArrayList<>();

        breakTimes.add(breakTime);
        breakTimes.add(breakTimeTwo);


        try {
             first = LocalTime.parse(doctorTimeDao.getStartTimeByDoctorIdDate(id, dateToPassIn).toString());
             second = LocalTime.parse(doctorTimeDao.getEndTimeByDoctorIdDate(id, dateToPassIn).toString());

            while (first.isBefore(second)) {
                arr.add(first.toString());
                first = first.plusMinutes(30L);
            }

            ArrayList<Appointment> appointmentArrayList = (ArrayList<Appointment>) appointmentDao.getAppointmentsByDoctorIdDate(id, dateToPassIn);
            ArrayList<String> appointmentTimes = new ArrayList<>();
            for (int i = 0; i < appointmentArrayList.size(); i++) {
                appointmentTimes.add(appointmentArrayList.get(i).getAppointmentTime().toString());
            }
            arr.removeAll(appointmentTimes);
            arr.removeAll(breakTimes);
            if(arr.isEmpty()) {
                first = LocalTime.parse("08:00:00");
                second = LocalTime.parse("17:00:00");
                while (first.isBefore(second)) {
                    arr.add(first.toString());
                    first = first.plusMinutes(30L);
                }
                arr.removeAll(breakTimes);
                return arr;
            }
            return arr;
        } catch (Exception e) {
            if(arr.isEmpty()) {
                first = LocalTime.parse("08:00:00");
                second = LocalTime.parse("17:00:00");
                while (first.isBefore(second)) {
                    arr.add(first.toString());
                    first = first.plusMinutes(30L);
                }
                arr.removeAll(breakTimes);
                return arr;
            }
        }
        return arr;
    }
//
//    @PostMapping("/array")
//    public ArrayList<String> poopoo() throws ParseException {
//        SimpleDateFormat localDateFormat = new SimpleDateFormat("HH:mm:ss");
//        Date first = localDateFormat.parse("08:00:00");
//        Date second = localDateFormat.parse("17:00:00");
//
//
//        Date next = first;
//        ArrayList<String> arr = new ArrayList<>();
//        do {
//            System.out.println(localDateFormat.format(next));
//            String time = (String) localDateFormat.format(next); // <== Only changed line (and using a deprecated API)
//            java.sql.Time timeValue = new java.sql.Time(localDateFormat.parse(time).getTime());
//            System.out.println(timeValue);
//            arr.add(time);
//        } while ((next = new Date(next.getTime() + 1800000
//        )).before(second));
//        return arr;
//    }
//}
}
