package com.example.codered;

public class Posts {

    public String category;
    public String pId;
    public String title;
    public int price;
    public String uId;
    public String description;



    public Posts(String category,String pId,String uId, String title, int price, String description) {
        this.category = category;
        this.pId=pId;
        this.uId=uId;
        this.title = title;
        this.price=price;
        this.description= description;

    }
    public Posts(){

    }
}

