package com.example.codered;

import android.content.Intent;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.TextView;

public class SellActivity extends AppCompatActivity {

    GridView gridView2;

    String[] fruitNames = {"Apple", "Orange", "strawberry", "Melon", "Kiwi", "Banana"};
    int[] fruitImages = {R.drawable.deadpool, R.drawable.deadpool, R.drawable.deadpool, R.drawable.deadpool, R.drawable.deadpool, R.drawable.deadpool};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sell);
        gridView2= findViewById(R.id.gridview);

        CustomAdapter2 customAdapter = new CustomAdapter2();
        gridView2.setAdapter(customAdapter);
        gridView2.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Intent postIntent= new Intent(SellActivity.this,PostActivity.class);
                postIntent.putExtra("category",fruitNames[i]);
                startActivity(postIntent);

            }
        });
    }
    private class CustomAdapter2 extends BaseAdapter {
        @Override
        public int getCount() {
            return fruitImages.length;
        }

        @Override
        public Object getItem(int i) {
            return null;
        }

        @Override
        public long getItemId(int i) {
            return 0;
        }

        @Override
        public View getView(int i, View view, ViewGroup viewGroup) {
            View view1 = getLayoutInflater().inflate(R.layout.item, null);
            //getting view in row_data
            TextView name = view1.findViewById(R.id.fruits);
            ImageView image = view1.findViewById(R.id.images);
            name.setText(fruitNames[i]);
            image.setImageResource(fruitImages[i]);
            return view1;


        }
    }
}

