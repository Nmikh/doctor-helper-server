package com.controllers.specialist;

import com.models.entity.specialist.DatasetEntity;
import com.models.entity.specialist.SpecialistEntity;
import com.services.specialist.DataSetService;
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
* 1. Create dataset @PostMapping("/doctor-system/specialist/dataset") +
* 2. Delete Dataset @DeleteMapping("/doctor-system/specialist/dataset")
* 3. Change Dataset @PutMapping("/doctor-system/specialist/dataset/{dataset_id}") +
* 4. Get Dataset by ID @GetMapping("/doctor-system/specialist/dataset/{dataset_id}")
* 5. Get All Datasets @GetMapping("/doctor-system/specialist/dataset/all")
* 6. Activation Dataset @PutMapping("/doctor-system/specialist/dataset/{dataset_id}/activate")
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
}
