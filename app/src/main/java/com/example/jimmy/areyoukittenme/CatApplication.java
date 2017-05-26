package com.example.jimmy.areyoukittenme;

import android.app.Application;

import io.realm.Realm;
import io.realm.RealmConfiguration;

/**
 * Created by jimmy on 5/25/17.
 */

public class CatApplication extends Application {
    @Override
    public void onCreate() {
        super.onCreate();

        // Init Realm, and create realmConfiguration
        Realm.init(this);
        RealmConfiguration realmConfiguration = new RealmConfiguration.Builder().build();
        Realm.setDefaultConfiguration(realmConfiguration);
    }
}
