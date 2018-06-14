package com.example.derbenevsv.myapplication.api_1c.Entitys;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.Date;
import java.util.List;

public class OrderEntity
{
    @SerializedName("orderGuid")
    @Expose
    private String orderGuid;
    @SerializedName("orderNumber")
    @Expose
    private String orderNumber;
    @SerializedName("orderDate")
    @Expose
    private Date orderDate;
    @SerializedName("orderSumm")
    @Expose
    private double orderSumm;
    @SerializedName("orderRows")
    @Expose
    private List<OrderRow> rows;

    public List<OrderRow> getRows()
    {
        return rows;
    }

    public String getOrderNumber()
    {
        return orderNumber;
    }

    public Date getOrderDate()
    {
        return orderDate;
    }

    public double getOrderSumm()
    {
        return orderSumm;
    }

    class OrderRow
    {
        @SerializedName("good")
        @Expose
        private GoodEntity goodEntity;
        @SerializedName("size")
        @Expose
        private SizeEntity sizeEntity;
        @SerializedName("count")
        @Expose
        private int count;
        @SerializedName("price")
        @Expose
        private double price;
        @SerializedName("summ")
        @Expose
        private double summ;

        public GoodEntity getGoodEntity()
        {
            return goodEntity;
        }

        public SizeEntity getSizeEntity()
        {
            return sizeEntity;
        }

        public int getCount()
        {
            return count;
        }

        public double getPrice()
        {
            return price;
        }

        public double getSumm()
        {
            return summ;
        }

    }
}
