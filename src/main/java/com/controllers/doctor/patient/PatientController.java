package com.controllers.doctor.patient;

import com.models.PatientPageReturn;
import com.models.entity.doctor.DoctorEntity;
import com.models.entity.doctor.PatientEntity;
import com.services.doctor.DoctorService;
import com.services.doctor.PatientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.List;

@Controller
public class PatientController {

    @Autowired
    PatientService patientService;

    @Autowired
    DoctorService doctorService;

    @PostMapping("/doctor-system/doctor/patient")
    public ResponseEntity createPatient(Principal principal, @RequestBody PatientEntity patient) {
        DoctorEntity doctor = doctorService.findDoctorByLogin(principal.getName());

        if (doctor == null) {
            return new ResponseEntity(HttpStatus.UNAUTHORIZED);
        }

        Long patientId = patientService.createPatient(patient);

        return new ResponseEntity(patientId, HttpStatus.resolve(201));
    }

    @GetMapping("/doctor-system/doctor/patient/{patient_id}")
    public ResponseEntity findPatientByID(Principal principal, @PathVariable("patient_id") Long patientId) {
        DoctorEntity doctor = doctorService.findDoctorByLogin(principal.getName());

        if (doctor == null) {
            return new ResponseEntity(HttpStatus.UNAUTHORIZED);
        }

        return new ResponseEntity(patientService.getPatient(patientId), HttpStatus.OK);
    }

    @GetMapping("/doctor-system/doctor/patient/params")
    public ResponseEntity findPatientByNameAndSurnameLike(Principal principal,
                                                          @RequestParam("name") String name,
                                                          @RequestParam("surname") String surname) {
        DoctorEntity doctor = doctorService.findDoctorByLogin(principal.getName());

        if (doctor == null) {
            return new ResponseEntity(HttpStatus.UNAUTHORIZED);
        }

        List<PatientEntity> patientByNameAndSurnameLike = patientService.getPatientByNameAndSurnameLike(name, surname);

        return new ResponseEntity(patientByNameAndSurnameLike, HttpStatus.OK);
    }

    @GetMapping("/doctor-system/doctor/patient/params/{page}")
    public ResponseEntity findPatientsPageByNameAndSurnameLike(Principal principal, @PathVariable("page") int page,
                                                               @RequestParam("name") String name,
                                                               @RequestParam("surname") String surname) {
        DoctorEntity doctor = doctorService.findDoctorByLogin(principal.getName());

        if (doctor == null) {
            return new ResponseEntity(HttpStatus.UNAUTHORIZED);
        }

        PatientPageReturn patients = patientService.getPatientsPageByNameAndSurnameLike(name, surname, page);

        return new ResponseEntity(patients, HttpStatus.OK);
    }

    @GetMapping("/doctor-system/doctor/patient/all")
    public ResponseEntity getAllPatients(Principal principal) {
        DoctorEntity doctor = doctorService.findDoctorByLogin(principal.getName());

        if (doctor == null) {
            return new ResponseEntity(HttpStatus.UNAUTHORIZED);
        }

        List<PatientEntity> patientByNameAndSurnameLike = patientService.getAllPatients();

        return new ResponseEntity(patientByNameAndSurnameLike, HttpStatus.OK);
    }

    @GetMapping("/doctor-system/doctor/patient/all/{page}")
    public ResponseEntity getAllPatientsPage(Principal principal, @PathVariable("page") int page) {
        DoctorEntity doctor = doctorService.findDoctorByLogin(principal.getName());

        if (doctor == null) {
            return new ResponseEntity(HttpStatus.UNAUTHORIZED);
        }

        return new ResponseEntity(patientService.getPageOfPatients(page), HttpStatus.OK);
    }

    @PutMapping("/doctor-system/doctor/patient/{patient_id}")
    public ResponseEntity changePatient(Principal principal,
                                        @RequestBody PatientEntity patientEntity,
                                        @PathVariable("patient_id") Long patientId) {
        DoctorEntity doctor = doctorService.findDoctorByLogin(principal.getName());

        if (doctor == null) {
            return new ResponseEntity(HttpStatus.UNAUTHORIZED);
        }

        patientService.changePatient(patientId, patientEntity);

        return new ResponseEntity(HttpStatus.OK);
    }
}
