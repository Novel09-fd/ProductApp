package com.example.productapp;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.Toast;

import com.example.productapp.adapter.ListProductAdapter;
import com.example.productapp.entity.Product;
import com.example.productapp.service.ApiClient;
import com.example.productapp.service.ProductInterface;

import org.json.JSONObject;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {
    private ArrayList<Product> productList;
    private ListProductAdapter productAdapter;
    private RecyclerView rcProduct;
    Button buttonTambah;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        rcProduct = findViewById(R.id.lst_Product);
        buttonTambah = findViewById(R.id.buttonTambah);

        buttonTambah.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this,AddProduct.class);
                startActivity(intent);
            }
        });

        ProductInterface productInterface = ApiClient.getClient().create(ProductInterface.class);
        Call<Product> call = productInterface.getAllProduct();
        call.enqueue(new Callback<Product>() {
            @RequiresApi(api = Build.VERSION_CODES.N)
            @Override
            public void onResponse(Call<Product> call, Response<Product> response) {
                productList = (ArrayList<Product>)response.body().getData();
                productAdapter = new ListProductAdapter(MainActivity.this, productList);

                RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(MainActivity.this, LinearLayoutManager.VERTICAL, false);
                rcProduct.setLayoutManager(layoutManager);
                rcProduct.setAdapter(productAdapter);
            }

            @Override
            public void onFailure(Call<Product> call, Throwable t) {

            }
        });

    }
}