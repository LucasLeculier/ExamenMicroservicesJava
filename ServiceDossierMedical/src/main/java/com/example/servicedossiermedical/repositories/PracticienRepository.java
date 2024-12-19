package com.example.servicedossiermedical.repositories;

import com.example.servicedossiermedical.models.Practicien;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface PracticienRepository extends JpaRepository<Practicien, Integer> {
    // Recherche par nom, insensible à la casse
    Optional<Practicien> findByNameIgnoreCase(String name);
}
