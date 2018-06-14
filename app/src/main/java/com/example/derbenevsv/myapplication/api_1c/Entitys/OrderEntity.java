package com.example.derbenevsv.myapplication.api_1c.Entitys;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.Date;

public class OrderEntity extends AbstractLink
{
    @SerializedName("orderNumber")
    @Expose
    private String orderNumber;
    @SerializedName("orderDate")
    @Expose
    private Date orderDate;
    @SerializedName("orderSumm")
    @Expose
    private double orderSumm;


    public String getOrderNumber()
    {
        return orderNumber;
    }

    public void setOrderNumber(String orderNumber)
    {
        this.orderNumber = orderNumber;
    }

    public Date getOrderDate()
    {
        return orderDate;
    }

    public void setOrderDate(Date orderDate)
    {
        this.orderDate = orderDate;
    }

    public double getOrderSumm()
    {
        return orderSumm;
    }

    public void setOrderSumm(int orderSumm)
    {
        this.orderSumm = orderSumm;
    }
}
