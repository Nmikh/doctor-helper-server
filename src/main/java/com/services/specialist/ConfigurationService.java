package com.services.specialist;

import com.DAO.specialist.ConfigurationPaginationRepository;
import com.DAO.specialist.ConfigurationRepository;
import com.DAO.specialist.SvmKernelParamsRepository;
import com.models.entity.specialist.DatasetConfigurationEntity;
import com.models.entity.specialist.DatasetEntity;
import com.models.entity.specialist.SpecialistEntity;
import com.models.entity.specialist.SvmKernelParametrsEntity;
import com.models.specialist.ConfigurationGet;
import com.models.specialist.ConfigurationPage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.List;

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

    public boolean changeConfiguration(ConfigurationGet configurationGet, Long configurationId, SpecialistEntity specialistEntity) {
        DatasetConfigurationEntity datasetConfigurationEntity = configurationRepository.findById(configurationId).get();

        if (datasetConfigurationEntity.getSpecialistEntity().getId() != specialistEntity.getId()) {
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

    public DatasetConfigurationEntity getConfigurationById(Long configurationId) {
        return configurationRepository.findById(configurationId).get();
    }

    public List<DatasetConfigurationEntity> getAllConfigurationsByDataSetId(Long dataSetId) {
        DatasetEntity dataSetById = dataSetService.getDataSetById(dataSetId);
        return configurationRepository.findByDatasetEntityOrderByNameAsc(dataSetById);
    }

    public ConfigurationPage getAllConfigurationsByDataSetIdPage(Long dataSetId, int page) {
        DatasetEntity dataSetById = dataSetService.getDataSetById(dataSetId);
        Page<DatasetConfigurationEntity> configurationPage =
                configurationPaginationRepository.findByDatasetEntityOrderByNameAsc(dataSetById, new PageRequest(--page, OBJECTS_ON_PAGE));

        return new ConfigurationPage(configurationPage.getTotalPages(), configurationPage.getContent());
    }

    public List<DatasetConfigurationEntity> getAllSpecialistConfigurationsByDataSetId(Long dataSetId, SpecialistEntity specialistEntity) {
        DatasetEntity dataSetById = dataSetService.getDataSetById(dataSetId);

        return configurationRepository.findByDatasetEntityAndSpecialistEntityOrderByNameAsc(dataSetById, specialistEntity);
    }

    public ConfigurationPage getAllSpecialistConfigurationsByDataSetIdPage(Long dataSetId, SpecialistEntity specialistEntity, int page) {
        DatasetEntity dataSetById = dataSetService.getDataSetById(dataSetId);

        Page<DatasetConfigurationEntity> configurationPage =
                configurationPaginationRepository.findByDatasetEntityAndSpecialistEntityOrderByNameAsc(dataSetById, specialistEntity, new PageRequest(--page, OBJECTS_ON_PAGE));

        return new ConfigurationPage(configurationPage.getTotalPages(), configurationPage.getContent());
    }

}
