package com.example.rendezvous.repositories;

import com.example.rendezvous.models.RendezVous;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;

@Repository
public interface RendezVousRepository extends JpaRepository<RendezVous, Integer> {

    // Trouver tous les rendez-vous d'un patient donné
    List<RendezVous> findByPatientId(int patientId);

    // Trouver tous les rendez-vous d'un praticien donné
    List<RendezVous> findByPracticienId(int praticienId);

    // Trouver tous les rendez-vous après une date donnée
    List<RendezVous> findByDateAfter(LocalDateTime date);

    // Trouver tous les rendez-vous entre deux dates données
    List<RendezVous> findByDateBetween(LocalDateTime startDate, LocalDateTime endDate);

    // Optionnel: Trouver un rendez-vous par son ID (fourni par défaut par JpaRepository)
    // RendezVous findById(int id); // Cette méthode est déjà incluse par JpaRepository
}
