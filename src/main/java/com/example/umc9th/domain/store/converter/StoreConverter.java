package com.example.umc9th.domain.store.converter;

import com.example.umc9th.domain.store.dto.req.StoreReqDTO;
import com.example.umc9th.domain.store.dto.res.StoreResDTO;
import com.example.umc9th.domain.store.entity.Location;
import com.example.umc9th.domain.store.entity.Store;

public class StoreConverter {

    // 엔티티 -> DTO
    public static StoreResDTO.StoreResponseDTO toStoreDTO(Store store) {
        return StoreResDTO.StoreResponseDTO.builder()
                .storeId(store.getId())
                .createdAt(store.getCreatedAt())
                .build();
    }

    // DTO -> 엔티티
    public static Store toStore(Location location , StoreReqDTO.StoreRequestDTO dto) {
        return Store.builder()
                .storeName(dto.storeName())
                .managerNumber(dto.managerNumber())
                .location(location)
                .build();
    }


}
