package com.example.umc9th.domain.store.controller;

import com.example.umc9th.domain.store.dto.req.StoreReqDTO;
import com.example.umc9th.domain.store.dto.res.StoreResDTO;
import com.example.umc9th.domain.store.exception.code.StoreSuccessCode;
import com.example.umc9th.domain.store.service.StoreCommandService;
import com.example.umc9th.global.apiPayload.ApiResponse;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api")
@Transactional
@Validated
public class StoreController {

    private final StoreCommandService storeCommandService;

    @PostMapping("/locations/{locationId}/stores")
    public ApiResponse<StoreResDTO.StoreResponseDTO> setStore(
            @PathVariable Long regionId,
            @Valid @RequestBody StoreReqDTO.StoreRequestDTO dto
    ) {
        return ApiResponse.onSuccess(StoreSuccessCode.CREATED, storeCommandService.createStore(regionId, dto));
    }

}
