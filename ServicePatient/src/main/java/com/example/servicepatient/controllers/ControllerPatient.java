package com.example.servicepatient.controllers;

import com.example.servicepatient.controllers.models.Patient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController
public class ControllerPatient {

    List<Patient> patients = new ArrayList<Patient>();
    public ControllerPatient() {
        patients.add(new Patient(1,"Lucas"));
        patients.add(new Patient(2,"Michael"));
        patients.add(new Patient(3,"Antoine"));
        patients.add(new Patient(4,"Thomas"));

    }

    @GetMapping("/getPatientByName/{name}")
    public Patient getPatientByName(@PathVariable("name") String name) {
        return patients.stream()
                .filter(patient -> patient.getName().equalsIgnoreCase(name))
                .findFirst()
                .orElse(null);
    }

    @GetMapping("/getPatientById/{id}")
    public Patient getPatientById(@PathVariable("id") int id) {
        return patients.stream()
                .filter(patient -> patient.getId() == id)
                .findFirst()
                .orElse(null);
    }


}
