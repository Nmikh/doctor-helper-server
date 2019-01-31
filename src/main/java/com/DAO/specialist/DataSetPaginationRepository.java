package com.DAO.specialist;

import com.models.entity.specialist.DatasetEntity;
import com.models.entity.specialist.SpecialistEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.PagingAndSortingRepository;

public interface DataSetPaginationRepository extends PagingAndSortingRepository<DatasetEntity, Long> {

    Page<DatasetEntity> findAllByOrderByNameAsc(Pageable pageable);

    Page<DatasetEntity> findAllBySpecialistEntityOrderByNameAsc(SpecialistEntity specialistEntity, Pageable pageable);
}
