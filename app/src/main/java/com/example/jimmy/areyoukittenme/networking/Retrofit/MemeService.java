package com.example.jimmy.areyoukittenme.networking.Retrofit;

import retrofit2.Call;
import retrofit2.http.GET;

/**
 * Created by jamescarlson on 5/26/17.
 */

public interface MemeService {
    @GET("get_memes")
    Call<MemesResponse> getMemes();
}
