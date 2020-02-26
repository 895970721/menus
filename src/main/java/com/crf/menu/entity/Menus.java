package com.crf.menu.entity;

import lombok.Data;

@Data
public class Menus {
    private Integer id;

    private String name;

    private String menuImg;

    private Integer cafromId;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name == null ? null : name.trim();
    }

    public String getMenuImg() {
        return menuImg;
    }

    public void setMenuImg(String menuImg) {
        this.menuImg = menuImg == null ? null : menuImg.trim();
    }

    public Integer getCafromId() {
        return cafromId;
    }

    public void setCafromId(Integer cafromId) {
        this.cafromId = cafromId;
    }
}