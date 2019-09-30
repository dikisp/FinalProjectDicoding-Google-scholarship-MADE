package com.diki.submisisatu.Model;

import android.os.Parcel;
import android.os.Parcelable;

public class FavoriteMovie implements Parcelable {

    public static final Creator<FavoriteMovie> CREATOR = new Creator<FavoriteMovie>() {
        @Override
        public FavoriteMovie createFromParcel(Parcel in) {
            return new FavoriteMovie(in);
        }

        @Override
        public FavoriteMovie[] newArray(int size) {
            return new FavoriteMovie[size];
        }
    };

    private int id;
    private int mId;
    private double voteAverage;
    private int voteCount;
    private String originalTitle;
    private String name;
    private int popularity;
    private String backdropPath;
    private String overview;
    private String releaseDate;
    private String posterPath;
    private String runtime;
    private String rating;
    private String category;


    public FavoriteMovie(int id, int mId, String title, String poster, int rating, String backdrop, String releaseDate, String overview, String orginLang) {
    }

    public static Creator<FavoriteMovie> getCREATOR() {
        return CREATOR;
    }

    public int getId() {
        return id;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getmId() {
        return mId;
    }

    public void setmId(int mId) {
        this.mId = mId;
    }

    public double getVoteAverage() {
        return voteAverage;
    }

    public void setVoteAverage(double voteAverage) {
        this.voteAverage = voteAverage;
    }

    public int getVoteCount() {
        return voteCount;
    }

    public void setVoteCount(int voteCount) {
        this.voteCount = voteCount;
    }

    public String getOriginalTitle() {
        return originalTitle;
    }

    public void setOriginalTitle(String originalTitle) {
        this.originalTitle = originalTitle;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getPopularity() {
        return popularity;
    }

    public void setPopularity(int popularity) {
        this.popularity = popularity;
    }

    public String getBackdropPath() {
        return backdropPath;
    }

    public void setBackdropPath(String backdropPath) {
        this.backdropPath = backdropPath;
    }

    public String getOverview() {
        return overview;
    }

    public void setOverview(String overview) {
        this.overview = overview;
    }

    public String getReleaseDate() {
        return releaseDate;
    }

    public void setReleaseDate(String releaseDate) {
        this.releaseDate = releaseDate;
    }

    public String getPosterPath() {
        return posterPath;
    }

    public void setPosterPath(String posterPath) {
        this.posterPath = posterPath;
    }

    public String getRuntime() {
        return runtime;
    }

    public void setRuntime(String runtime) {
        this.runtime = runtime;
    }

    public String getRating() {
        return rating;
    }

    public void setRating(String rating) {
        this.rating = rating;
    }

    public FavoriteMovie(int id,
                         int mId,
                         String originalTitle,
                         String name,
                         String backdropPath,
                         String overview,
                         String releaseDate,
                         String posterPath,
                         String runtime) {
        this.id = id;
        this.mId = mId;
        this.originalTitle = originalTitle;
        this.posterPath = posterPath;
        this.backdropPath = backdropPath;
        this.rating = rating;
        this.releaseDate = releaseDate;
        this.overview = overview;
        this.voteCount = voteCount;
    }

    private FavoriteMovie(Parcel in) {
        id = in.readInt();
        mId = in.readInt();
        originalTitle = in.readString();
        posterPath = in.readString();
        backdropPath = in.readString();
        rating = in.readString();
        releaseDate = in.readString();
        overview = in.readString();
        voteCount = in.readInt();
    }


    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeInt(id);
        parcel.writeInt(mId);
        parcel.writeString(originalTitle);
        parcel.writeString(posterPath);
        parcel.writeString(backdropPath);
        parcel.writeString(rating);
        parcel.writeString(releaseDate);
        parcel.writeString(overview);
        parcel.writeInt(voteCount);
    }
}
