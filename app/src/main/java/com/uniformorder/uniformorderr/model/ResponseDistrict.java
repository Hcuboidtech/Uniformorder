package com.uniformorder.uniformorderr.model;

import java.util.List;
import com.google.gson.annotations.SerializedName;

public class ResponseDistrict{

	@SerializedName("data")
	private List<DistrictName> data;

	@SerializedName("message")
	private String message;

	@SerializedName("status")
	private boolean status;

	public List<DistrictName> getData(){
		return data;
	}

	public String getMessage(){
		return message;
	}

	public boolean isStatus(){
		return status;
	}
}