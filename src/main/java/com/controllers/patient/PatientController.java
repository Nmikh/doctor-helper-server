package com.controllers.patient;

import com.models.entity.DoctorEntity;
import com.models.entity.PatientEntity;
import com.services.DoctorService;
import com.services.PatientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import java.security.Principal;
import java.util.List;

@Controller
public class PatientController {

    @Autowired
    PatientService patientService;

    @Autowired
    DoctorService doctorService;

    @PostMapping("/create_patient")
    public ResponseEntity createPatient(Principal principal, @RequestBody PatientEntity patient) {
        DoctorEntity doctor = doctorService.findDoctorByLogin(principal.getName());

        if (doctor == null) {
            return new ResponseEntity(doctor, HttpStatus.UNAUTHORIZED);
        }

        Long patientId = patientService.createPatient(patient);

        return new ResponseEntity(patientId, HttpStatus.resolve(201));
    }

    @GetMapping("/findPatientLikeNameSurname")
    public ResponseEntity findPatientByNameAndSurnameLike(Principal principal,
                                                          @PathVariable("name") String name,
                                                          @PathVariable("surname") String surname) {
        DoctorEntity doctor = doctorService.findDoctorByLogin(principal.getName());

        if (doctor == null) {
            return new ResponseEntity(doctor, HttpStatus.UNAUTHORIZED);
        }

        List<PatientEntity> patientByNameAndSurnameLike = patientService.getPatientByNameAndSurnameLike(name, surname);

        return new ResponseEntity(patientByNameAndSurnameLike, HttpStatus.OK);
    }
}
