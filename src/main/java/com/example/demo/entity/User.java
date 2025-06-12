package com.example.demo.entity;

import java.time.LocalDate;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "users")

public class User {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	private String name;
	private String tel;
	private String address;
	private String password;
	private Integer gender;
	private String nickname;
	private String email;
	private LocalDate birthday;

	public Integer getId() {
		return id;
	}

	public String getName() {
		return name;
	}

	public String getTel() {
		return tel;
	}

	public String getAddress() {
		return address;
	}

	public String getPassword() {
		return password;
	}

	public Integer getGender() {
		return gender;
	}

	public String getNickname() {
		return nickname;
	}

	public String getEmail() {
		return email;
	}

	public LocalDate getBirthday() {
		return birthday;
	}

	public User() {

	}

	public User(String name, String tel, String address, String password, Integer gender, String nickname, String email,
			LocalDate birthday) {
		this.name = name;
		this.tel = tel;
		this.address = address;
		this.password = password;
		this.gender = gender;
		this.nickname = nickname;
		this.email = email;
		this.birthday = birthday;
	}

}
