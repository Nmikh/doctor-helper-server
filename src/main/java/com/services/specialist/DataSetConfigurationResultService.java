package com.services.specialist;

import com.hazelcast.core.HazelcastInstance;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class DataSetConfigurationResultService {
    private final HazelcastInstance hazelcastInstance;

    @Autowired
    public DataSetConfigurationResultService(HazelcastInstance hazelcastInstance) {
        this.hazelcastInstance = hazelcastInstance;
    }

    public void getSingleResult() {

    }
}
