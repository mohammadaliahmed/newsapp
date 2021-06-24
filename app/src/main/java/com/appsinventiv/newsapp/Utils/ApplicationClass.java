package com.appsinventiv.newsapp.Utils;

import android.app.Application;
import android.content.Context;


/**
 * Created by AliAh on 29/03/2018.
 */

public class ApplicationClass extends Application {
    private static ApplicationClass instance;


    public static ApplicationClass getInstance() {
        return instance;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        instance = this;


    }

    @Override
    protected void attachBaseContext(Context base) {
        super.attachBaseContext(base);
    }
}
