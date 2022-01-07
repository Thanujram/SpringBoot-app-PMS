package com.example.Springapp.Repository;

import com.example.Springapp.Model.Staff;
import org.springframework.data.jpa.repository.JpaRepository;

public interface StaffRepo extends JpaRepository<Staff, Long> {
}
