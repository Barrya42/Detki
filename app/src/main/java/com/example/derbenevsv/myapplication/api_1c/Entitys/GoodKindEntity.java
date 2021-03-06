package com.example.derbenevsv.myapplication.api_1c.Entitys;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class GoodKindEntity
{
    @SerializedName("goodKindGuid")
    @Expose
    private String guid;

    @SerializedName("goodKindName")
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
