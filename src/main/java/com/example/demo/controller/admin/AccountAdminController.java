package com.example.demo.controller.admin;

import jakarta.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import com.example.demo.model.AccountAdmin;

@Controller
public class AccountAdminController {
	@Autowired
	HttpSession session;

	@Autowired
	AccountAdmin accountAdmin;

	//管理者のログイン画面を表示
	@GetMapping({ "/admin/login", "/admin/logout" })
	public String index() {
		session.invalidate();
		return "admin/adminLogin";
	}
}
