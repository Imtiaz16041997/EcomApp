package com.imtiaz.ecomapplication.Activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.widget.TextView;
import android.widget.Toast;

import com.imtiaz.ecomapplication.Adapters.CartAdapter;
import com.imtiaz.ecomapplication.Listeners.SingleProductListener;
import com.imtiaz.ecomapplication.Manager.RequestManager;
import com.imtiaz.ecomapplication.Models.CartData;
import com.imtiaz.ecomapplication.Models.CartProducts;
import com.imtiaz.ecomapplication.Models.Products;
import com.imtiaz.ecomapplication.R;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;


public class CartActivity extends AppCompatActivity {
    TextView userId,dateId;
    ProgressDialog dialog;
    int flag = 0;
    RecyclerView cart_recyclerView;
    CartAdapter adapter;
    List<CartData> listCartData = new ArrayList<>();
    List<CartProducts> cartProducts = new ArrayList<>();
    List<Products> productsList = new ArrayList<>();
    RequestManager manager;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cart);

        cart_recyclerView = findViewById(R.id.cart_recyclerView);

        listCartData = (List<CartData>) getIntent().getSerializableExtra("cart");

        cartProducts.addAll(listCartData.get(0).getProducts());

        dialog = new ProgressDialog(this);
        dialog.setTitle("Loading...");


        userId = findViewById(R.id.userId);
        dateId = findViewById(R.id.dateId);

        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd-MMM-yyyy");
        Date date = new Date();
        String currentDate = simpleDateFormat.format(date);
        userId.setText(String.valueOf(listCartData.get(0).getUserId()));
        dateId.setText(currentDate);




        manager = new RequestManager(this);
        dialog.show();
        for (int i = 0; i< cartProducts.size();i++){
            manager.GetProductDetails(listener,String.valueOf(cartProducts.get(i).getProductId()));
        }

    }

    private void showData() {
        cart_recyclerView.setHasFixedSize(true);
        cart_recyclerView.setLayoutManager(new LinearLayoutManager(this,LinearLayoutManager.VERTICAL,false));
        adapter = new CartAdapter(this,productsList);
        cart_recyclerView.setAdapter(adapter);
    }

    private  final SingleProductListener listener = new SingleProductListener() {
        @Override
        public void didFetch(Products products, String message) {
            productsList.add(products);
            flag++;
            if(flag == cartProducts.size()){
                showData();
                dialog.dismiss();
            }

        }

        @Override
        public void didError(String message) {
            Toast.makeText(CartActivity.this, message, Toast.LENGTH_SHORT).show();
        }
    };
}