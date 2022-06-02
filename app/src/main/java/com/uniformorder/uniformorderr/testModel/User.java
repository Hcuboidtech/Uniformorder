package com.uniformorder.uniformorderr.testModel;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class User implements Serializable {

	@SerializedName("is_active")
	private String isActive;

	@SerializedName("profile")
	private String profile;

	@SerializedName("mobile")
	private String mobile;

	@SerializedName("created_at")
	private String createdAt;

	@SerializedName("email_verified_at")
	private Object emailVerifiedAt;

	@SerializedName("device_type")
	private String deviceType;

	@SerializedName("org_password")
	private String orgPassword;

	@SerializedName("user_type")
	private String userType;

	@SerializedName("social_type")
	private String socialType;

	@SerializedName("updated_at")
	private String updatedAt;

	@SerializedName("device_token")
	private String deviceToken;

	@SerializedName("name")
	private String name;

	@SerializedName("id")
	private int id;

	@SerializedName("email")
	private String email;

	public String getIsActive(){
		return isActive;
	}

	public String getProfile(){
		return profile;
	}

	public String getMobile(){
		return mobile;
	}

	public String getCreatedAt(){
		return createdAt;
	}

	public Object getEmailVerifiedAt(){
		return emailVerifiedAt;
	}

	public String getDeviceType(){
		return deviceType;
	}

	public String getOrgPassword(){
		return orgPassword;
	}

	public String getUserType(){
		return userType;
	}

	public String getSocialType(){
		return socialType;
	}

	public String getUpdatedAt(){
		return updatedAt;
	}

	public String getDeviceToken(){
		return deviceToken;
	}

	public String getName(){
		return name;
	}

	public int getId(){
		return id;
	}

	public String getEmail(){
		return email;
	}
}