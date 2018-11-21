package com.bm.fmkj.domain;

import com.bm.fmkj.dao.PmImage;
import com.bm.fmkj.dao.PmTask;

import java.util.List;

/**
 * 任务明细
 */
public class TaskDto extends PmTask {

    /**
     * 任务照片
     */
    List<PmImage> images;

    public List<PmImage> getImages() {
        return images;
    }

    public void setImages(List<PmImage> images) {
        this.images = images;
    }
}
