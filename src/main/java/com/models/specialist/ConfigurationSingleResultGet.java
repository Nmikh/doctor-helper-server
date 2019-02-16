package com.models.specialist;

import java.io.Serializable;

public class ConfigurationSingleResultGet implements Serializable {
    private String params;
    private Double significance;

    public String getParams() {
        return params;
    }

    public void setParams(String params) {
        this.params = params;
    }

    public Double getSignificance() {
        return significance;
    }

    public void setSignificance(Double significance) {
        this.significance = significance;
    }
}
