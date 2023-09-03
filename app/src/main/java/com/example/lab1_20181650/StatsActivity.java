package com.example.lab1_20181650;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.util.ArrayList;

public class StatsActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_stats);
        getSupportActionBar().setTitle("TeleAhorcado");

        Intent intent = getIntent();
        ArrayList<String> estadisticas = intent.getStringArrayListExtra("estadisticas");
        if(estadisticas != null && !estadisticas.isEmpty()){
            String estadisticasS = "";
            for (String contador : estadisticas){
                estadisticasS = estadisticasS + contador + '\n';
            }
            TextView textView = findViewById(R.id.textViewContent);
            textView.setTextAlignment(View.TEXT_ALIGNMENT_TEXT_START);
            textView.setText(estadisticasS);
        }

    }
    public void nuevoJuego(View view){
        Intent intent = new Intent(StatsActivity.this, MainActivity.class);
        intent.putExtra("nuevo juego", 1);
        startActivity(intent);
    }
}