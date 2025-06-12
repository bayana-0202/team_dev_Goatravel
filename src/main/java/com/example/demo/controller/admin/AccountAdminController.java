package com.example.demo.controller.admin;

import java.util.List;

import jakarta.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.example.demo.entity.Admin;
import com.example.demo.model.AccountAdmin;
import com.example.demo.repository.AdminRepository;

@Controller
public class AccountAdminController {
	@Autowired
	HttpSession session;

	@Autowired
	AccountAdmin accountAdmin;

	@Autowired
	AdminRepository adminRepository;

	//管理者のログイン画面を表示
	@GetMapping({ "/admin/login", "/admin/logout" })
	public String index() {

		return "admin/adminLogin";
	}

	//ログイン処理
	@PostMapping("/admin/login")
	public String login(
			@RequestParam(name = "email", defaultValue = "") String email,
			@RequestParam(name = "password", defaultValue = "") String password,
			Model model) {

		//メール、パスワードが空の場合、エラーとする
		if (email.length() == 0 || password.length() == 0) {
			model.addAttribute("errormsg", "メールアドレスとパスワードを入力してください");
			return "admin/adminLogin";
		}
		//		if (email == null || email.length() == 0) {
		//			model.addAttribute("errormsg", "メールアドレスは必須です");
		//			return "admin/adminLogin";
		//		} else if (password == null || password.length() == 0) {
		//			model.addAttribute("errormsg", "パスワードは必須です");
		//			return "admin/adminLogin";
		//		}

		List<Admin> error = null;
		error = adminRepository.findByEmailAndPassword(email, password);
		if (error.size() == 1) {
			accountAdmin.setName(error.get(0).getName());
			accountAdmin.setId(error.get(0).getId());
			return "admin/adminHotels";
		} else {
			model.addAttribute("errormsg", "メールアドレスとパスワードが一致しません");
			return "admin/adminLogin";
		}

	}
}
