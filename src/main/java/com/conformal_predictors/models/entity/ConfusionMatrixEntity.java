package com.conformal_predictors.models.entity;

import com.models.entity.specialist.DatasetConfigurationEntity;

import javax.persistence.*;

@Entity
@Table(name = "CONFUSION_MATRIX")
public class ConfusionMatrixEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID", nullable = false)
    private long id;

    @Basic
    @Column(name = "EPSILON", nullable = true, precision = 0)
    private Double epsilon;

    @Basic
    @Column(name = "ACTIVE_PREDICTED_INACTIVE", nullable = true)
    private Long activePredictedInactive;

    @Basic
    @Column(name = "INACTIVE_PREDICTED_ACTIVE", nullable = true)
    private Long inactivePredictedActive;

    @Basic
    @Column(name = "INACTIVE_PREDICTED_INACTIVE", nullable = true)
    private Long inactivePredictedInactive;

    @Basic
    @Column(name = "ACTIVE_PREDICTED_ACTIVE", nullable = true)
    private Long activePredictedActive;

    @Basic
    @Column(name = "EMPTY_PREDICTIONS", nullable = true)
    private Long emptyPredictions;

    @Basic
    @Column(name = "UNCERTAIN_PREDICTIONS", nullable = true)
    private Long uncertainPredictions;

    @ManyToOne(fetch = FetchType.EAGER, cascade=CascadeType.REMOVE)
    @JoinColumn(name = "CONFIGURATION_ID")
    private DatasetConfigurationEntity datasetConfigurationEntity;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public Double getEpsilon() {
        return epsilon;
    }

    public void setEpsilon(Double epsilon) {
        this.epsilon = epsilon;
    }

    public Long getActivePredictedInactive() {
        return activePredictedInactive;
    }

    public void setActivePredictedInactive(Long activePredictedInactive) {
        this.activePredictedInactive = activePredictedInactive;
    }

    public Long getInactivePredictedActive() {
        return inactivePredictedActive;
    }

    public void setInactivePredictedActive(Long inactivePredictedActive) {
        this.inactivePredictedActive = inactivePredictedActive;
    }

    public Long getInactivePredictedInactive() {
        return inactivePredictedInactive;
    }

    public void setInactivePredictedInactive(Long inactivePredictedInactive) {
        this.inactivePredictedInactive = inactivePredictedInactive;
    }

    public Long getActivePredictedActive() {
        return activePredictedActive;
    }

    public void setActivePredictedActive(Long activePredictedActive) {
        this.activePredictedActive = activePredictedActive;
    }

    public Long getEmptyPredictions() {
        return emptyPredictions;
    }

    public void setEmptyPredictions(Long emptyPredictions) {
        this.emptyPredictions = emptyPredictions;
    }

    public Long getUncertainPredictions() {
        return uncertainPredictions;
    }

    public void setUncertainPredictions(Long uncertainPredictions) {
        this.uncertainPredictions = uncertainPredictions;
    }

    public DatasetConfigurationEntity getDatasetConfigurationEntity() {
        return datasetConfigurationEntity;
    }

    public void setDatasetConfigurationEntity(DatasetConfigurationEntity datasetConfigurationEntity) {
        this.datasetConfigurationEntity = datasetConfigurationEntity;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        ConfusionMatrixEntity that = (ConfusionMatrixEntity) o;

        if (id != that.id) return false;
        if (epsilon != null ? !epsilon.equals(that.epsilon) : that.epsilon != null) return false;
        if (activePredictedInactive != null ? !activePredictedInactive.equals(that.activePredictedInactive) : that.activePredictedInactive != null)
            return false;
        if (inactivePredictedActive != null ? !inactivePredictedActive.equals(that.inactivePredictedActive) : that.inactivePredictedActive != null)
            return false;
        if (inactivePredictedInactive != null ? !inactivePredictedInactive.equals(that.inactivePredictedInactive) : that.inactivePredictedInactive != null)
            return false;
        if (activePredictedActive != null ? !activePredictedActive.equals(that.activePredictedActive) : that.activePredictedActive != null)
            return false;
        if (emptyPredictions != null ? !emptyPredictions.equals(that.emptyPredictions) : that.emptyPredictions != null)
            return false;
        if (uncertainPredictions != null ? !uncertainPredictions.equals(that.uncertainPredictions) : that.uncertainPredictions != null)
            return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = (int) (id ^ (id >>> 32));
        result = 31 * result + (epsilon != null ? epsilon.hashCode() : 0);
        result = 31 * result + (activePredictedInactive != null ? activePredictedInactive.hashCode() : 0);
        result = 31 * result + (inactivePredictedActive != null ? inactivePredictedActive.hashCode() : 0);
        result = 31 * result + (inactivePredictedInactive != null ? inactivePredictedInactive.hashCode() : 0);
        result = 31 * result + (activePredictedActive != null ? activePredictedActive.hashCode() : 0);
        result = 31 * result + (emptyPredictions != null ? emptyPredictions.hashCode() : 0);
        result = 31 * result + (uncertainPredictions != null ? uncertainPredictions.hashCode() : 0);
        return result;
    }
}
