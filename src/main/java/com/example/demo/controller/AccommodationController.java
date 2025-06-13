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
import com.example.demo.repository.PlanRepository;

@Controller
public class AccommodationController {
	@Autowired
	AccommodationRepository accommodationRepository;

	@Autowired
	PlanRepository planRepository;

	@GetMapping("/")
	public String index() {
		return "search";
	}

	@PostMapping("/")
	public String search(
			@RequestParam(name = "startDate", defaultValue = "") LocalDate startDate,
			@RequestParam(name = "endDate", defaultValue = "") LocalDate endDate,
			@RequestParam(name = "address", defaultValue = "") String address,
			@RequestParam(name = "languageId", defaultValue = "") Integer languageId,
			@RequestParam(name = "categoryId", defaultValue = "") Integer categoryId,
			Model model) {
		List<Plan> planList = planRepository.findByDateBetween(startDate, endDate);
		List<Accommodation> accommodations = new ArrayList<>();
		for (Plan plan : planList) {
			Accommodation accommendationList = accommodationRepository.findById(plan.getAccommodationId()).get();
			accommodations.add(accommendationList);

		}
		if (languageId != null && categoryId != null) {
			accommodations = accommodationRepository.findByCategoryIdAndLanguageId(languageId, categoryId);
		} else if (languageId != null) {
			accommodations = accommodationRepository.findByLanguageId(languageId);
		} else if (categoryId != null) {
			accommodations = accommodationRepository.findByCategoryId(categoryId);
		}

		model.addAttribute("startDate", startDate);
		model.addAttribute("endDate", endDate);
		model.addAttribute("address", address);
		model.addAttribute("languagedId", languageId);
		model.addAttribute("categoryId", categoryId);
		model.addAttribute("accommodations", accommodations);
		return "search";
	}

	@GetMapping("/search")
	public String searchListView() {
		return "accommodation";
	}
}
