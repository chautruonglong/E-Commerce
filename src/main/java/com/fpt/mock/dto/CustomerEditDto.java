package com.fpt.mock.dto;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import lombok.Data;

@Data
public class CustomerEditDto {

    @NotNull
    @NotBlank
    private String password;

    @NotNull
    @NotBlank
    private String name;

    private String address;

    private String phone;

}
