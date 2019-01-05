package com.DAO;

import com.models.entity.SpecialistEntity;
import com.models.entity.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
public interface SpecialistRepository extends JpaRepository<SpecialistEntity, Long> {

    @Transactional(readOnly = true)
    SpecialistEntity findByUserEntity(UserEntity userEntity);
}
