package com.techelevator.controller;

import com.techelevator.dao.AppointmentDao;
import com.techelevator.model.Appointment;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import javax.validation.Valid;
import java.util.List;

@RequestMapping("/appointments")
@RestController
@CrossOrigin
//@PreAuthorize("isAuthenticated()")
public class AppointmentController {
    private final AppointmentDao appointmentDao;

    public AppointmentController(AppointmentDao appointmentDao) {
        this.appointmentDao = appointmentDao;
    }

    @GetMapping("")
    public List<Appointment> getAppointments() { return appointmentDao.getAppointments(); }

    @GetMapping("/{id}")
    public Appointment getAppointmentById(@PathVariable int id) {
        return appointmentDao.getAppointmentById(id);
    }

    @GetMapping("/patient/{id}")
    public List<Appointment> getAppointmentsByPatientId(@PathVariable int id) {
        return appointmentDao.getAppointmentsByPatientId(id);
    }

    @GetMapping("/date/{appointmentDate}")
    public List<Appointment> getAppointmentsByDate(@PathVariable String appointmentDate) {
        return appointmentDao.getAppointmentsByDate(appointmentDate.substring(0, 10));
    }

    @GetMapping("/patient/user/{userId}")
    public List<Appointment> getAppointmentsByPatientUserId(@PathVariable int userId) {
        return appointmentDao.getPatientAppointmentsByUserId(userId);
    }

    @GetMapping("/doctor/user/{userId}")
    public List<Appointment> getAppointmentsByDoctorUserId(@PathVariable int userId) {
        return appointmentDao.getDoctorAppointmentsByUserId(userId);
    }

    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping("")
    public void createAppointment(@Valid @RequestBody Appointment appointment) {
        appointmentDao.createAppointment(appointment);
    }
    @GetMapping("/doctor/{id}")
    public List<Appointment> getAppointmentsByDoctorId(@PathVariable int id) {
        return appointmentDao.getAppointmentsByDoctorId(id);
    }
    @PutMapping("/{id}")
    public void updateAppointment(@PathVariable int id, @Valid @RequestBody Appointment appointment){
        appointmentDao.updateAppointment(id, appointment);
    }
    @DeleteMapping("/{id}")
    public void deleteAppointment(@PathVariable int id) {
        appointmentDao.deleteAppointment(id);
    }
}


