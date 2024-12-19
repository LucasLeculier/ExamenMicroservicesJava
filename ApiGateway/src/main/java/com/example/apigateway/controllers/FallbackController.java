package com.example.apigateway.controllers;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.http.ResponseEntity;

@RestController
public class FallbackController {

    // Fallback pour le service patient
    @GetMapping("/fallback/patient")
    public ResponseEntity<String> patientFallback() {
        return ResponseEntity.status(503).body("Service Patient temporairement indisponible.");
    }

    // Fallback pour le service praticien
    @GetMapping("/fallback/practicien")
    public ResponseEntity<String> practicienFallback() {
        return ResponseEntity.status(503).body("Service Praticien temporairement indisponible.");
    }

    // Fallback pour le service dossier médical
    @GetMapping("/fallback/dossier-medical")
    public ResponseEntity<String> dossierMedicalFallback() {
        return ResponseEntity.status(503).body("Service Dossier Médical temporairement indisponible.");
    }

    // Fallback pour le service rendez-vous
    @GetMapping("/fallback/rendezvous")
    public ResponseEntity<String> rendezvousFallback() {
        return ResponseEntity.status(503).body("Service Rendez-vous temporairement indisponible.");
    }
}
