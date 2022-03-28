package com.fpt.mock.dto;

import java.util.UUID;
import javax.validation.constraints.NotNull;
import lombok.Data;

@Data
public class OrderDto {

    @NotNull
    private UUID productId;

    @NotNull
    private UUID customerId;

}
