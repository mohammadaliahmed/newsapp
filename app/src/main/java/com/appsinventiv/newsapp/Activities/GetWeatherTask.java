package com.appsinventiv.newsapp.Activities;

import android.appwidget.AppWidgetManager;
import android.content.Context;
import android.os.AsyncTask;
import android.util.Log;
import android.widget.RemoteViews;

import com.appsinventiv.newsapp.NetworkResponses.ApiResponse;
import com.appsinventiv.newsapp.NetworkResponses.Value;
import com.appsinventiv.newsapp.R;
import com.appsinventiv.newsapp.Utils.AppConfig;
import com.appsinventiv.newsapp.Utils.ApplicationClass;
import com.appsinventiv.newsapp.Utils.CommonUtils;
import com.appsinventiv.newsapp.Utils.UserClient;

import org.json.JSONArray;
import org.json.JSONObject;
import org.json.JSONTokener;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.security.SecureRandom;
import java.util.AbstractSequentialList;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class GetWeatherTask {

    private RemoteViews views;
    Context context;
    int appWidgetId;
    private List<Value> itemList = new ArrayList<>();

    GetWeatherTask(RemoteViews views, Context context, int appWidgetId) {
        this.context = context;
        this.appWidgetId = appWidgetId;
        this.views = views;

    }

    public void onPerform() {
        UserClient getResponse = AppConfig.getRetrofit().create(UserClient.class);


        Call<ApiResponse> call = getResponse.getNews(true, "58f438ba6dmshe12796b24713acdp17f947jsnadaaab228ba1", "bing-news-search1.p.rapidapi.com");
        call.enqueue(new Callback<ApiResponse>() {
            @Override
            public void onResponse(Call<ApiResponse> call, Response<ApiResponse> response) {
                if (response.code() == 200) {
                    if (response.body() != null) {
                        for (Value value : response.body().getValue()) {
                            itemList.add(value);
                        }
                        Random rand = new Random();

// nextInt as provided by Random is exclusive of the top value so you need to add 1

                        int randomNum = rand.nextInt((itemList.size() - 2) + 1) + 1;
                        views.setTextViewText(R.id.appwidget_text, itemList.get(randomNum).getName());
                        AppWidgetManager manager = AppWidgetManager.getInstance(context);
                        manager.updateAppWidget(appWidgetId, views);


                    }
                } else {
                    try {
                        CommonUtils.showToast(response.errorBody().string());
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            }

            @Override
            public void onFailure(Call<ApiResponse> call, Throwable t) {

            }
        });
    }

}