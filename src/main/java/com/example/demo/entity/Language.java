package com.example.demo.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "languages")
public class Language {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id; //言語ID
	private String name; //言語の種類

	//ゲッター
	public Integer getId() {
		return id;
	}

	public String getName() {
		return name;
	}

	//セッター
	public void setName(String name) {
		this.name = name;
	}

	//コンストラクタ
	public Language() {

	}

	public Language(String name) {
		this.name = name;
	}
}
