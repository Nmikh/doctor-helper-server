package com.configs;

import com.hazelcast.config.Config;
import com.hazelcast.config.MapConfig;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class HazelcastCacheConfig {
    private final String INSTANCE_NAME = "specialist";
    private final String CONFIG_RESULT_MAP_NAME = "configurationResultMap";

    @Bean
    public Config hazelCastConfig() {
        Config config = new Config();
        config.setInstanceName(INSTANCE_NAME);
        config.addMapConfig(new MapConfig().setName(CONFIG_RESULT_MAP_NAME));
        return config;
    }
}

