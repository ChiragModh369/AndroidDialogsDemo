package com.example.alertdialogdemo

import android.app.PendingIntent
import android.app.PendingIntent.*
import android.appwidget.AppWidgetManager
import android.appwidget.AppWidgetProvider
import android.content.Context
import android.content.Intent
import android.util.Log
import android.widget.RemoteViews
import android.widget.Toast

/**
 * Implementation of App Widget functionality.
 */
class NewAppWidget : AppWidgetProvider() {
    val Click:String ="Clicked on Button1"
    val Click2:String = "Clicked on Button2"
    override fun onUpdate(
        context: Context,
        appWidgetManager: AppWidgetManager,
        appWidgetIds: IntArray
    ) {
        // There may be multiple widgets active, so update all of them
        for (appWidgetId in appWidgetIds) {
            updateAppWidget(context, appWidgetManager, appWidgetId)
        }
    }

    override fun onEnabled(context: Context) {
        // Enter relevant functionality for when the first widget is created
    }

    override fun onDisabled(context: Context) {
        // Enter relevant functionality for when the last widget is disabled
    }

    override fun onReceive(context: Context?, intent: Intent?) {
        super.onReceive(context, intent)
        if(Click == intent!!.action)
        {
            Toast.makeText(context, "Clicked on Button 1", Toast.LENGTH_LONG).show()
            Log.v("Btn1","Clicked on  Button 1")
        }

        if (Click2 == intent.action)
        {
            Toast.makeText(context, "Clicked on Button 2", Toast.LENGTH_LONG).show()
            Log.v("Btn2","Clicked on  Button 2")
        }
    }
    internal fun updateAppWidget(
        context: Context,
        appWidgetManager: AppWidgetManager,
        appWidgetId: Int
    ) {
        val widgetText = context.getString(R.string.appwidget_text)
        // Construct the RemoteViews object
        val views = RemoteViews(context.packageName, R.layout.new_app_widget)
//        views.setTextViewText(R.id.appwidget_text, widgetText)

        val intent1 = Intent(context,javaClass)
        intent1.action = Click

        val intent2 = Intent(context,javaClass)
        intent2.action = Click2

        views.setOnClickPendingIntent(R.id.btn1, getBroadcast(context,0,intent1,FLAG_IMMUTABLE))
        views.setOnClickPendingIntent(R.id.btn2, getBroadcast(context,0,intent2, FLAG_IMMUTABLE))

        // Instruct the widget manager to update the widget
        appWidgetManager.updateAppWidget(appWidgetId, views)
    }
}

