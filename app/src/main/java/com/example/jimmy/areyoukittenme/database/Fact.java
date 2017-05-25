package com.example.jimmy.areyoukittenme.database;

import io.realm.RealmObject;
import io.realm.annotations.Required;

/**
 * Created by jimmy on 5/24/17.
 * Used to store data in a realm db
 */

public class Fact extends RealmObject{
    @Required
    private String fact;

    public Fact(String fact) {
        this.fact = fact;
    }

    public Fact() {
        this.fact = "";
    }

    public void setFact(String fact) {
        this.fact = fact;
    }

    public String getFact(){
        return this.fact;
    }
}
