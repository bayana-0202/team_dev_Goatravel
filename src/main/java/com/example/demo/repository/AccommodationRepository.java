package com.example.demo.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.demo.entity.Accommodation;

public interface AccommodationRepository extends JpaRepository<Accommodation, Integer> {

	/**
	 * 宿一覧検索
	 * 日付を組み合わせるときはJPAで使用してください
	 * @param categoryId
	 * @param bathId
	 * @param languageId
	 * @return
	 */
	List<Accommodation> findByCategoryIdAndBathIdAndLanguageId(Integer categoryId, Integer bathId,
			Integer languageId);

	//宿泊施設のカテゴリー
	List<Accommodation> findByCategoryId(Integer categoryId);

}