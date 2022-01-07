package com.example.Springapp.Repository;

import com.example.Springapp.Model.Treatment;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TreatmentRepo extends JpaRepository<Treatment, Long> {
}
