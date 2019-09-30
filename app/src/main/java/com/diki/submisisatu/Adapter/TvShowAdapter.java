package com.diki.submisisatu.Adapter;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.diki.submisisatu.BuildConfig;
import com.diki.submisisatu.DetailTVActivity;
import com.diki.submisisatu.Model.TV;
import com.diki.submisisatu.R;

import java.util.List;

public class TvShowAdapter extends RecyclerView.Adapter<TvShowAdapter.MovieViewHolder> {
    private static final String POSTER_PATH = BuildConfig.POSTER_PATH;

    public class MovieViewHolder extends RecyclerView.ViewHolder {
        private final RelativeLayout relativeLayout;
        private final ImageView Poster;
        private final TextView Title;
        private final TextView Overview;

        MovieViewHolder(@NonNull View itemView) {
            super(itemView);

            relativeLayout = itemView.findViewById(R.id.rv_listMovie);
            Poster = itemView.findViewById(R.id.img_item_foto);
            Title = itemView.findViewById(R.id.tv_item_name);
            Overview = itemView.findViewById(R.id.tv_item_remarks);
        }
    }

    private final Context context;
    private final List<TV> ListMovie;

    private List<TV> getMoviesList() {
        return ListMovie;
    }

    public TvShowAdapter(Context context, List<TV> ListMovie) {
        this.context = context;
        this.ListMovie = ListMovie;
    }

    @NonNull
    @Override
    public TvShowAdapter.MovieViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.item_row_movie, viewGroup, false);
        return new MovieViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull TvShowAdapter.MovieViewHolder movieViewHolder, int i) {
        final TV movie = getMoviesList().get(i);

        Glide.with(context)
                .load(POSTER_PATH +ListMovie.get(i).getPosterPath())
                .into(movieViewHolder.Poster);

        movieViewHolder.Title.setText(ListMovie.get(i).getName());
        movieViewHolder.Overview.setText(ListMovie.get(i).getOverview());
        movieViewHolder.relativeLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, DetailTVActivity.class);
                intent.putExtra(DetailTVActivity.EXTRA_TV, movie);
                context.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return ListMovie.size();
    }



}
