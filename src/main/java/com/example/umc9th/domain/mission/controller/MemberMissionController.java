package com.example.umc9th.domain.mission.controller;

import com.example.umc9th.domain.mission.dto.res.MemberMissionResDTO;
import com.example.umc9th.domain.mission.exception.code.MissionSuccessCode;
import com.example.umc9th.domain.mission.service.MemberMissionQueryService;
import com.example.umc9th.global.apiPayload.ApiResponse;
import com.example.umc9th.global.validation.annotation.ValidPage;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import lombok.RequiredArgsConstructor;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@Validated
@RequestMapping("/missions/member")
public class MemberMissionController {
    private final MemberMissionQueryService memberMissionQueryService;

    @Operation(
            summary = "내가 진행중인 미션 목록 조회 API",
            description = "특정 멤버가 진행중인 미션만 10개씩 페이지네이션으로 조회합니다."
    )
    @ApiResponses({
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "200", description = "성공"),
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "400", description = "page 값이 1 미만인 경우 등 잘못된 요청")
    })
    @GetMapping("/my")
    public ApiResponse<MemberMissionResDTO.MemberMissionListDTO> getMyInProgressMissions(
            @RequestParam Long memberId,
            @ValidPage @RequestParam Integer page
    ) {

        return ApiResponse.onSuccess(MissionSuccessCode.FOUND, memberMissionQueryService.getMyInProgressMissions(memberId, page));
    }
}
