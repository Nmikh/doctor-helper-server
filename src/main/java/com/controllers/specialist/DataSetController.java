package com.controllers.specialist;

import com.models.entity.specialist.DatasetEntity;
import com.models.entity.specialist.SpecialistEntity;
import com.services.specialist.DataSetService;
import com.services.specialist.SpecialistService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;

/*
 * 1. Create dataset @PostMapping("/doctor-system/specialist/dataset") +
 * 2. Delete Dataset @DeleteMapping("/doctor-system/specialist/dataset/{dataset_id}")
 * 3. Change Dataset @PutMapping("/doctor-system/specialist/dataset/{dataset_id}") +
 * 4. Get Dataset by ID @GetMapping("/doctor-system/specialist/dataset/{dataset_id}") +
 * 5. Get All Datasets @GetMapping("/doctor-system/specialist/dataset/all") +
 * 6. Get All Datasets Page @GetMapping("/doctor-system/specialist/dataset/all/{page}") +
 * 6. Get Specialist All Datasets @GetMapping("/doctor-system/specialist/dataset/all") +
 * 7. Get Specialist All Datasets Page @GetMapping("/doctor-system/specialist/dataset/all/{page} +
 * 8. Activation Dataset @PutMapping("/doctor-system/specialist/dataset/{dataset_id}/activate +
 * */
@Controller
public class DataSetController {

    @Autowired
    SpecialistService specialistService;

    @Autowired
    DataSetService dataSetService;

    @PostMapping("/doctor-system/specialist/dataset")
    public ResponseEntity createDataSet(Principal principal, @RequestBody DatasetEntity datasetEntity) {
        SpecialistEntity specialistEntity = specialistService.findSpecialistByLogin(principal.getName());
        if (specialistEntity == null) {
            return new ResponseEntity(HttpStatus.UNAUTHORIZED);
        }

        Long dataSetId = dataSetService.createDataSet(datasetEntity, specialistEntity);

        return new ResponseEntity(dataSetId, HttpStatus.resolve(201));
    }

    @GetMapping("/doctor-system/specialist/dataset/{dataset_id}")
    public ResponseEntity getDataSetById(Principal principal,
                                         @PathVariable("dataset_id") Long dataSetId) {
        SpecialistEntity specialistEntity = specialistService.findSpecialistByLogin(principal.getName());
        if (specialistEntity == null) {
            return new ResponseEntity(HttpStatus.UNAUTHORIZED);
        }

        return new ResponseEntity(dataSetService.getDataSetById(dataSetId), HttpStatus.OK);
    }

    @GetMapping("/doctor-system/specialist/dataset/all")
    public ResponseEntity getDataSetAll(Principal principal) {
        SpecialistEntity specialistEntity = specialistService.findSpecialistByLogin(principal.getName());
        if (specialistEntity == null) {
            return new ResponseEntity(HttpStatus.UNAUTHORIZED);
        }

        return new ResponseEntity(dataSetService.getDataSetAll(), HttpStatus.OK);
    }

    @GetMapping("/doctor-system/specialist/dataset/all/{page}/{objects_on_page}")
    public ResponseEntity getDataSetAllPage(Principal principal,
                                            @PathVariable("page") int page,
                                            @PathVariable("objects_on_page") int objectsOnPage) {
        SpecialistEntity specialistEntity = specialistService.findSpecialistByLogin(principal.getName());
        if (specialistEntity == null) {
            return new ResponseEntity(HttpStatus.UNAUTHORIZED);
        }

        return new ResponseEntity(
                dataSetService.getDataSetAllPage(page, objectsOnPage),
                HttpStatus.OK);
    }

    @GetMapping("/doctor-system/specialist/dataset/all/specialist")
    public ResponseEntity getSpecialistDataSetAll(Principal principal) {
        SpecialistEntity specialistEntity = specialistService.findSpecialistByLogin(principal.getName());
        if (specialistEntity == null) {
            return new ResponseEntity(HttpStatus.UNAUTHORIZED);
        }

        return new ResponseEntity(dataSetService.getSpecialistDataSetAll(specialistEntity), HttpStatus.OK);
    }

    @GetMapping("/doctor-system/specialist/dataset/all/{page}/{objects_on_page}/specialist")
    public ResponseEntity getSpecialistDataSetAllPage(Principal principal,
                                                      @PathVariable("page") int page,
                                                      @PathVariable("objects_on_page") int objectsOnPage) {
        SpecialistEntity specialistEntity = specialistService.findSpecialistByLogin(principal.getName());
        if (specialistEntity == null) {
            return new ResponseEntity(HttpStatus.UNAUTHORIZED);
        }

        return new ResponseEntity(
                dataSetService.getSpecialistDataSetAllPage(specialistEntity,page, objectsOnPage),
                HttpStatus.OK);
    }

    @PutMapping("/doctor-system/specialist/dataset/{dataset_id}")
    public ResponseEntity changeDataSet(Principal principal,
                                        @RequestBody DatasetEntity datasetEntity,
                                        @PathVariable("dataset_id") Long dataSetId) {
        SpecialistEntity specialistEntity = specialistService.findSpecialistByLogin(principal.getName());
        if (specialistEntity == null) {
            return new ResponseEntity(HttpStatus.UNAUTHORIZED);
        }

        if (dataSetService.changeDataSet(datasetEntity, specialistEntity, dataSetId)) {
            return new ResponseEntity(HttpStatus.OK);
        }
        return new ResponseEntity(HttpStatus.BAD_REQUEST);
    }

    @PutMapping("/doctor-system/specialist/dataset/{dataset_id}/activate")
    public ResponseEntity activateDataSet(Principal principal,
                                          @RequestBody Boolean dataSetActivate,
                                          @PathVariable("dataset_id") Long dataSetId) {
        SpecialistEntity specialistEntity = specialistService.findSpecialistByLogin(principal.getName());
        if (specialistEntity == null) {
            return new ResponseEntity(HttpStatus.UNAUTHORIZED);
        }

        if (dataSetService.activateDataSet(specialistEntity, dataSetId, dataSetActivate)) {
            return new ResponseEntity(HttpStatus.OK);
        }
        return new ResponseEntity(HttpStatus.BAD_REQUEST);
    }
}
