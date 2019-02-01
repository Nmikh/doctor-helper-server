package com.services.specialist;

import com.DAO.specialist.MagazineRepository;
import com.models.entity.specialist.DatasetConfigurationMagazineEntity;
import com.models.entity.specialist.DatasetEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MagazineService {
    @Autowired
    MagazineRepository magazineRepository;

    @Autowired
    DataSetService dataSetService;

    public void createMagazineRow(DatasetConfigurationMagazineEntity magazineRow){
       magazineRepository.save(magazineRow);
    }

//    public List<DatasetConfigurationMagazineEntity> getMagazineRowsByDataSetId(Long dataSetId){
//        DatasetEntity dataSetById = dataSetService.getDataSetById(dataSetId);
//
//    }
}
