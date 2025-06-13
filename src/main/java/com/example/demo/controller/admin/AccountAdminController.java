package com.example.demo.controller.admin;

import java.util.ArrayList;
import java.util.List;

import jakarta.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
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
	@GetMapping("/admin/login")
	public String index() {

		return "admin/adminLogin";
	}

	//ログイン処理
	@PostMapping("/admin/login")
	public String login(
			@RequestParam(name = "email", defaultValue = "") String email,
			@RequestParam(name = "password", defaultValue = "") String password,
			Model model) {

		//		String passwordMask = "●".repeat(password.length());
		//		model.addAttribute("passwordMask", passwordMask);

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

	//ログアウト
	@GetMapping("/admin/logout")
	public String logout() {
		session.invalidate();
		return "redirect:/admin/login";
	}

	//管理者の一覧画面
	@GetMapping("/admin/account")
	public String accouunt(Model model) {

		//全管理者の取得
		List<Admin> adminAccount = adminRepository.findAll();
		model.addAttribute("accounts", adminAccount);

		model.addAttribute("accountAdmin", accountAdmin);
		return "admin/adminAccount";
	}

	//管理者情報の更新画面
	@GetMapping("/admin/{id}/edit")
	public String edit(@PathVariable("id") Integer id, Model model) {
		Admin admin = adminRepository.findById(id).get();
		model.addAttribute("admin", admin);
		model.addAttribute("accountAdmin", accountAdmin);
		return "admin/adminEditAccount";
	}

	//管理者情報の更新処理
	@PostMapping("/admin/{id}/edit")
	public String update(
			@PathVariable("id") Integer id,
			@RequestParam(name = "name", defaultValue = "") String name,
			@RequestParam(name = "email", defaultValue = "") String email,
			@RequestParam(name = "tel", defaultValue = "") String tel,
			@RequestParam(name = "password", defaultValue = "") String password,
			@RequestParam(name = "password_confirm", defaultValue = "") String password_confirm,
			Model model) {

		Admin admins = new Admin(id, name, email, tel, password);

		//入力チェック
		ArrayList<String> errors = new ArrayList<>();
		if (name.length() == 0) {
			errors.add("名前を入力してください");
		}
		if (email.length() == 0) {
			errors.add("メールアドレスを入力してください");
		}
		if (tel.length() == 0) {
			errors.add("電話番号を入力してください");
		}
		if (password.length() == 0) {
			errors.add("パスワードを入力してください");
		} else if (!(password.equals(password_confirm))) {
			errors.add("パスワードが一致しませんでした");
		}

		if (errors.size() > 0) {
			model.addAttribute("errors", errors);
			model.addAttribute("admin", admins);
			model.addAttribute("accountAdmin", accountAdmin);
			return "admin/adminEditAccount";
		}

		String passwordMask = "●".repeat(password.length());
		model.addAttribute("passwordMask", passwordMask);

		Admin admin = adminRepository.findById(id).get();
		admin.setName(name);
		admin.setTel(tel);
		admin.setPassword(password);

		model.addAttribute("admin", admin);
		accountAdmin.setName(name);
		accountAdmin.setId(id);
		adminRepository.save(admin);
		model.addAttribute("accountAdmin", accountAdmin);
		return "redirect:/admin/account";
	}
}
