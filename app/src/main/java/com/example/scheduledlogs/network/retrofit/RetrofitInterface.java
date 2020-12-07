package com.example.scheduledlogs.network.retrofit;

import com.example.scheduledlogs.network.responseModel.RandomNameResponse;

import retrofit2.Call;
import retrofit2.http.GET;

public interface RetrofitInterface {
    @GET(".")
    Call<RandomNameResponse> sendRequestRandomName();
}
