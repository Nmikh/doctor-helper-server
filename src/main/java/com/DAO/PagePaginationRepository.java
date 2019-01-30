package com.DAO;

import com.models.entity.doctor.PageEntity;
import com.models.entity.doctor.RecordEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.PagingAndSortingRepository;

public interface PagePaginationRepository extends PagingAndSortingRepository<PageEntity, Long> {

    Page<PageEntity> findByRecordOrderByDateDesc(RecordEntity recordEntity, Pageable pageable);

}
