package com.uniformorder.uniformorderr.testModel;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class School implements Serializable {

	@SerializedName("login_id")
	private String loginId;

	@SerializedName("village_id")
	private String villageId;

	@SerializedName("pay_center_id")
	private String payCenterId;

	@SerializedName("is_active")
	private String isActive;

	@SerializedName("principal_name")
	private String principalName;

	@SerializedName("mobile1")
	private String mobile1;

	@SerializedName("created_at")
	private String createdAt;

	@SerializedName("combo")
	private Combo combo;

	@SerializedName("assistant_name")
	private String assistantName;

	@SerializedName("updated_at")
	private String updatedAt;

	@SerializedName("mobile2")
	private String mobile2;

	@SerializedName("name")
	private String name;

	@SerializedName("id")
	private int id;

	@SerializedName("district_id")
	private String districtId;

	public String getLoginId(){
		return loginId;
	}

	public String getVillageId(){
		return villageId;
	}

	public String getPayCenterId(){
		return payCenterId;
	}

	public String getIsActive(){
		return isActive;
	}

	public String getPrincipalName(){
		return principalName;
	}

	public String getMobile1(){
		return mobile1;
	}

	public String getCreatedAt(){
		return createdAt;
	}

	public Combo getCombo(){
		return combo;
	}

	public String getAssistantName(){
		return assistantName;
	}

	public String getUpdatedAt(){
		return updatedAt;
	}

	public String getMobile2(){
		return mobile2;
	}

	public String getName(){
		return name;
	}

	public int getId(){
		return id;
	}

	public String getDistrictId(){
		return districtId;
	}
}