package com.example.lab1_20181650;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class StatsActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_stats);
        Button button = findViewById(R.id.buttonNJuego2);
        button.setOnClickListener(view -> {
            Intent intent = new Intent(StatsActivity.this, MainActivity.class);
            startActivity(intent);
        });

    }

}