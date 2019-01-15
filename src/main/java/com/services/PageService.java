package com.services;

import com.DAO.DoctorRepository;
import com.DAO.PageRepository;
import com.DAO.RecordRepository;
import com.models.entity.DoctorEntity;
import com.models.entity.PageEntity;
import com.models.entity.RecordEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PageService {

    @Autowired
    PageRepository pageRepository;

    @Autowired
    RecordRepository recordRepository;

    @Autowired
    DoctorRepository doctorRepository;

    public Long createPage(Long recordId, PageEntity pageEntity, DoctorEntity doctorEntity){
        RecordEntity record = recordRepository.getOne(recordId);

        pageEntity.setRecord(record);
        pageEntity.setDoctor(doctorEntity);

        PageEntity save = pageRepository.save(pageEntity);

        return save.getId();
    }
}
