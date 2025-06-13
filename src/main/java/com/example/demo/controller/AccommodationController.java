package com.example.demo.controller;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.example.demo.entity.Accommodation;
import com.example.demo.entity.Plan;
import com.example.demo.repository.AccommodationRepository;
import com.example.demo.repository.BathTypeRepository;
import com.example.demo.repository.LanguageRepository;
import com.example.demo.repository.PlanRepository;

@Controller
public class AccommodationController {
	@Autowired
	AccommodationRepository accommodationRepository;

	@Autowired
	PlanRepository planRepository;

	@Autowired
	LanguageRepository languageRepository;

	@Autowired
	BathTypeRepository bathTypeRepository;

	@GetMapping("/")
	public String index() {
		return "search";
	}

	@PostMapping("/")
	public String search(
			@RequestParam(name = "startDate", defaultValue = "") LocalDate startDate,
			@RequestParam(name = "endDate", defaultValue = "") LocalDate endDate,
			@RequestParam(name = "address", defaultValue = "") String address,
			@RequestParam(name = "categoryId", defaultValue = "") Integer categoryId,
			@RequestParam(name = "bathId", defaultValue = "") Integer bathId,
			@RequestParam(name = "languageId", defaultValue = "") Integer languageId,
			Model model) {
		List<Plan> planList = planRepository.findByDateBetween(startDate, endDate);
		List<Accommodation> accommodations = new ArrayList<>();
		for (Plan plan : planList) {
			Accommodation accommendationList = accommodationRepository.findById(plan.getAccommodationId()).get();
			accommodations.add(accommendationList);

		}
		if (categoryId != null && bathId != null && languageId != null) {
			accommodations = accommodationRepository.findByCategoryIdAndBathIdAndLanguageId(categoryId, bathId,
					languageId);
		} else if (categoryId != null && bathId != null) {
			accommodations = accommodationRepository.findByCategoryIdAndBathId(categoryId, bathId);
		} else if (categoryId != null && languageId != null) {
			accommodations = accommodationRepository.findByCategoryIdAndLanguageId(categoryId, languageId);
		} else if (bathId != null && languageId != null) {
			accommodations = accommodationRepository.findByBathIdAndLanguageId(bathId, languageId);
		} else if (categoryId != null) {
			accommodations = accommodationRepository.findByCategoryId(categoryId);
		} else if (bathId != null) {
			accommodations = accommodationRepository.findByBathId(bathId);
		} else if (languageId != null) {
			accommodations = accommodationRepository.findByLanguageId(languageId);
		}

		for (Accommodation accommodation : accommodations) {
			Integer price = 999999999;
			LocalDate date = null;
			for (Plan plan : accommodation.getPlans()) {
				if (price > plan.getPrice()) {
					accommodation.setMinPrice(price);
					price = plan.getPrice();
					date = plan.getDate();
				}
			}
			accommodation.setMinPrice(price);
			accommodation.setMinDate(date);
		}
		model.addAttribute("startDate", startDate);
		model.addAttribute("endDate", endDate);
		model.addAttribute("address", address);
		model.addAttribute("bathId", bathId);
		model.addAttribute("categoryId", categoryId);
		model.addAttribute("languageId", languageId);
		model.addAttribute("accommodations", accommodations);
		return "accommodation";
	}

	@GetMapping("/search")
	public String searchListView() {
		return "accommodation";
	}
}
