package com.diki.submisisatu.widget;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.widget.RemoteViews;
import android.widget.RemoteViewsService;

import com.bumptech.glide.Glide;
import com.diki.submisisatu.BuildConfig;
import com.diki.submisisatu.R;
import com.diki.submisisatu.repo.FavoriteMovieRepository;
import com.diki.submisisatu.repo.dao.FavoriteMovieDB;

import java.util.ArrayList;
import java.util.List;

class MyWidgetViewFactory implements RemoteViewsService.RemoteViewsFactory {
    private List<FavoriteMovieDB> movies = new ArrayList<>();
    private FavoriteMovieRepository cardHelper;
    private Context context;

    public MyWidgetViewFactory(Context context) {
        this.context = context;
        cardHelper = FavoriteMovieRepository.getInstance(context);
    }

    @Override
    public void onCreate() {

    }

    @SuppressLint("CheckResult")
    @Override
    public void onDataSetChanged() {
        movies = cardHelper.findAll();
    }

    @Override
    public void onDestroy() {

    }

    @Override
    public int getCount() {
        return movies.size();
    }

    @Override
    public RemoteViews getViewAt(int i) {
        RemoteViews remoteViews = new RemoteViews(context.getPackageName(), R.layout.widget_item);
        System.out.println("GAMBAR ke " + i);
        System.out.println(BuildConfig.POSTER_PATH + movies.get(i).getPosterPath());
        try {
            Bitmap poster = Glide.with(context)
                    .asBitmap()
                    .load(BuildConfig.POSTER_PATH + movies.get(i).getPosterPath())
                    .submit()
                    .get();
            remoteViews.setImageViewBitmap(R.id.iv_widget_item, poster);
        } catch (Exception ex) {
            ex.printStackTrace();
        }

        Intent intent = new Intent();
        remoteViews.setOnClickFillInIntent(R.id.iv_widget_item, intent);
        return remoteViews;
    }

    @Override
    public RemoteViews getLoadingView() {
        return null;
    }

    @Override
    public int getViewTypeCount() {
        return 1;
    }

    @Override
    public long getItemId(int i) {
        return 0;
    }

    @Override
    public boolean hasStableIds() {
        return false;
    }
}
