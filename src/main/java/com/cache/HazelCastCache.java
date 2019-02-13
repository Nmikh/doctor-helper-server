package com.cache;

import com.hazelcast.config.Config;
import com.hazelcast.config.MapConfig;
import com.hazelcast.core.Hazelcast;
import com.hazelcast.core.HazelcastInstance;
import com.hazelcast.core.IMap;
import com.hazelcast.core.IdGenerator;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;

@Component
public class HazelCastCache {

    private static final String INSTANCE_NAME = "doctorSystem";
    private static final String CONFIGURATION_GENERAL_RESULT_MAP_NAME = "configurationGeneralResultMap";
    private static final String CONFIGURATION_SINGLE_RESULT_MAP_NAME = "configurationSingleResultMap";
    private static final String CONFIGURATION_ARRAY_RESULT_MAP_NAME = "configurationArrayResultMap";
    private static final String ID_GENERATOR_NAME = "idGenerator";

    private HazelcastInstance hazelcastInstance;
    private IdGenerator idGenerator;

    @PostConstruct
    private void init() {
        Config config = new Config();
        config.setInstanceName(INSTANCE_NAME);

        MapConfig ticketsMapConfig = new MapConfig();
        ticketsMapConfig.setName(CONFIGURATION_GENERAL_RESULT_MAP_NAME);

        config.addMapConfig(ticketsMapConfig);
        hazelcastInstance = Hazelcast.newHazelcastInstance(config);

        idGenerator = hazelcastInstance.getIdGenerator(ID_GENERATOR_NAME);
    }

    public <K, V> IMap<K, V> getConfigurationGeneralResultMap() {
        return hazelcastInstance.getMap(CONFIGURATION_GENERAL_RESULT_MAP_NAME);
    }

    public <K, V> IMap<K, V> getConfigurationSingleResultMap() {
        return hazelcastInstance.getMap(CONFIGURATION_SINGLE_RESULT_MAP_NAME);
    }

    public <K, V> IMap<K, V> getConfigurationArrayResultMap() {
        return hazelcastInstance.getMap(CONFIGURATION_ARRAY_RESULT_MAP_NAME);
    }

    public long getNewId(){
        return idGenerator.newId();
    }
}
