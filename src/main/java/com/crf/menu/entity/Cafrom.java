package com.crf.menu.entity;

import lombok.Data;

@Data
public class Cafrom {
    private Integer id;

    private Integer categoryBigId;

    private String categorySmall;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getCategoryBigId() {
        return categoryBigId;
    }

    public void setCategoryBigId(Integer categoryBigId) {
        this.categoryBigId = categoryBigId;
    }

    public String getCategorySmall() {
        return categorySmall;
    }

    public void setCategorySmall(String categorySmall) {
        this.categorySmall = categorySmall == null ? null : categorySmall.trim();
    }
}