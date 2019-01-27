package com.DAO;

import com.models.entity.PageEntity;
import com.models.entity.RecordEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Repository
public interface PageRepository extends JpaRepository<PageEntity, Long> {

    @Transactional(readOnly = true)
    @Override
    PageEntity getOne(Long id);

    @Transactional(readOnly = true)
    Optional<PageEntity> findById(long id);

    List<PageEntity> findByRecordOrderByDateDesc(RecordEntity recordEntity);
}
