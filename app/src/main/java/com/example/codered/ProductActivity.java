package com.example.codered;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.firebase.ui.auth.data.model.User;
import com.google.firebase.FirebaseError;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.squareup.picasso.Picasso;

public class ProductActivity extends AppCompatActivity {
    private DatabaseReference PostsRef;
    private String pId;
    TextView Title;
    TextView Price;
    TextView Description;
    ImageView Image;
    TextView SellerId;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_product);
        Intent categoryIntent=getIntent();
        String catName=categoryIntent.getStringExtra("category");
        pId=categoryIntent.getStringExtra("pId");
        PostsRef= FirebaseDatabase.getInstance().getReference().child("Posts").child(catName).child(pId);
        Title=findViewById(R.id.Title1);
        Price=findViewById(R.id.Price1);
        Description=findViewById(R.id.Description1);
        Image=findViewById(R.id.Image);
        SellerId=findViewById(R.id.Seller1);


        PostsRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot snapshot) {


                    //this is all you need to get a specific user by Uid

                       Posts wantedPost = snapshot.getValue(Posts.class);
                       Title.setText(wantedPost.title);
                       Price.setText(""+wantedPost.price);
                       Description.setText(wantedPost.description);
                       SellerId.setText(wantedPost.uId);
                       Picasso.get().load(wantedPost.image).into(Image);
                    //**********************************************


                Log.i("post", "onDataChange: post displayed " );
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                Log.i("post", "Error: " );
            }


        });




    }
}
