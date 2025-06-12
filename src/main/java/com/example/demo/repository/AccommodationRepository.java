package com.example.demo.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.demo.entity.Accommodation;

public interface AccommodationRepository extends JpaRepository<Accommodation, Integer> {

	//宿泊施設のカテゴリー
	List<Accommodation> findByCategoryId(Integer categoryId);

}