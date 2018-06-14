package com.example.derbenevsv.myapplication.api_1c.Responses;

import com.example.derbenevsv.myapplication.api_1c.Entitys.OrderEntity;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class GetOrdersResponse
{

    @SerializedName("orders")
    @Expose
    private List<OrderEntity> orders = null;

    public List<OrderEntity> getOrders()
    {
        return orders;
    }

   // public void setOrders(List<OrderEntity> orders)
    //{
    //    this.orders = orders;
    //}

}
