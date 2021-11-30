package com.olx.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@ApiModel("Category model")
public class Category {
	@ApiModelProperty("Unique identifier of the Category")
	private int id;
	@ApiModelProperty("Category identifier of the Category model")
	private String category;
	@ApiModelProperty("Category identifier of the Category model for description")
	private String description;
	
	

	public Category(int id, String category, String description) {
		super();
		this.id = id;
		this.category = category;
		this.description = description;
	}
	public Category(String category, String description) {
		super();
		this.category = category;
		this.description = description;
	}
	public Category()
	{
		super();
		
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getCategory() {
		return category;
	}
	public void setCategory(String category) {
		this.category = category;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	@Override
	public String toString() {
		return "Category [id=" + id + ", category=" + category + ", description=" + description + "]";
	}
	

	
}
