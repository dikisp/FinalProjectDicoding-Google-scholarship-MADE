package com.diki.submisisatu.Api;

import com.bumptech.glide.BuildConfig;
import com.diki.submisisatu.Model.Movie;
import com.diki.submisisatu.Model.MovieResponse;
import com.diki.submisisatu.Model.TV;

import io.reactivex.Single;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface MovieApi {

    @GET("movie/now_playing")
    Single<Scraper<Movie>> findNowPlayingMovies(@Query("api_key") String apiKey);

    @GET("tv/on_the_air")
    Single<Scraper<TV>> findOnTheAirTv(@Query("api_key") String apiKey);
}
