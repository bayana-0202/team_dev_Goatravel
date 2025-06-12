package com.example.demo.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.demo.entity.Accommodation;

public interface AccommodationRepository extends JpaRepository<Accommodation, Integer> {
	enum BathType {
		ユニットバス, 風呂, シャワールーム
	}

	/**
	 * 宿一覧検索
	 * 日付を組み合わせるときはJPAで使用してください
	 * @param categoryId
	 * @param bath
	 * @param languageId
	 * @return
	 */
	List<Accommodation> findByCategoryIdAndBathAndLanguageId(Integer categoryId, BathType bath,
			Integer languageId);

}