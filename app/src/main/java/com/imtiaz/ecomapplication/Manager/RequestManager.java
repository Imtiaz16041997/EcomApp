package com.imtiaz.ecomapplication.Manager;

import android.content.Context;

import com.imtiaz.ecomapplication.Listeners.ProductFetchedListener;
import com.imtiaz.ecomapplication.Listeners.SingleProductListener;
import com.imtiaz.ecomapplication.Models.Products;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.http.GET;
import retrofit2.http.Path;

public class RequestManager {
    Context context;
    Retrofit retrofit = new Retrofit.Builder()
            .baseUrl("https://fakestoreapi.com/")
            .addConverterFactory(GsonConverterFactory.create())
            .build();

    public RequestManager(Context context) {
        this.context = context;
    }

    public void GetProducts(ProductFetchedListener listener){
        CallProducts callProducts = retrofit.create(CallProducts.class);
        Call<List<Products>> call = callProducts.callProducts();
        call.enqueue(new Callback<List<Products>>() {
            @Override
            public void onResponse(Call<List<Products>> call, Response<List<Products>> response) {
                if (!response.isSuccessful()){
                    listener.didError(response.message());
                    return;
                }
                listener.didFetch(response.body(), response.message());
            }

            @Override
            public void onFailure(Call<List<Products>> call, Throwable t) {
                listener.didError(t.getMessage());
            }
        });
    }

    public void GetSpecificCatProducts(ProductFetchedListener listener, String specific){
        CallSpecificCategory specificCatProduct = retrofit.create(CallSpecificCategory.class);
        Call<List<Products>> call = specificCatProduct.specificCatProducts(specific);

        call.enqueue(new Callback<List<Products>>() {
            @Override
            public void onResponse(Call<List<Products>> call, Response<List<Products>> response) {
                if(!response.isSuccessful())
                {
                    listener.didError(response.message());
                    return;
                }
                listener.didFetch(response.body(), response.message());
            }

            @Override
            public void onFailure(Call<List<Products>> call, Throwable t) {
                listener.didError(t.getMessage());
            }
        });


    }

    public void GetProductDetails(SingleProductListener listener, String id){
        ProductDetails productDetails = retrofit.create(ProductDetails.class);
        Call<Products> call = productDetails.getDetails(id);

        call.enqueue(new Callback<Products>() {
            @Override
            public void onResponse(Call<Products> call, Response<Products> response) {
                if(!response.isSuccessful())
                {
                    listener.didError(response.message());
                    return;
                }
                listener.didFetch(response.body(), response.message());
            }

            @Override
            public void onFailure(Call<Products> call, Throwable t) {
                listener.didError(t.getMessage());
            }
        });


    }


    private interface CallProducts{
        @GET("products")
        Call<List<Products>> callProducts();

//        @GET("products/categories")
//        Call<List<String>> callCategories();
    }

    private  interface  CallSpecificCategory{
        @GET("products/category/{specific}")
        Call<List<Products>> specificCatProducts(@Path("specific") String specific);
    }

    private interface ProductDetails{
        @GET("products/{id}")
        Call<Products> getDetails(@Path("id") String id);
    }
}
