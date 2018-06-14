package com.example.derbenevsv.myapplication.api_1c;

import com.example.derbenevsv.myapplication.api_1c.Responses.GetOrdersResponse;
import com.example.derbenevsv.myapplication.api_1c.Responses.LoginResponse;
import com.example.derbenevsv.myapplication.api_1c.Responses.LogoutResponse;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.Headers;
import retrofit2.http.POST;
import retrofit2.http.Query;

public interface ApiMethods
{
    @GET("orders/GetOrders")
    Call<GetOrdersResponse> GetOrders(@Header("Authorization") String auth, @Query("sessionGuid") String sessionGuid);

    @GET("users/CheckSession")
    Call<String> CheckSession(@Header("Authorization") String auth, @Query("sessionGuid") String sessionGuid);

    @POST("users/Login")
    @Headers("Content-Type: application/json")
    Call<LoginResponse> Login(@Header("Authorization") String auth, @Query("phone") String phone, @Query("pass") String pass);

    @POST("users/Logout")
    Call<LogoutResponse> Logout(@Header("Authorization") String auth, @Query("sessionGuid") String sessionGuid);
}
