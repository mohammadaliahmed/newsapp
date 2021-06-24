package com.appsinventiv.newsapp.Utils;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class AppConfig {
//    public static String LPTOP_ID = "http://172.29.27.84/accessale/";
        public static String LPTOP_ID = "http://192.168.8.102/accessaleserver/";
    public static String SERVER_URL = "https://bing-news-search1.p.rapidapi.com/";
    public static String SHARE_URL = "http://accessale.com/";
    public static String BASE_URL = SERVER_URL;
    public static String API_USERNAME = "WF9.FJ8u'FP{c5Pw";
    public static String API_PASSOWRD = "3B~fauh5s93j[FKb";

    public static String BASE_URL_Image = BASE_URL + "public/images/";
    public static String BASE_URL_AUDIO = BASE_URL + "public/audio/";
    public static String BASE_URL_Videos = BASE_URL + "public/videos/";
    public static String BASE_URL_QR = BASE_URL + "public/qr/";
    public static String TOKKEN = "http://acnure.com/";

    public static Retrofit getRetrofit() {
        return new Retrofit.Builder()
                .baseUrl(AppConfig.BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

    }

}
