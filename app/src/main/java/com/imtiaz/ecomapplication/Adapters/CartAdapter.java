package com.imtiaz.ecomapplication.Adapters;


import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;


import com.imtiaz.ecomapplication.Models.Products;
import com.imtiaz.ecomapplication.R;
import com.squareup.picasso.Picasso;

import java.util.List;


public class CartAdapter extends RecyclerView.Adapter<CartViewHolder> {
    Context context;
    List<Products> list;

    public CartAdapter(Context context, List<Products> list) {
        this.context = context;
        this.list = list;
    }

    @NonNull
    @Override
    public CartViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new CartViewHolder(LayoutInflater.from(context).inflate(R.layout.cart_layout,parent,false));
    }

    @Override
    public void onBindViewHolder(@NonNull CartViewHolder holder, int position) {
        holder.cartProduct_title.setText(list.get(position).getTitle());
        holder.cartProduct_price.setText(String.valueOf(list.get(position).getPrice()));
        Picasso.get().load(list.get(position).getImage()).into(holder.cartProduct_imageView);
    }

    @Override
    public int getItemCount() {
        return list.size();
    }
}

class CartViewHolder extends RecyclerView.ViewHolder{

    ImageView cartProduct_imageView;
    TextView cartProduct_title, cartProduct_price;
    public CartViewHolder(@NonNull View itemView) {
        super(itemView);
        cartProduct_imageView = itemView.findViewById(R.id.cartProduct_imageView);
        cartProduct_title = itemView.findViewById(R.id.cartProduct_title);
        cartProduct_price = itemView.findViewById(R.id.cartProduct_price);

    }
}