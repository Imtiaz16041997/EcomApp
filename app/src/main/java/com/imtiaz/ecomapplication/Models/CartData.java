package com.imtiaz.ecomapplication.Models;

import java.io.Serializable;
import java.util.List;

public class CartData implements Serializable {
    int userId = 0;
    String date = "";
    List<CartProducts> products;

    public CartData(int userId, String date, List<CartProducts> products) {
        this.userId = userId;
        this.date = date;
        this.products = products;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public List<CartProducts> getProducts() {
        return products;
    }

    public void setProducts(List<CartProducts> products) {
        this.products = products;
    }
}
