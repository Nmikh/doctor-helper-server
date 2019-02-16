package com.controllers.doctor;

import com.models.entity.doctor.DoctorEntity;
import com.models.entity.specialist.DatasetObjectsEntity;
import com.models.specialist.ConfigurationSingleResultGet;
import com.services.doctor.DoctorService;
import com.services.doctor.IllnessResultService;
import com.services.specialist.DataSetConfigurationResultService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import java.io.IOException;
import java.security.Principal;

@Controller
public class IllnessResultController {

    @Autowired
    DoctorService doctorService;

    @Autowired
    IllnessResultService illnessResultService;

    @Autowired
    DataSetConfigurationResultService dataSetConfigurationResultService;

    @GetMapping("/doctor-system/doctor/illness/datasets")
    public ResponseEntity getAllActiveDataSets(Principal principal) {
        DoctorEntity doctor = doctorService.findDoctorByLogin(principal.getName());

        if (doctor == null) {
            return new ResponseEntity(HttpStatus.UNAUTHORIZED);
        }

        return new ResponseEntity(illnessResultService.getAllWorkDataSets(), HttpStatus.OK);
    }

    @PostMapping("/doctor-system/doctor/illness/result/{configutration_id}/start")
    public ResponseEntity startConfigutrationSingleTest(
            @PathVariable("configutration_id") Long configurationId,
            @RequestBody ConfigurationSingleResultGet configurationSingleResultGet) throws IOException {

        DatasetObjectsEntity datasetObjectsEntity = new DatasetObjectsEntity();
        datasetObjectsEntity.setParams(configurationSingleResultGet.getParams());

        return new ResponseEntity(
                dataSetConfigurationResultService.startConfigurationSingleTest(configurationId, datasetObjectsEntity, configurationSingleResultGet.getSignificance()),
                HttpStatus.OK);
    }


    @GetMapping("/doctor-system/doctor/illness/result/{process_id}/start")
    public ResponseEntity getStartConfigutrationtSingleResult(@PathVariable("process_id") Long processId) {

        return new ResponseEntity(dataSetConfigurationResultService.getConfigurationSingleTestResult(processId), HttpStatus.OK);
    }

}


