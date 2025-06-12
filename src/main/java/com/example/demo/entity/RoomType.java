package com.example.demo.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "room_types")
public class RoomType {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id; //部屋ID
	private String name; //部屋の種類

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
	public RoomType() {

	}

	public RoomType(String name) {
		this.name = name;
	}

}
