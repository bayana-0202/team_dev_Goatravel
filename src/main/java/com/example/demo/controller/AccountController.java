package com.example.demo.controller;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import jakarta.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.example.demo.entity.User;
import com.example.demo.model.Account;
import com.example.demo.repository.UserRepository;

@Controller
public class AccountController {
	@Autowired
	HttpSession session;

	@Autowired
	Account account;

	@Autowired
	UserRepository userRepository;

	@GetMapping({ "/login", "/logout" })
	public String index(Model model) {
		session.invalidate();
		return "login";
	}

	@PostMapping("/login")
	public String login(
			@RequestParam(name = "email", defaultValue = "") String email,
			@RequestParam(name = "password", defaultValue = "") String password,
			Model model) {
		List<User> users = userRepository.findByEmailAndPassword(email, password);
		if (users.size() == 0) {
			model.addAttribute("message", "メールアドレスまたはパスワードが違います");
			return "login";
		} else if (users.size() == 1) {
			account.setId(users.get(0).getId());
			account.setName(users.get(0).getName());
			account.setTel(users.get(0).getTel());
			account.setAddress(users.get(0).getAddress());
			account.setPassword(users.get(0).getPassword());
			account.setGender(users.get(0).getGender());
			account.setNickname(users.get(0).getNickname());
			account.setEmail(users.get(0).getEmail());
			account.setBirthday(users.get(0).getBirthday());
		}
		return "redirect:/";
	}

	@GetMapping("/signup")
	public String signupView() {
		return "addAccount";
	}

	@PostMapping("/signup/confirm")
	public String confirm(
			@RequestParam(name = "name", defaultValue = "") String name,
			@RequestParam(name = "nickname", defaultValue = "") String nickname,
			@RequestParam(name = "password", defaultValue = "") String password,
			@RequestParam(name = "checkPassword", defaultValue = "") String checkPassword,
			@RequestParam(name = "email", defaultValue = "") String email,
			@RequestParam(name = "checkEmail", defaultValue = "") String checkEmail,
			@RequestParam(name = "tel", defaultValue = "") String tel,
			@RequestParam(name = "gender", defaultValue = "") Integer gender,
			@RequestParam(name = "birthday", defaultValue = "") LocalDate birthday,
			@RequestParam(name = "address", defaultValue = "") String address,
			Model model) {
		List<String> errorList = new ArrayList<>();
		if (name.length() == 0) {
			errorList.add("名前を入力してください");
		}
		if (nickname.length() == 0) {
			errorList.add("ニックネームを入力してください");
		}
		if (password.length() == 0) {
			errorList.add("パスワードを入力してください");
		}
		if (email.length() == 0) {
			errorList.add("メールアドレスを入力してください");
		}
		if (tel.length() == 0) {
			errorList.add("電話番号を入力してください");
		}
		if (gender == null) {
			errorList.add("性別を選択してください");
		}
		if (birthday == null) {
			errorList.add("生年月日を入力してください");
		}
		if (address.length() == 0) {
			errorList.add("住所を入力してください");
		}

		Optional<User> userEmail = userRepository.findByEmail(email);
		if (userEmail.isPresent()) {
			errorList.add("このメールアドレスは登録済みです");
		}
		Optional<User> userPassword = userRepository.findByTel(tel);
		if (userPassword.isPresent()) {
			errorList.add("この電話番号は登録済みです");
		}

		if (!(password.equals(checkPassword))) {
			errorList.add("パスワードが一致しませんでした");
		}
		if (!(email.equals(checkEmail))) {
			errorList.add("メールアドレスが一致しませんでした");
		}

		if (errorList.size() > 0) {
			model.addAttribute("errorList", errorList);
			return "addAccount";
		}
		model.addAttribute("name", name);
		model.addAttribute("nickname", nickname);
		model.addAttribute("password", password);
		model.addAttribute("checkPassword", checkPassword);
		model.addAttribute("email", email);
		model.addAttribute("checkEmail", checkEmail);
		model.addAttribute("tel", tel);
		model.addAttribute("gender", gender);
		model.addAttribute("birthday", birthday);
		model.addAttribute("address", address);

		String passwordMask = "●".repeat(password.length());
		model.addAttribute("passwordMask", passwordMask);

		return "confirmAccount";
	}

	@PostMapping("/signup/complete")
	public String complete(
			@RequestParam(name = "name", defaultValue = "") String name,
			@RequestParam(name = "tel", defaultValue = "") String tel,
			@RequestParam(name = "address", defaultValue = "") String address,
			@RequestParam(name = "password", defaultValue = "") String password,
			@RequestParam(name = "gender", defaultValue = "") Integer gender,
			@RequestParam(name = "nickname", defaultValue = "") String nickname,
			@RequestParam(name = "email", defaultValue = "") String email,
			@RequestParam(name = "birthday", defaultValue = "") LocalDate birthday,
			Model model) {
		User user = new User(name, tel, address, password, gender, nickname, email, birthday);
		userRepository.save(user);
		model.addAttribute("user", user);

		return "completeAccount";
	}

}
