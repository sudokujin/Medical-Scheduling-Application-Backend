package com.techelevator.controller;

import com.techelevator.dao.DoctorDao;
import com.techelevator.model.Doctor;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.Map;

@RequestMapping("/doctors")
@RestController
@CrossOrigin
//@PreAuthorize("isAuthenticated()")
public class DoctorController {
    private final DoctorDao doctorDao;

    public DoctorController(DoctorDao doctorDao) {
        this.doctorDao = doctorDao;
    }
    @GetMapping("/{id}")
    public Doctor getDoctorById(@PathVariable int id) {
        return doctorDao.getDoctorById(id);
    }

    @GetMapping("")
    public List<Doctor> getAllDoctors() {
        return doctorDao.findAll();
    }

    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping("")
    public void createDoctor(@Valid @RequestBody Doctor doctor) {
        doctorDao.create(doctor);
    }

    @PutMapping("/{id}")
    public void updateDoctor(@PathVariable int id, @Valid @RequestBody Doctor doctor) {
        doctorDao.updateDoctor(id, doctor);
    }

    @DeleteMapping("/{id}")
    public void deleteDoctor(@PathVariable int id) {
        doctorDao.deleteDoctorById(id);
    }
    @GetMapping("/maxId")
    public Map<String, Integer> getMaxId() {
        return Map.of("userId", (Integer) doctorDao.getMaxId());
    }

    @GetMapping("/getDoctorId/{userId}")
    public Map<String, Integer> getDoctorId(@PathVariable Integer userId) {
        return Map.of("doctorId", (int) doctorDao.getDoctorIdByUserId(userId));
    }


}
