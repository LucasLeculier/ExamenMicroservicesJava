package com.example.servicepatient.repositories;

import com.example.servicepatient.models.Patient;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface PatientRepository extends JpaRepository<Patient, Integer> {
    Optional<Patient> findByNameIgnoreCase(String name);
}
