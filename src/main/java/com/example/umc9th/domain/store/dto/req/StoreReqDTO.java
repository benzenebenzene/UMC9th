package com.example.umc9th.domain.store.dto.req;

import jakarta.validation.constraints.NotNull;

public class StoreReqDTO {
    public record StoreRequestDTO(
            @NotNull
            String storeName,
            @NotNull
            Long managerNumber
    ){}
}
