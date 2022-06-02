package com.uniformorder.uniformorderr.testModel;

import java.io.Serializable;
import java.util.List;
import com.google.gson.annotations.SerializedName;

public class DataItem implements Serializable {

	@SerializedName("login_id")
	private String loginId;

	@SerializedName("pending_students")
	private String pendingStudents;

	@SerializedName("is_active")
	private String isActive;

	@SerializedName("rate1")
	private String rate1;

	@SerializedName("rate2")
	private String rate2;

	@SerializedName("rate3")
	private String rate3;

	@SerializedName("pattern")
	private Pattern pattern;

	@SerializedName("created_at")
	private String createdAt;

	@SerializedName("pending_amount")
	private String pendingAmount;

	@SerializedName("standards")
	private List<StandardsItem> standards;

	@SerializedName("delivery_date")
	private Object deliveryDate;

	@SerializedName("school_id")
	private String schoolId;

	@SerializedName("pattern_id")
	private String patternId;

	@SerializedName("updated_at")
	private String updatedAt;

	@SerializedName("total_amount")
	private String totalAmount;

	@SerializedName("school")
	private School school;

	@SerializedName("id")
	private int id;

	@SerializedName("total_students")
	private String totalStudents;

	@SerializedName("deposite")
	private String deposite;

	@SerializedName("orderDate")
	private String orderDate;

	@SerializedName("user")
	private User user;

	@SerializedName("status")
	private String status;

	public String getLoginId(){
		return loginId;
	}

	public String getPendingStudents(){
		return pendingStudents;
	}

	public String getIsActive(){
		return isActive;
	}

	public String getRate1(){
		return rate1;
	}

	public String getRate2(){
		return rate2;
	}

	public String getRate3(){
		return rate3;
	}

	public Pattern getPattern(){
		return pattern;
	}

	public String getCreatedAt(){
		return createdAt;
	}

	public String getPendingAmount(){
		return pendingAmount;
	}

	public List<StandardsItem> getStandards(){
		return standards;
	}

	public Object getDeliveryDate(){
		return deliveryDate;
	}

	public String getSchoolId(){
		return schoolId;
	}

	public String getPatternId(){
		return patternId;
	}

	public String getUpdatedAt(){
		return updatedAt;
	}

	public String getTotalAmount(){
		return totalAmount;
	}

	public School getSchool(){
		return school;
	}

	public int getId(){
		return id;
	}

	public String getTotalStudents(){
		return totalStudents;
	}

	public String getDeposite(){
		return deposite;
	}

	public String getOrderDate(){
		return orderDate;
	}

	public User getUser(){
		return user;
	}

	public String getStatus(){
		return status;
	}
}