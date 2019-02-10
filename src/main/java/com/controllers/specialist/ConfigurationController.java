package com.controllers.specialist;

import com.models.entity.specialist.SpecialistEntity;
import com.models.specialist.ConfigurationGet;
import com.services.specialist.ConfigurationService;
import com.services.specialist.SpecialistService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.text.ParseException;

/*
 * 1. Create Configuration @PostMapping("/doctor-system/specialist/configuration/{dataset_id}") +
 * 2. Delete Configuration @DeleteMapping("/doctor-system/specialist/configuration")
 * 3. Change Configuration @PutMapping("/doctor-system/specialist/dataset/{configuration_id}") +
 * 4. Get Configuration by ID @GetMapping("/doctor-system/specialist/configuration/{configuration_id}") +
 * 5. Get All DataSet Configurations @GetMapping("/doctor-system/specialist/configuration/all/{dataset_id}") +
 * 6. Get All DataSet Configurations Page @GetMapping("/doctor-system/specialist/configuration/all/{dataset_id}/{page}") +
 * 7. Get Specialist All DataSet Configurations @GetMapping("/doctor-system/specialist/configuration/all/{dataset_id}/specialist") +
 * 8. Get Specialist All DataSet Configurations Page @GetMapping("/doctor-system/specialist/configuration/all/{dataset_id}/{page}/specialist") +
 * 9. Activation Configurations @PutMapping("/doctor-system/specialist/configuration/{configuration_id}/activate")
 * */
@Controller()
public class ConfigurationController {

    @Autowired
    ConfigurationService configurationService;

    @Autowired
    SpecialistService specialistService;

    @PostMapping("/doctor-system/specialist/configuration/{dataset_id}")
    public ResponseEntity createDataSetConfiguration(Principal principal,
                                                     @RequestBody ConfigurationGet configurationGet,
                                                     @PathVariable("dataset_id") Long dataSetId) {
        SpecialistEntity specialistEntity = specialistService.findSpecialistByLogin(principal.getName());
        if (specialistEntity == null) {
            return new ResponseEntity(HttpStatus.UNAUTHORIZED);
        }

        Long id = configurationService.createConfiguration(configurationGet, dataSetId, specialistEntity);

        return new ResponseEntity(id, HttpStatus.resolve(201));
    }

    @GetMapping("/doctor-system/specialist/configuration/{configuration_id}")
    public ResponseEntity getDataSetConfigurationByConfigurationId(Principal principal, @PathVariable("configuration_id") Long configurationId) {
        SpecialistEntity specialistEntity = specialistService.findSpecialistByLogin(principal.getName());
        if (specialistEntity == null) {
            return new ResponseEntity(HttpStatus.UNAUTHORIZED);
        }

        return new ResponseEntity(configurationService.getConfigurationById(configurationId), HttpStatus.OK);
    }

    @GetMapping("/doctor-system/specialist/configuration/all/{dataset_id}")
    public ResponseEntity getAllDataSetConfigurationByDataSetId(Principal principal, @PathVariable("dataset_id") Long dataSetId) {
        SpecialistEntity specialistEntity = specialistService.findSpecialistByLogin(principal.getName());
        if (specialistEntity == null) {
            return new ResponseEntity(HttpStatus.UNAUTHORIZED);
        }

        return new ResponseEntity(configurationService.getAllConfigurationsByDataSetId(dataSetId), HttpStatus.OK);
    }

    @GetMapping("/doctor-system/specialist/configuration/all/{dataset_id}/{page}/{objects_on_page}")
    public ResponseEntity getAllDataSetConfigurationByDataSetIdPage(Principal principal,
                                                                    @PathVariable("dataset_id") Long dataSetId,
                                                                    @PathVariable("page") int page,
                                                                    @PathVariable("objects_on_page") int objectsOnPage) {
        SpecialistEntity specialistEntity = specialistService.findSpecialistByLogin(principal.getName());
        if (specialistEntity == null) {
            return new ResponseEntity(HttpStatus.UNAUTHORIZED);
        }

        return new ResponseEntity(
                configurationService.getAllConfigurationsByDataSetIdPage(dataSetId, page, objectsOnPage),
                HttpStatus.OK);
    }

    @GetMapping("/doctor-system/specialist/configuration/all/{dataset_id}/specialist")
    public ResponseEntity getAllSpecialistDataSetConfigurationByDataSetId(Principal principal, @PathVariable("dataset_id") Long dataSetId) {
        SpecialistEntity specialistEntity = specialistService.findSpecialistByLogin(principal.getName());
        if (specialistEntity == null) {
            return new ResponseEntity(HttpStatus.UNAUTHORIZED);
        }

        return new ResponseEntity(configurationService.getAllSpecialistConfigurationsByDataSetId(dataSetId, specialistEntity), HttpStatus.OK);
    }

    @GetMapping("/doctor-system/specialist/configuration/all/{dataset_id}/{page}/{objects_on_page}/specialist")
    public ResponseEntity getAllSpecialistDataSetConfigurationByDataSetIdPage(Principal principal,
                                                                              @PathVariable("dataset_id") Long dataSetId,
                                                                              @PathVariable("page") int page,
                                                                              @PathVariable("objects_on_page") int objectsOnPage) {
        SpecialistEntity specialistEntity = specialistService.findSpecialistByLogin(principal.getName());
        if (specialistEntity == null) {
            return new ResponseEntity(HttpStatus.UNAUTHORIZED);
        }

        return new ResponseEntity(
                configurationService
                        .getAllSpecialistConfigurationsByDataSetIdPage(dataSetId, specialistEntity, page, objectsOnPage),
                HttpStatus.OK);
    }

    @PutMapping("/doctor-system/specialist/configuration/{configuration_id}")
    public ResponseEntity changeDataSetConfiguration(Principal principal,
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

    @PutMapping("/doctor-system/specialist/configuration/{configuration_id}/activate")
    public ResponseEntity activateDataSetConfiguration(Principal principal, @PathVariable("configuration_id") Long configurationId) {
        SpecialistEntity specialistEntity = specialistService.findSpecialistByLogin(principal.getName());
        if (specialistEntity == null) {
            return new ResponseEntity(HttpStatus.UNAUTHORIZED);
        }

        configurationService.activateConfiguration(configurationId, specialistEntity);

        return new ResponseEntity(HttpStatus.OK);
    }

    @GetMapping("/doctor-system/specialist/configuration/svm")
    public ResponseEntity getAllSvmTypes(Principal principal) {
        SpecialistEntity specialistEntity = specialistService.findSpecialistByLogin(principal.getName());
        if (specialistEntity == null) {
            return new ResponseEntity(HttpStatus.UNAUTHORIZED);
        }

        return new ResponseEntity(configurationService.getAllSvmTypes(), HttpStatus.OK);
    }

    @GetMapping("/doctor-system/specialist/configuration/kernel")
    public ResponseEntity getAllKernelTypes(Principal principal) {
        SpecialistEntity specialistEntity = specialistService.findSpecialistByLogin(principal.getName());
        if (specialistEntity == null) {
            return new ResponseEntity(HttpStatus.UNAUTHORIZED);
        }

        return new ResponseEntity(configurationService.getAllKernelTypes(), HttpStatus.OK);
    }
}
