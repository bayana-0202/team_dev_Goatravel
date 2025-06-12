package com.example.demo.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.demo.entity.Accommodation;

public interface AccommodationRepository extends JpaRepository<Accommodation, Integer> {

	//宿泊施設のカテゴリー、対応言語
	List<Accommodation> findByCategoryIdAndLanguageId(Integer categoryId, Integer languageId);

}