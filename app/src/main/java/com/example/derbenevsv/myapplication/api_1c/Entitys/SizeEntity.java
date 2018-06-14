package com.example.derbenevsv.myapplication.api_1c.Entitys;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class SizeEntity
{
    @SerializedName("sizeGuid")
    @Expose
    private String guid;

    @SerializedName("sizeName")
    @Expose
    private String name;

    public String getGuid()
    {
        return guid;
    }

    public String getName()
    {
        return name;
    }
}