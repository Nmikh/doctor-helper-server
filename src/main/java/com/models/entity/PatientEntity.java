package com.models.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Table(name = "PATIENT")
public class PatientEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID")
    private long id;

    @Basic
    @Column(name = "NAME", nullable = false, length = 255)
    private String name;

    @Basic
    @Column(name = "SURNAME", nullable = false, length = 255)
    private String surname;

    @Basic
    @Column(name = "TELEPHONE", length = 50)
    private String telephone;

    @Basic
    @Column(name = "ADDRESS", length = 500)
    private String address;

    @Basic
    @Column(name = "EMAIL", length =  100)
    private String email;

    @JsonIgnore
    @OneToOne(mappedBy = "patient", cascade = CascadeType.ALL,
            fetch = FetchType.EAGER, optional = false)
    private RecordEntity record;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public String getTelephone() {
        return telephone;
    }

    public void setTelephone(String telephone) {
        this.telephone = telephone;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

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
