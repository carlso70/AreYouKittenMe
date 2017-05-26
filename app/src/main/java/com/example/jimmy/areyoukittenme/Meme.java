package com.example.jimmy.areyoukittenme;

import android.media.Image;

import java.net.URL;

/**
 * Created by jamescarlson on 5/26/17.
 */

public class Meme {
    private String title;
    private String thumbnail;

    public Meme(String title, String thumbnail) {
        this.title = title;
        this.thumbnail = thumbnail;
    }

    public String getTitle() {
        return this.title;
    }

    public String getThumbnail() {
        return this.thumbnail;
    }
}
