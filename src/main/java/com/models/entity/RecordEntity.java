package com.models.entity;

import javax.persistence.*;
import java.util.List;
import java.util.Objects;

@Entity
@Table(name = "RECORD")
public class RecordEntity {
    private long id;
    private String description;
    private PatientEntity patient;
    private List<PageEntity> pages;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID")
    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    @Basic
    @Column(name = "DESCRIPTION")
    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "PATIENT_ID")
    public PatientEntity getPatient() {
        return patient;
    }

    public void setPatient(PatientEntity patient) {
        this.patient = patient;
    }

    @OneToMany(mappedBy = "record", fetch = FetchType.EAGER)
    public List<PageEntity> getPages() {
        return pages;
    }

    public void setPages(List<PageEntity> pages) {
        this.pages = pages;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        RecordEntity that = (RecordEntity) o;
        return id == that.id &&
                Objects.equals(description, that.description);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, description);
    }
}
