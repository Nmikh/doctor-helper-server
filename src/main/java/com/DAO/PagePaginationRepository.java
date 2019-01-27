package com.DAO;

import com.models.entity.PageEntity;
import com.models.entity.RecordEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.PagingAndSortingRepository;

public interface PagePaginationRepository extends PagingAndSortingRepository<PageEntity, Long> {

    Page<PageEntity> findByRecordOrderByDateDesc(RecordEntity recordEntity, Pageable pageable);

}
