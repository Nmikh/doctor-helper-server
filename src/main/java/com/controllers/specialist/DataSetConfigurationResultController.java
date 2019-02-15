package com.controllers.specialist;

import com.hazelcast.core.HazelcastInstance;
import com.models.entity.specialist.DatasetObjectsEntity;
import com.models.entity.specialist.SpecialistEntity;
import com.services.specialist.DataSetConfigurationResultService;
import com.services.specialist.SpecialistService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.Map;

@Controller
public class DataSetConfigurationResultController {

    @Autowired
    DataSetConfigurationResultService dataSetConfigurationResultService;

    @Autowired
    SpecialistService specialistService;

    @PostMapping("/doctor-system/specialist/result/general/{configutration_id}/start")
    public ResponseEntity startConfigutrationTest(@PathVariable("configutration_id") Long configurationId, Principal principal) {
        SpecialistEntity specialistEntity = specialistService.findSpecialistByLogin(principal.getName());
        if (specialistEntity == null) {
            return new ResponseEntity(HttpStatus.UNAUTHORIZED);
        }
        return new ResponseEntity(
                dataSetConfigurationResultService.startConfigurationTest(configurationId),
                HttpStatus.OK);
    }

    @GetMapping("/doctor-system/specialist/result/general/{process_id}/start")
    public ResponseEntity getStartConfigutrationtResult(@PathVariable("process_id") Long processId, Principal principal) {
        SpecialistEntity specialistEntity = specialistService.findSpecialistByLogin(principal.getName());
        if (specialistEntity == null) {
            return new ResponseEntity(HttpStatus.UNAUTHORIZED);
        }

        return new ResponseEntity(
                dataSetConfigurationResultService.getConfigurationTestResult(processId),
                HttpStatus.OK);
    }

    //todo
    @PostMapping("/doctor-system/specialist/result/simple/{configutration_id}/start")
    public ResponseEntity startConfigutrationTestOnSingleObject(
            @PathVariable("configutration_id") Long configurationId,
            @RequestBody String params,
            Principal principal) {
        SpecialistEntity specialistEntity = specialistService.findSpecialistByLogin(principal.getName());
        if (specialistEntity == null) {
            return new ResponseEntity(HttpStatus.UNAUTHORIZED);
        }

        DatasetObjectsEntity datasetObject = new DatasetObjectsEntity();
        datasetObject.setParams(params);
        datasetObject.setObjectClass((long) 0);

        return new ResponseEntity(dataSetConfigurationResultService.getConfigurationResultSingle(configurationId, datasetObject), HttpStatus.OK);
    }

    @GetMapping("/doctor-system/specialist/result/general/{configutration_id}/confusion_matrix")
    public ResponseEntity getConfusionMatrix(
            @PathVariable("configutration_id") Long configurationId,
            Principal principal) {
        SpecialistEntity specialistEntity = specialistService.findSpecialistByLogin(principal.getName());
        if (specialistEntity == null) {
            return new ResponseEntity(HttpStatus.UNAUTHORIZED);
        }

        return new ResponseEntity(dataSetConfigurationResultService.getConfusionMatrix(configurationId), HttpStatus.OK);
    }

    @GetMapping("/doctor-system/specialist/result/general/{configutration_id}/configuration_result")
    public ResponseEntity getConfigurationResult(
            @PathVariable("configutration_id") Long configurationId,
            Principal principal) {
        SpecialistEntity specialistEntity = specialistService.findSpecialistByLogin(principal.getName());
        if (specialistEntity == null) {
            return new ResponseEntity(HttpStatus.UNAUTHORIZED);
        }

        return new ResponseEntity(dataSetConfigurationResultService.getConfigurationResult(configurationId), HttpStatus.OK);
    }

    @GetMapping("/doctor-system/specialist/result/general/{configutration_id}/configuration_result/{page}/{objects_on_page}")
    public ResponseEntity getConfigurationResultPage(
            @PathVariable("configutration_id") Long configurationId,
            @PathVariable("page") int page,
            @PathVariable("objects_on_page") int objectsOnPage,
            Principal principal) {
        SpecialistEntity specialistEntity = specialistService.findSpecialistByLogin(principal.getName());
        if (specialistEntity == null) {
            return new ResponseEntity(HttpStatus.UNAUTHORIZED);
        }

        return new ResponseEntity(dataSetConfigurationResultService.getConfigurationResultPage(configurationId, page, objectsOnPage), HttpStatus.OK);
    }
}
