package com.example.demo.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import com.example.demo.entity.Accommodation;
import com.example.demo.entity.Plan;
import com.example.demo.entity.Reserve;
import com.example.demo.entity.RoomType;
import com.example.demo.repository.AccommodationRepository;
import com.example.demo.repository.PlanRepository;
import com.example.demo.repository.ReserveRepository;
import com.example.demo.repository.RoomTypeRepository;

@Controller
public class ReserveController {
	@Autowired
	PlanRepository planRepository;
	@Autowired
	ReserveRepository reserveRepository;
	@Autowired
	AccommodationRepository accommodationRepository;
	@Autowired
	RoomTypeRepository roomTypeRepository;

	@GetMapping("/reservedList")
	public String reservedListView(Model model) {
		//reservesテーブルから予約の一覧を取得
		List<Reserve> reserveList = reserveRepository.findAll();
		List<Plan> planList = planRepository.findAll();
		List<Accommodation> accommodationList = accommodationRepository.findAll();
		List<RoomType> roomTypeList = roomTypeRepository.findAll();
		model.addAttribute("reserves", reserveList);
		model.addAttribute("plans", planList);
		model.addAttribute("accommodations", accommodationList);
		model.addAttribute("roomType", roomTypeList);
		return "reserveHistoryList";

	}

}
