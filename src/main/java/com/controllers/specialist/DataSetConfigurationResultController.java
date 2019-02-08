package com.controllers.specialist;

import com.hazelcast.core.HazelcastInstance;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Map;

@Controller
public class DataSetConfigurationResultController {
    private final HazelcastInstance hazelcastInstance;

    @Autowired
    public DataSetConfigurationResultController(HazelcastInstance hazelcastInstance) {
        this.hazelcastInstance = hazelcastInstance;
    }


    @PostMapping(value = "/write-data")
    public String writeDataToHazelcast(@RequestParam String key, @RequestParam String value) {
        Map<String, String> hazelcastMap = hazelcastInstance.getMap("my-map");
        hazelcastMap.put(key, value);
        return "Data is stored.";
    }

    @GetMapping(value = "/read-data")
    public String readDataFromHazelcast(@RequestParam String key) {
        Map<String, String> hazelcastMap = hazelcastInstance.getMap("my-map");
        return hazelcastMap.get(key);
    }

    @GetMapping(value = "/read-all-data")
    public Map<String, String> readAllDataFromHazelcast() {
        Map<String, String> hazelcastMap = hazelcastInstance.getMap("my-map");
        return hazelcastInstance.getMap("my-map");
    }
}
