package com.controllers.specialist;

import com.models.Specialist;
import com.services.SpecialistService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@Controller
public class SpecialistController {

    @Autowired
    SpecialistService specialistService;

    @PostMapping("/specialist_registration")
    public ResponseEntity registrationSpecialist(
            @RequestBody Specialist specialist) {
        boolean result = specialistService.registerSpecialist(specialist);

        if (result) {
            return new ResponseEntity( HttpStatus.OK);
        }
        return new ResponseEntity( HttpStatus.BAD_REQUEST);
    }
}
