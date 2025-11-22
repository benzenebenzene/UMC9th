package com.example.umc9th.domain.mission.dto.res;

import lombok.Builder;

import java.time.LocalDate;
import java.util.List;

public class MissionResDTO {

    @Builder
    public record MissionPreviewDTO(
            Long missionId,
            String conditional,
            Integer point,
            LocalDate deadline
    ) {}

    @Builder
    public record MissionPreviewListDTO(
            List<MissionPreviewDTO> missions,
            Integer listSize,
            Integer totalPage,
            Long totalElements,
            Boolean isFirst,
            Boolean isLast
    ) {}
}
