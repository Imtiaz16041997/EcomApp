package com.imtiaz.ecomapplication.Listeners;


import com.imtiaz.ecomapplication.Models.Products;

import java.util.List;

public interface ProductFetchedListener {
    void didFetch(List<Products> response, String message);
    void didError(String message);
}
