package com.uniformorder.uniformorderr.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;
import java.util.List;

public class Orderlistdetails implements Serializable {
    @SerializedName("id")
    @Expose
    private String id;
    @SerializedName("login_id")
    @Expose
    private String loginId;
    @SerializedName("school_id")
    @Expose
    private String schoolId;
    @SerializedName("pattern_id")
    @Expose
    private String patternId;
    @SerializedName("total_students")
    @Expose
    private String totalStudents;
    @SerializedName("pending_students")
    @Expose
    private String pendingStudents;
    @SerializedName("rate1")
    @Expose
    private String rate1;
    @SerializedName("rate2")
    @Expose
    private String rate2;
    @SerializedName("total_amount")
    @Expose
    private String totalAmount;
    @SerializedName("pending_amount")
    @Expose
    private Integer pendingAmount;
    @SerializedName("deposite")
    @Expose
    private String deposite;
    @SerializedName("delivery_date")
    @Expose
    private Object deliveryDate;
    @SerializedName("created_at")
    @Expose
    private String createdAt;
    @SerializedName("updated_at")
    @Expose
    private String updatedAt;
    @SerializedName("status")
    @Expose
    private String status;
    @SerializedName("is_active")
    @Expose
    private String isActive;
    @SerializedName("created_name")
    @Expose
    private String createdName;
    @SerializedName("created_email")
    @Expose
    private String createdEmail;
    @SerializedName("created_mobile")
    @Expose
    private String createdMobile;
    @SerializedName("created_profile")
    @Expose
    private String createdProfile;
    @SerializedName("pattern_name")
    @Expose
    private String patternName;
    @SerializedName("schools_name")
    @Expose
    private String schoolsName;
    @SerializedName("principal_name")
    @Expose
    private String principalName;
    @SerializedName("assistant_name")
    @Expose
    private String assistantName;
    @SerializedName("state")
    @Expose
    private String state;
    @SerializedName("pay_center")
    @Expose
    private String payCenter;
    @SerializedName("school_mobile1")
    @Expose
    private String schoolMobile1;
    @SerializedName("school_mobile2")
    @Expose
    private String schoolMobile2;
    @SerializedName("standards")
    @Expose

    private List<Standard> standards = null;

    @SerializedName("orderDate")
    @Expose
    private String orderDate;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getLoginId() {
        return loginId;
    }

    public void setLoginId(String loginId) {
        this.loginId = loginId;
    }

    public String getSchoolId() {
        return schoolId;
    }

    public void setSchoolId(String schoolId) {
        this.schoolId = schoolId;
    }

    public String getPatternId() {
        return patternId;
    }

    public void setPatternId(String patternId) {
        this.patternId = patternId;
    }

    public String getTotalStudents() {
        return totalStudents;
    }

    public void setTotalStudents(String totalStudents) {
        this.totalStudents = totalStudents;
    }

    public String getPendingStudents() {
        return pendingStudents;
    }

    public void setPendingStudents(String pendingStudents) {
        this.pendingStudents = pendingStudents;
    }

    public String getRate1() {
        return rate1;
    }

    public void setRate1(String rate1) {
        this.rate1 = rate1;
    }

    public String getRate2() {
        return rate2;
    }

    public void setRate2(String rate2) {
        this.rate2 = rate2;
    }

    public String getTotalAmount() {
        return totalAmount;
    }

    public void setTotalAmount(String totalAmount) {
        this.totalAmount = totalAmount;
    }

    public Integer getPendingAmount() {
        return pendingAmount;
    }

    public void setPendingAmount(Integer pendingAmount) {
        this.pendingAmount = pendingAmount;
    }

    public String getDeposite() {
        return deposite;
    }

    public void setDeposite(String deposite) {
        this.deposite = deposite;
    }

    public Object getDeliveryDate() {
        return deliveryDate;
    }

    public void setDeliveryDate(Object deliveryDate) {
        this.deliveryDate = deliveryDate;
    }

    public String getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(String createdAt) {
        this.createdAt = createdAt;
    }

    public String getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(String updatedAt) {
        this.updatedAt = updatedAt;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getIsActive() {
        return isActive;
    }

    public void setIsActive(String isActive) {
        this.isActive = isActive;
    }

    public String getCreatedName() {
        return createdName;
    }

    public void setCreatedName(String createdName) {
        this.createdName = createdName;
    }

    public String getCreatedEmail() {
        return createdEmail;
    }

    public void setCreatedEmail(String createdEmail) {
        this.createdEmail = createdEmail;
    }

    public String getCreatedMobile() {
        return createdMobile;
    }

    public void setCreatedMobile(String createdMobile) {
        this.createdMobile = createdMobile;
    }

    public String getCreatedProfile() {
        return createdProfile;
    }

    public void setCreatedProfile(String createdProfile) {
        this.createdProfile = createdProfile;
    }

    public String getPatternName() {
        return patternName;
    }

    public void setPatternName(String patternName) {
        this.patternName = patternName;
    }

    public String getSchoolsName() {
        return schoolsName;
    }

    public void setSchoolsName(String schoolsName) {
        this.schoolsName = schoolsName;
    }

    public String getPrincipalName() {
        return principalName;
    }

    public void setPrincipalName(String principalName) {
        this.principalName = principalName;
    }

    public String getAssistantName() {
        return assistantName;
    }

    public void setAssistantName(String assistantName) {
        this.assistantName = assistantName;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String getPayCenter() {
        return payCenter;
    }

    public void setPayCenter(String payCenter) {
        this.payCenter = payCenter;
    }

    public String getSchoolMobile1() {
        return schoolMobile1;
    }

    public void setSchoolMobile1(String schoolMobile1) {
        this.schoolMobile1 = schoolMobile1;
    }

    public String getSchoolMobile2() {
        return schoolMobile2;
    }

    public void setSchoolMobile2(String schoolMobile2) {
        this.schoolMobile2 = schoolMobile2;
    }

    public List<Standard> getStandards() {
        return standards;
    }

    public void setStandards(List<Standard> standards) {
        this.standards = standards;
    }


    public String getOrderDate() {
        return orderDate;
    }

    public void setOrderDate(String orderDate) {
        this.orderDate = orderDate;
    }


}
