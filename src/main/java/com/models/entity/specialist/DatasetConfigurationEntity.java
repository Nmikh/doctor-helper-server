package com.models.entity.specialist;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Objects;

@Entity
@Table(name = "DATASET_CONFIGURATION")
public class DatasetConfigurationEntity implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID")
    private long id;

    @Basic
    @Column(name = "NAME")
    private String name;

    @Basic
    @Column(name = "ACTIVE")
    private Boolean active;

    @Basic
    @Column(name = "EPS")
    private Double eps;

    @Basic
    @Column(name = "DEGREE")
    private Double degree;

    @Basic
    @Column(name = "PROBABILITY")
    private Double probability;

    @Basic
    @Column(name = "GAMMA")
    private Double gamma;

    @Basic
    @Column(name = "C")
    private Double c;

    @Basic
    @Column(name = "NU")
    private Double nu;

    @Basic
    @Column(name = "TEST_PART")
    private Double testPart;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "SVM_TYPE_ID")
    private SvmKernelParametrsEntity svmParameter;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "KERNEL_TYPE_ID")
    private SvmKernelParametrsEntity kernelParameter;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "DATASET_ID")
    private DatasetEntity datasetEntity;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "SPECIALIST_ID")
    private SpecialistEntity specialistEntity;

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

    public Boolean getActive() {
        return active;
    }

    public void setActive(Boolean active) {
        this.active = active;
    }

    public Double getEps() {
        return eps;
    }

    public void setEps(Double eps) {
        this.eps = eps;
    }

    public Double getDegree() {
        return degree;
    }

    public void setDegree(Double degree) {
        this.degree = degree;
    }

    public Double getProbability() {
        return probability;
    }

    public void setProbability(Double probability) {
        this.probability = probability;
    }

    public Double getGamma() {
        return gamma;
    }

    public void setGamma(Double gamma) {
        this.gamma = gamma;
    }

    public Double getC() {
        return c;
    }

    public void setC(Double c) {
        this.c = c;
    }

    public Double getNu() {
        return nu;
    }

    public void setNu(Double nu) {
        this.nu = nu;
    }

    public Double getTestPart() {
        return testPart;
    }

    public void setTestPart(Double testPart) {
        this.testPart = testPart;
    }

    public SvmKernelParametrsEntity getSvmParameter() {
        return svmParameter;
    }

    public void setSvmParameter(SvmKernelParametrsEntity svmParametr) {
        this.svmParameter = svmParametr;
    }

    public SvmKernelParametrsEntity getKernelParameter() {
        return kernelParameter;
    }

    public void setKernelParameter(SvmKernelParametrsEntity kernelParametr) {
        this.kernelParameter = kernelParametr;
    }

    public DatasetEntity getDatasetEntity() {
        return datasetEntity;
    }

    public void setDatasetEntity(DatasetEntity datasetEntity) {
        this.datasetEntity = datasetEntity;
    }

    public SpecialistEntity getSpecialistEntity() {
        return specialistEntity;
    }

    public void setSpecialistEntity(SpecialistEntity specialistEntity) {
        this.specialistEntity = specialistEntity;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        DatasetConfigurationEntity that = (DatasetConfigurationEntity) o;
        return id == that.id &&
                Objects.equals(name, that.name) &&
                Objects.equals(active, that.active) &&
                Objects.equals(eps, that.eps) &&
                Objects.equals(degree, that.degree) &&
                Objects.equals(probability, that.probability) &&
                Objects.equals(gamma, that.gamma) &&
                Objects.equals(c, that.c) &&
                Objects.equals(nu, that.nu);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, active, eps, degree, probability, gamma, c, nu);
    }
}
