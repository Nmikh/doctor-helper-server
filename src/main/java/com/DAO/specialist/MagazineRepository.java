package com.DAO.specialist;

import com.models.entity.specialist.DatasetConfigurationMagazineEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MagazineRepository extends JpaRepository<DatasetConfigurationMagazineEntity, Long> {

}
