package com.olx.dto;

import java.time.LocalDateTime;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.olx.utils.LocalDateDeserializer;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@ApiModel(value = "Advertise Model")
public class Advertise {

    @ApiModelProperty(value = "Unique identifier of the advertise")
    private int id;

    @ApiModelProperty(value = "Title of the advertise")
    private String title;

    @ApiModelProperty(value = "int of the advertise")
    private int categoryId;
    
    @ApiModelProperty(value = "Current statusId of the advertise")
    private int statusId;

	@ApiModelProperty(value = "Current status Name of the advertise")
    private String statusType;
    
    @ApiModelProperty(value = "Current category Name of the advertise")
    private String categoryType;
    
    @ApiModelProperty(value = "Current price of the advertise")
    private double price;

    @ApiModelProperty(value = "Description of the advertise")
    private String description;

    @JsonDeserialize(using= LocalDateDeserializer.class)
    @ApiModelProperty(value = "Date when the advertise is created")
    private LocalDateTime createdDate;

    @JsonDeserialize(using=LocalDateDeserializer.class)
    @ApiModelProperty(value = "Date when the advertise is last updated")
    private LocalDateTime modifiedDate;
    
    @ApiModelProperty(value = "Posted by user who has posted the advertise")
    private String posted_by;
    
    @ApiModelProperty(value = "Username of a user who has posted the advertise")
    private String username;

    public Advertise() {

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

	public String getStatusType() {
		return statusType;
	}

	public void setStatusType(String statusType) {
		this.statusType = statusType;
	}

	public String getCategoryType() {
		return categoryType;
	}

	public void setCategoryType(String categoryType) {
		this.categoryType = categoryType;
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

	public Advertise(int id, String title, int categoryId, int statusId, String statusType, String categoryType, double price,
			String description, LocalDateTime createdDate, LocalDateTime modifiedDate, String posted_by,
			String username) {
		super();
		this.id = id;
		this.title = title;
		this.categoryId = categoryId;
		this.statusId = statusId;
		this.statusType = statusType;
		this.categoryType = categoryType;
		this.price = price;
		this.description = description;
		this.createdDate = createdDate;
		this.modifiedDate = modifiedDate;
		this.posted_by = posted_by;
		this.username = username;
	}
	public Advertise(String title, int categoryId, int statusId, String statusType, String categoryType, double price,
			String description, LocalDateTime createdDate, LocalDateTime modifiedDate, String posted_by,
			String username) {
		super();
		this.title = title;
		this.categoryId = categoryId;
		this.statusId = statusId;
		this.statusType = statusType;
		this.categoryType = categoryType;
		this.price = price;
		this.description = description;
		this.createdDate = createdDate;
		this.modifiedDate = modifiedDate;
		this.posted_by = posted_by;
		this.username = username;
	}

	@Override
	public String toString() {
		return "Advertise [id=" + id + ", title=" + title + ", categoryId=" + categoryId + ", statusId=" + statusId
				+ ", statusType=" + statusType + ", categoryType=" + categoryType + ", price=" + price
				+ ", description=" + description + ", createdDate=" + createdDate + ", modifiedDate=" + modifiedDate
				+ ", posted_by=" + posted_by + ", username=" + username + "]";
	}
    }