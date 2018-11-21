package com.bm.fmkj.domain;

import com.bm.fmkj.dao.PmPart;

public class PmPartDto extends PmPart {

    private String imageUrl;

    private String nickName;

    private String title;

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public String getNickName() {
        return nickName;
    }

    public void setNickName(String nickName) {
        this.nickName = nickName;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }
}
