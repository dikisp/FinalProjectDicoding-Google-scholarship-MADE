package com.diki.submisisatu.Api;

import com.diki.submisisatu.BuildConfig;

import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

public class RetrofitApi {
    private  static  final  String WEBAPI = BuildConfig.WEBAPI;
    private static Retrofit retrofit = null;
    private  RetrofitApi api;
    private static RetrofitApi instance = null;


    public  static Retrofit getClient(){
        if (retrofit == null){
            retrofit = new Retrofit.Builder()
                    .addConverterFactory(GsonConverterFactory.create())
                    .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                    .baseUrl(WEBAPI)
                    .build();
        }
        return retrofit;
    }

}
