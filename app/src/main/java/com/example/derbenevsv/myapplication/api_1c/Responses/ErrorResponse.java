package com.example.derbenevsv.myapplication.api_1c.Responses;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class ErrorResponse
{
    @SerializedName("errorText")
    @Expose
    private String errorText;

    public String getErrorText()
    {
        return errorText;
    }

    public void setErrorText(String errorText)
    {
        this.errorText = errorText;
    }

}
