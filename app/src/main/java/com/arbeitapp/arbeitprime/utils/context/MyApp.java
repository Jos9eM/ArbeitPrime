package com.arbeitapp.arbeitprime.utils.context;

import android.app.Application;
import android.content.Context;

public class MyApp extends Application {

    private static MyApp instance;

    public static MyApp getInstance(){
        return instance;
    }
    public static Context getMyContext(){
        return instance;
    }

    @Override
    public void onCreate() {
        instance = this;
        super.onCreate();
    }
}
