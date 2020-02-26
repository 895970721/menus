package com.crf.menu.vo;

import com.crf.menu.dto.CategorySmallDTO;
import lombok.Data;

import java.util.List;

@Data
public class CategoryVO {
    private Integer categoryBigId;

    private String categoryBigWord;

    private List<CategorySmallDTO> categorySmallDTOList;
}
