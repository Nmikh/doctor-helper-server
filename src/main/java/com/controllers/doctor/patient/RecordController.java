package com.controllers.doctor.patient;

import com.models.entity.doctor.DoctorEntity;
import com.models.entity.doctor.RecordEntity;
import com.services.DoctorService;
import com.services.RecordService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;

@Controller
public class RecordController {

    @Autowired
    RecordService recordService;

    @Autowired
    DoctorService doctorService;

    @PutMapping("doctor-system/doctor/record/{patient_id}")
    public ResponseEntity changeRecord(Principal principal,
                                       @PathVariable("patient_id") Long patientId,
                                       @RequestBody RecordEntity recordEntity) {
        DoctorEntity doctor = doctorService.findDoctorByLogin(principal.getName());

        if (doctor == null) {
            return new ResponseEntity(HttpStatus.UNAUTHORIZED);
        }

        recordService.createRecord(patientId, recordEntity);

        return new ResponseEntity(HttpStatus.resolve(200));
    }

    @GetMapping("doctor-system/doctor/record/{patient_id}")
    public ResponseEntity getRecord(Principal principal, @PathVariable("patient_id") Long patientId) {
        DoctorEntity doctor = doctorService.findDoctorByLogin(principal.getName());

        if (doctor == null) {
            return new ResponseEntity(HttpStatus.UNAUTHORIZED);
        }

        return new ResponseEntity(recordService.findRecordByPatientId(patientId), HttpStatus.resolve(200));
    }
}
