package com.isdl.spajam2019.Models;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Music {

    @SerializedName("id")
    @Expose
    private Integer id;
    @SerializedName("name")
    @Expose
    private String name;
    @SerializedName("artist")
    @Expose
    private String artist;
    @SerializedName("genre")
    @Expose
    private Integer genre;
    @SerializedName("like")
    @Expose
    private Integer like;
    @SerializedName("intro")
    @Expose
    private Boolean intro;
    @SerializedName("cross")
    @Expose
    private Boolean cross;
    @SerializedName("text")
    @Expose
    private String text;
    @SerializedName("created_at")
    @Expose
    private String createdAt;
    @SerializedName("updated_at")
    @Expose
    private String updatedAt;
    @SerializedName("upload")
    @Expose
    private Boolean upload;
    @SerializedName("path")
    @Expose
    private String path;
    @SerializedName("user_id")
    @Expose
    private Integer userId;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getArtist() {
        return artist;
    }

    public void setArtist(String artist) {
        this.artist = artist;
    }

    public Integer getGenre() {
        return genre;
    }

    public void setGenre(Integer genre) {
        this.genre = genre;
    }

    public Integer getLike() {
        return like;
    }

    public void setLike(Integer like) {
        this.like = like;
    }

    public Boolean getIntro() {
        return intro;
    }

    public void setIntro(Boolean intro) {
        this.intro = intro;
    }

    public Boolean getCross() {
        return cross;
    }

    public void setCross(Boolean cross) {
        this.cross = cross;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
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

    public Boolean getUpload() {
        return upload;
    }

    public void setUpload(Boolean upload) {
        this.upload = upload;
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

}

