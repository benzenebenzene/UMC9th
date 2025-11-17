package com.example.umc9th.domain.member.repository;

import com.example.umc9th.domain.member.entity.Member;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MemberRepository extends JpaRepository<Member, Long> {

    @Query(value = "SELECT * " +
            "FROM member m " +
            "WHERE m.deleted_at IS NULL AND m.point >= :point", nativeQuery = true)
    List<Member> searchMemberByPoint(
            @Param("point") Long point
    );


}
