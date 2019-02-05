package com.models.specialist;

public class ConfigurationGet {

    private String name;

    private Double eps;

    private Double degree;

    private Double probability;

    private Double gamma;

    private Double c;

    private Double nu;

    private Long svmParametr;

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

    public Long getSvmParametr() {
        return svmParametr;
    }

    public void setSvmParametr(Long svmParametr) {
        this.svmParametr = svmParametr;
    }

    public Long getKernelParametr() {
        return kernelParametr;
    }

    public void setKernelParametr(Long kernelParametr) {
        this.kernelParametr = kernelParametr;
    }
}