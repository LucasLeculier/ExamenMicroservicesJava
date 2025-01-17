package com.example.servicedossiermedical.repositories;

import com.example.servicedossiermedical.models.Patient;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface PatientRepository extends JpaRepository<Patient, Integer> {
    Optional<Patient> findByNameIgnoreCase(String name);
}
