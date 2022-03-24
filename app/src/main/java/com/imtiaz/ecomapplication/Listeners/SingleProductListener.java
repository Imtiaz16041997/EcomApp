package com.imtiaz.ecomapplication.Listeners;


import com.imtiaz.ecomapplication.Models.Products;

public interface SingleProductListener {
    void didFetch(Products products, String message);
    void didError(String message);
}
