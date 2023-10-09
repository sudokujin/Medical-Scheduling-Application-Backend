package com.techelevator.dao;

import com.techelevator.model.Patient;

import java.util.List;

public interface PatientDao {
    String getPatientName();

    List<Patient> findAllPatients();

    Patient getPatientById(int patientId);

    Patient getPatientByUserId(int userId);

    int findPatientIdByPatientLastName (String patientLastName);

    void create(Patient patient);

    void deletePatientById(int patientId);

    Integer getMaxId();

    void updatePatient (int patientId, Patient patient);

    int getPatientIdByUserId(int id);

    List<Patient> getPatientByBookedAppointment(int doctorId);
}
