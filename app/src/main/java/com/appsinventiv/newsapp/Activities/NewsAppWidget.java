package com.appsinventiv.newsapp.Activities;

import android.app.PendingIntent;
import android.appwidget.AppWidgetManager;
import android.appwidget.AppWidgetProvider;
import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.widget.RemoteViews;

import com.appsinventiv.newsapp.NetworkResponses.ApiResponse;
import com.appsinventiv.newsapp.NetworkResponses.Value;
import com.appsinventiv.newsapp.R;
import com.appsinventiv.newsapp.Utils.AppConfig;
import com.appsinventiv.newsapp.Utils.CommonUtils;
import com.appsinventiv.newsapp.Utils.UserClient;

import java.io.IOException;
import java.security.SecureRandom;
import java.util.AbstractSequentialList;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Implementation of App Widget functionality.
 * App Widget Configuration implemented in {@link NewsAppWidgetConfigureActivity NewsAppWidgetConfigureActivity}
 */
public class NewsAppWidget extends AppWidgetProvider {
    public static String ACTION_WIDGET_CONFIGURE = "ConfigureWidget";
    private static final String ACTION_UPDATE_CLICK_NEXT = "action.UPDATE_CLICK_NEXT";
    private static final String ACTION_UPDATE_CLICK_PREVIOUS = "action.UPDATE_CLICK_PREVIOUS";


    private static List<Value> itemList = new ArrayList<>();
    private static RemoteViews viewsa;


    public static int appWidgetIdasd;

    static void updateAppWidget(Context context, AppWidgetManager appWidgetManager,
                                int appWidgetId) {

        CharSequence widgetText = NewsAppWidgetConfigureActivity.loadTitlePref(context, appWidgetId);
        // Construct the RemoteViews object
        RemoteViews views = new RemoteViews(context.getPackageName(), R.layout.news_app_widget);
        views.setTextViewText(R.id.appwidget_text, widgetText);
        viewsa = views;
        appWidgetIdasd=appWidgetId;


//        Intent configIntent = new Intent(context, NewsAppWidgetConfigureActivity.class);
//        configIntent.putExtra(AppWidgetManager.EXTRA_APPWIDGET_ID, appWidgetId);
//        PendingIntent configPendingIntent = PendingIntent.getActivity(context, appWidgetId, configIntent, 0);
//        views.setOnClickPendingIntent(R.id.next, configPendingIntent);
//        configIntent.setAction(ACTION_WIDGET_CONFIGURE + Integer.toString(appWidgetId));
        views.setOnClickPendingIntent(R.id.next, getPendingSelfIntent(context, ACTION_UPDATE_CLICK_NEXT));
        views.setOnClickPendingIntent(R.id.back, getPendingSelfIntent(context, ACTION_UPDATE_CLICK_NEXT));


        GetWeatherTask getWeatherTask = new GetWeatherTask(views, context, appWidgetId);
        getWeatherTask.onPerform();

        // Instruct the widget manager to update the widget
        appWidgetManager.updateAppWidget(appWidgetId, views);
    }

    private static PendingIntent getPendingSelfIntent(Context context, String action) {

        Intent intent = new Intent(context, NewsAppWidget.class); // An intent directed at the current class (the "self").
        intent.setAction(action);
        return PendingIntent.getBroadcast(context, 0, intent, 0);
    }

    @Override
    public void onReceive(Context context, Intent intent) {
        super.onReceive(context, intent);


        if (ACTION_UPDATE_CLICK_NEXT.equals(intent.getAction())) {
            // if the user clicked next
            Log.d("asdfsf", "Sdf");
            GetWeatherTask getWeatherTask = new GetWeatherTask(viewsa, context, appWidgetIdasd);
            getWeatherTask.onPerform();
        }


//        else if (ACTION_UPDATE_CLICK_PREVIOUS.equals(intent.getAction())) {
//            // if the user clicked previous
//        }

    }

    @Override
    public void onUpdate(Context context, AppWidgetManager appWidgetManager, int[] appWidgetIds) {
        // There may be multiple widgets active, so update all of them
        for (int appWidgetId : appWidgetIds) {
            updateAppWidget(context, appWidgetManager, appWidgetId);
        }
    }

    @Override
    public void onDeleted(Context context, int[] appWidgetIds) {
        // When the user deletes the widget, delete the preference associated with it.
        for (int appWidgetId : appWidgetIds) {
            NewsAppWidgetConfigureActivity.deleteTitlePref(context, appWidgetId);
        }
    }

    @Override
    public void onEnabled(Context context) {
        // Enter relevant functionality for when the first widget is created
    }

    @Override
    public void onDisabled(Context context) {
        // Enter relevant functionality for when the last widget is disabled
    }

}