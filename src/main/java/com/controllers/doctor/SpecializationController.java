package com.controllers.doctor;

import com.services.doctor.SpecializationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class SpecializationController {

    @Autowired
    SpecializationService specializationService;

    @GetMapping("/doctor-system/doctor/specializations")
    public ResponseEntity authDoctor() {
        return new ResponseEntity(specializationService.getAllSpecializations(), HttpStatus.OK);
    }
}
