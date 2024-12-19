package com.example.servicedossiermedical.controllers;


import com.example.servicedossiermedical.models.DossierMedical;
import com.example.servicedossiermedical.models.Patient;
import com.example.servicedossiermedical.models.Practicien;
import com.example.servicedossiermedical.repositories.DossierMedicalRepository;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import java.util.List;

@RestController
@RequestMapping("/dossiers")
@Api(tags = "DossierMedical Controller", description = "Endpoints pour gérer les dossiers médicaux")
public class DossierMedicalController {

    @Autowired
    private DossierMedicalRepository dossierMedicalRepository;

    @Autowired
    private RestTemplate restTemplate;

    private static final String PATIENT_SERVICE_URL = "http://patient-service/patients/getById/";
    private static final String PRATICIEN_SERVICE_URL = "http://praticien-service/practiciens/getById/";

    @ApiOperation(value = "Récupérer un dossier médical par ID", notes = "Renvoie un dossier médical correspondant à l'ID spécifié")
    @GetMapping("/getById/{id}")
    public DossierMedical getDossierById(@PathVariable("id") int id) {
        // Récupérer le dossier médical de la base de données
        DossierMedical dossierMedical = dossierMedicalRepository.findById(id).orElse(null);

        // Si le dossier existe, on récupère le patient et le praticien
        if (dossierMedical != null) {
            // Appeler le service Patient pour récupérer les détails du patient
            Patient patient = restTemplate.getForObject(PATIENT_SERVICE_URL + dossierMedical.getPatient().getId(), Patient.class);
            dossierMedical.setPatient(patient);

            // Appeler le service Practicien pour récupérer les détails du praticien
            Practicien praticien = restTemplate.getForObject(PRATICIEN_SERVICE_URL + dossierMedical.getPracticien().getId(), Practicien.class);
            dossierMedical.setPracticien(praticien);
        }

        return dossierMedical;
    }

    @ApiOperation(value = "Lister tous les dossiers médicaux", notes = "Renvoie la liste complète des dossiers médicaux")
    @GetMapping("/getAll")
    public List<DossierMedical> getAllDossiers() {
        return dossierMedicalRepository.findAll();
    }

    @ApiOperation(value = "Ajouter un nouveau dossier médical", notes = "Ajoute un dossier médical dans la base de données")
    @PostMapping("/add")
    public DossierMedical addDossierMedical(@RequestBody DossierMedical dossierMedical) {
        // Appeler les services Patient et Practicien pour récupérer les détails avant d'ajouter
        Patient patient = restTemplate.getForObject(PATIENT_SERVICE_URL + dossierMedical.getPatient().getId(), Patient.class);
        Practicien praticien = restTemplate.getForObject(PRATICIEN_SERVICE_URL + dossierMedical.getPracticien().getId(), Practicien.class);

        dossierMedical.setPatient(patient);
        dossierMedical.setPracticien(praticien);

        return dossierMedicalRepository.save(dossierMedical);
    }

    @ApiOperation(value = "Mettre à jour un dossier médical", notes = "Met à jour un dossier médical existant par ID")
    @PutMapping("/update/{id}")
    public DossierMedical updateDossierMedical(@PathVariable("id") int id, @RequestBody DossierMedical updatedDossierMedical) {
        return dossierMedicalRepository.findById(id).map(existingDossier -> {
            existingDossier.setHistorique(updatedDossierMedical.getHistorique());
            existingDossier.setPatient(updatedDossierMedical.getPatient());
            existingDossier.setPracticien(updatedDossierMedical.getPracticien());
            return dossierMedicalRepository.save(existingDossier);
        }).orElse(null);
    }

    @ApiOperation(value = "Supprimer un dossier médical", notes = "Supprime un dossier médical par ID")
    @DeleteMapping("/delete/{id}")
    public String deleteDossierMedical(@PathVariable("id") int id) {
        if (dossierMedicalRepository.existsById(id)) {
            dossierMedicalRepository.deleteById(id);
            return "Dossier médical supprimé avec succès.";
        } else {
            return "Dossier médical non trouvé.";
        }
    }
}
