package com.example.jimmy.areyoukittenme.networking.Retrofit;

/**
 * Created by jamescarlson on 5/26/17.
 * Used as a result from the meme api
 */

import java.util.List;

import com.example.jimmy.areyoukittenme.Meme;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Data {

    @SerializedName("memes")
    @Expose
    private List<Meme> memes = null;

    public List<Meme> getMemes() {
        return memes;
    }

    public void setMemes(List<Meme> memes) {
        this.memes = memes;
    }

}