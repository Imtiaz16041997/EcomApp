package com.imtiaz.ecomapplication.Adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;


import com.imtiaz.ecomapplication.Listeners.CartClickListener;
import com.imtiaz.ecomapplication.Listeners.CategoriesClickListener;
import com.imtiaz.ecomapplication.Models.Products;
import com.imtiaz.ecomapplication.R;
import com.squareup.picasso.Picasso;

import java.util.List;


public class ProductListAdapter extends RecyclerView.Adapter<ProductViewHolder>{

    Context context;
    List<Products> list;
    CategoriesClickListener listener;
    CartClickListener cartClickListener;

    public ProductListAdapter(Context context, List<Products> list, CategoriesClickListener listener, CartClickListener cartClickListener) {
        this.context = context;
        this.list = list;
        this.listener = listener;
        this.cartClickListener = cartClickListener;
    }

    @NonNull
    @Override
    public ProductViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new ProductViewHolder(LayoutInflater.from(context).inflate(R.layout.list_products, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull ProductViewHolder holder, int position) {
        holder.textView_product_title.setText(list.get(position).getTitle());
        holder.productCard.setSelected(true);
        holder.textView_product_price.setText(String.valueOf(list.get(position).getPrice()));
        holder.textView_count.setText(String.valueOf(list.indexOf(list.get(position))));

        holder.textView_product_rating.setText(list.get(position).getRating().getRate() + "(" + list.get(position).getRating().getCount() + " Votes)");

        Picasso.get().load(list.get(position).getImage()).into(holder.imageView_product);

        holder.productCard.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                listener.onClick(String.valueOf(list.get(holder.getAdapterPosition()).getId()));
            }
        });

        holder.btn_cart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                cartClickListener.didClickCart(list.get(holder.getAdapterPosition()).getId(), 1);
            }
        });
    }

    @Override
    public int getItemCount() {
        return list.size();
    }
}

class ProductViewHolder extends RecyclerView.ViewHolder {

    ImageView imageView_product;
    CardView productCard;
    TextView textView_product_title, textView_product_price, textView_count, btn_cart, textView_product_rating, textView_product_count;

    public ProductViewHolder(@NonNull View itemView) {
        super(itemView);

        imageView_product = itemView.findViewById(R.id.imageView_product);
        productCard = itemView.findViewById(R.id.productCard);
        textView_product_title = itemView.findViewById(R.id.textView_product_title);
        textView_product_price = itemView.findViewById(R.id.textView_product_price);
        textView_count = itemView.findViewById(R.id.textView_count);
        btn_cart = itemView.findViewById(R.id.btn_cart);
        textView_product_rating = itemView.findViewById(R.id.textView_product_rating);
        textView_product_count = itemView.findViewById(R.id.textView_product_count);
    }
}
