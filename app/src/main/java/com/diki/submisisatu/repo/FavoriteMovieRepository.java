package com.diki.submisisatu.repo;

import android.arch.persistence.room.Room;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteException;
import android.util.Log;

import com.diki.submisisatu.Database.DatabaseContract;
import com.diki.submisisatu.Database.DatabaseHelper;
import com.diki.submisisatu.Model.FavoriteMovie;
import com.diki.submisisatu.Model.Movie;
import com.diki.submisisatu.repo.dao.FavoriteMovieDB;

import java.util.ArrayList;
import java.util.List;

public class FavoriteMovieRepository {
    private String DB_NAME = "db_profileApp";
    private MovieDatabase database;
    DatabaseHelper databaseHelper;
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


    public void open() {
        database = databaseHelper.getWritableDatabase();
    }

    public void close() {
        databaseHelper.close();

        if (database.isOpen()) database.close();
    }

    public ArrayList<Movie> findAll() {
        ArrayList<Movie> arrayList = new ArrayList<>();
        Cursor cursor = database.query(DATABASE_TABLE
                , null
                , null
                , null
                , null
                , null
                , null
                , null, DatabaseContract.PokemonCardsColumns._ID + " DESC"
                , null);
        cursor.moveToFirst();
        Movie pokemonCard;
        if (cursor.getCount() > 0) {
            do {
                String name = cursor.getString(cursor.getColumnIndexOrThrow(DatabaseContract.PokemonCardsColumns.NAME));
                String image = cursor.getString(cursor.getColumnIndexOrThrow(DatabaseContract.PokemonCardsColumns.IMAGE));
                String rarity = cursor.getString(cursor.getColumnIndexOrThrow(DatabaseContract.PokemonCardsColumns.RARITY));
                String series = cursor.getString(cursor.getColumnIndexOrThrow(DatabaseContract.PokemonCardsColumns.SERIES));

                pokemonCard = new Movie(name, image, rarity, series);
                arrayList.add(pokemonCard);
                cursor.moveToNext();
            } while (!cursor.isAfterLast());
        }
        cursor.close();
        return arrayList;
    }



}
