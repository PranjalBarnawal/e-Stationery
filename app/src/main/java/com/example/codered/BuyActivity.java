package com.example.codered;

import android.content.Intent;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.squareup.picasso.Picasso;

public class BuyActivity extends AppCompatActivity {
    private DatabaseReference PostsRef;
    private RecyclerView recyclerView;
    RecyclerView.LayoutManager layoutManager;
    private String pId;
    String catName;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_buy);
        Intent categoryIntent=getIntent();
        catName=categoryIntent.getStringExtra("catName");

        PostsRef= FirebaseDatabase.getInstance().getReference().child("Posts").child(catName);

        recyclerView=findViewById(R.id.recycler_menu);
        recyclerView.setHasFixedSize(true);
        layoutManager=new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);

    }

    @Override
    protected void onStart() {
        super.onStart();
        FirebaseRecyclerOptions<Posts> options=
                new FirebaseRecyclerOptions.Builder<Posts>().setQuery(PostsRef,Posts.class).build();

        FirebaseRecyclerAdapter<Posts,ViewHolderClass> adapter=
                new FirebaseRecyclerAdapter<Posts, ViewHolderClass>(options) {
                    @Override
                    protected void onBindViewHolder(@NonNull ViewHolderClass holder, int position, @NonNull Posts model) {
                        holder.txtName.setText(model.title);
                        holder.txtPrice.setText("₹" + model.price);
                        Picasso.get().load(model.image).into(holder.imageView);
                        pId=model.pId;
                    }

                    @NonNull
                    @Override
                    public ViewHolderClass onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
                        View view= LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.ads,viewGroup,false);
                        ViewHolderClass holder=new ViewHolderClass(view);
                        return holder;
                    }
                };
        recyclerView.setAdapter(adapter);
        adapter.startListening();
    }

    public void startProductActivity(View view) {
        Intent intent=new Intent(BuyActivity.this,ProductActivity.class);
        intent.putExtra("category",catName);
        intent.putExtra("pId",pId);
        startActivity(intent);

    }
}
