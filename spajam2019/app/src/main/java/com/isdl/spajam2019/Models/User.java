package com.isdl.spajam2019.Models;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class User {

    @SerializedName("id")
    @Expose
    private Integer id;
    @SerializedName("user_id")
    @Expose
    private String userId;
    @SerializedName("age")
    @Expose
    private Integer age;
    @SerializedName("sex")
    @Expose
    private Integer sex;
    @SerializedName("job")
    @Expose
    private Integer job;
    @SerializedName("area")
    @Expose
    private String area;
    @SerializedName("password")
    @Expose
    private String password;
    @SerializedName("text")
    @Expose
    private String text;
    @SerializedName("artist")
    @Expose
    private Boolean artist;
    @SerializedName("like")
    @Expose
    private Integer like;
    @SerializedName("live_like")
    @Expose
    private Integer liveLike;
    @SerializedName("cross")
    @Expose
    private Boolean cross;
    @SerializedName("created_at")
    @Expose
    private String createdAt;
    @SerializedName("updated_at")
    @Expose
    private String updatedAt;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public Integer getSex() {
        return sex;
    }

    public void setSex(Integer sex) {
        this.sex = sex;
    }

    public Integer getJob() {
        return job;
    }

    public void setJob(Integer job) {
        this.job = job;
    }

    public String getArea() {
        return area;
    }

    public void setArea(String area) {
        this.area = area;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public Boolean getArtist() {
        return artist;
    }

    public void setArtist(Boolean artist) {
        this.artist = artist;
    }

    public Integer getLike() {
        return like;
    }

    public void setLike(Integer like) {
        this.like = like;
    }

    public Integer getLiveLike() {
        return liveLike;
    }

    public void setLiveLike(Integer liveLike) {
        this.liveLike = liveLike;
    }

    public Boolean getCross() {
        return cross;
    }

    public void setCross(Boolean cross) {
        this.cross = cross;
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

}
