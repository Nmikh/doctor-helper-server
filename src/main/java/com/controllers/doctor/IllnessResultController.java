package com.controllers.doctor;

import com.models.entity.doctor.DoctorEntity;
import com.services.doctor.DoctorService;
import com.services.doctor.IllnessResultService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import java.security.Principal;

@Controller
public class IllnessResultController {

    @Autowired
    DoctorService doctorService;

    @Autowired
    IllnessResultService illnessResultService;

    @GetMapping("/doctor-system/doctor/illness/datasets")
    public ResponseEntity getAllActiveDataSets(Principal principal) {
        DoctorEntity doctor = doctorService.findDoctorByLogin(principal.getName());

        if (doctor == null) {
            return new ResponseEntity(HttpStatus.UNAUTHORIZED);
        }

        return new ResponseEntity(illnessResultService.getAllWorkDataSets(), HttpStatus.OK);
    }
}


