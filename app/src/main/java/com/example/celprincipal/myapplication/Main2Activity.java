package com.example.celprincipal.myapplication;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.LinearLayout;

public class Main2Activity extends AppCompatActivity {
    LinearLayout l;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);
        l = (LinearLayout) findViewById(R.id.milayout);
    }
}
