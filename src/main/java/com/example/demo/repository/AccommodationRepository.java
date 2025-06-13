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

	List<Accommodation> findByCategoryIdAndBathIdAndLanguageId(Integer categoryId, Integer bathId, Integer languageId);

	List<Accommodation> findByCategoryIdAndBathId(Integer categoryId, Integer bathId);

	List<Accommodation> findByCategoryIdAndLanguageId(Integer categoryId, Integer languageId);

	List<Accommodation> findByBathIdAndLanguageId(Integer bathId, Integer languageId);

	List<Accommodation> findByCategoryId(Integer categoryId);

	List<Accommodation> findByBathId(Integer bathId);

	List<Accommodation> findByLanguageId(Integer languageId);

}