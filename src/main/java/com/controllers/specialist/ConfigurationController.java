package com.controllers.specialist;

import com.models.entity.specialist.SpecialistEntity;
import com.models.specialist.ConfigurationGet;
import com.services.specialist.ConfigurationService;
import com.services.specialist.SpecialistService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;

import java.security.Principal;

/*
 * 1. Create Configuration @PostMapping("/doctor-system/specialist/configuration/{dataset_id}") +
 * 2. Delete Configuration @DeleteMapping("/doctor-system/specialist/configuration")
 * 3. Change Configuration @PutMapping("/doctor-system/specialist/dataset/{configuration_id}")
 * 4. Get Configuration by ID @GetMapping("/doctor-system/specialist/configuration/{configuration_id}")
 * 5. Get All Configurations @GetMapping("/doctor-system/specialist/configuration/all")
 * 6. Get All Configurations Page @GetMapping("/doctor-system/specialist/configuration/all/{page}")
 * 6. Get Specialist All Configurations @GetMapping("/doctor-system/specialist/configuration/all")
 * 7. Get Specialist All Configurations Page @GetMapping("/doctor-system/specialist/configuration/all/{page}")
 * 8. Activation Configurations @PutMapping("/doctor-system/specialist/configuration/{configuration_id}/activate")
 * */
@Controller
public class ConfigurationController {

    @Autowired
    ConfigurationService configurationService;

    @Autowired
    SpecialistService specialistService;

    @PostMapping("/doctor-system/specialist/configuration/{dataset_id}")
    public ResponseEntity createDataSet(Principal principal,
                                        @RequestBody ConfigurationGet configurationGet,
                                        @PathVariable("dataset_id") Long dataSetId) {
        SpecialistEntity specialistEntity = specialistService.findSpecialistByLogin(principal.getName());
        if (specialistEntity == null) {
            return new ResponseEntity(HttpStatus.UNAUTHORIZED);
        }

        Long id = configurationService.createConfiguration(configurationGet, dataSetId, specialistEntity);

        return new ResponseEntity(id, HttpStatus.resolve(201));
    }

    @PutMapping("/doctor-system/specialist/configuration/{configuration_id}")
    public ResponseEntity changeDataSet(Principal principal,
                                        @RequestBody ConfigurationGet configurationGet,
                                        @PathVariable("configuration_id") Long configurationId) {
        SpecialistEntity specialistEntity = specialistService.findSpecialistByLogin(principal.getName());
        if (specialistEntity == null) {
            return new ResponseEntity(HttpStatus.UNAUTHORIZED);
        }

        if (configurationService.changeConfiguration(configurationGet, configurationId, specialistEntity)) {
            return new ResponseEntity(HttpStatus.OK);
        }
        return new ResponseEntity(HttpStatus.BAD_REQUEST);
    }


}
