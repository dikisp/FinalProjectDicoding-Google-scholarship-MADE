package com.diki.submisisatu.repo.dao;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Delete;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.OnConflictStrategy;
import android.arch.persistence.room.Query;
import android.arch.persistence.room.Update;

import java.util.List;

@Dao
public interface FavoriteMovieDao {
    @Query("SELECT * FROM favoritemovies WHERE id = :movieId")
    FavoriteMovieDB getFavoriteMovie(int movieId);

    @Query("SELECT * FROM favoritemovies")
    List<FavoriteMovieDB> getFavoriteMovies();

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    long createFavoriteMovie(FavoriteMovieDB movie);

    @Delete
    void delete(FavoriteMovieDB movie);

    @Update
    void update(FavoriteMovieDB... movies);

}
