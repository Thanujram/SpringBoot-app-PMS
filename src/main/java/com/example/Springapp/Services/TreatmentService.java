package com.example.Springapp.Services;

import com.example.Springapp.Model.Treatment;

import java.util.List;

public interface TreatmentService {
    List<Treatment> getAllTreatment();
    void saveTreatment(Treatment reatment);
    Treatment getTreatmentById(long id);
    void deleteTreatmentById(long id);

}
