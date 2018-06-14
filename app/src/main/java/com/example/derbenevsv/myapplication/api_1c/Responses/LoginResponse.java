package com.example.derbenevsv.myapplication.api_1c.Responses;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class LoginResponse
{
    @SerializedName("sessionGuid")
    @Expose
    private String sessionGuid;

    public String GetSessionGuid()
    {
        return sessionGuid;
    }

    public void SetSessionGuid(String value)
    {
        sessionGuid = value;
    }
}
