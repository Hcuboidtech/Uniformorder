package com.uniformorder.uniformorderr.model;

import java.util.List;
import com.google.gson.annotations.SerializedName;

public class ResponsePayCenter{

	@SerializedName("data")
	private List<PayCenterName> data;

	@SerializedName("message")
	private String message;

	@SerializedName("status")
	private boolean status;

	public List<PayCenterName> getData(){
		return data;
	}

	public String getMessage(){
		return message;
	}

	public boolean isStatus(){
		return status;
	}
}