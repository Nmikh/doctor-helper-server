package com.controllers.doctor;

import com.models.doctor.Doctor;
import com.models.entity.doctor.DoctorEntity;
import com.services.doctor.DoctorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;

@Controller
public class DoctorController {

    @Autowired
    DoctorService doctorService;

    @PostMapping("/doctor-system/doctor/registration/{specializationId}")
    public ResponseEntity registrationDoctor(
            @RequestBody Doctor doctor,
            @PathVariable("specializationId") Long specializationId) {
        boolean result = doctorService.registerDoctor(doctor, specializationId);

        if (result) {
            return new ResponseEntity(HttpStatus.resolve(201));
        }
        return new ResponseEntity(HttpStatus.BAD_REQUEST);
    }

    @GetMapping("/doctor-system/doctor/authorization")
    public ResponseEntity authDoctor(Principal principal) {

        DoctorEntity doctor = doctorService.findDoctorByLogin(principal.getName());

        if (doctor != null) {
            return new ResponseEntity(doctor, HttpStatus.OK);
        }

        return new ResponseEntity(HttpStatus.UNAUTHORIZED);
    }

    @PutMapping("/doctor-system/doctor/psw")
    public ResponseEntity changeDoctorPassword(Principal principal, @RequestBody String password) {

        DoctorEntity doctor = doctorService.findDoctorByLogin(principal.getName());

        if (doctor != null) {
            doctorService.changePassword(doctor, password);

            return new ResponseEntity(doctor, HttpStatus.OK);
        }

        return new ResponseEntity(HttpStatus.UNAUTHORIZED);
    }

    @PutMapping("/doctor-system/doctor/{specializationId}")
    public ResponseEntity changeDoctorPassword(Principal principal,
                                               @RequestBody DoctorEntity doctorEntity,
                                               @PathVariable("specializationId") Long specializationId) {

        DoctorEntity doctor = doctorService.findDoctorByLogin(principal.getName());

        if (doctor != null) {
            doctorService.changeDoctor(doctor, doctorEntity, specializationId);

            return new ResponseEntity(HttpStatus.OK);
        }

        return new ResponseEntity(HttpStatus.UNAUTHORIZED);
    }
}
