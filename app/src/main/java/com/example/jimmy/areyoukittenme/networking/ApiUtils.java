package com.example.jimmy.areyoukittenme.networking;

import com.example.jimmy.areyoukittenme.Meme;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

import java.util.List;

/**
 * Created by jimmy on 5/24/17.
 */

public class ApiUtils {
    public static final String FACT_URL = "http://catfacts-api.appspot.com/api/facts?number=";
    public static final String MEME_URL = "https://api.imgflip.com/get_memes";


    public static void getFacts(List<String> facts, int factCnt) {
        String response = HttpRequest.get(FACT_URL + factCnt).body();
        System.out.println("Response was: " + response);
        try {
            JsonElement jelement = new JsonParser().parse(response);
            JsonObject jobject = jelement.getAsJsonObject();
            JsonArray jarray = jobject.getAsJsonArray("facts");
            for (int i = 0; i < jarray.size(); i++) {
                facts.add(jarray.get(i).toString());
            }
        } catch (Exception e){
            e.printStackTrace();
        }
    }

    public static void getMemes(List<Meme> memes) {
        String response = HttpRequest.get(MEME_URL).body();
        System.out.println("Response was: " + response);
        try {
            JsonElement jelement = new JsonParser().parse(response);
            JsonObject jobject = jelement.getAsJsonObject();
            JsonObject jdata = jobject.getAsJsonObject("data");
            JsonArray jarray = jdata.getAsJsonArray("memes");
            for (int i = 0; i < jarray.size(); i++) {
                System.out.println(jarray.get(i));
                JsonElement name = jarray.get(i).getAsJsonObject().get("name");
                JsonElement url = jarray.get(i).getAsJsonObject().get("url");
                memes.add(new Meme(name.getAsString(), url.getAsString()));
            }
        } catch (Exception e){
            e.printStackTrace();
        }
    }


}
