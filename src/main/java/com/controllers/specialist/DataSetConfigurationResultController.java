package com.controllers.specialist;

import com.hazelcast.core.HazelcastInstance;
import com.services.specialist.DataSetConfigurationResultService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Map;

@Controller
public class DataSetConfigurationResultController {

    @Autowired
    DataSetConfigurationResultService dataSetConfigurationResultService;

    @PostMapping("/doctor-system/specialist/result/single/{configutration_id}")
    public ResponseEntity getSingleResult(@PathVariable Long configurationId){
        return new ResponseEntity(HttpStatus.OK);
    }

//    @PostMapping(value = "/write-data")
//    public String writeDataToHazelcast(@RequestParam String key, @RequestParam String value) {
//        Map<String, String> hazelcastMap = hazelcastInstance.getMap("my-map");
//        hazelcastMap.put(key, value);
//        return "Data is stored.";
//    }
//
//    @GetMapping(value = "/read-data")
//    public String readDataFromHazelcast(@RequestParam String key) {
//        Map<String, String> hazelcastMap = hazelcastInstance.getMap("my-map2");
//        return hazelcastMap.get(key);
//    }
//
//    @GetMapping(value = "/read-all-data")
//    public Map<String, String> readAllDataFromHazelcast() {
//        Map<String, String> hazelcastMap = hazelcastInstance.getMap("my-map");
//        return hazelcastInstance.getMap("my-map");
//    }
}
