package com.example.servicepracticien.repositories;

import com.example.servicepracticien.models.Practicien;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface PracticienRepository extends JpaRepository<Practicien, Integer> {
    // Recherche par nom, insensible à la casse
    Optional<Practicien> findByNameIgnoreCase(String name);
}
