package com.imtiaz.ecomapplication.Activities;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;


import com.imtiaz.ecomapplication.Listeners.SingleProductListener;
import com.imtiaz.ecomapplication.Manager.RequestManager;
import com.imtiaz.ecomapplication.Models.Products;
import com.imtiaz.ecomapplication.R;
import com.squareup.picasso.Picasso;

public class ProductDetailsActivity extends AppCompatActivity {
    TextView tv_product_title,tv_product_price,tv_product_description,tv_product_category,tv_product_rating,tv_product_count;
    ImageView imageView_product;
    RequestManager manager;
    ProgressDialog dialog;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_product_details);

        imageView_product = findViewById(R.id.imageView_product);
        tv_product_title = findViewById(R.id.tv_product_title);
        tv_product_price = findViewById(R.id.tv_product_price);
        tv_product_description = findViewById(R.id.tv_product_description);
        tv_product_category = findViewById(R.id.tv_product_category);
        tv_product_rating = findViewById(R.id.tv_product_rating);
        tv_product_count = findViewById(R.id.tv_product_count);

        manager = new RequestManager(this);
        String id = getIntent().getStringExtra("id");
        manager.GetProductDetails(detailListener,id);

        dialog = new ProgressDialog(this);
        dialog.setTitle("Loading..");
        dialog.show();




    }

    private final SingleProductListener detailListener = new SingleProductListener() {
        @Override
        public void didFetch(Products products, String message) {
            dialog.dismiss();
            tv_product_title.setText("Title: "+products.getTitle());
            tv_product_description.setText("Description: "+products.getDescription());
            tv_product_price.setText("Price: "+String.valueOf(products.getPrice()));
            tv_product_category.setText("Category: "+products.getCategory());
            tv_product_rating.setText("Rating: "+String.valueOf(products.getRating().getRate()));
            tv_product_count.setText("Stock-Count: "+String.valueOf(products.getRating().getCount()));


            Picasso.get().load(products.getImage()).into(imageView_product);
        }

        @Override
        public void didError(String message) {
            dialog.dismiss();
            Toast.makeText(ProductDetailsActivity.this, message, Toast.LENGTH_SHORT).show();
        }
    };

}