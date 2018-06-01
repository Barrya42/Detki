package com.example.derbenevsv.myapplication.api_1c.Entitys;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public abstract class AbstractLink
{
    @SerializedName("Guid")
    @Expose
    protected String guid;

    public String getGuid()
    {
        return guid;
    }
}
