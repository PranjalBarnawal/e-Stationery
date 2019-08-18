package com.example.codered;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;

public class AutoComplete extends AppCompatActivity {

    String[] KalijNames={"DTU","NSUT","IGDTUW","IPU","DAICCT"};

    AutoCompleteTextView autoCompleteTextView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_auto_complete);

        autoCompleteTextView=findViewById(R.id.autoView);
        ArrayAdapter arrayAdapter=new ArrayAdapter(this,android.R.layout.simple_dropdown_item_1line,KalijNames);
        autoCompleteTextView.setThreshold(0);
        autoCompleteTextView.setAdapter(arrayAdapter);


    }
}
