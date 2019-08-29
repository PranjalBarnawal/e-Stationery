package com.example.codered;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.firebase.ui.auth.data.model.User;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class PostActivity extends AppCompatActivity {

    TextView categoryShow;
    String pId;
    EditText title;
    String uId;
    EditText price;
    EditText description;
    Button postButton;
    private DatabaseReference mDatabase;
    private String category;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_post);

        categoryShow=findViewById(R.id.categoryShow);
        title=findViewById(R.id.title);
        price=findViewById(R.id.price);
        description=findViewById(R.id.description);
        postButton=findViewById(R.id.postButton);

        Intent postIntent=getIntent();
        category= postIntent.getStringExtra("category");
        categoryShow.setText(category);

        mDatabase = FirebaseDatabase.getInstance().getReference("Posts");
        pId = mDatabase.push().getKey();
        FirebaseUser currentFirebaseUser = FirebaseAuth.getInstance().getCurrentUser() ;
        uId=currentFirebaseUser.getUid();



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

    public void post(View view) {
        Posts post = new Posts(category, pId,uId,title.getText().toString(),Integer.parseInt(price.getText().toString()),description.getText().toString());
        mDatabase.child(category).child(pId).setValue(post);
        addPostChangeListener();
    }
}
