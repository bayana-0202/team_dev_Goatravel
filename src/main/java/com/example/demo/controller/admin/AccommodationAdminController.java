package com.example.demo.controller.admin;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.example.demo.entity.Accommodation;
import com.example.demo.entity.Category;
import com.example.demo.repository.AccommodationRepository;
import com.example.demo.repository.CategoryRepository;

@Controller
public class AccommodationAdminController {

	@Autowired
	CategoryRepository categoryRepository;

	@Autowired
	AccommodationRepository accommodationRepository;

	//宿泊施設一覧表示
	@GetMapping("/admin/accommodation")
	public String index(
			@RequestParam(name = "categoryId", defaultValue = "") Integer categoryId,
			Model model) {

		//宿泊施設の種類の一覧を取得
		List<Category> categoryList = categoryRepository.findAll();
		model.addAttribute("categories", categoryList);

		//宿泊施設の一覧情報を取得
		List<Accommodation> accommodationList = null;
		if (categoryId == null) {
			accommodationList = accommodationRepository.findAll();//宿泊施設一覧
		} else {
			accommodationList = accommodationRepository.findByCategoryId(categoryId);//カテゴリー別宿泊施設一覧
		}
		model.addAttribute("accommodations", accommodationList);

		return "admin/adminHotels";
	}
}
