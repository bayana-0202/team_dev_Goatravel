package com.example.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import com.example.demo.repository.AccommodationRepository;

@Controller
public class AccommodationController {
	@Autowired
	AccommodationRepository accommodationRepository;

	@GetMapping("/")
	public String index() {
		return "searchView";
	}

	@GetMapping("/search")
	public String searchListView() {
		return "searchListView";
	}
}
