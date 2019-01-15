package com.controllers.doctor.patient;

import com.models.entity.DoctorEntity;
import com.models.entity.PageEntity;
import com.services.DoctorService;
import com.services.PageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import java.security.Principal;

@Controller
public class PageController {

    @Autowired
    PageService pageService;

    @Autowired
    DoctorService doctorService;

    @PostMapping("/create_page/{record_id}")
    public ResponseEntity createPage(Principal principal,
                                        @PathVariable("record_id") Long recordId,
                                        @RequestBody PageEntity pageEntity) {
        DoctorEntity doctor = doctorService.findDoctorByLogin(principal.getName());

        if (doctor == null) {
            return new ResponseEntity(doctor, HttpStatus.UNAUTHORIZED);
        }

        Long page = pageService.createPage(recordId, pageEntity, doctor);

        return new ResponseEntity(page, HttpStatus.resolve(201));
    }
}
