package com.services.specialist;

import com.DAO.specialist.ConfigurationPaginationRepository;
import com.DAO.specialist.ConfigurationRepository;
import com.DAO.specialist.SvmKernelParamsRepository;
import com.models.entity.specialist.DatasetEntity;
import com.models.specialist.ConfigurationGet;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ConfigurationService {
    private final static int OBJECTS_ON_PAGE = 10;

    @Autowired
    ConfigurationRepository configurationRepository;

    @Autowired
    ConfigurationPaginationRepository configurationPaginationRepository;

    @Autowired
    DataSetService dataSetService;

    @Autowired
    SvmKernelParamsRepository svmKernelParamsRepository;

    public void create(ConfigurationGet configurationGet, Long dataset_id) {
        DatasetEntity dataSetById = dataSetService.getDataSetById(dataset_id);

        

    }
}
