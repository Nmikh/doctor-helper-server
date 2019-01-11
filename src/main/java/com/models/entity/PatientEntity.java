package com.models.entity;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Table(name = "PATIENT")
public class PatientEntity {
    private long id;
    private String name;
    private String surname;
    private RecordEntity record;

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
    @Column(name = "NAME")
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Basic
    @Column(name = "SURNAME")
    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    @OneToOne(mappedBy = "patient", cascade = CascadeType.ALL,
            fetch = FetchType.EAGER, optional = false)
    public RecordEntity getRecord() {
        return record;
    }

    public void setRecord(RecordEntity recordEntity) {
        this.record = recordEntity;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        PatientEntity that = (PatientEntity) o;
        return id == that.id &&
                Objects.equals(name, that.name) &&
                Objects.equals(surname, that.surname);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, surname);
    }
}
