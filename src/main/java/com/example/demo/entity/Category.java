package com.example.demo.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "categories")
public class Category {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id; //宿泊施設ID
	private String name; //宿泊施設名

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
	public Category() {

	}

	public Category(String name) {
		this.name = name;
	}

}
