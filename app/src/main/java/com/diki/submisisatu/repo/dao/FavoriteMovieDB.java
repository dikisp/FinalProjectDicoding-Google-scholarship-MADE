package com.diki.submisisatu.repo.dao;

import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;

@Entity(tableName = "favoriteMovies")
public class FavoriteMovieDB {
    @PrimaryKey

    @ColumnInfo(name = "id")
    private int id;

    @ColumnInfo(name = "name")
    private String name;

    @ColumnInfo(name = "rating")
    private Double rating;

    @ColumnInfo(name = "date")
    private String date;

    @ColumnInfo(name = "description")
    private String description;

    @ColumnInfo(name = "posterPath")
    private String posterPath;

    @ColumnInfo(name = "isTvShow")
    private boolean isTvShow;

    public FavoriteMovieDB(int id, String name, Double rating, String date, String description, String posterPath, boolean isTvShow) {
        this.id = id;
        this.name = name;
        this.rating = rating;
        this.date = date;
        this.description = description;
        this.posterPath = posterPath;
        this.isTvShow = isTvShow;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Double getRating() {
        return rating;
    }

    public void setRating(Double rating) {
        this.rating = rating;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getPosterPath() {
        return posterPath;
    }

    public void setPosterPath(String posterPath) {
        this.posterPath = posterPath;
    }

    public boolean isTvShow() {
        return isTvShow;
    }

    public void setTvShow(boolean tvShow) {
        isTvShow = tvShow;
    }
}
