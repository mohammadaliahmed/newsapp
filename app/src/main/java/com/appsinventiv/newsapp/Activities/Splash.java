package com.appsinventiv.newsapp.Activities;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;


import com.appsinventiv.newsapp.Adapters.MainSliderAdapter;
import com.appsinventiv.newsapp.NetworkResponses.ApiResponse;
import com.appsinventiv.newsapp.NetworkResponses.Value;
import com.appsinventiv.newsapp.R;
import com.appsinventiv.newsapp.Utils.AppConfig;
import com.appsinventiv.newsapp.Utils.CommonUtils;
import com.appsinventiv.newsapp.Utils.UserClient;
import com.google.gson.JsonObject;

import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.ViewPager;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class Splash extends AppCompatActivity {

    ViewPager viewPager;
    private List<Value> itemList = new ArrayList<>();
    private int currentPic = 0;
    private MainSliderAdapter mViewPagerAdapter;

    ImageView back, next;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);
        next = findViewById(R.id.next);
        back = findViewById(R.id.back);
        viewPager = findViewById(R.id.viewPager);

        mViewPagerAdapter = new MainSliderAdapter(this, itemList);
        viewPager.setAdapter(mViewPagerAdapter);

        next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                currentPic++;
                viewPager.setCurrentItem(currentPic);
            }
        });
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (currentPic > 0) {
                    currentPic--;
                    viewPager.setCurrentItem(currentPic);
                }
            }
        });


        viewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                currentPic = position;
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });
        getDataFromServer();
    }

    private void getDataFromServer() {
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
                        mViewPagerAdapter.notifyDataSetChanged();

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
