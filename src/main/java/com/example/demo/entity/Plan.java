package com.example.demo.entity;

import java.time.LocalDate;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "plans")
public class Plan {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;

	@Column(name = "room_id")
	private Integer roomId;//部屋のタイプ

	private Integer price;//部屋の値段

	@Column(name = "accommodation_id")
	private Integer accommodationId;//宿泊施設のID

	private LocalDate date;

	//コンストラクタ
	public Plan() {

	}

	public Plan(Integer roomId, Integer price, Integer accommodationId, LocalDate date) {
		this.roomId = roomId;
		this.price = price;
		this.accommodationId = accommodationId;
		this.date = date;
	}

	public Integer getRoomId() {
		return roomId;
	}

	public void setRoomId(Integer roomId) {
		this.roomId = roomId;
	}

	public Integer getPrice() {
		return price;
	}

	public void setPrice(Integer price) {
		this.price = price;
	}

	public Integer getAccommodationId() {
		return accommodationId;
	}

	public void setAccommodationId(Integer accommodationId) {
		this.accommodationId = accommodationId;
	}

	public LocalDate getDate() {
		return date;
	}

	public void setDate(LocalDate date) {
		this.date = date;
	}

	public Integer getId() {
		return id;
	}

}
