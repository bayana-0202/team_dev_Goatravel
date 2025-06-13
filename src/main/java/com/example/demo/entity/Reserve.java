package com.example.demo.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
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
	@Column(name = "room_count")
	private Integer roomCount;

	@ManyToOne
	@JoinColumn(name = "plan_id", insertable = false, updatable = false)
	private Plan plan;

	@ManyToOne
	@JoinColumn(name = "user_id", insertable = false, updatable = false)
	private User user;

	public User getUser() {
		return user;
	}

	public void setRoomCount(Integer roomCount) {
		this.roomCount = roomCount;
	}

	public Plan getPlan() {
		return plan;
	}

	public void setPlan(Plan plan) {
		this.plan = plan;
	}

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
