package com.controllers.specialist;

import com.models.Specialist;
import com.models.entity.specialist.SpecialistEntity;
import com.services.specialist.SpecialistService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;

import java.security.Principal;

@Controller
public class SpecialistController {

    @Autowired
    SpecialistService specialistService;

    @PostMapping("/doctor-system/specialist/registration")
    public ResponseEntity registrationSpecialist(
            @RequestBody Specialist specialist) {
        boolean result = specialistService.registerSpecialist(specialist);

        if (result) {
            return new ResponseEntity(HttpStatus.resolve(201));
        }
        return new ResponseEntity(HttpStatus.BAD_REQUEST);
    }

    @GetMapping("/doctor-system/specialist/authorization")
    public ResponseEntity authSpecialist(Principal principal) {

        SpecialistEntity specialist = specialistService.findSpecialistByLogin(principal.getName());

        if (specialist != null) {
            return new ResponseEntity(specialist, HttpStatus.OK);
        }

        return new ResponseEntity(HttpStatus.UNAUTHORIZED);
    }

    @PutMapping("/doctor-system/specialist/psw")
    public ResponseEntity changePassword(Principal principal, @RequestBody String password) {

        SpecialistEntity specialist = specialistService.findSpecialistByLogin(principal.getName());

        if (specialist != null) {
            specialistService.changePassword(specialist, password);

            return new ResponseEntity(HttpStatus.OK);
        }

        return new ResponseEntity(HttpStatus.UNAUTHORIZED);
    }

    @PutMapping("/doctor-system/specialist")
    public ResponseEntity changeSpecialist(Principal principal, @RequestBody SpecialistEntity specialistEntity) {

        SpecialistEntity specialist = specialistService.findSpecialistByLogin(principal.getName());

        if (specialist != null) {
            specialistService.changeSpecialist(specialist, specialistEntity);

            return new ResponseEntity(HttpStatus.OK);
        }

        return new ResponseEntity(HttpStatus.UNAUTHORIZED);
    }
}
