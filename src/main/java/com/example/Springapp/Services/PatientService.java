package com.example.Springapp.Services;

import com.example.Springapp.Model.Patient;

import java.util.List;

public interface PatientService {
    List<Patient> getAllPatients();
    void savePatient(Patient patient);
    Patient getPatientById(long id);
    void deletePatientById(long id);
}
