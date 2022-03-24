package com.imtiaz.ecomapplication.Models;

import java.io.Serializable;

public class Category implements Serializable {
    String title = "";
    int icon = 0;

    public Category(String title, int icon) {
        this.title = title;
        this.icon = icon;
    }

    public Category() {
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public int getIcon() {
        return icon;
    }

    public void setIcon(int icon) {
        this.icon = icon;
    }
}
