package com.example.derbenevsv.myapplication.api_1c.Entitys;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class OrderEntity extends AbstractLink
{
    @SerializedName("orderNumber")
    @Expose
    private String orderNumber;
    @SerializedName("orderDate")
    @Expose
    private String orderDate;
    @SerializedName("orderSumm")
    @Expose
    private int orderSumm;


    public String getOrderNumber()
    {
        return orderNumber;
    }

    public void setOrderNumber(String orderNumber)
    {
        this.orderNumber = orderNumber;
    }

    public String getOrderDate()
    {
        return orderDate;
    }

    public void setOrderDate(String orderDate)
    {
        this.orderDate = orderDate;
    }

    public int getOrderSumm()
    {
        return orderSumm;
    }

    public void setOrderSumm(int orderSumm)
    {
        this.orderSumm = orderSumm;
    }
}
