package com.example.demo.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "reserves")
public class Reserve {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	@Column(name = "user_id")
	private Integer userId;
	@Column(name = "plan_id")
	private Integer planId;
	private Integer quantity;
	private Integer roomCount;

	public Reserve() {

	}

	public Reserve(Integer userId, Integer planId, Integer quantity, Integer roomCount) {
		this.userId = userId;
		this.planId = planId;
		this.quantity = quantity;
		this.roomCount = roomCount;
	}

	public Integer getId() {
		return id;
	}

	public Integer getUserId() {
		return userId;
	}

	public Integer getPlanId() {
		return planId;
	}

	public Integer getQuantity() {
		return quantity;
	}

	public Integer getRoomCount() {
		return roomCount;
	}

	public void setUserId(Integer userId) {
		this.userId = userId;
	}

	public void setPlanId(Integer planId) {
		this.planId = planId;
	}

	public void setQuantity(Integer quantity) {
		this.quantity = quantity;
	}

}
