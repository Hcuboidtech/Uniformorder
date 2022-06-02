package com.uniformorder.uniformorderr.testModel;

import java.io.Serializable;
import java.util.List;
import com.google.gson.annotations.SerializedName;

public class ResponseOrderList implements Serializable {

	@SerializedName("data")
	private List<DataItem> data;

	@SerializedName("message")
	private String message;

	@SerializedName("status")
	private boolean status;

	public List<DataItem> getData(){
		return data;
	}

	public String getMessage(){
		return message;
	}

	public boolean isStatus(){
		return status;
	}
}