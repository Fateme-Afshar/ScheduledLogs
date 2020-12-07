package com.example.scheduledlogs.network.retrofit;

import com.example.scheduledlogs.network.NetworkParams;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class RetrofitInstance {

    public static Retrofit getRetrofit(){
        return new Retrofit.Builder().
                baseUrl(NetworkParams.BASE_URL).
                addConverterFactory(GsonConverterFactory.create()).
                build();
    }
}
