package com.DAO.specialist;

import com.models.entity.specialist.SvmKernelParametrsEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface SvmKernelParamsRepository extends JpaRepository<SvmKernelParametrsEntity, Long> {
    @Override
    Optional<SvmKernelParametrsEntity> findById(Long aLong);
}
