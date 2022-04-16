package com.uniformorder.uniformorderr.model;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class Editorder  implements Serializable {
    @SerializedName("standard_id")
    @Expose
    private String standardId;
    @SerializedName("standard")
    @Expose
    private String standard;
    @SerializedName("boys")
    @Expose
    private String boys;
    @SerializedName("girls")
    @Expose
    private String girls;
    @SerializedName("total")
    @Expose
    private String total;
    @SerializedName("pending_boys")
    @Expose
    private String pendingBoys;
    @SerializedName("pending_girls")
    @Expose
    private String pendingGirls;
    @SerializedName("total_pending")
    @Expose
    private String totalPending;

    public String getStandardId() {
        return standardId;
    }

    public void setStandardId(String standardId) {
        this.standardId = standardId;
    }

    public String getStandard() {
        return standard;
    }

    public void setStandard(String standard) {
        this.standard = standard;
    }

    public String getBoys() {
        return boys;
    }

    public void setBoys(String boys) {
        this.boys = boys;
    }

    public String getGirls() {
        return girls;
    }

    public void setGirls(String girls) {
        this.girls = girls;
    }

    public String getTotal() {
        return total;
    }

    public void setTotal(String total) {
        this.total = total;
    }

    public String getPendingBoys() {
        return pendingBoys;
    }

    public void setPendingBoys(String pendingBoys) {
        this.pendingBoys = pendingBoys;
    }

    public String getPendingGirls() {
        return pendingGirls;
    }

    public void setPendingGirls(String pendingGirls) {
        this.pendingGirls = pendingGirls;
    }

    public String getTotalPending() {
        return totalPending;
    }

    public void setTotalPending(String totalPending) {
        this.totalPending = totalPending;
    }
}
