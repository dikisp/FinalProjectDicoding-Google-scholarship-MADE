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
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.diki.submisisatu.BuildConfig;
import com.diki.submisisatu.DetailMovieActivity;
import com.diki.submisisatu.Model.Movie;
import com.diki.submisisatu.R;

import java.util.List;

public class ListMovieAdapter extends RecyclerView.Adapter<ListMovieAdapter.MovieViewHolder> {
    private static final String POSTER_PATH = BuildConfig.POSTER_PATH;

    private final Context context;
    private final List<Movie> ListMovie;
    private ProgressBar loading;
    private List<Movie> getMoviesList() {
        return ListMovie;
    }

    public ListMovieAdapter(Context context, List<Movie> ListMovie) {
        this.context = context;
        this.ListMovie = ListMovie;
    }

    @NonNull
    @Override
    public ListMovieAdapter.MovieViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.item_row_movie, viewGroup, false);
        return new MovieViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ListMovieAdapter.MovieViewHolder movieViewHolder, final int i) {

        Glide.with(context)
                .load(POSTER_PATH +ListMovie.get(i).getPosterPath())
                .into(movieViewHolder.Poster);

        movieViewHolder.Title.setText(ListMovie.get(i).getTitle());
        movieViewHolder.Overview.setText(ListMovie.get(i).getOverview());
        movieViewHolder.relativeLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Movie movies = ListMovie.get(i);

//                Log.d("cek",movies.getRating());
                Intent intent = new Intent(context, DetailMovieActivity.class);
                intent.putExtra(DetailMovieActivity.EXTRA_MOVIE, movies);
                context.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return ListMovie.size();
    }

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




}
