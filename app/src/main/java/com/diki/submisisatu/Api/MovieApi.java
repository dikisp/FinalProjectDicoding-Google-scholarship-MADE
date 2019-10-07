package com.diki.submisisatu.Api;

import com.diki.submisisatu.Model.Movie;
import com.diki.submisisatu.Model.MovieResponse;
import com.diki.submisisatu.Model.Response;
import com.diki.submisisatu.Model.TV;

import io.reactivex.Single;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface MovieApi {

    @GET("movie/now_playing")
    Single<Scraper<Movie>> findNowPlayingMovies(@Query("api_key") String apiKey);

    @GET("tv/on_the_air")
    Single<Scraper<TV>> findOnTheAirTv(@Query("api_key") String apiKey);

    @GET("search/movie")
    Single<Response<Movie>> searchMovie(
            @Query("api_key") String api_key,
            @Query("query") String query
    );

    @GET("search/tv")
    Single<Response<TV>> searchTvShow(
            @Query("api_key") String api_key,
            @Query("query") String query
    );

    @GET("search/movie")
    Call<MovieResponse> getSearchMovies(@Query("api_key") String apiKey, @Query("language") String language, @Query("query") String querySearch);

    @GET("movie/upcoming")
    Single<MovieResponse> findUpcomingMovie(@Query("api_key") String apiKey);
}
