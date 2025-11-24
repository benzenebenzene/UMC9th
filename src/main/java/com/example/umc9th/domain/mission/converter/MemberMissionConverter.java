package com.example.umc9th.domain.mission.converter;

import com.example.umc9th.domain.mission.dto.res.MemberMissionResDTO;
import com.example.umc9th.domain.mission.entity.MemberMission;
import com.example.umc9th.domain.mission.entity.Mission;
import org.springframework.data.domain.Page;

public class MemberMissionConverter {
    public static MemberMissionResDTO.MemberMissionDTO toMemberMissionDTO(MemberMission mm) {
        Mission mission = mm.getMission();

        return MemberMissionResDTO.MemberMissionDTO.builder()
                .missionId(mission.getId())
                .conditional(mission.getConditional())
                .point(mission.getPoint())
                .deadline(mission.getDeadline())
                .complete(mm.isComplete())
                .build();
    }

    public static MemberMissionResDTO.MemberMissionListDTO toMemberMissionListDTO(Page<MemberMission> result) {
        return MemberMissionResDTO.MemberMissionListDTO.builder()
                .missions(result.getContent().stream()
                        .map(MemberMissionConverter::toMemberMissionDTO)
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
