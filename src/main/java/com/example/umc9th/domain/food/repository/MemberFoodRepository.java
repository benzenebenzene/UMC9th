package com.example.umc9th.domain.food.repository;


import com.example.umc9th.domain.food.entity.MemberFood;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MemberFoodRepository extends JpaRepository<MemberFood, Long> {
}
