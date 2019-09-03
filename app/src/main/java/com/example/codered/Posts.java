package com.example.codered;

import android.net.Uri;

import java.net.URL;

public class Posts {

    public String category;
    public String pId;
    public String title;
    public int price;
    public String uId;
    public String image;
    public String description;



    public Posts(String category,String pId,String uId, String title, int price, String description, String image) {
        this.category = category;
        this.pId=pId;
        this.uId=uId;
        this.title = title;
        this.price=price;
        this.image=image;
        this.description= description;

    }
    public Posts(){

    }
}

