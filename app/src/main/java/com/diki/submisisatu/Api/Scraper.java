package com.diki.submisisatu.Api;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class Scraper<T> {

    @SerializedName("results")
    private final List<T> results = null;

    public List<T> getResultMovies() {
        //noinspection ConstantConditions
        return results;
    }

    public List<T> getResultTv() {
        //noinspection ConstantConditions
        return results;
    }
}
