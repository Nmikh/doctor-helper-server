package com.controllers.specialist;

import com.models.entity.specialist.SpecialistEntity;
import com.services.specialist.DataSetObjectsService;
import com.services.specialist.SpecialistService;
import org.apache.commons.io.IOUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.security.Principal;

@Controller
public class DataSetObjectsController {

    @Autowired
    SpecialistService specialistService;

    @Autowired
    DataSetObjectsService dataSetObjectsService;

    @PostMapping("/doctor-system/specialist/dataset/{dataset_id}/objects")
    public ResponseEntity uploadDataSet(Principal principal,
                                        @RequestParam("file") MultipartFile file,
                                        @PathVariable("dataset_id") Long dataSetId) throws IOException {
        SpecialistEntity specialistEntity = specialistService.findSpecialistByLogin(principal.getName());
        if (specialistEntity == null) {
            return new ResponseEntity(HttpStatus.UNAUTHORIZED);
        } else if (file.isEmpty()) {
            return new ResponseEntity(HttpStatus.BAD_REQUEST);
        }

        if (dataSetObjectsService.addDataObjectsToDataSet(file, specialistEntity, dataSetId)) {
            return new ResponseEntity(HttpStatus.resolve(200));
        }
        return new ResponseEntity(HttpStatus.BAD_REQUEST);
    }

    @GetMapping("/doctor-system/specialist/dataset/{dataset_id}/objects")
    @ResponseBody
    public ResponseEntity downloadDataSet(Principal principal, @PathVariable("dataset_id") Long dataSetId) {
        SpecialistEntity specialistEntity = specialistService.findSpecialistByLogin(principal.getName());
        if (specialistEntity == null) {
            return new ResponseEntity(HttpStatus.UNAUTHORIZED);
        }

        return new ResponseEntity(dataSetObjectsService.getDatSet(dataSetId), HttpStatus.OK);
    }
}
