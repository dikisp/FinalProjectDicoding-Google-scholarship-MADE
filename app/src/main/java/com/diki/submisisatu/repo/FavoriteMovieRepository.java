package com.diki.submisisatu.repo;

import android.arch.persistence.room.Room;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteException;
import android.widget.CursorAdapter;

import com.diki.submisisatu.Database.DatabaseContract;
import com.diki.submisisatu.Database.DatabaseHelper;
import com.diki.submisisatu.Model.Movie;
import com.diki.submisisatu.repo.dao.FavoriteMovieDB;
import com.diki.submisisatu.repo.dao.FavoriteMovieDao;

import java.util.ArrayList;
import java.util.List;

public class FavoriteMovieRepository {
    private static FavoriteMovieRepository INSTANCE = null;
    private String DB_NAME = "db_profileApp";
    private MovieDatabase database;
    private FavoriteMovieDao dao;

    private FavoriteMovieRepository(Context context) {
        database = Room.databaseBuilder(context, MovieDatabase.class, DB_NAME)
                .allowMainThreadQueries()
                .build();
        dao = database.favoriteMovieDao();
    }

    public static FavoriteMovieRepository getInstance(Context context) {
        if (INSTANCE == null) {
            INSTANCE = new FavoriteMovieRepository(context);
        }

        return INSTANCE;
    }

    public List<FavoriteMovieDB> getFavoriteMovies() {
        return dao.getFavoriteMovies();
    }

    public FavoriteMovieDB getFavoriteMovie(int movieId) {
        return dao.getFavoriteMovie(movieId);
    }

    public boolean isFavorite(int movieId) {
        return dao.getFavoriteMovie(movieId) != null;
    }

    public boolean createFavoriteMovie(Movie movie) {
        FavoriteMovieDB movieDB = new FavoriteMovieDB(
                movie.getId(),
                movie.getTitle(),
                movie.getVoteAverage(),
                movie.getReleaseDate(),
                movie.getOverview(),
                movie.getPosterPath(),
                movie.isTvShow()
        );
        return dao.createFavoriteMovie(movieDB) > 0;
    }


    public boolean deleteFavoriteMovie(Movie movie) {
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
            dao.delete(movieDB);
        } catch (SQLiteException e) {
            e.printStackTrace();
            status = false;
        }

        return status;
    }


    public void open() {
//        database = databaseHelper.getWritableDatabase();
    }

    public void close() {
        if (database.isOpen()) database.close();
    }

    public List<FavoriteMovieDB> findAll() {
        return dao.getFavoriteMovies();
    }

    public Cursor queryProvider() {
        return database.query("SELECT * FROM favoritemovies", null);
    }
}
