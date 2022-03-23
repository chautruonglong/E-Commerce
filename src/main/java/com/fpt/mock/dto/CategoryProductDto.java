package com.fpt.mock.dto;

import java.util.List;
import lombok.Data;

@Data
public class CategoryProductDto {

    private String category;
    private List<IndexProductDto> indexProductDtoList;

}
