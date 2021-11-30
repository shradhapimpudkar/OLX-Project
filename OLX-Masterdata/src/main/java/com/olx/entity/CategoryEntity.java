package com.olx.entity;

import javax.persistence.*;

@Entity
@Table(name="categories")
public class CategoryEntity {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int Id;
	
	@Column(name="category")
	private String category;
	
	@Column(name="description")
	private String description;

	public CategoryEntity(int id, String category, String description) {
		super();
		Id = id;
		this.category = category;
		this.description = description;
	}
	public CategoryEntity(String category, String description) {
		super();
		this.category = category;
		this.description = description;
	}
	
	public CategoryEntity() {
		
	}

	public int getId() {
		return Id;
	}


	public void setId(int id) {
		Id = id;
	}

	public String getCategory() {
		return category;
	}

	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public void setCategory(String category) {
		this.category = category;
	}



	@Override
	public String toString() {
		return "CategoryEntity [Id=" + Id + ", category=" + category + ", desc=" + description + "]";
	}
}
