package com.diki.submisisatu.repo;

import android.arch.persistence.room.Room;
import android.content.Context;
import android.database.sqlite.SQLiteException;
import android.util.Log;

import com.diki.submisisatu.Model.Movie;
import com.diki.submisisatu.repo.dao.FavoriteMovieDB;

import java.util.List;

public class FavoriteMovieRepository {
    private String DB_NAME = "db_profileApp";
    private MovieDatabase database;
    private Context context;

    private FavoriteMovieRepository(Context context) {
        this.context = context;
        database = Room.databaseBuilder(context, MovieDatabase.class, DB_NAME)
                .allowMainThreadQueries()
                .build();
    }

    private static FavoriteMovieRepository INSTANCE = null;

    public static FavoriteMovieRepository getInstance(Context context) {
        if (INSTANCE == null) {
            INSTANCE = new FavoriteMovieRepository(context);
        }

        return INSTANCE;
    }

    public List<FavoriteMovieDB> getFavoriteMovies(){
        return database.favoriteMovieDao().getFavoriteMovies();
    }

    public FavoriteMovieDB getFavoriteMovie(int movieId){
        return database.favoriteMovieDao().getFavoriteMovie(movieId);
    }

    public boolean isFavorite(int movieId){

        return database.favoriteMovieDao().getFavoriteMovie(movieId) != null;
    }

    public boolean createFavoriteMovie(Movie movie){

        FavoriteMovieDB movieDB = new FavoriteMovieDB(
                movie.getId(),
                movie.getTitle(),
                movie.getVoteAverage(),
                movie.getReleaseDate(),
                movie.getOverview(),
                movie.getPosterPath(),
                movie.isTvShow()
        );
        return database.favoriteMovieDao().createFavoriteMovie(movieDB) > 0;
    }


    public boolean deleteFavoriteMovie(Movie movie){
        FavoriteMovieDB movieDB = new FavoriteMovieDB(
                movie.getId(),
                movie.getTitle(),
                movie.getVoteAverage(),
                movie.getReleaseDate(),
                movie.getOverview(),
                movie.getPosterPath(),
                movie.isTvShow()
        );
        boolean status = true;
        try {
            database.favoriteMovieDao().delete(movieDB);
        } catch (SQLiteException e){
            e.printStackTrace();
            status = false;
        }

        return status;
    }



}
