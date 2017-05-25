package com.example.jimmy.areyoukittenme.database;

import android.content.Context;

import java.util.List;

import io.realm.Realm;
import io.realm.RealmResults;

/**
 * Created by jimmy on 5/25/17.
 */

public class DbClient {

    public static void saveFact(String f, Context context) {
        Realm realm = Realm.getDefaultInstance();

        realm.beginTransaction();
        Fact fact = realm.createObject(Fact.class);
        fact.setFact(f);
        realm.commitTransaction();
    }

    public static List<String> getFacts(List<String> facts, Context context) {
        Realm realm = Realm.getDefaultInstance();
        RealmResults<Fact> results = realm.where(Fact.class).findAll();
        if (results != null) {
            for (Fact fact : results)
                facts.add(fact.getFact());
        }
        System.out.println(facts);
        return facts;
    }

    public static void deleteFact(String fact, Context context) {
        Realm realm = Realm.getDefaultInstance();
        realm.beginTransaction();
        RealmResults<Fact> results = realm.where(Fact.class).equalTo("fact", fact).findAll();
        results.deleteAllFromRealm();
        realm.commitTransaction();
    }
}