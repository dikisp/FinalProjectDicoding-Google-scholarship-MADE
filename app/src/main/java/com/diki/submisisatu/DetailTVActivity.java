package com.diki.submisisatu;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.diki.submisisatu.Model.Movie;
import com.diki.submisisatu.Model.TV;
import com.diki.submisisatu.repo.FavoriteMovieRepository;
import com.github.ivbaranov.mfb.MaterialFavoriteButton;

import java.util.Objects;


public class DetailTVActivity extends AppCompatActivity {
    public static final  String EXTRA_TV = "EXTRA_TV";
    public  static  final String TID = "tv_id";
    private MaterialFavoriteButton favoriteButton;
    private FavoriteMovieRepository db;
    private TV data;
    private ProgressBar progressBar;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        TextView  title, tvRelease, deskripsi, rating, voteCount;
        final String posterPath = BuildConfig.POSTER_PATH;
        ImageView circleImageView;
        final  String TAG = "cek";
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_tv);


        circleImageView = findViewById(R.id.gambarMovie);
        title = findViewById(R.id.tvTitle);
        deskripsi = findViewById(R.id.tvOverview);
        tvRelease = findViewById(R.id.tvRelease);
        rating = findViewById(R.id.tvShowRating);
        voteCount = findViewById(R.id.TVvoteCount);
        favoriteButton = findViewById(R.id.favorite_tv_button);

        progressBar = findViewById(R.id.Loading);
        progressBar.setVisibility(View.VISIBLE);


        Intent intent = getIntent();
        data = intent.getParcelableExtra(EXTRA_TV);
//        Log.d("cek",data.getOriginalTitle());
        Log.d("cek",data.toString());

        Intent intentThatStartedThisActivity = getIntent();
        if (intentThatStartedThisActivity.hasExtra("movies")) {
            Intent intent1;
        }
        else {
            progressBar.setVisibility(View.GONE);
        }

        Glide.with(this).load(posterPath+data.getPosterPath())
                .into(circleImageView);
        title.setText(data.getName());
        rating.setText(String.valueOf(data.getVoteAverage()));
        deskripsi.setText(data.getOverview());
        tvRelease.setText(data.getReleaseDate());
        voteCount.setText(String.valueOf(data.getVoteCount()));

        Objects.requireNonNull(getSupportActionBar()).setDisplayHomeAsUpEnabled(true);

        db = FavoriteMovieRepository.getInstance(this);

        favoriteButton.setFavorite(db.isFavorite(data.getId()));

        favoriteButton.setOnFavoriteChangeListener(new MaterialFavoriteButton.OnFavoriteChangeListener() {
            @Override
            public void onFavoriteChanged(MaterialFavoriteButton buttonView, boolean favorite) {
                Movie tv = new Movie(
                        data.getId(),
                        data.getVoteAverage(),
                        data.getName(),
                        data.getPosterPath(),
                        "",
                        data.getBackdropPath(),
                        data.getOverview(),
                        data.getReleaseDate()
                );
                tv.setTvShow(true);
                if (favorite) {
                    db.createFavoriteMovie(tv);
                } else {
                    db.deleteFavoriteMovie(tv);
                }
            }
        });

    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()){
            case android.R.id.home:
                onBackPressed();
                return true;
        }
        return super.onOptionsItemSelected(item);
    }
}
