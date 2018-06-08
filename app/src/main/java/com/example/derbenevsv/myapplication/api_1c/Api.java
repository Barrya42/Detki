package com.example.derbenevsv.myapplication.api_1c;

import android.support.annotation.NonNull;
import android.util.Base64;

import com.example.derbenevsv.myapplication.PreferenceHelper;
import com.example.derbenevsv.myapplication.api_1c.Responses.GetOrdersResponse;
import com.example.derbenevsv.myapplication.api_1c.Responses.LoginResponse;

import java.net.InetSocketAddress;
import java.net.Proxy;
import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

import okhttp3.OkHttpClient;
import retrofit2.Callback;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;


public class Api
{
    private final String baseUser = "Admin";
    private final String basePass = "";
    private final String baseURL = "http://167.16.1.88";//
    private final String publicBaseName = "UT";
    private final String httpServiceName = "TestService";
    private final Retrofit retrofit;
    private String sessionGuid = "";
    private ApiMethods instance;
    private String authEncoded = "";

    public Api()
    {
        java.net.Proxy proxy = new Proxy(Proxy.Type.HTTP,  new InetSocketAddress("167.16.1.250", 8080));
        OkHttpClient client = new OkHttpClient.Builder().proxy(proxy).build();
        retrofit = new Retrofit.Builder().client(client)
                .baseUrl(baseURL + "/" + publicBaseName + "/hs/" + httpServiceName + "/") //Базовая часть адреса
                .addConverterFactory(GsonConverterFactory.create()) //Конвертер, необходимый для преобразования JSON'а в объекты
                .build();
        instance = retrofit.create(ApiMethods.class); //Создаем объект, при помощи которого будем выполнять запросы
        authEncoded = "Basic " + Base64.encodeToString((baseUser + ":" + basePass).getBytes(), Base64.NO_WRAP);
        sessionGuid = PreferenceHelper.GetSessionGuid();

    }

//    public static void Initialize()
//    {
//    }

    public void GetOrders(@NonNull Callback<GetOrdersResponse> callback)
    {
        instance.GetOrders(authEncoded, sessionGuid)
                .enqueue(callback);
    }


    public void CheckSession(@NonNull Callback<String> callback)
    {
        instance.CheckSession(authEncoded, sessionGuid)
                .enqueue(callback);
    }

    public void Login(String phone, String pass, @NonNull Callback<LoginResponse> callback)
    {
        // TODO: 24.05.2018 Нужен другой алгоритм SHA256
        MessageDigest digest = null;
        try
        {
            digest = MessageDigest.getInstance("SHA-256");
        }
        catch (NoSuchAlgorithmException e)
        {
            e.printStackTrace();
        }
        byte[] byteOfTextToHash = pass.getBytes(StandardCharsets.UTF_8);
        byte[] hashedByetArray = digest.digest(byteOfTextToHash);
        String encoded = Base64.encodeToString(hashedByetArray, Base64.NO_WRAP);


        instance.Login(authEncoded, phone, encoded)
                .enqueue(callback);

    }

    public void SetSessionGuid(String newGuid)
    {
        sessionGuid = newGuid;
        PreferenceHelper.SetSessionGuid(sessionGuid);
    }
}
