package com.example.umc9th.domain.store.dto.res;

import lombok.Builder;

import java.time.LocalDateTime;

public class StoreResDTO {

    @Builder
    public record StoreResponseDTO(
            Long storeId,
            LocalDateTime createdAt
    ){}


}
