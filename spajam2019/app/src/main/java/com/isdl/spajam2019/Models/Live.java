package com.isdl.spajam2019.Models;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Live {

    @SerializedName("id")
    @Expose
    private Integer id;
    @SerializedName("user_id")
    @Expose
    private Integer userId;
    @SerializedName("name")
    @Expose
    private String name;
    @SerializedName("date")
    @Expose
    private Object date;
    @SerializedName("like")
    @Expose
    private Integer like;
    @SerializedName("location")
    @Expose
    private Object location;
    @SerializedName("cd_price")
    @Expose
    private Object cdPrice;
    @SerializedName("message")
    @Expose
    private Object message;
    @SerializedName("next_data")
    @Expose
    private Object nextData;
    @SerializedName("created_at")
    @Expose
    private String createdAt;
    @SerializedName("updated_at")
    @Expose
    private String updatedAt;
    @SerializedName("open")
    @Expose
    private Boolean open;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Object getDate() {
        return date;
    }

    public void setDate(Object date) {
        this.date = date;
    }

    public Integer getLike() {
        return like;
    }

    public void setLike(Integer like) {
        this.like = like;
    }

    public Object getLocation() {
        return location;
    }

    public void setLocation(Object location) {
        this.location = location;
    }

    public Object getCdPrice() {
        return cdPrice;
    }

    public void setCdPrice(Object cdPrice) {
        this.cdPrice = cdPrice;
    }

    public Object getMessage() {
        return message;
    }

    public void setMessage(Object message) {
        this.message = message;
    }

    public Object getNextData() {
        return nextData;
    }

    public void setNextData(Object nextData) {
        this.nextData = nextData;
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

    public Boolean getOpen() {
        return open;
    }

    public void setOpen(Boolean open) {
        this.open = open;
    }

}