package com.controllers.doctor.patient;

import com.models.entity.DoctorEntity;
import com.models.entity.PageEntity;
import com.services.DoctorService;
import com.services.PageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;

@Controller
public class PageController {

    @Autowired
    PageService pageService;

    @Autowired
    DoctorService doctorService;

    @PostMapping("/create_page/{patient_id}")
    public ResponseEntity createPage(Principal principal,
                                     @PathVariable("patient_id") Long patientId,
                                     @RequestBody PageEntity pageEntity) {
        DoctorEntity doctor = doctorService.findDoctorByLogin(principal.getName());

        if (doctor == null) {
            return new ResponseEntity(doctor, HttpStatus.UNAUTHORIZED);
        }

        Long page = pageService.createPage(patientId, pageEntity, doctor);

        return new ResponseEntity(page, HttpStatus.resolve(201));
    }

    @GetMapping("/read_page/{page_id}")
    public ResponseEntity readPage(Principal principal, @PathVariable("page_id") Long pageId) {
        DoctorEntity doctor = doctorService.findDoctorByLogin(principal.getName());

        if (doctor == null) {
            return new ResponseEntity(doctor, HttpStatus.UNAUTHORIZED);
        }

        return new ResponseEntity(pageService.readPage(pageId), HttpStatus.OK);
    }

    @PostMapping("/update_page/{page_id}")
    public ResponseEntity updatePage(Principal principal,
                                     @PathVariable("page_id") Long pageId,
                                     @RequestBody PageEntity pageEntity) {
        DoctorEntity doctor = doctorService.findDoctorByLogin(principal.getName());

        if (doctor == null) {
            return new ResponseEntity(doctor, HttpStatus.UNAUTHORIZED);
        }

        boolean answer = pageService.updatePage(pageId, pageEntity, doctor);

        if (answer) {
            return new ResponseEntity(HttpStatus.OK);
        }
        return new ResponseEntity(HttpStatus.BAD_REQUEST);
    }

    @DeleteMapping("/delete_page/{page_id}")
    public ResponseEntity deletePage(Principal principal, @PathVariable("page_id") Long pageId) {
        DoctorEntity doctor = doctorService.findDoctorByLogin(principal.getName());

        if (doctor == null) {
            return new ResponseEntity(doctor, HttpStatus.UNAUTHORIZED);
        }

        boolean answer = pageService.deletePage(pageId, doctor);

        if (answer) {
            return new ResponseEntity(HttpStatus.OK);
        }
        return new ResponseEntity(HttpStatus.BAD_REQUEST);
    }
}
