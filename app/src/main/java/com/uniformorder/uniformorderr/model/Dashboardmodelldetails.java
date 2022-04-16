package com.uniformorder.uniformorderr.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class Dashboardmodelldetails implements Serializable {
    @SerializedName("PendingOrders")
    @Expose
    private Integer pendingOrders;
    @SerializedName("Schools")
    @Expose
    private Integer schools;
    @SerializedName("Patterns")
    @Expose
    private Integer patterns;
    @SerializedName("Members")
    @Expose
    private Integer members;
    @SerializedName("TodayReceivedOrders")
    @Expose
    private Integer todayReceivedOrders;
    @SerializedName("TodayOrderforBoys")
    @Expose
    private Integer todayOrderforBoys;
    @SerializedName("TodayOrderforGirls")
    @Expose
    private Integer todayOrderforGirls;
    @SerializedName("TodayReceivedPayment")
    @Expose
    private Integer todayReceivedPayment;
    @SerializedName("TodayRemainPayment")
    @Expose
    private String todayRemainPayment;
    @SerializedName("TodayDeliveredOrders")
    @Expose
    private Integer todayDeliveredOrders;
    @SerializedName("TodayDeliveredBoysDressCount")
    @Expose
    private Integer todayDeliveredBoysDressCount;
    @SerializedName("TodayDeliveredGirlsDressCount")
    @Expose
    private Integer todayDeliveredGirlsDressCount;

    public Integer getPendingOrders() {
        return pendingOrders;
    }

    public void setPendingOrders(Integer pendingOrders) {
        this.pendingOrders = pendingOrders;
    }

    public Integer getSchools() {
        return schools;
    }

    public void setSchools(Integer schools) {
        this.schools = schools;
    }

    public Integer getPatterns() {
        return patterns;
    }

    public void setPatterns(Integer patterns) {
        this.patterns = patterns;
    }

    public Integer getMembers() {
        return members;
    }

    public void setMembers(Integer members) {
        this.members = members;
    }

    public Integer getTodayReceivedOrders() {
        return todayReceivedOrders;
    }

    public void setTodayReceivedOrders(Integer todayReceivedOrders) {
        this.todayReceivedOrders = todayReceivedOrders;
    }

    public Integer getTodayOrderforBoys() {
        return todayOrderforBoys;
    }

    public void setTodayOrderforBoys(Integer todayOrderforBoys) {
        this.todayOrderforBoys = todayOrderforBoys;
    }

    public Integer getTodayOrderforGirls() {
        return todayOrderforGirls;
    }

    public void setTodayOrderforGirls(Integer todayOrderforGirls) {
        this.todayOrderforGirls = todayOrderforGirls;
    }

    public Integer getTodayReceivedPayment() {
        return todayReceivedPayment;
    }

    public void setTodayReceivedPayment(Integer todayReceivedPayment) {
        this.todayReceivedPayment = todayReceivedPayment;
    }

    public String getTodayRemainPayment() {
        return todayRemainPayment;
    }

    public void setTodayRemainPayment(String todayRemainPayment) {
        this.todayRemainPayment = todayRemainPayment;
    }

    public Integer getTodayDeliveredOrders() {
        return todayDeliveredOrders;
    }

    public void setTodayDeliveredOrders(Integer todayDeliveredOrders) {
        this.todayDeliveredOrders = todayDeliveredOrders;
    }

    public Integer getTodayDeliveredBoysDressCount() {
        return todayDeliveredBoysDressCount;
    }

    public void setTodayDeliveredBoysDressCount(Integer todayDeliveredBoysDressCount) {
        this.todayDeliveredBoysDressCount = todayDeliveredBoysDressCount;
    }

    public Integer getTodayDeliveredGirlsDressCount() {
        return todayDeliveredGirlsDressCount;
    }

    public void setTodayDeliveredGirlsDressCount(Integer todayDeliveredGirlsDressCount) {
        this.todayDeliveredGirlsDressCount = todayDeliveredGirlsDressCount;
    }

}
