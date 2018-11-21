package com.bm.fmkj.domain;

import com.bm.fmkj.dao.PmTask;



/**
 * 任务明细
 */
public class TaskDto extends PmTask {



    private String logoUrl;

    private String imageUrl;

    public String getLogoUrl() {
        return logoUrl;
    }

    public void setLogoUrl(String logoUrl) {
        this.logoUrl = logoUrl;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }
}
