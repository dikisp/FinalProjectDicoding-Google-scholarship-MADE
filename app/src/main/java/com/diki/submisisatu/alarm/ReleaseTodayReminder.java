package com.diki.submisisatu.alarm;

import android.annotation.SuppressLint;
import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.graphics.BitmapFactory;
import android.os.Build;
import android.support.v4.app.NotificationCompat;
import android.util.Log;

import com.diki.submisisatu.Api.APIClient;
import com.diki.submisisatu.Api.MovieApi;
import com.diki.submisisatu.Api.Scraper;
import com.diki.submisisatu.BuildConfig;
import com.diki.submisisatu.Model.Movie;
import com.diki.submisisatu.R;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;

public class ReleaseTodayReminder extends BroadcastReceiver {
    public static final int NOTIFICATION_ID = 2;
    public static String CHANNEL_ID = "Film";
    public static CharSequence CHANNEL_NAME = "Hari Ini";
    private static final String API_KEY = BuildConfig.APIKEY;

    private MovieApi mMovieApi;

    @SuppressLint("CheckResult")
    @Override
    public void onReceive(final Context context, Intent intent) {
        mMovieApi = APIClient.getClient().create(MovieApi.class);
        mMovieApi.findUpcomingMovie(BuildConfig.APIKEY)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Consumer<Scraper<Movie>>() {
                    @Override
                    public void accept(Scraper<Movie> movieScraper) throws Exception {
                        onSuccess(context, movieScraper);
                    }
                });
    }

    private void onSuccess(Context context, Scraper<Movie> movieScraper) {
        @SuppressLint("SimpleDateFormat")
        String today = new SimpleDateFormat("yyyy-MM-dd", Locale.getDefault()).format(new Date());
        NotificationCompat.InboxStyle inboxStyle = new NotificationCompat.InboxStyle();
        for (Movie movie : movieScraper.getResultMovies()) {
            if (movie.getReleaseDate().equals(today)) {
                inboxStyle.addLine(movie.getTitle());
            }
        }
        inboxStyle.setSummaryText("Dan masih banyak lagi...");
        showNotification(context, inboxStyle, today);
    }

    private void showNotification(Context context, NotificationCompat.InboxStyle inboxStyle,
                                  String today) {
        NotificationManager mNotificationManager =
                (NotificationManager) context.getSystemService(Context.NOTIFICATION_SERVICE);

        NotificationCompat.Builder mBuilder;
        mBuilder = new NotificationCompat.Builder(context, CHANNEL_ID)
                .setSmallIcon(R.drawable.ic_notifications)
                .setLargeIcon(BitmapFactory.decodeResource(context.getResources(), R.drawable.ic_notifications))
                .setContentTitle("Film yang rilis hari ini")
                .setContentText(today)
                .setStyle(inboxStyle)
                .setAutoCancel(true);

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            NotificationChannel channel = new NotificationChannel(CHANNEL_ID, CHANNEL_NAME, NotificationManager.IMPORTANCE_DEFAULT);
            mBuilder.setChannelId(CHANNEL_ID);
            if (mNotificationManager != null) {
                mNotificationManager.createNotificationChannel(channel);
            }
        }

        Notification notification = mBuilder.build();
        if (mNotificationManager != null) {
            mNotificationManager.notify(NOTIFICATION_ID, notification);
        }
    }
}
