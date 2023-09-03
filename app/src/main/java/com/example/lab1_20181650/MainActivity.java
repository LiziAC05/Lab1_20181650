package com.example.lab1_20181650;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.media.Image;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.animation.Animation;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import org.w3c.dom.Text;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Scanner;

public class MainActivity extends AppCompatActivity {
    ArrayList<String> listaPalabras;
    TextView palabraIncog;
    String palabraIncoS;
    EditText edtIn;
    String palabraDesplegada;
    char[] letraPalabraDesplegada;
    TextView txtIntentos;
    String intentos;
    Image imagen1; //cabeza
    ImageView imageViewCabeza;
    Image imagen2; //torso
    ImageView imageViewTorso;
    Image imagen3; //brazo derecho
    ImageView imageViewBrazoDerecho;
    Image imagen4; //brazo izquierdo
    ImageView imageViewBrazoIzquierdo;
    Image imagen5; //pierna izquierda
    ImageView imageViewPiernaIzquierda;
    Image imagen6; //pierna derecha
    ImageView imageViewPiernaDerecha;
    final String GANADOR = "Gano";
    final String PERDEROR = "Perdio";
    Animation rotateAnimation;
    Animation scaleAnimation;
    Animation scaleAndRotateAnimation;
    public void revelarLetraPalabra(char letra){
        int indiceLetra  = palabraIncoS.indexOf(letra);
        while(indiceLetra >= 0){
            letraPalabraDesplegada[indiceLetra] = palabraIncoS.charAt(indiceLetra);
            indiceLetra = palabraIncoS.indexOf(letra, indiceLetra + 1);
        }
        palabraIncoS = String.valueOf(letraPalabraDesplegada);
    }

    public void despliegaPalabraPantalla(){
        String palabraFormada = "";
        for(char caracter : letraPalabraDesplegada){
            palabraFormada += caracter + " ";
        }
        palabraIncog.setText(palabraFormada);
    }
    public void iniciaJuego(){
        Collections.shuffle(listaPalabras);
        palabraIncoS = listaPalabras.get(0);
        listaPalabras.remove(0);
        letraPalabraDesplegada = palabraIncoS.toCharArray();
        for(int i = 0; i < letraPalabraDesplegada.length - 1; i++){
            letraPalabraDesplegada[i] = '_';
        }
        revelarLetraPalabra(letraPalabraDesplegada[0]);
        revelarLetraPalabra(letraPalabraDesplegada[letraPalabraDesplegada.length - 1]);
        palabraDesplegada = String.valueOf(letraPalabraDesplegada);
        despliegaPalabraPantalla();
        edtIn.setText("");
        intentos = " ";
        txtIntentos.setText(intentos);
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        getSupportActionBar().setTitle("TeleAhorcado");
        listaPalabras = new ArrayList<String>();
        palabraIncog = (TextView) findViewById(R.id.palabraInc);
        txtIntentos = (TextView) findViewById(R.id.txtIntentos);
        edtIn = (EditText) findViewById(R.id.edtInput);
        listaPalabras.add("REDES");
        listaPalabras.add("PROPA");
        listaPalabras.add("PUCP");
        listaPalabras.add("TELITO");
        listaPalabras.add("TELECO");
        listaPalabras.add("BATI");
        /*InputStream inputStream = null;
        Scanner in = null;
        String palabra = "";
        try  {
            inputStream = getAssets().open("/database/palabras_ahorcado.txt");
            in = new Scanner(inputStream);
            while(in.hasNext()){
                palabra = in.next();
                listaPalabras.add(palabra);
            }
        } catch (IOException e){
            Toast.makeText(MainActivity.this, e.getClass().getSimpleName() + " : " + e.getMessage(), Toast.LENGTH_SHORT).show();
        }
        finally {
            if(in != null){
                in.close();
            }
            try{
                if(inputStream != null){
                    inputStream.close();
                }
            } catch (IOException e){
                Toast.makeText(MainActivity.this, e.getClass().getSimpleName() + " : " + e.getMessage(), Toast.LENGTH_SHORT).show();
            }
        }*/
        iniciaJuego();
        edtIn.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                if(charSequence.length() != 0){
                    revisaLetraEnPalabra(charSequence.charAt(0));

                }
            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });

    }
    public void revisaLetraEnPalabra(char letra){
        if(palabraIncoS.indexOf(letra) >= 0){
            //letra no desplegada
            if(palabraDesplegada.indexOf(letra) < 0){
                revelarLetraPalabra(letra);
                despliegaPalabraPantalla();
                if(!palabraDesplegada.contains("_")){
                    txtIntentos.setText(GANADOR + ": Termino en ");
                }
            }
        }
        else{
            //revelará la imagen una por una
            //desplegarImagen();
            //perdio el juego
            if(intentos.isEmpty()){
                txtIntentos.setText(PERDEROR + ": Termino en ");
                palabraIncog.setText(palabraIncoS);
            }
        }
    }

    /*public void desplegarImagen(){

    }*/

   /* public void reiniciaJuego(){
        iniciaJuego();
    }*/
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main_act,menu);
        return true;
    }

    public boolean onOptionsItemSelected(@NonNull MenuItem item){
        if(item.getItemId() == R.id.stadistics){
            Intent intent = new Intent(MainActivity.this, StatsActivity.class);
            startActivity(intent);
        }
        return super.onOptionsItemSelected(item);
    }

}