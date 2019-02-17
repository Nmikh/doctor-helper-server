package com.models.specialist;

import java.io.Serializable;

public class ConfigurationGet implements Serializable {

    private String name;
    private Double eps;
    private Double degree;
    private Double probability;
    private Double gamma;
    private Double c;
    private Double nu;
    private Double testPart;
    private Long svmParameter;
    private Long kernelParametr;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
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

    public Long getSvmParameter() {
        return svmParameter;
    }

    public void setSvmParameter(Long svmParameter) {
        this.svmParameter = svmParameter;
    }

    public Long getKernelParametr() {
        return kernelParametr;
    }

    public void setKernelParametr(Long kernelParametr) {
        this.kernelParametr = kernelParametr;
    }
}
