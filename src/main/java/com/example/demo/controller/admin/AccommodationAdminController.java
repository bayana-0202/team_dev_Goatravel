package com.example.demo.controller.admin;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.example.demo.entity.Accommodation;
import com.example.demo.entity.Category;
import com.example.demo.entity.Reserve;
import com.example.demo.entity.User;
import com.example.demo.repository.AccommodationRepository;
import com.example.demo.repository.CategoryRepository;
import com.example.demo.repository.ReserveRepository;
import com.example.demo.repository.UserRepository;

@Controller
public class AccommodationAdminController {

	@Autowired
	CategoryRepository categoryRepository;

	@Autowired
	AccommodationRepository accommodationRepository;

	@Autowired
	ReserveRepository reserveRepository;

	@Autowired
	UserRepository userRepository;

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

	//宿泊施設の新規登録画面表示
	@GetMapping("/admin/add")
	public String create() {
		return "admin/adminAddHotels";
	}

	//新規作成し、確認画面に遷移する
	@PostMapping("/admin/check")
	public String check(
			@RequestParam(name = "name", defaultValue = "") String name,
			@RequestParam(name = "categoryId", defaultValue = "") Integer categoryId,
			@RequestParam(name = "address", defaultValue = "") String address,
			@RequestParam(name = "tel", defaultValue = "") String tel,
			@RequestParam(name = "languageId", defaultValue = "") Integer languageId,
			@RequestParam(name = "content", defaultValue = "") String content,
			Model model) {

		Accommodation accommodation = new Accommodation(categoryId, name, tel, address, languageId,
				content);
		accommodationRepository.save(accommodation);

		return "admin/adminConfirmHotels";
	}

	//確認画面からホテル一覧にリダイレクトする
	@PostMapping("/admin/check/add")
	public String add() {
		return "redirect:/admin/accommodation";
	}

	//予約画面を一覧で表示する
	@GetMapping("/admin/reserve")
	public String booking(@RequestParam(name = "reserveId", defaultValue = "") Integer reserveId, Model model) {

		List<Reserve> reserveList = reserveRepository.findAll();
		model.addAttribute("reserves", reserveList);

		List<User> userList = userRepository.findAll();
		model.addAttribute("users", userList);

		return "admin/adminReserve";
	}
}
