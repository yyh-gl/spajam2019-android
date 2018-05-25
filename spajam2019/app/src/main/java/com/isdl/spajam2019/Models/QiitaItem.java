package com.isdl.spajam2019.Models;

import com.google.gson.annotations.SerializedName;

/**
 * Created by takayayuuki on 2018/05/25.
 */
public class QiitaItem {
    @SerializedName("id")
    public int id;

    @SerializedName("uuid")
    public String uuid;

    @SerializedName("title")
    public String title;

    @SerializedName("url")
    public String url;

    @SerializedName("user")
    public QiitaItemUser user;
}
