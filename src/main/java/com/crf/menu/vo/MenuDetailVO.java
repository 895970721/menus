package com.crf.menu.vo;

import lombok.Data;

import java.util.List;

@Data
public class MenuDetailVO {
    private Integer id;

    private String name;

    private String menuImg;

    private List<String> mainIngredient;

    private List<String> dosage;

    private List<String> accessories;

    private List<String> acdosage;    // 配料量,命名重复

    private List<Integer> stepNum;

    private List<String> stepWord;

    private List<String> stepImg;
}
