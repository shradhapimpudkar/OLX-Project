package com.olx.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@ApiModel("Status model")
public class Status {
@Override
	public String toString() {
		return "Status [id=" + id + ", status=" + status + "]";
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
public Status(int id, String status) {
		super();
		this.id = id;
		this.status = status;
	}

public  Status()
{
	super();
}


@ApiModelProperty("Unique identifier of the Status")
private int id;
@ApiModelProperty("Status identifier of the Status model")
private String status;
}
