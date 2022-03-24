package com.imtiaz.ecomapplication.Models;

import java.io.Serializable;

public class Rating implements Serializable {
    public double rate;
    public int count;

    public double getRate() {
        return rate;
    }

    public void setRate(double rate) {
        this.rate = rate;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }
}
