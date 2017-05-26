package com.example.jimmy.areyoukittenme.networking.Retrofit;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;

/**
 * Created by jamescarlson on 5/26/17.
 */

public interface FactService {
    @GET("api/facts?=")
    Call<FactsResponse> getCatFacts(@Query("number") int number);
}
