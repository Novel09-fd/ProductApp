package com.example.productapp.service;

import com.example.productapp.entity.Product;
import com.google.android.material.bottomsheet.BottomSheetBehavior;

import retrofit2.Call;
import retrofit2.Response;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;

public interface ProductInterface {

    @GET("/product/")
    Call<Product> getAllProduct();

    @POST("/product/add")
    Call<Product> save(@Body Product product);
}
