package com.uniformorder.uniformorderr.testModel;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class StandardsItem implements Serializable {

	@SerializedName("standard_id")
	private String standardId;

	@SerializedName("standard")
	private String standard;

	@SerializedName("total")
	private String total;

	@SerializedName("pending_girls")
	private String pendingGirls;

	@SerializedName("total_pending")
	private String totalPending;

	@SerializedName("boys")
	private String boys;

	@SerializedName("girls")
	private String girls;

	@SerializedName("pending_boys")
	private String pendingBoys;

	public String getStandardId(){
		return standardId;
	}

	public String getStandard(){
		return standard;
	}

	public String getTotal(){
		return total;
	}

	public String getPendingGirls(){
		return pendingGirls;
	}

	public String getTotalPending(){
		return totalPending;
	}

	public String getBoys(){
		return boys;
	}

	public String getGirls(){
		return girls;
	}

	public String getPendingBoys(){
		return pendingBoys;
	}

	public void setStandardId(String standardId) {
		this.standardId = standardId;
	}

	public void setStandard(String standard) {
		this.standard = standard;
	}

	public void setTotal(String total) {
		this.total = total;
	}

	public void setPendingGirls(String pendingGirls) {
		this.pendingGirls = pendingGirls;
	}

	public void setTotalPending(String totalPending) {
		this.totalPending = totalPending;
	}

	public void setBoys(String boys) {
		this.boys = boys;
	}

	public void setGirls(String girls) {
		this.girls = girls;
	}

	public void setPendingBoys(String pendingBoys) {
		this.pendingBoys = pendingBoys;
	}
}