package com.diki.submisisatu.Database;


import android.arch.lifecycle.LiveData;
import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Delete;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.Query;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.OnConflictStrategy;
import android.arch.persistence.room.Update;

import com.diki.submisisatu.Model.FavoriteMovie;

import java.util.List;


@Dao
@Entity
public interface DAO {
    //  Movie

//    @Query("SELECT * FROM FavoriteMovie")
//    LiveData<List<FavoriteMovie>> loadAllFavorite();
//
//    @Query("SELECT * FROM FavoriteMovie WHERE move_id = :moveid")
//    List<FavoriteMovie> loadAll(String moveid);
//
//    @Insert
//    long insertFavorite(FavoriteMovie favoriteMovie);
//
//    @Update(onConflict = OnConflictStrategy.REPLACE)
//    void updateFavorite(FavoriteMovie favoriteMovie);
//
//    @Delete
//    void deleteFavorite(FavoriteMovie favoriteMovie);

//    @Query("DELETE FROM tMovie WHERE movieid = :movie_id")
//    void deleteFavoriteWithId(int movie_id);

//    @Query("SELECT * FROM tMovie WHERE id = :id")
//    LiveData<FavoriteMovie> loadFavoriteById(int id);
}
