package com.diki.submisisatu.widget;

import android.app.PendingIntent;
import android.appwidget.AppWidgetManager;
import android.appwidget.AppWidgetProvider;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.widget.RemoteViews;
import android.widget.Toast;

import com.diki.submisisatu.MainActivity;
import com.diki.submisisatu.R;

import timber.log.Timber;

/**
 * Implementation of App Widget functionality.
 */
public class MyStackWidget extends AppWidgetProvider {

    private static final String DETAIL_ACTION = "io.github.golok56.DETAIL_ACTION";

    static void updateAppWidget(Context context, AppWidgetManager appWidgetManager,
                                int appWidgetId) {
        Intent intent = new Intent(context, MyWidgetService.class)
                .putExtra(AppWidgetManager.EXTRA_APPWIDGET_ID, appWidgetId);
        intent.setData(Uri.parse(intent.toUri(Intent.URI_INTENT_SCHEME)));

        RemoteViews views = new RemoteViews(context.getPackageName(), R.layout.my_stack_widget);
        views.setRemoteAdapter(R.id.sv_widget, intent);
        views.setEmptyView(R.id.sv_widget, R.id.appwidget_text);

        Intent detailIntent = new Intent(context, MyStackWidget.class);
        detailIntent.setAction(MyStackWidget.DETAIL_ACTION);
        detailIntent.putExtra(AppWidgetManager.EXTRA_APPWIDGET_ID, appWidgetId);
        intent.setData(Uri.parse(intent.toUri(Intent.URI_INTENT_SCHEME)));
        PendingIntent toastPendingIntent = PendingIntent
                .getBroadcast(context, 0, detailIntent, PendingIntent.FLAG_UPDATE_CURRENT);
        views.setPendingIntentTemplate(R.id.sv_widget, toastPendingIntent);

        appWidgetManager.updateAppWidget(appWidgetId, views);
    }

    @Override
    public void onUpdate(Context context, AppWidgetManager appWidgetManager, int[] appWidgetIds) {
        for (int appWidgetId : appWidgetIds) {
            updateAppWidget(context, appWidgetManager, appWidgetId);
        }
    }

    @Override
    public void onReceive(Context context, Intent intent) {
        super.onReceive(context, intent);
        Timber.i(intent.getAction());
        if (intent.getAction() != null) {
            if (DETAIL_ACTION.equals(intent.getAction())) {
                Toast.makeText(context, "Ada item yang diklik", Toast.LENGTH_SHORT).show();
            }
        }
    }

    @Override
    public void onEnabled(Context context) {
    }

    @Override
    public void onDisabled(Context context) {
    }
}

