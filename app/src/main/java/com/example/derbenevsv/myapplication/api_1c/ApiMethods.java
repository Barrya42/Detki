package com.example.derbenevsv.myapplication.api_1c;

import com.example.derbenevsv.myapplication.api_1c.Responses.LoginResponse;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.POST;
import retrofit2.http.Query;

public interface ApiMethods
{
    @GET("users/CheckSession")
    Call<String> CheckSession(@Header("Authorization") String auth, @Query("sessionGuid") String sessionGuid);

    @POST("users/Login")
    Call<LoginResponse> Login(@Header("Authorization") String auth, @Query("phone") String phone, @Query("pass") String pass);
}
