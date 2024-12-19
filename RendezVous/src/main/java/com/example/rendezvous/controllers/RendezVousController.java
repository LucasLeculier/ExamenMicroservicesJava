package com.example.rendezvous.controllers;

import com.example.rendezvous.models.RendezVous;
import com.example.rendezvous.repositories.RendezVousRepository;
import com.example.rendezvous.services.GoogleCalendarRetryService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;

@RestController
@RequestMapping("/rendezvous")
@Api(tags = "RendezVous Controller", description = "Endpoints pour gérer les rendez-vous")
public class RendezVousController {

    @Autowired
    private RendezVousRepository rendezVousRepository;

    @Autowired
    private GoogleCalendarRetryService googleCalendarRetryService;

    @ApiOperation(value = "Ajouter un nouveau rendez-vous et l'ajouter au Google Calendar", notes = "Ajoute un rendez-vous et tente de l'ajouter au Google Calendar avec une logique de retry.")
    @PostMapping("/addToCalendar")
    public String addRendezVousToCalendar(@RequestParam("summary") String summary,
                                          @RequestParam("location") String location,
                                          @RequestParam("description") String description,
                                          @RequestParam("startTime") String startTime,
                                          @RequestParam("endTime") String endTime) {
        try {
            googleCalendarRetryService.createEventWithRetry(summary, location, description, startTime, endTime);
            return "Rendez-vous ajouté au calendrier Google avec succès.";
        } catch (RuntimeException e) {
            return "Échec de l'ajout au calendrier Google après plusieurs tentatives.";
        }
    }

    @ApiOperation(value = "Récupérer un rendez-vous par ID", notes = "Renvoie un rendez-vous correspondant à l'ID spécifié")
    @GetMapping("/getById/{id}")
    public RendezVous getRendezVousById(@PathVariable("id") int id) {
        return rendezVousRepository.findById(id).orElse(null);
    }

    @ApiOperation(value = "Lister tous les rendez-vous", notes = "Renvoie la liste complète des rendez-vous")
    @GetMapping("/getAll")
    public List<RendezVous> getAllRendezVous() {
        return rendezVousRepository.findAll();
    }

    @ApiOperation(value = "Lister les rendez-vous d'un patient", notes = "Renvoie la liste des rendez-vous d'un patient spécifié")
    @GetMapping("/getByPatient/{patientId}")
    public List<RendezVous> getRendezVousByPatient(@PathVariable("patientId") int patientId) {
        return rendezVousRepository.findByPatientId(patientId);
    }

    @ApiOperation(value = "Lister les rendez-vous d'un praticien", notes = "Renvoie la liste des rendez-vous d'un praticien spécifié")
    @GetMapping("/getByPracticien/{praticienId}")
    public List<RendezVous> getRendezVousByPracticien(@PathVariable("praticienId") int praticienId) {
        return rendezVousRepository.findByPracticienId(praticienId);
    }

    @ApiOperation(value = "Lister les rendez-vous après une date spécifique", notes = "Renvoie la liste des rendez-vous programmés après une date donnée")
    @GetMapping("/getByDateAfter")
    public List<RendezVous> getRendezVousByDateAfter(@RequestParam("date") LocalDateTime date) {
        return rendezVousRepository.findByDateAfter(date);
    }

    @ApiOperation(value = "Ajouter un nouveau rendez-vous", notes = "Ajoute un rendez-vous dans la base de données")
    @PostMapping("/add")
    public RendezVous addRendezVous(@RequestBody RendezVous rendezVous) {
        return rendezVousRepository.save(rendezVous);
    }

    @ApiOperation(value = "Mettre à jour un rendez-vous", notes = "Met à jour un rendez-vous existant par ID")
    @PutMapping("/update/{id}")
    public RendezVous updateRendezVous(@PathVariable("id") int id, @RequestBody RendezVous updatedRendezVous) {
        return rendezVousRepository.findById(id).map(existingRendezVous -> {
            existingRendezVous.setPatient(updatedRendezVous.getPatient());
            existingRendezVous.setPracticien(updatedRendezVous.getPracticien());
            existingRendezVous.setDate(updatedRendezVous.getDate());
            return rendezVousRepository.save(existingRendezVous);
        }).orElse(null);
    }

    @ApiOperation(value = "Supprimer un rendez-vous", notes = "Supprime un rendez-vous par ID")
    @DeleteMapping("/delete/{id}")
    public String deleteRendezVous(@PathVariable("id") int id) {
        if (rendezVousRepository.existsById(id)) {
            rendezVousRepository.deleteById(id);
            return "Rendez-vous supprimé avec succès.";
        } else {
            return "Rendez-vous non trouvé.";
        }
    }

    @ApiOperation(value = "Lister les rendez-vous entre deux dates spécifiques", notes = "Renvoie la liste des rendez-vous entre deux dates données")
    @GetMapping("/getByDateBetween")
    public List<RendezVous> getRendezVousByDateBetween(@RequestParam("startDate") LocalDateTime startDate,
                                                       @RequestParam("endDate") LocalDateTime endDate) {
        return rendezVousRepository.findByDateBetween(startDate, endDate);
    }
}
