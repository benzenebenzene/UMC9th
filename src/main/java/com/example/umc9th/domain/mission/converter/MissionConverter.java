package com.example.umc9th.domain.mission.converter;

import com.example.umc9th.domain.mission.dto.res.MissionResDTO;
import com.example.umc9th.domain.mission.entity.Mission;
import org.springframework.data.domain.Page;

public class MissionConverter {
    public static MissionResDTO.MissionPreviewDTO toMissionPreviewDTO(Mission mission) {
        return MissionResDTO.MissionPreviewDTO.builder()
                .missionId(mission.getId())
                .conditional(mission.getConditional())
                .point(mission.getPoint())
                .deadline(mission.getDeadline())
                .build();
    }

    public static MissionResDTO.MissionPreviewListDTO toMissionPreviewListDTO(Page<Mission> result) {
        return MissionResDTO.MissionPreviewListDTO.builder()
                .missions(result.getContent().stream()
                        .map(MissionConverter::toMissionPreviewDTO)
                        .toList()
                )
                .listSize(result.getSize())
                .totalPage(result.getTotalPages())
                .totalElements(result.getTotalElements())
                .isFirst(result.isFirst())
                .isLast(result.isLast())
                .build();
    }
}
