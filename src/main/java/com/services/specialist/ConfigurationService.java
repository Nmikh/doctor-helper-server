package com.services.specialist;

import com.DAO.specialist.ConfigurationPaginationRepository;
import com.DAO.specialist.ConfigurationRepository;
import com.DAO.specialist.SvmKernelParamsRepository;
import com.models.entity.specialist.DatasetConfigurationEntity;
import com.models.entity.specialist.DatasetEntity;
import com.models.entity.specialist.SpecialistEntity;
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

    public Long createConfiguration(ConfigurationGet configurationGet, Long dataSetId, SpecialistEntity specialistEntity) {
        DatasetEntity dataSetById = dataSetService.getDataSetById(dataSetId);

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
        datasetConfigurationEntity.setSpecialistEntity(specialistEntity);

        DatasetConfigurationEntity save = configurationRepository.save(datasetConfigurationEntity);
        return save.getId();
    }

    public boolean changeConfiguration(ConfigurationGet configurationGet, Long configurationId, SpecialistEntity specialistEntity){
        DatasetConfigurationEntity datasetConfigurationEntity = configurationRepository.findById(configurationId).get();

        if(datasetConfigurationEntity.getSpecialistEntity().getId() != specialistEntity.getId()){
            return false;
        }

        SvmKernelParametrsEntity svmParam = svmKernelParamsRepository.findById(configurationGet.getSvmParametr()).get();
        SvmKernelParametrsEntity kernelParam = svmKernelParamsRepository.findById(configurationGet.getKernelParametr()).get();

        datasetConfigurationEntity.setActive(false);
        datasetConfigurationEntity.setC(configurationGet.getC());
        datasetConfigurationEntity.setDegree(configurationGet.getDegree());
        datasetConfigurationEntity.setEps(configurationGet.getEps());
        datasetConfigurationEntity.setGamma(configurationGet.getGamma());
        datasetConfigurationEntity.setNu(configurationGet.getNu());
        datasetConfigurationEntity.setProbability(configurationGet.getProbability());
        datasetConfigurationEntity.setName(configurationGet.getName());

        datasetConfigurationEntity.setKernelParametr(kernelParam);
        datasetConfigurationEntity.setSvmParametr(svmParam);
        datasetConfigurationEntity.setSpecialistEntity(specialistEntity);

        DatasetConfigurationEntity save = configurationRepository.save(datasetConfigurationEntity);

        return true;
    }
}
