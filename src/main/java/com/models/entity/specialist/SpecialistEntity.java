package com.models.entity.specialist;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.models.entity.UserEntity;

import javax.persistence.*;

@Entity
@Table(name = "SPECIALIST")
public class SpecialistEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID", nullable = false)
    private long id;

    @Basic
    @Column(name = "NAME", nullable = false, length = 100)
    private String name;

    @Basic
    @Column(name = "SURNAME", nullable = false, length = 100)
    private String surname;

    @JsonIgnore
    @OneToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "USER_ID")
    private UserEntity userEntity;

    public SpecialistEntity() {
    }

    public SpecialistEntity(String name, String surname, UserEntity userEntity) {
        this.name = name;
        this.surname = surname;
        this.userEntity = userEntity;
    }

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

    public UserEntity getUserEntity() {
        return userEntity;
    }

    public void setUserEntity(UserEntity userEntity) {
        this.userEntity = userEntity;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        SpecialistEntity that = (SpecialistEntity) o;

        if (id != that.id) return false;
        if (name != null ? !name.equals(that.name) : that.name != null) return false;
        if (surname != null ? !surname.equals(that.surname) : that.surname != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = (int) (id ^ (id >>> 32));
        result = 31 * result + (name != null ? name.hashCode() : 0);
        result = 31 * result + (surname != null ? surname.hashCode() : 0);
        return result;
    }
}
