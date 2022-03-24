package com.imtiaz.ecomapplication.Models;

import java.io.Serializable;

public class CartProducts implements Serializable {
    int productId = 0;
    int quantity = 0;

    public CartProducts(int productId, int quantity) {
        this.productId = productId;
        this.quantity = quantity;
    }

    public int getProductId() {
        return productId;
    }

    public void setProductId(int productId) {
        this.productId = productId;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }
}