package com.controllers.doctor.patient;

import com.models.entity.DoctorEntity;
import com.models.entity.RecordEntity;
import com.services.DoctorService;
import com.services.RecordService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import java.security.Principal;

@Controller
public class RecordController {

    @Autowired
    RecordService recordService;

    @Autowired
    DoctorService doctorService;

    @PostMapping("/change_record/{patient_id}")
    public ResponseEntity createPatient(Principal principal,
                                        @PathVariable("patient_id") Long patientId,
                                        @RequestBody RecordEntity recordEntity) {
        DoctorEntity doctor = doctorService.findDoctorByLogin(principal.getName());

        if (doctor == null) {
            return new ResponseEntity(HttpStatus.UNAUTHORIZED);
        }

        Long record = recordService.createRecord(patientId, recordEntity);

        return new ResponseEntity(record, HttpStatus.resolve(201));
    }
}
