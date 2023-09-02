package com.example.lab1_20181650;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.ContextMenu;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;

import com.example.lab1_20181650.databinding.ActivityMenuBinding;

public class MenuActivity extends AppCompatActivity {

    ActivityMenuBinding binding;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMenuBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        registerForContextMenu((TextView) findViewById(R.id.textView2));
    }

    public void onContextItemSelect(@NonNull MenuItem item){
        super.onContextItemSelected(item);

    }

    public void irAlJuego(View view){
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
    }

}