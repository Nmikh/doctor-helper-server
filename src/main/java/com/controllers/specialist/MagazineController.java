package com.controllers.specialist;

import com.models.entity.specialist.SpecialistEntity;
import com.services.specialist.MagazineService;
import com.services.specialist.SpecialistService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.security.Principal;

@Controller
public class MagazineController {
    @Autowired
    MagazineService magazineService;

    @Autowired
    SpecialistService specialistService;

    @GetMapping("/doctor-system/specialist/magazine/{dataset_id}")
    public ResponseEntity getMagazineByDataSetId(Principal principal, @PathVariable("dataset_id") Long dataSetId) {
        SpecialistEntity specialistEntity = specialistService.findSpecialistByLogin(principal.getName());
        if (specialistEntity == null) {
            return new ResponseEntity(HttpStatus.UNAUTHORIZED);
        }

        return new ResponseEntity(magazineService.getMagazineByDataSetId(dataSetId), HttpStatus.OK);
    }
}
