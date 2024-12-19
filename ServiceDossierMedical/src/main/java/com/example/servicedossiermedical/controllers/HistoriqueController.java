package com.example.servicedossiermedical.controllers;

import com.example.servicedossiermedical.models.DossierMedical;
import com.example.servicedossiermedical.repositories.DossierMedicalRepository;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/historiques")
@Api(tags = "Historique Controller", description = "Endpoints pour gérer les historiques des dossiers médicaux")
public class HistoriqueController {

    @Autowired
    private DossierMedicalRepository dossierMedicalRepository;

    @ApiOperation(value = "Ajouter un élément à l'historique d'un dossier médical", notes = "Ajoute un nouvel élément dans l'historique d'un dossier médical")
    @PostMapping("/add/{dossierId}")
    public DossierMedical addHistorique(@PathVariable("dossierId") int dossierId, @RequestBody String historiqueElement) {
        Optional<DossierMedical> dossierOptional = dossierMedicalRepository.findById(dossierId);
        if (dossierOptional.isPresent()) {
            DossierMedical dossierMedical = dossierOptional.get();
            dossierMedical.getHistorique().add(historiqueElement);
            return dossierMedicalRepository.save(dossierMedical);
        } else {
            return null; // Dossier médical non trouvé
        }
    }

    @ApiOperation(value = "Récupérer l'historique d'un dossier médical", notes = "Récupère l'historique d'un dossier médical par son ID")
    @GetMapping("/get/{dossierId}")
    public List<String> getHistorique(@PathVariable("dossierId") int dossierId) {
        Optional<DossierMedical> dossierOptional = dossierMedicalRepository.findById(dossierId);
        if (dossierOptional.isPresent()) {
            return dossierOptional.get().getHistorique();
        } else {
            return null; // Dossier médical non trouvé
        }
    }

    @ApiOperation(value = "Mettre à jour un élément de l'historique", notes = "Met à jour un élément spécifique de l'historique dans un dossier médical")
    @PutMapping("/update/{dossierId}/{index}")
    public DossierMedical updateHistorique(@PathVariable("dossierId") int dossierId, @PathVariable("index") int index, @RequestBody String updatedHistoriqueElement) {
        Optional<DossierMedical> dossierOptional = dossierMedicalRepository.findById(dossierId);
        if (dossierOptional.isPresent()) {
            DossierMedical dossierMedical = dossierOptional.get();
            List<String> historique = dossierMedical.getHistorique();
            if (index >= 0 && index < historique.size()) {
                historique.set(index, updatedHistoriqueElement);
                return dossierMedicalRepository.save(dossierMedical);
            } else {
                return null; // Index non valide
            }
        } else {
            return null; // Dossier médical non trouvé
        }
    }

    @ApiOperation(value = "Supprimer un élément de l'historique", notes = "Supprime un élément spécifique de l'historique d'un dossier médical")
    @DeleteMapping("/delete/{dossierId}/{index}")
    public DossierMedical deleteHistorique(@PathVariable("dossierId") int dossierId, @PathVariable("index") int index) {
        Optional<DossierMedical> dossierOptional = dossierMedicalRepository.findById(dossierId);
        if (dossierOptional.isPresent()) {
            DossierMedical dossierMedical = dossierOptional.get();
            List<String> historique = dossierMedical.getHistorique();
            if (index >= 0 && index < historique.size()) {
                historique.remove(index);
                return dossierMedicalRepository.save(dossierMedical);
            } else {
                return null; // Index non valide
            }
        } else {
            return null; // Dossier médical non trouvé
        }
    }
}
