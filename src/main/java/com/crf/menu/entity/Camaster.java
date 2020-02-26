package com.crf.menu.entity;

import lombok.Data;

@Data
public class Camaster {
    private Integer id;

    private String categoryBig;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getCategoryBig() {
        return categoryBig;
    }

    public void setCategoryBig(String categoryBig) {
        this.categoryBig = categoryBig == null ? null : categoryBig.trim();
    }
}