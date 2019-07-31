package com.example.swastha_naari;

import android.app.Application;

import io.realm.Realm;
import io.realm.RealmConfiguration;

public class MyApplication extends Application {
    @Override
    public void onCreate() {
        super.onCreate();
        Realm.init(this);
        RealmConfiguration realmConfig = new RealmConfiguration.Builder()
                .name("login.realm")
                .schemaVersion(1)
                .migration(new Migration())
                .build();
        Realm.setDefaultConfiguration(realmConfig);
    }
}
