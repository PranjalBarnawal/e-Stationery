package com.example.codered;

import android.content.Intent;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.provider.MediaStore;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.firebase.ui.auth.data.model.User;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;

import java.io.ByteArrayOutputStream;
import java.io.File;

public class PostActivity extends AppCompatActivity {

    TextView categoryShow;
    String pId;
    EditText title;
    String uId;
    ImageView uploadImage;
    EditText price;
    EditText description;
    Button postButton;
    private DatabaseReference mDatabase;
    private String category;
    private StorageReference mountainsRef;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_post);

        categoryShow = findViewById(R.id.categoryShow);
        mountainsRef = FirebaseStorage.getInstance().getReference();
        uploadImage = findViewById(R.id.uploadImage);
        title = findViewById(R.id.title);
        price = findViewById(R.id.price);
        description = findViewById(R.id.description);
        postButton = findViewById(R.id.postButton);

        Intent postIntent = getIntent();
        category = postIntent.getStringExtra("category");
        categoryShow.setText(category);

        mDatabase = FirebaseDatabase.getInstance().getReference("Posts");
        pId = mDatabase.push().getKey();
        FirebaseUser currentFirebaseUser = FirebaseAuth.getInstance().getCurrentUser();
        uId = currentFirebaseUser.getUid();


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
        Posts post = new Posts(category, pId, uId, title.getText().toString(), Integer.parseInt(price.getText().toString()), description.getText().toString());
        mDatabase.child(category).child(pId).setValue(post);
        addPostChangeListener();
    }


    public void uploadImage(View view) {

        Intent i = new Intent(Intent.ACTION_PICK, android.provider.MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
        startActivityForResult(i, 0);

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == 0 && resultCode == RESULT_OK && null != data) {
            Uri selectedImage = data.getData();


            StorageReference riversRef = mountainsRef.child("images/rivers.jpg");

            riversRef.putFile(selectedImage)
                    .addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
                        @Override
                        public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
                            // Get a URL to the uploaded content
                            Uri downloadUrl = taskSnapshot.getDownloadUrl();
                        }
                    })
                    .addOnFailureListener(new OnFailureListener() {
                        @Override
                        public void onFailure(@NonNull Exception exception) {
                            // Handle unsuccessful uploads
                            // ...
                        }
                    });



            String[] filePathColumn = {MediaStore.Images.Media.DATA};
            Cursor cursor = getContentResolver().query(selectedImage, filePathColumn, null, null, null);
            cursor.moveToFirst();
            int columnIndex = cursor.getColumnIndex(filePathColumn[0]);
            String picturePath = cursor.getString(columnIndex);
            cursor.close();


            uploadImage.setImageBitmap(BitmapFactory.decodeFile(picturePath));
        }
    }
}






