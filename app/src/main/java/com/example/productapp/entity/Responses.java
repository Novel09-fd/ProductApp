package com.example.productapp.entity;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class Responses {

    @SerializedName("statusCode")
    private int statusCode;
    @SerializedName("pesan")
    private String pesan;
    @SerializedName("data")
    private List<Product> data;
    public int getStatusCode() {
        return statusCode;
    }

    public List<Product> getData() {
        return data;
    }

    public void setData(List<Product> data) {
        this.data = data;
    }
}
