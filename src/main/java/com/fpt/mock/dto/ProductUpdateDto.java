package com.fpt.mock.dto;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import lombok.Data;
import org.springframework.web.multipart.MultipartFile;

@Data
public class ProductUpdateDto {

    @NotNull
    @NotBlank
    private String name;

    @NotNull
    @NotBlank
    private String category;

    @Min(0)
    private double price;

    @Min(0)
    private double discount;

    private String description;

    private MultipartFile thumbnailImage;

}
