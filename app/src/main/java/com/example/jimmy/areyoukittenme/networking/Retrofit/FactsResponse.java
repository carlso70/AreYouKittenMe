package com.example.jimmy.areyoukittenme.networking.Retrofit;

/**
 * Created by jamescarlson on 5/26/17.
 */

import java.util.List;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class FactsResponse {

    @SerializedName("facts")
    @Expose
    private List<String> facts = null;
    @SerializedName("success")
    @Expose
    private String success;

    public List<String> getFacts() {
        return facts;
    }

    public void setFacts(List<String> facts) {
        this.facts = facts;
    }

    public String getSuccess() {
        return success;
    }

    public void setSuccess(String success) {
        this.success = success;
    }

}