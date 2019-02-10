package com.controllers.specialist;

import com.hazelcast.core.HazelcastInstance;
import com.models.entity.specialist.SpecialistEntity;
import com.services.specialist.DataSetConfigurationResultService;
import com.services.specialist.SpecialistService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.security.Principal;
import java.util.Map;

@Controller
public class DataSetConfigurationResultController {

    @Autowired
    DataSetConfigurationResultService dataSetConfigurationResultService;

    @Autowired
    SpecialistService specialistService;

    @PostMapping("/doctor-system/specialist/result/{configutration_id}")
    public ResponseEntity startConfigutrationTest(@PathVariable("configutration_id") Long configurationId, Principal principal) {
        SpecialistEntity specialistEntity = specialistService.findSpecialistByLogin(principal.getName());
        if (specialistEntity == null) {
            return new ResponseEntity(HttpStatus.UNAUTHORIZED);
        }
        return new ResponseEntity(
                dataSetConfigurationResultService.startConfigurationTest(configurationId),
                HttpStatus.OK);
    }

    @GetMapping("/doctor-system/specialist/result/{process_id}")
    public ResponseEntity getStartConfigutrationtResult(@PathVariable("process_id") Long processId, Principal principal) {
        SpecialistEntity specialistEntity = specialistService.findSpecialistByLogin(principal.getName());
        if (specialistEntity == null) {
            return new ResponseEntity(HttpStatus.UNAUTHORIZED);
        }

        return new ResponseEntity(
                dataSetConfigurationResultService.getConfigurationTestResult(processId),
                HttpStatus.OK);
    }

}
