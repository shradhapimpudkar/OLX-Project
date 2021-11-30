package com.olx.entity;

import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "ADVERTISE")
public class AdvertiseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;

    @Column(name = "adv_title")
    private String title;

    @Column(name = "adv_category_id")
    private int categoryId;
    
    @Column(name = "adv_statusId")
    private int statusId;
    
    @Column(name = "adv_price")
    private double price;    

    @Column(name = "adv_description")
    private String description;
    
    @Column(name = "adv_created_date")
    private LocalDateTime createdDate;

    @Column(name = "adv_modified_date")
    private LocalDateTime modifiedDate;
    
    @Column(name = "adv_posted_by")
    private String posted_by;

    @Column(name = "adv_username")
    private String username;

    public AdvertiseEntity() {

    }

	public AdvertiseEntity(int id, String title, int categoryId, int statusId, double price, String description,
			LocalDateTime createdDate, LocalDateTime modifiedDate, String posted_by, String username) {
		super();
		this.id = id;
		this.title = title;
		this.categoryId = categoryId;
		this.statusId = statusId;
		this.price = price;
		this.description = description;
		this.createdDate = createdDate;
		this.modifiedDate = modifiedDate;
		this.posted_by = posted_by;
		this.username = username;
	}
	
	public AdvertiseEntity(String title, int categoryId, int statusId, double price, String description,
			LocalDateTime createdDate, LocalDateTime modifiedDate, String posted_by, String username) {
		super();
		this.title = title;
		this.categoryId = categoryId;
		this.statusId = statusId;
		this.price = price;
		this.description = description;
		this.createdDate = createdDate;
		this.modifiedDate = modifiedDate;
		this.posted_by = posted_by;
		this.username = username;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public int getCategoryId() {
		return categoryId;
	}

	public void setCategoryId(int categoryId) {
		this.categoryId = categoryId;
	}

	public int getStatusId() {
		return statusId;
	}

	public void setStatusId(int statusId) {
		this.statusId = statusId;
	}

	public double getPrice() {
		return price;
	}

	public void setPrice(double price) {
		this.price = price;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public LocalDateTime getCreatedDate() {
		return createdDate;
	}

	public void setCreatedDate(LocalDateTime createdDate) {
		this.createdDate = createdDate;
	}

	public LocalDateTime getModifiedDate() {
		return modifiedDate;
	}

	public void setModifiedDate(LocalDateTime modifiedDate) {
		this.modifiedDate = modifiedDate;
	}

	public String getPosted_by() {
		return posted_by;
	}

	public void setPosted_by(String posted_by) {
		this.posted_by = posted_by;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	@Override
	public String toString() {
		return "AdvertiseEntity [id=" + id + ", title=" + title + ", categoryId=" + categoryId + ", statusId="
				+ statusId + ", price=" + price + ", description=" + description + ", createdDate=" + createdDate
				+ ", modifiedDate=" + modifiedDate + ", posted_by=" + posted_by + ", username=" + username + "]";
	}
}
