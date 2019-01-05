package com.DAO;

import com.models.entity.DoctorEntity;
import com.models.entity.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
public interface DoctorRepository extends JpaRepository<DoctorEntity, Long> {

    @Transactional(readOnly = true)
    DoctorEntity findByUserEntity(UserEntity userEntity);
}
