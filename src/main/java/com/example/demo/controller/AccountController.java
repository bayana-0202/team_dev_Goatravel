package com.example.demo.controller;

import java.util.List;

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

	@GetMapping({ "/login" })
	public String index(Model model) {
		session.invalidate();
		return "login";
	}

	@PostMapping("/login")
	public String login(
			@RequestParam("email") String email,
			@RequestParam("password") String password,
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
		return "redirect:/search";
	}
}
