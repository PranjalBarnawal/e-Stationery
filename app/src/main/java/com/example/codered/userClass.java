package com.example.codered;

import java.util.ArrayList;

public class userClass {

    public ArrayList<String> products;
    public String userName;
    public String email;
    public String uId;
    public String phoneNo;

    public userClass(String userName,String email, String uId, String phoneNo) {
        this.userName = userName;
        this.email= email;
        this.uId= uId;
        this.phoneNo= phoneNo;
    }
    public userClass(){

    }
    public void addProduct(String pId)
    {
        products.add(pId);
    }
}
