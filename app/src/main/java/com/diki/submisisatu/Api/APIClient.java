package com.diki.submisisatu.Api;

import com.diki.submisisatu.BuildConfig;

import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

public class APIClient {
    private  static  final  String WEBAPI = BuildConfig.WEBAPI;
    private static Retrofit retrofit = null;
    private MovieApi api;
    private static APIClient instance = null;

    public static APIClient getInstance() {
        if (instance == null) {
            instance = new APIClient();
        }
        return instance;
    }

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

    public MovieApi getApi() {
        return api;
    }

}
