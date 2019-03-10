package com.models.entity.specialist;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Objects;

@Entity
@Table(name = "DATASET_OBJECTS")
public class DatasetObjectsEntity implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID")
    private long id;

    @Column(name = "USER_OBJECT_ID")
    private long userObjectId;

    @Basic
    @Column(name = "PARAMS")
    private String params;

    @Basic
    @Column(name = "OBJECT_CLASS")
    private Long objectClass;

    @JsonIgnore
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "DATASET_ID")
    private DatasetEntity datasetEntity;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public long getUserObjectId() {
        return userObjectId;
    }

    public void setUserObjectId(long userObjectId) {
        this.userObjectId = userObjectId;
    }

    public String getParams() {
        return params;
    }

    public void setParams(String params) {
        this.params = params;
    }

    public Long getObjectClass() {
        return objectClass;
    }

    public void setObjectClass(Long objectClass) {
        this.objectClass = objectClass;
    }

    public DatasetEntity getDatasetEntity() {
        return datasetEntity;
    }

    public void setDatasetEntity(DatasetEntity datasetEntity) {
        this.datasetEntity = datasetEntity;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        DatasetObjectsEntity that = (DatasetObjectsEntity) o;
        return id == that.id &&
                Objects.equals(params, that.params);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, params);
    }
}
