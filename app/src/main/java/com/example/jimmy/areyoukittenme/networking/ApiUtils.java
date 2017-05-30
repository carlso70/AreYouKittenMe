package com.example.jimmy.areyoukittenme.networking;

import com.example.jimmy.areyoukittenme.Meme;
import com.example.jimmy.areyoukittenme.networking.Retrofit.FactService;
import com.example.jimmy.areyoukittenme.networking.Retrofit.FactsResponse;
import com.example.jimmy.areyoukittenme.networking.Retrofit.MemeService;
import com.example.jimmy.areyoukittenme.networking.Retrofit.MemesResponse;
import java.util.List;

import retrofit2.Call;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by jimmy on 5/24/17.
 */

public class ApiUtils {
    private static final String FACT_URL = "http://catfacts-api.appspot.com/";
    private static final String MEME_URL = "https://api.imgflip.com/";

    public static void getFacts(List<String> facts, int cnt) {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(FACT_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        FactService factService = retrofit.create(FactService.class);
        Call<FactsResponse> factsResponseCall = factService.getCatFacts(cnt);
        // Execute retrofit call
        try {
            System.out.println(factsResponseCall.request().toString());
            FactsResponse response = factsResponseCall.execute().body();
            List<String> factList = response.getFacts();
            if (factList == null) throw new Exception("FactsResponse is null");
            for (String fact:factList)
                facts.add(fact);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void getMemes(List<Meme> memes) {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(MEME_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        MemeService memeService = retrofit.create(MemeService.class);
        Call<MemesResponse> memesResponseCall = memeService.getMemes();
        try {
            MemesResponse memesResponse = memesResponseCall.execute().body();
            List<Meme> tmpList = memesResponse.getData().getMemes();
            if (tmpList == null) throw new Exception("memeResponse is null");
            for (Meme m: tmpList)
                memes.add(m);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
