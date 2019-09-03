package com.example.codered;

import android.content.Intent;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class Register extends AppCompatActivity {

    EditText userName;
    EditText email;
    String uId;
    EditText phoneNo;
    Button SubmitButton;
    private DatabaseReference mDatabase;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        //categoryShow=findViewById(R.id.categoryShow);
        userName= findViewById(R.id.username);
        email= findViewById(R.id.email);
        SubmitButton=findViewById(R.id.submitButton);
        phoneNo= findViewById(R.id.phone);


        mDatabase = FirebaseDatabase.getInstance().getReference("Users");
        FirebaseUser currentFirebaseUser = FirebaseAuth.getInstance().getCurrentUser() ;
        uId=currentFirebaseUser.getUid();



    }
    private void addUserChangeListener() {
        // User data change listener
        mDatabase.child(uId).addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                userClass user = dataSnapshot.getValue(userClass.class);

                // Check for null
                if (user == null) {
                    Log.e("LOL", "User data is null!");
                    return;
                }

                Log.e("LOL", "User is registered" );
            }

            @Override
            public void onCancelled(DatabaseError error) {
                // Failed to read value
                Log.e("LOL", "registration error", error.toException());
            }
        });
    }


    public void register(View view) {
        userClass user = new userClass(userName.getText().toString(),email.getText().toString(), uId, phoneNo.getText().toString());
        mDatabase.child(uId).setValue(user);
        addUserChangeListener();
        Intent intent=new Intent(Register.this,MainActivity.class);
        startActivity(intent);
    }
}
