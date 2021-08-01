package com.example.productapp.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;

import com.example.productapp.R;
import com.example.productapp.entity.Product;

import java.util.ArrayList;
import java.util.List;

public class ListProductAdapter extends RecyclerView.Adapter<ListProductAdapter.ViewHolder> {
    private Context context;
    private List<Product> data;

    public ListProductAdapter(Context context, java.util.List<Product> data){



        this.data = data;
        this.context = context;

    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view ;

        view = LayoutInflater.from(parent.getContext()).inflate(R.layout.mockup_product, parent, false);


        ViewHolder myViewHolder = new ViewHolder(view);
        return myViewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, final int position) {


        holder.editProductName.setText(data.get(position).getNama());
        holder.editProductPrice.setText(data.get(position).getPrice());
        holder.editProductDescription.setText(data.get(position).getDescription());
        holder.editProductVariant.setText(data.get(position).getVariant());


        String image =  data.get(position).getGambar();
       // Picasso.get().load(image).into(holder.img_Film);


    }

    @Override
    public int getItemCount() {
        return data.size();
    }


    // Static inner class to initialize the views of rows
    static class ViewHolder extends RecyclerView.ViewHolder  {
        public TextView editProductName,editProductPrice,editProductDescription,editProductVariant ;
        public ImageView imageProduct;


        public ViewHolder(View itemView) {


            super(itemView);

            editProductName = (TextView) itemView.findViewById(R.id.editProductName);
            editProductPrice = (TextView) itemView.findViewById(R.id.editProductPrice);
            editProductDescription = (TextView) itemView.findViewById(R.id.editProductDescription);
            editProductVariant = (TextView) itemView.findViewById(R.id.editProductVariant);
            imageProduct = (ImageView)itemView.findViewById(R.id.imgProduct);


        }

    }




}
