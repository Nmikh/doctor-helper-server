package com.services.specialist;

import com.DAO.specialist.ConfigurationPaginationRepository;
import com.DAO.specialist.ConfigurationRepository;
import com.DAO.specialist.SvmKernelParamsRepository;
import com.models.entity.specialist.DatasetConfigurationEntity;
import com.models.entity.specialist.DatasetEntity;
import com.models.entity.specialist.SvmKernelParametrsEntity;
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
    SvmKernelParamsRepository svmKernelParamsRepository;

    @Autowired
    DataSetService dataSetService;

    public Long create(ConfigurationGet configurationGet, Long dataset_id) {
        DatasetEntity dataSetById = dataSetService.getDataSetById(dataset_id);

        SvmKernelParametrsEntity svmParam = svmKernelParamsRepository.findById(configurationGet.getSvmParametr()).get();
        SvmKernelParametrsEntity kernelParam = svmKernelParamsRepository.findById(configurationGet.getKernelParametr()).get();

        DatasetConfigurationEntity datasetConfigurationEntity = new DatasetConfigurationEntity();

        datasetConfigurationEntity.setActive(false);
        datasetConfigurationEntity.setC(configurationGet.getC());
        datasetConfigurationEntity.setDegree(configurationGet.getDegree());
        datasetConfigurationEntity.setEps(configurationGet.getEps());
        datasetConfigurationEntity.setGamma(configurationGet.getGamma());
        datasetConfigurationEntity.setNu(configurationGet.getNu());
        datasetConfigurationEntity.setProbability(configurationGet.getProbability());
        datasetConfigurationEntity.setName(configurationGet.getName());

        datasetConfigurationEntity.setDatasetEntity(dataSetById);
        datasetConfigurationEntity.setKernelParametr(kernelParam);
        datasetConfigurationEntity.setSvmParametr(svmParam);

        DatasetConfigurationEntity save = configurationRepository.save(datasetConfigurationEntity);
        return save.getId();
    }
}
