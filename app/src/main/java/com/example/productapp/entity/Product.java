package com.example.productapp.entity;


import com.google.gson.annotations.SerializedName;

import java.util.List;

public class Product {

    @SerializedName("id")
    private long id;
    @SerializedName("nama")
    private String nama;
    @SerializedName("price")
    private String price;
    @SerializedName("description")
    private String description;
    @SerializedName("variant")
    private String variant;
    @SerializedName("gambar")
    private String gambar;

    @SerializedName("data")
    private List<Product> data;

    public String getLinkProduk() {
        return linkProduk;
    }

    public void setLinkProduk(String linkProduk) {
        this.linkProduk = linkProduk;
    }

    @SerializedName("linkproduk")
    private String linkProduk;

//    public Product(long id, String nama, int price, String description, String variant, String gambar) {
//        this.id = id;
//        this.nama = nama;
//        this.price = price;
//        this.description = description;
//        this.variant = variant;
//        this.gambar = gambar;
//    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getNama() {
        return nama;
    }

    public void setNama(String nama) {
        this.nama = nama;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getVariant() {
        return variant;
    }

    public void setVariant(String variant) {
        this.variant = variant;
    }

    public String getGambar() {
        return gambar;
    }

    public void setGambar(String gambar) {
        this.gambar = gambar;
    }

    public List<Product> getData() {
        return data;
    }

    public void setData(List<Product> data) {
        this.data = data;
    }


}
