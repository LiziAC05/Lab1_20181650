package com.example.lab1_20181650;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

    }

    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main_act,menu);
        return true;
    }

    public void returnBtn(){
        Intent intent = new Intent(MainActivity.this, MenuActivity.class);
        startActivity(intent);
        //finish();
    }

    public void statBtn(){
        Intent intent = new Intent(MainActivity.this, StatsActivity.class);
        startActivity(intent);
    }
}