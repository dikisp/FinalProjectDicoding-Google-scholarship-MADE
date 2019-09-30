package com.diki.submisisatu;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.diki.submisisatu.Adapter.ListMovieAdapter;
import com.diki.submisisatu.Model.FavoriteMovie;
import com.diki.submisisatu.Model.Movie;
import com.diki.submisisatu.repo.FavoriteMovieRepository;
import com.github.ivbaranov.mfb.MaterialFavoriteButton;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

//import com.diki.submisisatu.Database.AppDatabase;


public class DetailMovieActivity extends AppCompatActivity {
    private TextView title, tvRelease, deskripsi, rating, voteCount;
    private static final String posterPath = BuildConfig.POSTER_PATH;
    private ImageView circleImageView;
    public static final String EXTRA_MOVIE = "extra_movie";
    private String mPosition;
    private MaterialFavoriteButton favoriteButton;

    Movie movie;

    String posterMovie, movieName, overview, ratingMovie, dateOfRelease;
    int movie_id;
    List<FavoriteMovie> entries = new ArrayList<>();

    private FavoriteMovieRepository db;

    private ProgressBar progressBar;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_movie);


        circleImageView = findViewById(R.id.poster);
        title = findViewById(R.id.tvName);
        deskripsi = findViewById(R.id.tvOverview);
        tvRelease = findViewById(R.id.tvRelease);
        rating = findViewById(R.id.tvRating);
        voteCount = findViewById(R.id.tvVote);
        favoriteButton = findViewById(R.id.favorite_button);

        progressBar = findViewById(R.id.Loading);
        progressBar.setVisibility(View.VISIBLE);

        db = FavoriteMovieRepository.getInstance(this);

        Intent intent = getIntent();
        final Movie data = intent.getParcelableExtra(EXTRA_MOVIE);
        Log.d("cek", data.toString());


        Intent intentThatStartedThisActivity = getIntent();
        if (intentThatStartedThisActivity.hasExtra("movies")) {

            movie = getIntent().getParcelableExtra("movies");

            posterMovie = movie.getPosterPath();
            movieName = movie.getTitle();
            overview = movie.getOverview();
            ratingMovie = Double.toString(movie.getVoteAverage());
            dateOfRelease = movie.getReleaseDate();
            movie_id = movie.getId();

            String poster = "https://image.tmdb.org/t/p/w500" + posterMovie;

            Glide.with(this)
                    .load(poster + data.getPosterPath())
                    .into(circleImageView);

            title.setText(movieName);
            deskripsi.setText(overview);
            rating.setText(ratingMovie);
            tvRelease.setText(dateOfRelease);

            //TODO

        }
        else {
            progressBar.setVisibility(View.GONE);
        }


        if (data != null) {
//            setView(true);
            Glide.with(this).load(posterPath + data.getPosterPath())
                    .into(circleImageView);
            title.setText(data.getTitle());
            rating.setText(String.valueOf(data.getVoteAverage()));
            deskripsi.setText(data.getOverview());
            tvRelease.setText(data.getReleaseDate());
            voteCount.setText(String.valueOf(data.getVoteCount()));

            Objects.requireNonNull(getSupportActionBar()).setDisplayHomeAsUpEnabled(true);

            favoriteButton.setFavorite(db.isFavorite(data.getId()));

            favoriteButton.setOnFavoriteChangeListener(new MaterialFavoriteButton.OnFavoriteChangeListener() {
                @Override
                public void onFavoriteChanged(MaterialFavoriteButton buttonView, boolean favorite) {
                    if (favorite) {
                        db.createFavoriteMovie(data);
                    } else {
                        db.deleteFavoriteMovie(data);
                    }
                }
            });
        }

    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                onBackPressed();
                return true;
        }
        return super.onOptionsItemSelected(item);
    }

//    private void setView(Boolean status) {
//        if (status) {
//            loading.setVisibility(View.GONE);
//            circleImageView.setVisibility(View.VISIBLE);
//            title.setVisibility(View.VISIBLE);
//            rating.setVisibility(View.VISIBLE);
//            deskripsi.setVisibility(View.VISIBLE);
//        } else {
//            loading.setVisibility(View.VISIBLE);
//            circleImageView.setVisibility(View.GONE);
//            title.setVisibility(View.GONE);
//            rating.setVisibility(View.GONE);
//            deskripsi.setVisibility(View.GONE);
//        }
//    }

    //rotate
    protected void onSaveInstanceState(final Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putString(EXTRA_MOVIE, mPosition);
    }

    protected void onRestoreInstanceState(final Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);
        mPosition = savedInstanceState.getString(EXTRA_MOVIE);
    }

    public void setRuntime(int anInt) {
    }
}