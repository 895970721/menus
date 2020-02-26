package com.crf.menu.entity;

import lombok.Data;

@Data
public class MenuAccessories {
    private Integer id;

    private Integer menuId;

    private String accessories;

    private String dosage;

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

    public String getAccessories() {
        return accessories;
    }

    public void setAccessories(String accessories) {
        this.accessories = accessories == null ? null : accessories.trim();
    }

    public String getDosage() {
        return dosage;
    }

    public void setDosage(String dosage) {
        this.dosage = dosage == null ? null : dosage.trim();
    }
}