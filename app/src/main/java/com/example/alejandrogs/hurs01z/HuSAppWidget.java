package com.example.alejandrogs.hurs01z;

import android.app.PendingIntent;
import android.appwidget.AppWidgetManager;
import android.appwidget.AppWidgetProvider;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.Uri;
import android.widget.RemoteViews;

import java.util.Date;

/**
 * Implementation of App Widget functionality.
 */
public class HuSAppWidget extends AppWidgetProvider {
    static AppWidgetManager appWidgetManager;
    static void updateAppWidget(Context context, AppWidgetManager appWidgetManager,
                                int appWidgetId) {


        RemoteViews views = new RemoteViews(context.getPackageName(), R.layout.hu_sapp_widget);

        Intent intent = new Intent(context,HuSAppWidget.class);
        intent.setAction("ACTUALIZAR");
        intent.putExtra(AppWidgetManager.EXTRA_APPWIDGET_ID,0);
        PendingIntent pendingIntent = PendingIntent.getBroadcast(context,0,intent,0);
        views.setOnClickPendingIntent(R.id.bt_activo,pendingIntent);
        // Instruct the widget manager to update the widget
        appWidgetManager.updateAppWidget(appWidgetId, views);

    }

    @Override
    public void onUpdate(Context context, AppWidgetManager appWidgetManager, int[] appWidgetIds) {
        // There may be multiple widgets active, so update all of them
        for (int appWidgetId : appWidgetIds) {
            updateAppWidget(context, appWidgetManager, appWidgetId);
        }
    }

    static void actualizarAppWidget(Context context, AppWidgetManager appWidgetManager, int widgetId) {

        SharedPreferences preferences = context.getSharedPreferences("preferenciaswidget", Context.MODE_PRIVATE);
        String dato = preferences.getString("dato","dato");

        Date Dato = new Date();
        int hora = Dato.getHours();
        int minutos = Dato.getMinutes();
        int segundos = Dato.getSeconds();


        Intent intent = new Intent(context,HuSAppWidget.class);
        intent.setAction("ACTUALIZAR");
        intent.putExtra(AppWidgetManager.EXTRA_APPWIDGET_ID,widgetId);
        PendingIntent pendingIntent = PendingIntent.getBroadcast(context,widgetId,intent,0);

    }

    @Override
    public void onEnabled(Context context) {
        // Enter relevant functionality for when the first widget is created
    }

    @Override
    public void onDisabled(Context context) {
        // Enter relevant functionality for when the last widget is disabled
    }

    @Override
    public void onReceive(Context context, Intent intent) {
        super.onReceive(context, intent);
        if (intent.getAction().equals("ACTUALIZAR")){
            Intent Nota =new Intent(context,NoteActivity.class);
            Nota.putExtra("Nota","");
            Nota.putExtra("ID","");
            Nota.putExtra("Edit","0");
            Nota.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            Nota.setData(Uri.parse(Nota.toUri(Intent.URI_INTENT_SCHEME)));
            context.startActivity(Nota);
        }
    }
}

