package com.example.servicepracticien.controllers;

import com.example.servicepracticien.models.Practicien;
import com.example.servicepracticien.repositories.PracticienRepository;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/practiciens")
@Api(tags = "Practicien Controller", description = "Endpoints pour gérer les praticiens")
public class ControllerPracticien {

    @Autowired
    private PracticienRepository praticienRepository;

    @ApiOperation(value = "Récupérer un praticien par son nom", notes = "Renvoie un praticien correspondant au nom spécifié")
    @GetMapping("/getByName/{name}")
    public Practicien getPracticienByName(@PathVariable("name") String name) {
        return praticienRepository.findByNameIgnoreCase(name)
                .orElse(null);
    }

    @ApiOperation(value = "Récupérer un praticien par son ID", notes = "Renvoie un praticien correspondant à l'ID spécifié")
    @GetMapping("/getById/{id}")
    public Practicien getPracticienById(@PathVariable("id") int id) {
        return praticienRepository.findById(id)
                .orElse(null);
    }

    @ApiOperation(value = "Lister tous les praticiens", notes = "Renvoie la liste complète des praticiens")
    @GetMapping("/getAll")
    public List<Practicien> getAllPracticiens() {
        return praticienRepository.findAll();
    }

    @ApiOperation(value = "Ajouter un nouveau praticien", notes = "Ajoute un praticien dans la base de données")
    @PostMapping("/add")
    public Practicien addPracticien(@RequestBody Practicien praticien) {
        return praticienRepository.save(praticien);
    }

    @ApiOperation(value = "Mettre à jour un praticien existant", notes = "Met à jour les informations d'un praticien par ID")
    @PutMapping("/update/{id}")
    public Practicien updatePracticien(@PathVariable("id") int id, @RequestBody Practicien updatedPracticien) {
        return praticienRepository.findById(id).map(existingPracticien -> {
            existingPracticien.setName(updatedPracticien.getName());
            return praticienRepository.save(existingPracticien);
        }).orElse(null);
    }

    @ApiOperation(value = "Supprimer un praticien", notes = "Supprime un praticien de la base de données par son ID")
    @DeleteMapping("/delete/{id}")
    public String deletePracticien(@PathVariable("id") int id) {
        if (praticienRepository.existsById(id)) {
            praticienRepository.deleteById(id);
            return "Praticien supprimé avec succès.";
        } else {
            return "Praticien non trouvé.";
        }
    }
}
