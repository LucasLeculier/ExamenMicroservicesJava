package com.example.servicedossiermedical.models;

import jakarta.persistence.*;

import java.util.List;


@Entity
public class DossierMedical {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @ManyToOne
    @JoinColumn(name = "patient_id", nullable = false)
    private Patient patient;

    @ManyToOne
    @JoinColumn(name = "practicien_id", nullable = false)
    private Practicien praticien;

    @ElementCollection
    @CollectionTable(name = "historique", joinColumns = @JoinColumn(name = "dossier_medical_id"))
    @Column(name = "historique")
    private List<String> historique;

    public DossierMedical() {
    }

    public DossierMedical(Patient patient, Practicien praticien, List<String> historique) {
        this.patient = patient;
        this.praticien = praticien;
        this.historique = historique;
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

    public List<String> getHistorique() {
        return historique;
    }

    public void setHistorique(List<String> historique) {
        this.historique = historique;
    }
}
