package com.diki.submisisatu.repo;

import android.arch.persistence.room.Database;
import android.arch.persistence.room.RoomDatabase;

import com.diki.submisisatu.repo.dao.FavoriteMovieDB;
import com.diki.submisisatu.repo.dao.FavoriteMovieDao;

@Database(entities = {FavoriteMovieDB.class}, version = 1)
public abstract class MovieDatabase extends RoomDatabase {
    public abstract FavoriteMovieDao favoriteMovieDao();
}