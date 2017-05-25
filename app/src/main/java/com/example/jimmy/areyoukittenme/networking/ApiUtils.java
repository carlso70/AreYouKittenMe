package com.example.jimmy.areyoukittenme.networking;

import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

import java.util.List;

/**
 * Created by jimmy on 5/24/17.
 */

public class ApiUtils {
    public static final String BASE_URL = "http://catfacts-api.appspot.com/api/facts?number=";

    public static void getFacts(List<String> facts, int factCnt) {
        String response = HttpRequest.get(BASE_URL + factCnt).body();
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


}
