package com.example.productapp;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.example.productapp.entity.Product;
import com.example.productapp.service.ApiClient;
import com.example.productapp.service.ProductInterface;
import com.google.gson.annotations.SerializedName;

import java.io.ByteArrayOutputStream;
import java.util.Arrays;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class AddProduct extends AppCompatActivity {

    ImageButton imgProduct;
    EditText editTextName , editTextPrice , editTextDescription , editTextVariant ;
    Button buttonSave;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.add_product);

        editTextName = findViewById(R.id.editTextName);
        editTextPrice = findViewById(R.id.editTextPrice);
        editTextDescription = findViewById(R.id.editTextDescription);
        editTextVariant = findViewById(R.id.editTextVariant);
        imgProduct = findViewById(R.id.imgProduct);
        imgProduct.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openFolder();
            }
        });
        buttonSave = findViewById(R.id.buttonSave);
        buttonSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Save_onClick(v);

                editTextName.setText("");
                editTextDescription.setText("");
                editTextPrice.setText("");
                editTextVariant.setText("");

                Intent intent = new Intent(AddProduct.this,MainActivity.class);
                startActivity(intent);


            }
        });
    }

    public Uri fileUri;
    private int REQUEST_GALLERY = 100;

    public void openFolder() {

        Intent folderIntent = new Intent(Intent.ACTION_PICK, android.provider.MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
        startActivityForResult(folderIntent, REQUEST_GALLERY);

    }

    Bitmap bitmap;
    byte[] byteArray;

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == REQUEST_GALLERY && resultCode == Activity.RESULT_OK) {
            Uri selectedImage = data.getData();

            imgProduct.setImageURI(selectedImage);
            Bitmap bitmap = ((BitmapDrawable) imgProduct.getDrawable()).getBitmap();
            ByteArrayOutputStream baos = new ByteArrayOutputStream();
            bitmap.compress(Bitmap.CompressFormat.JPEG, 70, baos);
            byteArray = baos.toByteArray();
        }
    }

    private void Save_onClick(View v) {
        Product product = new Product();
        product.setNama(editTextName.getText().toString());
        product.setPrice(editTextPrice.getText().toString());
        product.setDescription(editTextDescription.getText().toString());
        product.setVariant(editTextVariant.getText().toString());
        product.setGambar(imgProduct.toString());

        ProductInterface productInterface = ApiClient.getClient().create(ProductInterface.class);
        productInterface.save(product).enqueue(new Callback<Product>() {
            @Override
            public void onResponse(Call<Product> call, Response<Product> response) {
                try {
                    if (response.isSuccessful()) {
                        Product newProduct = response.body();
                        Toast.makeText(getApplicationContext(), "Data successfully added", Toast.LENGTH_LONG).show();
                    } else {
                        Toast.makeText(getApplicationContext(), response.errorBody().string(), Toast.LENGTH_LONG).show();
                    }
                } catch (Exception e) {
                    Toast.makeText(getApplicationContext(), e.getMessage(), Toast.LENGTH_LONG).show();
                }
            }


            @Override
            public void onFailure(Call<Product> call, Throwable t) {
            Toast.makeText(getApplicationContext(), t.getMessage(), Toast.LENGTH_LONG).show();
            }
        });
    }
}
