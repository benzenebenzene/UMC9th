package com.example.umc9th.domain.store.service;

import com.example.umc9th.domain.store.converter.StoreConverter;
import com.example.umc9th.domain.store.dto.req.StoreReqDTO;
import com.example.umc9th.domain.store.dto.res.StoreResDTO;
import com.example.umc9th.domain.store.entity.Location;
import com.example.umc9th.domain.store.entity.Store;
import com.example.umc9th.domain.store.exception.LocationException;
import com.example.umc9th.domain.store.exception.code.LocationErrorCode;
import com.example.umc9th.domain.store.repository.LocationRepository;
import com.example.umc9th.domain.store.repository.StoreRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class StoreCommandService {

    private final LocationRepository locationRepository;
    private final StoreRepository storeRepository;

    public StoreResDTO.StoreResponseDTO createStore(Long locationId, StoreReqDTO.StoreRequestDTO dto){
        Location location = locationRepository.findLocationById(locationId).orElseThrow(()->
                new LocationException(LocationErrorCode.NOT_FOUND));

        Store store = storeRepository.save(StoreConverter.toStore(location, dto));
        return StoreConverter.toStoreDTO(store);
    }
}
