package com.uniformorder.uniformorderr.testModel;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class Pattern implements Serializable {

	@SerializedName("login_id")
	private String loginId;

	@SerializedName("is_active")
	private String isActive;

	@SerializedName("updated_at")
	private String updatedAt;

	@SerializedName("name")
	private String name;

	@SerializedName("created_at")
	private String createdAt;

	@SerializedName("id")
	private int id;

	public String getLoginId(){
		return loginId;
	}

	public String getIsActive(){
		return isActive;
	}

	public String getUpdatedAt(){
		return updatedAt;
	}

	public String getName(){
		return name;
	}

	public String getCreatedAt(){
		return createdAt;
	}

	public int getId(){
		return id;
	}
}