package com.example.demo.repository;

import java.time.LocalDate;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.demo.entity.Plan;

public interface PlanRepository extends JpaRepository<Plan, Integer> {

	List<Plan> findByRoomIdAndPriceAndAccommodationIdAndDateBetween(Integer roomId, Integer price,
			Integer accommodationId, LocalDate startDate, LocalDate endDate);

}
