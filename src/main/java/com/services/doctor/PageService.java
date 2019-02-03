package com.services.doctor;

import com.DAO.doctor.*;
import com.models.doctor.PagesPageReturn;
import com.models.entity.doctor.DoctorEntity;
import com.models.entity.doctor.PageEntity;
import com.models.entity.doctor.PatientEntity;
import com.models.entity.doctor.RecordEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.List;
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

    @Autowired
    PagePaginationRepository pagePaginationRepository;

    public Long createPage(Long patientId, PageEntity pageEntity, DoctorEntity doctorEntity) {
        PatientEntity patient = patientRepository.findById(patientId).get();

        RecordEntity record = patient.getRecord();

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
        page.setParameters(pageEntity.getParameters());
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

    public List<PageEntity> getAllPages(Long patientId) {
        PatientEntity patientEntity = patientRepository.findById(patientId).get();

        return pageRepository.findByRecordOrderByDateDesc(patientEntity.getRecord());
    }

    public PagesPageReturn getAllPagesPage(Long patientId, int page, int objectsOnPage) {
        PatientEntity patientEntity = patientRepository.findById(patientId).get();

        Page<PageEntity> pagesPage =
                pagePaginationRepository.findByRecordOrderByDateDesc(
                        patientEntity.getRecord(), new PageRequest(--page, objectsOnPage));

        return new PagesPageReturn(pagesPage.getTotalPages(), pagesPage.getContent());
    }
}