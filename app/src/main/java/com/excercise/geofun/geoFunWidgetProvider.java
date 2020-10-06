package com.excercise.geofun;

import android.app.PendingIntent;
import android.appwidget.AppWidgetManager;
import android.appwidget.AppWidgetProvider;
import android.content.Context;
import android.content.Intent;
import android.widget.RemoteViews;

public class geoFunWidgetProvider extends AppWidgetProvider {

    @Override
    public void onUpdate(Context context, AppWidgetManager appWidgetManager, int[] appWidgetIds) {
        for (int i : appWidgetIds){
            Intent intent = new Intent (context, MainActivity.class);
            PendingIntent pI = PendingIntent.getActivity(context, 0, intent, 0);
            RemoteViews v = new RemoteViews(context.getPackageName(), R.layout.widget);

            v.setOnClickPendingIntent(R.id.goToAppButton, pI);

            appWidgetManager.updateAppWidget(i, v);
        }
    }
}
