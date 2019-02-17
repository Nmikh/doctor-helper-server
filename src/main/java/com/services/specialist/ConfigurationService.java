package com.services.specialist;

import com.DAO.specialist.ConfigurationPaginationRepository;
import com.DAO.specialist.ConfigurationRepository;
import com.DAO.specialist.SvmKernelParamsRepository;
import com.models.entity.specialist.*;
import com.models.specialist.ConfigurationGet;
import com.models.specialist.ConfigurationPage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.util.List;

@Service
public class ConfigurationService {

    @Autowired
    ConfigurationRepository configurationRepository;

    @Autowired
    ConfigurationPaginationRepository configurationPaginationRepository;

    @Autowired
    SvmKernelParamsRepository svmKernelParamsRepository;

    @Autowired
    DataSetService dataSetService;

    @Autowired
    MagazineService magazineService;

    @Autowired
    DataSetConfigurationResultService dataSetConfigurationResultService;

    public Long createConfiguration(ConfigurationGet configurationGet, Long dataSetId, SpecialistEntity specialistEntity) {
        DatasetEntity dataSetById = dataSetService.getDataSetById(dataSetId);

        SvmKernelParametrsEntity svmParam = svmKernelParamsRepository.findById(configurationGet.getSvmParameter()).get();
        SvmKernelParametrsEntity kernelParam = svmKernelParamsRepository.findById(configurationGet.getKernelParameter()).get();

        DatasetConfigurationEntity datasetConfigurationEntity = new DatasetConfigurationEntity();

        datasetConfigurationEntity.setActive(false);
        datasetConfigurationEntity.setC(configurationGet.getC());
        datasetConfigurationEntity.setDegree(configurationGet.getDegree());
        datasetConfigurationEntity.setEps(configurationGet.getEps());
        datasetConfigurationEntity.setGamma(configurationGet.getGamma());
        datasetConfigurationEntity.setNu(configurationGet.getNu());
        datasetConfigurationEntity.setProbability(configurationGet.getProbability());
        datasetConfigurationEntity.setName(configurationGet.getName());
        datasetConfigurationEntity.setTestPart(configurationGet.getTestPart());

        datasetConfigurationEntity.setDatasetEntity(dataSetById);
        datasetConfigurationEntity.setKernelParameter(kernelParam);
        datasetConfigurationEntity.setSvmParameter(svmParam);
        datasetConfigurationEntity.setSpecialistEntity(specialistEntity);

        DatasetConfigurationEntity save = configurationRepository.save(datasetConfigurationEntity);
        return save.getId();
    }

    public boolean changeConfiguration(ConfigurationGet configurationGet, Long configurationId, SpecialistEntity specialistEntity) {
        DatasetConfigurationEntity datasetConfigurationEntity = configurationRepository.findById(configurationId).get();

        if (datasetConfigurationEntity.getSpecialistEntity().getId() != specialistEntity.getId()) {
            return false;
        }

        SvmKernelParametrsEntity svmParam = svmKernelParamsRepository.findById(configurationGet.getSvmParameter()).get();
        SvmKernelParametrsEntity kernelParam = svmKernelParamsRepository.findById(configurationGet.getKernelParameter()).get();

        datasetConfigurationEntity.setActive(false);
        datasetConfigurationEntity.setC(configurationGet.getC());
        datasetConfigurationEntity.setDegree(configurationGet.getDegree());
        datasetConfigurationEntity.setEps(configurationGet.getEps());
        datasetConfigurationEntity.setGamma(configurationGet.getGamma());
        datasetConfigurationEntity.setNu(configurationGet.getNu());
        datasetConfigurationEntity.setProbability(configurationGet.getProbability());
        datasetConfigurationEntity.setName(configurationGet.getName());
        datasetConfigurationEntity.setTestPart(configurationGet.getTestPart());

        datasetConfigurationEntity.setKernelParameter(kernelParam);
        datasetConfigurationEntity.setSvmParameter(svmParam);
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

    public ConfigurationPage getAllConfigurationsByDataSetIdPage(Long dataSetId, int page, int objectsOnPage) {
        DatasetEntity dataSetById = dataSetService.getDataSetById(dataSetId);
        Page<DatasetConfigurationEntity> configurationPage =
                configurationPaginationRepository.findByDatasetEntityOrderByNameAsc(dataSetById, new PageRequest(--page, objectsOnPage));

        return new ConfigurationPage(configurationPage.getTotalPages(), configurationPage.getContent());
    }

    public List<DatasetConfigurationEntity> getAllSpecialistConfigurationsByDataSetId(Long dataSetId, SpecialistEntity specialistEntity) {
        DatasetEntity dataSetById = dataSetService.getDataSetById(dataSetId);

        return configurationRepository.findByDatasetEntityAndSpecialistEntityOrderByNameAsc(dataSetById, specialistEntity);
    }

    public ConfigurationPage getAllSpecialistConfigurationsByDataSetIdPage(Long dataSetId, SpecialistEntity specialistEntity, int page, int objectsOnPage) {
        DatasetEntity dataSetById = dataSetService.getDataSetById(dataSetId);

        Page<DatasetConfigurationEntity> configurationPage =
                configurationPaginationRepository.findByDatasetEntityAndSpecialistEntityOrderByNameAsc(dataSetById, specialistEntity, new PageRequest(--page, objectsOnPage));

        return new ConfigurationPage(configurationPage.getTotalPages(), configurationPage.getContent());
    }

    public void activateConfiguration(Long configurationId, SpecialistEntity specialistEntity) {
        DatasetConfigurationMagazineEntity datasetConfigurationMagazineEntity = new DatasetConfigurationMagazineEntity();

        DatasetConfigurationEntity datasetConfigurationAfter = configurationRepository.findById(configurationId).get();
        DatasetConfigurationEntity datasetConfigurationBefore = configurationRepository.findByDatasetEntityAndActiveTrue(datasetConfigurationAfter.getDatasetEntity());

        if (datasetConfigurationBefore != null) {
            if (datasetConfigurationAfter.getId() == datasetConfigurationBefore.getId()) {
                return;
            }

            datasetConfigurationBefore.setActive(false);
            configurationRepository.save(datasetConfigurationBefore);
            datasetConfigurationMagazineEntity.setDatasetConfigurationBefore(datasetConfigurationBefore);
        }

        datasetConfigurationAfter.setActive(true);
        configurationRepository.save(datasetConfigurationAfter);

        datasetConfigurationMagazineEntity.setDateTime(new Timestamp(System.currentTimeMillis()));
        datasetConfigurationMagazineEntity.setDatasetConfigurationAfter(datasetConfigurationAfter);
        datasetConfigurationMagazineEntity.setSpecialistEntity(specialistEntity);

        magazineService.createMagazineRow(datasetConfigurationMagazineEntity);
    }

    public List<SvmKernelParametrsEntity> getAllSvmTypes(){
        return svmKernelParamsRepository.findBySvmKernelTrue();
    }

    public List<SvmKernelParametrsEntity> getAllKernelTypes(){
        return svmKernelParamsRepository.findBySvmKernelFalse();
    }

    public DatasetConfigurationEntity findActiveConfiguration(DatasetEntity datasetEntity){
        return configurationRepository.findByDatasetEntityAndActiveTrue(datasetEntity);
    }

    public boolean removeConfiguration(Long configurationId, SpecialistEntity specialistEntity){
        DatasetConfigurationEntity datasetConfiguration = configurationRepository.findById(configurationId).get();

        if(datasetConfiguration.getSpecialistEntity().getId() != specialistEntity.getId()){
            return false;
        }

        magazineService.removeConfiguration(datasetConfiguration);
        dataSetConfigurationResultService.removeAllResults(datasetConfiguration);
        configurationRepository.delete(datasetConfiguration);

        return true;
    }
}
