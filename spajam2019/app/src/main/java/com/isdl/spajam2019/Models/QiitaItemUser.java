package com.isdl.spajam2019.Models;

import com.google.gson.annotations.SerializedName;

/**
 * Created by takayayuuki on 2018/05/25.
 */

public class QiitaItemUser {
    @SerializedName("id")
    public int id;

    @SerializedName("url_name")
    public String urlName;

    @SerializedName("profile_image_url")
    public String profileImageUrl;
}
