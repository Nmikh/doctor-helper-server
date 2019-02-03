package com.controllers.specialist;

import com.models.entity.specialist.SpecialistEntity;
import com.services.specialist.DataSetObjectsService;
import com.services.specialist.SpecialistService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.security.Principal;

@Controller
public class DataSetObjectsController {

    @Autowired
    SpecialistService specialistService;

    @Autowired
    DataSetObjectsService dataSetObjectsService;

    @PostMapping("/doctor-system/specialist/dataset/{dataset_id}/objects")
    public ResponseEntity createDataSet(Principal principal,
                                        @RequestParam("file") MultipartFile file,
                                        @PathVariable("dataset_id") Long dataSetId) throws IOException {
        SpecialistEntity specialistEntity = specialistService.findSpecialistByLogin(principal.getName());
        if (specialistEntity == null) {
            return new ResponseEntity(HttpStatus.UNAUTHORIZED);
        } else if (file.isEmpty()) {
            return new ResponseEntity(HttpStatus.BAD_REQUEST);
        }

        if(dataSetObjectsService.addDataObjectsToDataSet(file, specialistEntity, dataSetId)){
            return new ResponseEntity(HttpStatus.resolve(200));
        }
        return new ResponseEntity(HttpStatus.BAD_REQUEST);
    }

}
