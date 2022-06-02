package com.uniformorder.uniformorderr.model;

import com.google.gson.annotations.SerializedName;

public class DistrictName {

	@SerializedName("district_name")
	private String districtName;

	public String getDistrictName(){
		return districtName;
	}
}