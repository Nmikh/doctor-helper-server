package com.controllers.doctor;

import com.models.Doctor;
import com.models.entity.DoctorEntity;
import com.services.DoctorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import java.security.Principal;

@Controller
public class DoctorController {

    @Autowired
    DoctorService doctorService;

    @PostMapping("/doctor_registration")
    public ResponseEntity registrationDoctor(
            @RequestBody Doctor doctor) {
        boolean result = doctorService.registerDoctor(doctor);

        if (result) {
            return new ResponseEntity(HttpStatus.OK);
        }
        return new ResponseEntity(HttpStatus.BAD_REQUEST);
    }

    @GetMapping("/doctor_auth")
    public ResponseEntity authDoctor(Principal principal) {

        DoctorEntity doctor = doctorService.findDoctorByLogin(principal.getName());

        if (doctor != null) {
            return new ResponseEntity(doctor, HttpStatus.OK);
        }

        return new ResponseEntity(HttpStatus.UNAUTHORIZED);
    }
}
