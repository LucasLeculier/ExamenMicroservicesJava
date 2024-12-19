package com.example.servicedossiermedical.repositories;

import com.example.servicedossiermedical.models.DossierMedical;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DossierMedicalRepository extends JpaRepository<DossierMedical, Integer> {
    // Recherche de DossierMedical par l'id du patient
    DossierMedical findByPatientId(int patientId);
}
