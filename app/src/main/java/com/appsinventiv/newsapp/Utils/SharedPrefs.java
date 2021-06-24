package com.appsinventiv.newsapp.Utils;

import android.content.Context;
import android.content.SharedPreferences;

import com.appsinventiv.newsapp.Models.User;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.util.List;

/**
 * Created by AliAh on 29/03/2018.
 */

public class SharedPrefs {
    Context context;

    private SharedPrefs() {

    }

    public static void setLikedList(List<Integer> itemList) {

        Gson gson = new Gson();
        String json = gson.toJson(itemList);
        preferenceSetter("likes", json);
    }

    public static List getLikedList() {
        Gson gson = new Gson();
        List<Integer> playersList = (List<Integer>) gson.fromJson(preferenceGetter("likes"),
                new TypeToken<List<Integer>>() {
                }.getType());
        return playersList;
    }

//    public static void setViewedAdList(HashMap<Integer, AdsModel> itemList) {
//
//        Gson gson = new Gson();
//        String json = gson.toJson(itemList);
//        preferenceSetter("setViewedAdList", json);
//    }
//
//    public static HashMap<Integer, AdsModel> getViewedAdList() {
//        Gson gson = new Gson();
//
//        HashMap<Integer, AdsModel> retMap = new Gson().fromJson(
//                preferenceGetter("setViewedAdList"), new TypeToken<HashMap<Integer, AdsModel>>() {
//                }.getType()
//        );
//
//        return retMap;
//    }


    public static String getadminFcmKey() {
        return preferenceGetter("adminfcm");
    }

    public static void setadminFcmKey(String username) {
        preferenceSetter("adminfcm", username);
    }

    public static String getFcmKey() {
        return preferenceGetter("getFcmKey");
    }

    public static void setFcmKey(String username) {
        preferenceSetter("getFcmKey", username);
    }

    public static String getLat() {
        return preferenceGetter("getLat");
    }

    public static void setLat(String username) {
        preferenceSetter("getLat", username);
    }

    public static String getLon() {
        return preferenceGetter("getLon");
    }

    public static void setLon(String username) {
        preferenceSetter("getLon", username);
    }


    public static void setUser(User model) {

        Gson gson = new Gson();
        String json = gson.toJson(model);
        preferenceSetter("customerModel", json);
    }

    public static User getUser() {
        Gson gson = new Gson();
        User customer = gson.fromJson(preferenceGetter("customerModel"), User.class);

        return customer;
    }

    public static void setTempUser(User model) {

        Gson gson = new Gson();
        String json = gson.toJson(model);
        preferenceSetter("setTempUser", json);
    }

    public static User getTempUser() {
        Gson gson = new Gson();
        User customer = gson.fromJson(preferenceGetter("setTempUser"), User.class);

        return customer;
    }

    public static void preferenceSetter(String key, String value) {
        SharedPreferences pref = ApplicationClass.getInstance().getApplicationContext().getSharedPreferences("user", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = pref.edit();
        editor.putString(key, value);
        editor.apply();
    }

    public static String preferenceGetter(String key) {
        SharedPreferences pref;
        String value = "";
        pref = ApplicationClass.getInstance().getApplicationContext().getSharedPreferences("user", Context.MODE_PRIVATE);
        value = pref.getString(key, "");
        return value;
    }

    public static void clearApp() {
        SharedPreferences pref = ApplicationClass.getInstance().getApplicationContext().getSharedPreferences("user", Context.MODE_PRIVATE);
        pref.edit().clear().commit();


    }

    public static void logout() {
        SharedPreferences pref = ApplicationClass.getInstance().getApplicationContext().getSharedPreferences("user", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = pref.edit();
        editor.clear();
        editor.apply();
    }
}
