package com.uniformorder.uniformorderr.model;

import com.google.gson.annotations.SerializedName;

public class Data{

	@SerializedName("School Name")
	private String schoolName;

	@SerializedName("Principal Name")
	private String principalName;

	public String getSchoolName(){
		return schoolName;
	}

	public String getPrincipalName(){
		return principalName;
	}
}