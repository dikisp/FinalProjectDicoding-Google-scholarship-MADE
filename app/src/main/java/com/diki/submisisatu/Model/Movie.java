package com.diki.submisisatu.Model;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.SerializedName;


public class Movie implements Parcelable {

    @SerializedName("id")
    private final int id;
    @SerializedName("vote_average")
    private final double voteAverage;
    @SerializedName("vote_count")
    private double voteCount;
    @SerializedName("title")
    private final String title;
    @SerializedName("poster_path")
    private final String posterPath;
    @SerializedName("original_language")
    private final String originalLanguage;
    @SerializedName("backdrop_path")
    private final String backdropPath;
    @SerializedName("overview")
    private final String overview;
    @SerializedName("release_date")
    private final String releaseDate;
    @SerializedName("runtime")
    private int runtime;
    @SerializedName("rating")
    private int rating;

    private boolean isTvShow;

    public Movie(int id, double voteAverage, String title, String posterPath, String originalLanguage, String backdropPath, String overview, String releaseDate) {
        this.id = id;
        this.voteAverage = voteAverage;
        this.title = title;
        this.posterPath = posterPath;
        this.originalLanguage = originalLanguage;
        this.backdropPath = backdropPath;
        this.overview = overview;
        this.releaseDate = releaseDate;
    }


    public int getId() {
        return id;
    }

    public double getVoteAverage() {
        return voteAverage;
    }

    public String getTitle() {
        return title;
    }

    public String getPosterPath() {
        return posterPath;
    }

    public String getOriginalLanguage() {
        return originalLanguage;
    }

    public String getBackdropPath() {
        return backdropPath;
    }

    public String getOverview() {
        return overview;
    }

    public String getReleaseDate() {
        return releaseDate;
    }

    public int getRuntime() {
        return runtime;
    }

    public double getVoteCount() {
        return voteCount;
    }

    public void setVoteCount(double voteCount) {
        this.voteCount = voteCount;
    }

    public void setRuntime(int runtime) {
        this.runtime = runtime;
    }

    public int getRating() {
        return rating;
    }

    public void setRating(int rating) {
        this.rating = rating;
    }

    @SuppressWarnings("SameReturnValue")
    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(this.id);
        dest.writeDouble(this.voteAverage);
        dest.writeString(this.title);
        dest.writeString(this.posterPath);
        dest.writeString(this.originalLanguage);
        dest.writeString(this.backdropPath);
        dest.writeString(this.overview);
        dest.writeString(this.releaseDate);
        dest.writeInt(this.runtime);
        dest.writeInt(this.rating);
    }

    private Movie(Parcel in) {
        this.id = in.readInt();
        this.voteAverage = in.readDouble();
        this.title = in.readString();
        this.posterPath = in.readString();
        this.originalLanguage = in.readString();
        this.backdropPath = in.readString();
        this.overview = in.readString();
        this.releaseDate = in.readString();
        this.runtime = in.readInt();
        this.rating = in.readInt();
    }

    public static final Parcelable.Creator<Movie> CREATOR = new Parcelable.Creator<Movie>() {
        @Override
        public Movie createFromParcel(Parcel source) {
            return new Movie(source);
        }

        @Override
        public Movie[] newArray(int size) {
            return new Movie[size];
        }
    };

    public boolean isTvShow() {
        return isTvShow;
    }

    public void setTvShow(boolean tvShow) {
        isTvShow = tvShow;
    }
}
