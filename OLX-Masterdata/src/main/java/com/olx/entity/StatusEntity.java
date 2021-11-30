package com.olx.entity;

import javax.persistence.*;

@Entity
@Table(name="status")
public class StatusEntity {
    
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int id;
	
	@Column(name="status")
	private String status;

	
	public StatusEntity() {
		
	}

	public StatusEntity(int id, String status) {
		this.id = id;
		this.status = status;
	}
	public StatusEntity(String status) {
		this.status = status;
	}

	public int getId() {
		return id;
	}


	public void setId(int id) {
		this.id = id;
	}


	public String getStatus() {
		return status;
	}


	public void setStatus(String status) {
		this.status = status;
	}
}