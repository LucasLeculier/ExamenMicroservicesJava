package com.example.servicepatient.controllers;

import com.example.servicepatient.models.Patient;
import com.example.servicepatient.repositories.PatientRepository;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/patients")
@Api(value = "Patient Controller", description = "Endpoints pour gérer les patients")
public class ControllerPatient {

    @Autowired
    private PatientRepository patientRepository;

    @ApiOperation(value = "Récupérer un patient par son nom", notes = "Renvoie un patient correspondant au nom spécifié")
    @GetMapping("/getByName/{name}")
    public Patient getPatientByName(
            @ApiParam(value = "Nom du patient", required = true) @PathVariable("name") String name) {
        return patientRepository.findByNameIgnoreCase(name)
                .orElse(null);
    }

    @ApiOperation(value = "Récupérer un patient par son ID", notes = "Renvoie un patient correspondant à l'ID spécifié")
    @GetMapping("/getById/{id}")
    public Patient getPatientById(
            @ApiParam(value = "ID du patient", required = true) @PathVariable("id") int id) {
        return patientRepository.findById(id)
                .orElse(null);
    }

    @ApiOperation(value = "Lister tous les patients", notes = "Renvoie la liste complète des patients")
    @GetMapping("/getAll")
    public List<Patient> getAllPatients() {
        return patientRepository.findAll();
    }

    @ApiOperation(value = "Ajouter un nouveau patient", notes = "Ajoute un patient dans la base de données")
    @PostMapping("/add")
    public Patient addPatient(
            @ApiParam(value = "Patient à ajouter", required = true) @RequestBody Patient patient) {
        return patientRepository.save(patient);
    }

    @ApiOperation(value = "Mettre à jour un patient existant", notes = "Met à jour les informations d'un patient par ID")
    @PutMapping("/update/{id}")
    public Patient updatePatient(
            @ApiParam(value = "ID du patient", required = true) @PathVariable("id") int id,
            @ApiParam(value = "Patient mis à jour", required = true) @RequestBody Patient updatedPatient) {
        return patientRepository.findById(id).map(existingPatient -> {
            existingPatient.setName(updatedPatient.getName());
            return patientRepository.save(existingPatient);
        }).orElse(null);
    }

    @ApiOperation(value = "Supprimer un patient", notes = "Supprime un patient de la base de données par son ID")
    @DeleteMapping("/delete/{id}")
    public String deletePatient(@ApiParam(value = "ID du patient à supprimer", required = true) @PathVariable("id") int id) {
        if (patientRepository.existsById(id)) {
            patientRepository.deleteById(id);
            return "Patient supprimé avec succès.";
        } else {
            return "Patient non trouvé.";
        }
    }
}
