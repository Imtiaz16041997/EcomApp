package com.imtiaz.ecomapplication;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.imtiaz.ecomapplication.Activities.CartActivity;
import com.imtiaz.ecomapplication.Activities.ProductDetailsActivity;
import com.imtiaz.ecomapplication.Activities.ProfileActivity;
import com.imtiaz.ecomapplication.Adapters.CategoriesAdapter;
import com.imtiaz.ecomapplication.Adapters.ProductListAdapter;
import com.imtiaz.ecomapplication.Listeners.CartClickListener;
import com.imtiaz.ecomapplication.Listeners.CategoriesClickListener;
import com.imtiaz.ecomapplication.Listeners.ProductFetchedListener;
import com.imtiaz.ecomapplication.Manager.RequestManager;
import com.imtiaz.ecomapplication.Models.CartData;
import com.imtiaz.ecomapplication.Models.CartProducts;
import com.imtiaz.ecomapplication.Models.Category;
import com.imtiaz.ecomapplication.Models.Products;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;


public class MainActivity extends AppCompatActivity {

    RecyclerView recycler_home,recycler_category;
    RequestManager manager;
    ProgressDialog dialog;
    ProductListAdapter adapter;
    ImageView imageView_checkout,imageView_profile;
    TextView textView_product_home, textView_product_home_count;
    Button button_more;
    int endIndex = 0;
    List<CartData> cartData = new ArrayList<>();
    List<Category> categoryList;
    List<Products> allProducts = new ArrayList<>();
    List<Products> pagerList = new ArrayList<>();
    List<CartProducts> cartDataList = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        recycler_home = findViewById(R.id.recycler_home);
        recycler_category = findViewById(R.id.recycler_category);
        button_more = findViewById(R.id.button_more);
        imageView_checkout = findViewById(R.id.imageView_checkout);
        imageView_profile = findViewById(R.id.imageView_profile);
        textView_product_home = findViewById(R.id.textView_product_home);
        textView_product_home_count = findViewById(R.id.textView_product_home_count);

        categoryList = setupCategories();
        recycler_category.setHasFixedSize(true);
        recycler_category.setLayoutManager(new LinearLayoutManager(this,LinearLayoutManager.HORIZONTAL,false));
        CategoriesAdapter categoriesAdapter = new CategoriesAdapter(this,categoryList,categoryListener);
        recycler_category.setAdapter(categoriesAdapter);


        imageView_checkout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                startActivity(new Intent(MainActivity.this, CartActivity.class).putExtra("cart", (Serializable) cartData));

            }
        });

        imageView_profile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(MainActivity.this, ProfileActivity.class));
            }
        });

        dialog = new ProgressDialog(this);
        dialog.setTitle("Loading...");

        manager = new RequestManager(this);
        manager.GetProducts(listener);
        dialog.show();

        button_more.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showData(allProducts, endIndex);
            }
        });

    }



    private List<Category> setupCategories() {
        List<Category> categories = new ArrayList<>();
        categories.add(new Category("electronics", R.drawable.electronics));
        categories.add(new Category("jewelery", R.drawable.jewellery));
        categories.add(new Category("men's clothing", R.drawable.mens));
        categories.add(new Category("women's clothing", R.drawable.womens));

        return categories;
    }

    private final ProductFetchedListener listener = new ProductFetchedListener() {
        @Override
        public void didFetch(List<Products> response, String message) {
            dialog.dismiss();
            textView_product_home_count.setText("Total: "+response.size());
            allProducts = response;
            showData(response, 0);
        }

        @Override
        public void didError(String message) {
            dialog.dismiss();

            Toast.makeText(MainActivity.this, message, Toast.LENGTH_SHORT).show();
        }
    };

    private void showData(List<Products> response, int startIndex) {
        try {
            endIndex = startIndex+2;
            for (int i =startIndex; i<endIndex; i++){

                pagerList.add(response.get(i));
            }

            recycler_home.setHasFixedSize(true);
            recycler_home.setLayoutManager(new GridLayoutManager(this, 2));
            adapter = new ProductListAdapter(MainActivity.this, pagerList,productSelectListener,cartClickListener);
            recycler_home.setAdapter(adapter);
        }catch (Exception e){
            e.printStackTrace();
        }

    }

    private  final CategoriesClickListener categoryListener = new CategoriesClickListener() {
        @Override
        public void onClick(String category) {
            textView_product_home.setText(category);
            pagerList.clear();
            manager.GetSpecificCatProducts(listener,category);
            dialog.show();
        }
    };


    private  final CategoriesClickListener productSelectListener = new CategoriesClickListener() {
        @Override
        public void onClick(String category) {

            startActivity(new Intent(MainActivity.this, ProductDetailsActivity.class)
                    .putExtra("id",category));

        }
    };

    private final CartClickListener cartClickListener = new CartClickListener() {
        @Override
        public void didClickCart(int prodId, int quantity) {
            CartProducts cartProducts = new CartProducts(prodId, quantity);
            cartDataList.add(cartProducts);
            cartData.add( new CartData(1, "23-03-2022", cartDataList)) ;
            Toast.makeText(MainActivity.this, "Added to cart!", Toast.LENGTH_SHORT).show();
        }
    };
}