package com.controllers.doctor.patient;

import com.models.entity.doctor.DoctorEntity;
import com.models.entity.doctor.PageEntity;
import com.services.doctor.DoctorService;
import com.services.doctor.PageService;
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

    @PostMapping("/doctor-system/doctor/page/{patient_id}")
    public ResponseEntity createPage(Principal principal,
                                     @PathVariable("patient_id") Long patientId,
                                     @RequestBody PageEntity pageEntity) {
        DoctorEntity doctor = doctorService.findDoctorByLogin(principal.getName());

        if (doctor == null) {
            return new ResponseEntity(HttpStatus.UNAUTHORIZED);
        }

        Long page = pageService.createPage(patientId, pageEntity, doctor);

        return new ResponseEntity(page, HttpStatus.resolve(201));
    }

    @GetMapping("/doctor-system/doctor/page/{page_id}")
    public ResponseEntity getPage(Principal principal, @PathVariable("page_id") Long pageId) {
        DoctorEntity doctor = doctorService.findDoctorByLogin(principal.getName());

        if (doctor == null) {
            return new ResponseEntity(HttpStatus.UNAUTHORIZED);
        }

        return new ResponseEntity(pageService.readPage(pageId), HttpStatus.OK);
    }

    @GetMapping("/doctor-system/doctor/page/{patient_id}/all")
    public ResponseEntity getAllPages(Principal principal, @PathVariable("patient_id") Long patientId) {
        DoctorEntity doctor = doctorService.findDoctorByLogin(principal.getName());

        if (doctor == null) {
            return new ResponseEntity(HttpStatus.UNAUTHORIZED);
        }

        return new ResponseEntity(pageService.getAllPages(patientId), HttpStatus.OK);
    }

    @GetMapping("/doctor-system/doctor/page/{patient_id}/all/{page}")
    public ResponseEntity getAllPagesPage(Principal principal,
                                          @PathVariable("patient_id") Long patientId,
                                          @PathVariable("page") int page) {
        DoctorEntity doctor = doctorService.findDoctorByLogin(principal.getName());

        if (doctor == null) {
            return new ResponseEntity(HttpStatus.UNAUTHORIZED);
        }

        return new ResponseEntity(pageService.getAllPagesPage(patientId, page), HttpStatus.OK);
    }


    @PutMapping("/doctor-system/doctor/page/{page_id}")
    public ResponseEntity updatePage(Principal principal,
                                     @PathVariable("page_id") Long pageId,
                                     @RequestBody PageEntity pageEntity) {
        DoctorEntity doctor = doctorService.findDoctorByLogin(principal.getName());

        if (doctor == null) {
            return new ResponseEntity(HttpStatus.UNAUTHORIZED);
        }

        boolean answer = pageService.updatePage(pageId, pageEntity, doctor);

        if (answer) {
            return new ResponseEntity(HttpStatus.OK);
        }
        return new ResponseEntity(HttpStatus.BAD_REQUEST);
    }

    @DeleteMapping("/doctor-system/doctor/page/{page_id}")
    public ResponseEntity deletePage(Principal principal, @PathVariable("page_id") Long pageId) {
        DoctorEntity doctor = doctorService.findDoctorByLogin(principal.getName());

        if (doctor == null) {
            return new ResponseEntity(HttpStatus.UNAUTHORIZED);
        }

        boolean answer = pageService.deletePage(pageId, doctor);

        if (answer) {
            return new ResponseEntity(HttpStatus.OK);
        }
        return new ResponseEntity(HttpStatus.BAD_REQUEST);
    }
}
