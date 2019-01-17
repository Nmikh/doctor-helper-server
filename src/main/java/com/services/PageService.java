package com.services;

import com.DAO.DoctorRepository;
import com.DAO.PageRepository;
import com.DAO.PatientRepository;
import com.DAO.RecordRepository;
import com.models.entity.DoctorEntity;
import com.models.entity.PageEntity;
import com.models.entity.PatientEntity;
import com.models.entity.RecordEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class PageService {

    @Autowired
    PageRepository pageRepository;

    @Autowired
    RecordRepository recordRepository;

    @Autowired
    DoctorRepository doctorRepository;

    @Autowired
    PatientRepository patientRepository;

    public Long createPage(Long patientId, PageEntity pageEntity, DoctorEntity doctorEntity) {
        PatientEntity patient = patientRepository.getOne(patientId);

        RecordEntity record = (RecordEntity) recordRepository.findByPatient(patient);

        pageEntity.setRecord(record);
        pageEntity.setDoctor(doctorEntity);

        PageEntity save = pageRepository.save(pageEntity);

        return save.getId();
    }

    public PageEntity readPage(Long pageId) {
        Optional<PageEntity> page = pageRepository.findById(pageId);
        return page.get();
    }

    public boolean updatePage(Long pageId, PageEntity pageEntity, DoctorEntity doctorEntity) {

        PageEntity page = pageRepository.findById(pageId).get();

        if (page.getDoctor().getId() != doctorEntity.getId()) {
            return false;
        }

        page.setTheme(pageEntity.getTheme());
        page.setDescription(pageEntity.getDescription());
        page.setAnswer(pageEntity.getAnswer());
        page.setDate(pageEntity.getDate());

        pageRepository.save(page);

        return true;
    }

    public boolean deletePage(Long pageId, DoctorEntity doctorEntity) {

        PageEntity page = pageRepository.findById(pageId).get();

        if (page.getDoctor().getId() != doctorEntity.getId()) {
            return false;
        }

        pageRepository.delete(page);

        return true;
    }
}
