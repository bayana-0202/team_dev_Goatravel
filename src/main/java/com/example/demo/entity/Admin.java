package com.example.demo.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "admins")
public class Admin {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;//管理者ID

	private String name;//管理者名

	private String password;//管理者のパスワード

	private Integer authority;//権限レベル

	private String email;//管理者メールアドレス

	private String tel;//管理者電話番号

	//コンストラクタ
	public Admin() {

	}

	public Admin(Integer id, String name, String password, String email, String tel) {
		this.id = id;
		this.name = name;
		this.password = password;
		this.email = email;
		this.tel = tel;
	}

	//ゲッターセッター
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public Integer getAuthority() {
		return authority;
	}

	public void setAuthority(Integer authority) {
		this.authority = authority;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getTel() {
		return tel;
	}

	public void setTel(String tel) {
		this.tel = tel;
	}

	public Integer getId() {
		return id;
	}
}
