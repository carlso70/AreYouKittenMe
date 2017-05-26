package com.example.jimmy.areyoukittenme.networking.Retrofit;

/**
 * Created by jamescarlson on 5/26/17.
 */

import com.example.jimmy.areyoukittenme.networking.Retrofit.Data;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class MemesResponse {

    @SerializedName("success")
    @Expose
    private Boolean success;
    @SerializedName("data")
    @Expose
    private Data data;

    public Boolean getSuccess() {
        return success;
    }

    public void setSuccess(Boolean success) {
        this.success = success;
    }

    public Data getData() {
        return data;
    }

    public void setData(Data data) {
        this.data = data;
    }

}

