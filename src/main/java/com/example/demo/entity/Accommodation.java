package com.example.demo.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "accommodations")
public class Accommodation {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;//プランID

	@Column(name = "category_id")
	private Integer categoryId;//宿カテゴリーID

	private enum bathType {
		//お風呂の種類
		ユニットバス, 風呂トイレ別, シャワールーム
	}

	@Enumerated(EnumType.STRING)
	private bathType bath;

	private String name;//宿泊施設の名前

	private String tel;//宿泊施設の電話番号

	private String address;//宿泊施設住所

	@Column(name = "language_id")
	private Integer languageId;//対応言語ID

	private String content;//サービス内容

	//コンストラクタ
	public Accommodation() {

	}

	public Accommodation(Integer categoryId, String name, String tel, String address, Integer languageId,
			String content) {
		this.categoryId = categoryId;
		this.name = name;
		this.tel = tel;
		this.address = address;
		this.languageId = languageId;
		this.content = content;
	}

	public Integer getCategoryId() {
		return categoryId;
	}

	public void setCategoryId(Integer categoryId) {
		this.categoryId = categoryId;
	}

	public bathType getBath() {
		return bath;
	}

	public void setBath(bathType bath) {
		this.bath = bath;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getTel() {
		return tel;
	}

	public void setTel(String tel) {
		this.tel = tel;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public Integer getLanguageId() {
		return languageId;
	}

	public void setLanguageId(Integer languageId) {
		this.languageId = languageId;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public Integer getId() {
		return id;
	}

}
