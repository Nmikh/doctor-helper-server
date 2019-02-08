package com.services.specialist;

import com.DAO.specialist.MagazineRepository;
import com.models.entity.specialist.DatasetConfigurationMagazineEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;

@Service
public class MagazineService {
    @Autowired
    MagazineRepository magazineRepository;

    public void createMagazineRow(DatasetConfigurationMagazineEntity magazineRow) {
        magazineRepository.save(magazineRow);
    }

    public List<DatasetConfigurationMagazineEntity> getMagazineByDataSetId(Long dataSetId) {
        List<BigInteger> magazineRowsId = magazineRepository.findMagazineId(dataSetId);
        ArrayList<DatasetConfigurationMagazineEntity> magazine = new ArrayList<>();

        for (int i = 0; i < magazineRowsId.size(); i++) {
            magazine.add(magazineRepository.findById(magazineRowsId.get(i).longValue()).get());
        }
        return magazine;
    }

}
