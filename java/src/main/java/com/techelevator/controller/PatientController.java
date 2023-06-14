package com.techelevator.controller;

import com.techelevator.dao.PatientDao;
import com.techelevator.model.Doctor;
import com.techelevator.model.Patient;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.Map;

@RequestMapping("/patients")
@RestController
@CrossOrigin
//@PreAuthorize("isAuthenticated()")
public class PatientController {

    private final PatientDao patientDao;
    public PatientController(PatientDao patientDao) {
        this.patientDao = patientDao;
    }

    @GetMapping("/{id}")
    public Patient getPatientById(@PathVariable int id) {
        return patientDao.getPatientById(id);
    }

    @GetMapping("/getPatientId/{userId}")
    public Map<String, Integer> getPatientIdByUserId(@PathVariable int userId) {
        return Map.of("patientId", (int) patientDao.getPatientIdByUserId(userId));
    }
    @GetMapping("/maxId")
    public Map<String, Integer> getMaxId() {
        return Map.of("userId", (Integer) patientDao.getMaxId());
    }

    @GetMapping("")
    public List<Patient> getAllPatients() {
        return patientDao.findAllPatients();
    }

    @GetMapping("/user/{id}")
    public Patient getPatientByUserId(@PathVariable int id) {
        return patientDao.getPatientByUserId(id);
    }

    @PostMapping("")
    public void createPatient(@Valid @RequestBody Patient patient) {
        Patient newPatient = patient;
        newPatient.setUserId(patientDao.getMaxId().intValue());
        patientDao.create(patient);
    }

    @PutMapping("/{id}")
    public void updatePatient(@PathVariable int id, @Valid @RequestBody Patient patient) {
        patientDao.updatePatient(id, patient);
    }

    @DeleteMapping("/{id}")
    public void deletePatient(@PathVariable int id, @Valid @RequestBody Patient patient) {
        patientDao.deletePatientById(id);
    }

    @GetMapping("/hello")
    public Map<String, String> getCurrentUsername() {
        User user = (User)SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        String name = user.getUsername();
        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        String username = principal.toString();
        return Map.of("username", username);

    }
}
