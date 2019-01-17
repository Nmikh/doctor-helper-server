package com.DAO;

import com.models.entity.PageEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
public interface PageRepository extends JpaRepository<PageEntity, Long> {
    @Override
    PageEntity getOne(Long id);

    @Transactional(readOnly = true)
    PageEntity findById(long id);
}
