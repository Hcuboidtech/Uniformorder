package com.uniformorder.uniformorderr.model;

import com.google.gson.annotations.SerializedName;

public class PayCenterName {

	@SerializedName("district_name")
	private String districtName;

	@SerializedName("updated_at")
	private String updatedAt;

	@SerializedName("pay_center_name")
	private String payCenterName;

	@SerializedName("created_at")
	private String createdAt;

	@SerializedName("id")
	private int id;

	@SerializedName("village_name")
	private String villageName;

	public String getDistrictName(){
		return districtName;
	}

	public String getUpdatedAt(){
		return updatedAt;
	}

	public String getPayCenterName(){
		return payCenterName;
	}

	public String getCreatedAt(){
		return createdAt;
	}

	public int getId(){
		return id;
	}

	public String getVillageName(){
		return villageName;
	}
}