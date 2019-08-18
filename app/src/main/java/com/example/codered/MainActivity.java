package com.example.codered;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.NavigationView;
import android.support.design.widget.Snackbar;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    GridView gridView;


    private DrawerLayout dl;
    private ActionBarDrawerToggle t;
    private NavigationView nv;

    String[] fruitNames = {"Apple", "Orange", "strawberry", "Melon", "Kiwi", "Banana"};
    int[] fruitImages = {R.drawable.deadpool, R.drawable.deadpool, R.drawable.deadpool, R.drawable.deadpool, R.drawable.deadpool, R.drawable.deadpool};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.navigation_drawer);


        dl = findViewById(R.id.activity_main);
        t = new ActionBarDrawerToggle(this, dl, R.string.Open, R.string.Close);

        dl.addDrawerListener(t);
        t.syncState();

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        nv = findViewById(R.id.nv);


        nv.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                int id = item.getItemId();
                switch (id) {
                    case R.id.account:
                        Toast.makeText(MainActivity.this, "My Account", Toast.LENGTH_SHORT).show();
                        break;
                    case R.id.settings:
                        Toast.makeText(MainActivity.this, "Settings", Toast.LENGTH_SHORT).show();
                        break;
                    case R.id.mycart:
                        Toast.makeText(MainActivity.this, "My Cart", Toast.LENGTH_SHORT).show();
                        break;
                    default:
                        return true;
                }


                return true;

            }
        });


        FloatingActionButton fab = findViewById(R.id.floatingActionButton);

        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Here's a Snackbar", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });

        Toolbar toolbar = findViewById(R.id.toolbar);


        gridView = findViewById(R.id.gridview);
        gridView.setNestedScrollingEnabled(true);


        CustomAdapter customAdapter = new CustomAdapter();
        gridView.setAdapter(customAdapter);
        gridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {

                Toast.makeText(getApplicationContext(), fruitNames[i], Toast.LENGTH_LONG).show();

            }
        });


    }


    private class CustomAdapter extends BaseAdapter {
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

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.mymenu, menu);
        return super.onCreateOptionsMenu(menu);
    }

    // handle button activities
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if (t.onOptionsItemSelected(item))
            return true;


        if (id == R.id.mybutton) {

            Intent intent = new Intent(MainActivity.this, AutoComplete.class);
            startActivity(intent);

        }

        return super.onOptionsItemSelected(item);


    }
}





