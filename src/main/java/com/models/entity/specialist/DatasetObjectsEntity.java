package com.models.entity.specialist;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Table(name = "DATASET_OBJECTS")
public class DatasetObjectsEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID")
    private long id;

    @Basic
    @Column(name = "PARAMS")
    private String params;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "DATASET_ID")
    private DatasetEntity datasetEntity;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getParams() {
        return params;
    }

    public void setParams(String params) {
        this.params = params;
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
