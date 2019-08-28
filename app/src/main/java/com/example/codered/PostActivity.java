package com.example.codered;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

import com.firebase.ui.auth.data.model.User;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class PostActivity extends AppCompatActivity {

    TextView categoryShow;
    String pId;
    private DatabaseReference mDatabase;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_post);

        categoryShow=findViewById(R.id.categoryShow);

        Intent postIntent=getIntent();
        String category= postIntent.getStringExtra("category");
        categoryShow.setText(category);



//        mDatabase = FirebaseDatabase.getInstance().getReference("Posts");
//        pId = mDatabase.push().getKey();
//        Posts post = new Posts(category, pId,"title",100,"this is description of our post");
//        mDatabase.child(pId).setValue(post);
//        addPostChangeListener();
    }


    private void addPostChangeListener() {
        // User data change listener
        mDatabase.child(pId).addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                Posts post = dataSnapshot.getValue(Posts.class);

                // Check for null
                if (post == null) {
                    Log.e("LOL", "User data is null!");
                    return;
                }

                Log.e("LOL", "User data is changed!" + post.category + ", " + post.title);
            }

            @Override
            public void onCancelled(DatabaseError error) {
                // Failed to read value
                Log.e("LOL", "Failed to read user", error.toException());
            }
        });
    }

}
