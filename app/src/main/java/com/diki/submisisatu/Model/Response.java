package com.diki.submisisatu.Model;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class Response<T> {
    @SerializedName("page")
    private Integer page;
    @SerializedName("results")
    private List<T> results = null;

//    @SerializedName("results")
//    private List<T> resultTV = null;

    public Integer getPage() {
        return page;
    }

    public void setPage(Integer page) {
        this.page = page;
    }

    public List<T> getResults() {
        return results;
    }

//    public List<T> getResultTV() {
//        return resultTV;
//    }
//
//    public void setResultTV(List<T> resultTV) {
//        this.resultTV = resultTV;
//    }

    public void setResults(List<T> results) {
        this.results = results;
    }
}
