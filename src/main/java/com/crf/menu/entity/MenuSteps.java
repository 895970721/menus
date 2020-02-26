package com.crf.menu.entity;

import lombok.Data;

@Data
public class MenuSteps {
    private Integer id;

    private Integer menuId;

    private Integer stepNum;

    private String stepWord;

    private String stepImg;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getMenuId() {
        return menuId;
    }

    public void setMenuId(Integer menuId) {
        this.menuId = menuId;
    }

    public Integer getStepNum() {
        return stepNum;
    }

    public void setStepNum(Integer stepNum) {
        this.stepNum = stepNum;
    }

    public String getStepWord() {
        return stepWord;
    }

    public void setStepWord(String stepWord) {
        this.stepWord = stepWord == null ? null : stepWord.trim();
    }

    public String getStepImg() {
        return stepImg;
    }

    public void setStepImg(String stepImg) {
        this.stepImg = stepImg == null ? null : stepImg.trim();
    }
}