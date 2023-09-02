package com.example.lab1_20181650;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.ContentResolver;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.ContextMenu;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.example.lab1_20181650.databinding.ActivityMenuBinding;

public class MenuActivity extends AppCompatActivity {

    ActivityMenuBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMenuBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        registerForContextMenu((TextView) findViewById(R.id.textView2));
        Button button = findViewById(R.id.button);
        button.setOnClickListener(view -> {
            Intent intent = new Intent(MenuActivity.this, MainActivity.class);
            startActivity(intent);
        });
    }

    @Override
    public void onCreateContextMenu(ContextMenu menu, View view, ContextMenu.ContextMenuInfo menuInfo){
        super.onCreateContextMenu(menu, view, menuInfo);
        getMenuInflater().inflate(R.menu.menu_context,menu);
    }

   @Override
    public boolean onContextItemSelected(@NonNull MenuItem item){
        switch(item.getItemId()){
            case R.id.textoRojo:
                Toast.makeText(this,"TextoRojo", Toast.LENGTH_SHORT).show();
                return true;
            case R.id.textoVerde:
                Toast.makeText(this,"TextoVerde", Toast.LENGTH_SHORT).show();
                return true;
            case R.id.textoAzul:
                Toast.makeText(this,"TextoAzul", Toast.LENGTH_SHORT).show();
                return true;
            default:
                return super.onContextItemSelected(item);
        }
    }
}