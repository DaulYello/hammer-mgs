package com.bm.fmkj.domain;

import com.bm.fmkj.dao.PmStrategy;

public class PmStrategyDto extends PmStrategy{

    private String title;

    private String imageUrl;

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

}
