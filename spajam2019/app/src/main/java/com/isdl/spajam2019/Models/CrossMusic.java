package com.isdl.spajam2019.Models;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class CrossMusic {

    @SerializedName("id")
    @Expose
    private Integer id;
    @SerializedName("name")
    @Expose
    private String name;
    @SerializedName("artist")
    @Expose
    private String artist;
    @SerializedName("user_cross_music_id")
    @Expose
    private Integer userCrossMusicId;

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

    public Integer getUserCrossMusicId() {
        return userCrossMusicId;
    }

    public void setUserCrossMusicId(Integer userCrossMusicId) {
        this.userCrossMusicId = userCrossMusicId;
    }
}
