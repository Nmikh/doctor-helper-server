package com.models.doctor;

import com.models.entity.doctor.PageEntity;

import java.util.List;

public class PagesPageReturn {
    private int numberOfPages;
    private List<PageEntity> pageEntities;

    public PagesPageReturn(int numberOfPages, List<PageEntity> pageEntities) {
        this.numberOfPages = numberOfPages;
        this.pageEntities = pageEntities;
    }

    public int getNumberOfPages() {
        return numberOfPages;
    }

    public void setNumberOfPages(int numberOfPages) {
        this.numberOfPages = numberOfPages;
    }

    public List<PageEntity> getPageEntities() {
        return pageEntities;
    }

    public void setPageEntities(List<PageEntity> pageEntities) {
        this.pageEntities = pageEntities;
    }
}
