package com.example.lab1_20181650;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main_act,menu);
        return true;
    }

    /*public void returnBtn(){
        Intent intent = new Intent(MainActivity.this, StatsActivity.class);
        startActivity(intent);
        //finish();
    }*/

    public boolean onOptionsItemSelected(@NonNull MenuItem item){
        if(item.getItemId() == R.id.stadistics){
            Intent intent = new Intent(MainActivity.this, StatsActivity.class);
            startActivity(intent);
        }
        return super.onOptionsItemSelected(item);
    }

}