package com.example.umc9th.domain.mission.service;

import com.example.umc9th.domain.member.exception.MemberException;
import com.example.umc9th.domain.member.exception.code.MemberErrorCode;
import com.example.umc9th.domain.member.repository.MemberRepository;
import com.example.umc9th.domain.mission.converter.MemberMissionConverter;
import com.example.umc9th.domain.mission.dto.res.MemberMissionResDTO;
import com.example.umc9th.domain.mission.entity.MemberMission;
import com.example.umc9th.domain.mission.repository.MemberMissionRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class MemberMissionQueryService {
    private final MemberMissionRepository memberMissionRepository;
    private final MemberRepository memberRepository;

    // 내가 진행중인 미션 목록 조회
    public MemberMissionResDTO.MemberMissionListDTO getMyInProgressMissions(Long memberId, Integer page) {

        memberRepository.findById(memberId)
                .orElseThrow(() -> new MemberException(MemberErrorCode.NOT_FOUND));

        PageRequest pageRequest = PageRequest.of(page - 1, 10);
        Page<MemberMission> result = memberMissionRepository.findAllByMemberIdAndCompleteFalse(memberId, pageRequest);

        return MemberMissionConverter.toMemberMissionListDTO(result);
    }

}
