package com.example.rendezvous.models;

import jakarta.persistence.*;

import java.time.LocalDateTime;
import java.util.List;


@Entity
public class RendezVous {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @ManyToOne
    @JoinColumn(name = "patient_id", nullable = false)
    private Patient patient;

    @ManyToOne
    @JoinColumn(name = "practicien_id", nullable = false)
    private Practicien praticien;

    private LocalDateTime date;


    public RendezVous() {
    }

    public RendezVous(Patient patient, Practicien praticien, LocalDateTime date) {
        this.patient = patient;
        this.praticien = praticien;
        this.date = date;
    }

    // Getters et Setters
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Patient getPatient() {
        return patient;
    }

    public void setPatient(Patient patient) {
        this.patient = patient;
    }

    public Practicien getPracticien() {
        return praticien;
    }

    public void setPracticien(Practicien praticien) {
        this.praticien = praticien;
    }
    public LocalDateTime getDate() {
        return date;
    }
    public void setDate(LocalDateTime date) {
        this.date = date;
    }

}
