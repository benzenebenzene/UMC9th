package com.example.umc9th.domain.mission.dto.res;

import lombok.Builder;

import java.time.LocalDate;
import java.util.List;

public class MemberMissionResDTO {

    @Builder
    public record MemberMissionDTO(
            Long missionId,
            String conditional,
            Integer point,
            LocalDate deadline,
            boolean complete
    ) {}

    @Builder
    public record MemberMissionListDTO(
            List<MemberMissionDTO> missions,
            Integer listSize,
            Integer totalPage,
            Long totalElements,
            Boolean isFirst,
            Boolean isLast
    ) {}
}
