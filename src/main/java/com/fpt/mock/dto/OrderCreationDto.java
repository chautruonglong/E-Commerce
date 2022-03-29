package com.fpt.mock.dto;

import java.util.UUID;
import javax.validation.constraints.NotNull;
import lombok.Data;

@Data
public class OrderCreationDto {

    @NotNull
    private UUID productId;

}
