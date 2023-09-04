package com.example.lab1_20181650;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.media.Image;
import android.os.Build;
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

import java.time.Instant;
import java.util.ArrayList;
import java.util.Collections;

public class MainActivity extends AppCompatActivity {
    TextView palabraIncog;
    String palabraIncoS;
    String palabraDesplegada;
    char[] letraPalabraDesplegada;
    ArrayList<String> listaPalabras;
    EditText edtIn;
    TextView txtSeisIntentos;
    String seisIntentos;
    final String MENSAJE = "Letra ingresada: ";
    TextView txtIntentos;
    String intentos;
    final String GANADOR = "Gano";
    final String PERDEROR = "Perdio";
    Image imagen0; //cabeza
    ImageView hang0;
    Image imagen1; //torso
    ImageView hang1;
    Image imagen2; //brazo derecho
    ImageView hang2;
    Image imagen3; //brazo izquierdo
    ImageView hang3;
    Image imagen4; //pierna izquierda
    ImageView hang4;
    Image imagen5; //pierna derecha
    ImageView hang5;
    Integer count = 0;
    Instant instantStart, instantEnd;

    Animation rotateAnimation;
    Animation scaleAnimation;
    Animation scaleAndRotateAnimation;
    ArrayList<String> estadisticasAhorcado = new ArrayList();
    public void revelarLetraPalabra(char letra){
        int indiceLetra  = palabraIncoS.indexOf(letra);
        //itera si el indice es positivo o 0
        while(indiceLetra >= 0){
            letraPalabraDesplegada[indiceLetra] = palabraIncoS.charAt(indiceLetra);
            indiceLetra = palabraIncoS.indexOf(letra, indiceLetra + 1);
        }
        //actualiza el string
        palabraDesplegada = String.valueOf(letraPalabraDesplegada);
    }

    public void despliegaPalabraPantalla(){
        String palabraFormada = "";
        for(char caracter : letraPalabraDesplegada){
            palabraFormada += caracter + " ";
        }
        palabraIncog.setText(palabraFormada);
    }
    public void iniciaJuego(){
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            instantStart = Instant.now();
        }
        //mezcla las matrices
        Collections.shuffle(listaPalabras);
        palabraIncoS = listaPalabras.get(0);
        listaPalabras.remove(0);
        //inicia el arreglo de letras
        letraPalabraDesplegada = palabraIncoS.toCharArray();
        //disminuye
        for(int i = 1; i < letraPalabraDesplegada.length - 1; i++){
            letraPalabraDesplegada[i] = '_';
        }
        //revelar ocurrencias del primer caracter o letra
        revelarLetraPalabra(letraPalabraDesplegada[0]);

        //revelar todas las ocurrencias del ultimo caracter o letra
        revelarLetraPalabra(letraPalabraDesplegada[letraPalabraDesplegada.length - 1]);

        //inicializar un string de este arreglo de letras para propositos de busqueda
        palabraDesplegada = String.valueOf(letraPalabraDesplegada);

        //desplegar palabra
        despliegaPalabraPantalla();

        //Entrada
        edtIn.setText("");

        //seis intentos
        //inicializa el string para las letras intentadas con espacio
        seisIntentos = " ";

        //mostrar en pantalla
        txtSeisIntentos.setText(MENSAJE);

        //intentos sobrantes
        //inicializa los intentos sobrantes
        intentos = "XXXXXX";
        txtIntentos.setText(intentos);
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        getSupportActionBar().setTitle("TeleAhorcado");
        listaPalabras = new ArrayList<String>();
        palabraIncog = (TextView) findViewById(R.id.palabraInc);
        txtSeisIntentos = (TextView) findViewById(R.id.textSeisIntentos);
        txtIntentos = (TextView) findViewById(R.id.txtIntentos);
        edtIn = (EditText) findViewById(R.id.edtInput);
        //ingresar palabras para la lista
        listaPalabras.add("REDES");
        listaPalabras.add("PROPA");
        listaPalabras.add("PUCP");
        listaPalabras.add("TELITO");
        listaPalabras.add("TELECO");
        listaPalabras.add("BATI");
        iniciaJuego();
        //configurar el text changed listener para el editor de texto
        edtIn.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                //si hay una letra en la entrada
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
        //si la letra esta dentro de la palabra a adivinar
        if(palabraIncoS.indexOf(letra) >= 0){
            //letra aun no desplegada
            if(palabraDesplegada.indexOf(letra) < 0){
                //reemplazo con la letra
                revelarLetraPalabra(letra);
                //actualiza los cambios en pantalla
                despliegaPalabraPantalla();
                //ver si se gano el juego
                if(!palabraDesplegada.contains("_")){
                    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
                        instantEnd = Instant.now();
                    }

                    if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.O) {
                        long seconds = instantEnd.getEpochSecond() - instantStart.getEpochSecond();
                        String tiempo = Math.round((seconds/60.0)*100.0)/100.0 + " minutos";
                        String estadistica = "Juego " + (estadisticasAhorcado.size() + 1) + "Ganó : Terminó en " + tiempo;
                        estadisticasAhorcado.add(estadistica);
                        txtIntentos.setText(GANADOR + ": Termino en "+tiempo);
                    }
                }
            }
        }
        else if(!intentos.isEmpty()){
            {
                //revelará la imagen una por una
                int count;
                for (count = 0; count < intentos.length(); count++) {
                    desplegarImagen(count);
                }
                intentos = intentos.substring(0, intentos.length()-1);
                txtIntentos.setText(intentos);

            }if(intentos.isEmpty()){

                hang0 = (ImageView) findViewById(R.id.hang0);
                hang1 = (ImageView) findViewById(R.id.hang1);
                hang2 = (ImageView) findViewById(R.id.hang2);
                hang3 = (ImageView) findViewById(R.id.hang3);
                hang4 = (ImageView) findViewById(R.id.hang4);
                hang5 = (ImageView) findViewById(R.id.hang5);
                hang0.setVisibility(View.VISIBLE);
                hang1.setVisibility(View.VISIBLE);
                hang2.setVisibility(View.VISIBLE);
                hang3.setVisibility(View.VISIBLE);
                hang4.setVisibility(View.VISIBLE);
                hang5.setVisibility(View.VISIBLE);
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
                    instantEnd = Instant.now();
                }

                if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.O) {
                    long seconds = instantEnd.getEpochSecond() - instantStart.getEpochSecond();
                    String tiempo = Math.round((seconds / 60.0) * 100.0) / 100.0 + " minutos";
                    String estadistica = "Juego " + (estadisticasAhorcado.size() + 1) + " Perdió : Terminó en " + tiempo;
                    estadisticasAhorcado.add(estadistica);
                    txtIntentos.setText(PERDEROR + ": Termino en "+tiempo);
                    palabraIncog.setText(palabraIncoS);
                }
            }
        }
        if(seisIntentos.indexOf(letra) > 0){
            seisIntentos += letra + ", ";
            String mensajeDesplegado = MENSAJE + seisIntentos;
            txtSeisIntentos.setText(mensajeDesplegado);
        }
    }

    public void desplegarImagen(int count) {
        //algunos intentos
        switch (count) {
            case 0:
                hang0 = (ImageView) findViewById(R.id.hang0);
                hang0.setVisibility(View.VISIBLE);
                break;
            case 1:
                hang1 = (ImageView) findViewById(R.id.hang1);
                hang1.setVisibility(View.VISIBLE);
                break;
            case 2:
                hang2 = (ImageView) findViewById(R.id.hang2);
                hang2.setVisibility(View.VISIBLE);
                break;
            case 3:
                hang3 = (ImageView) findViewById(R.id.hang3);
                hang3.setVisibility(View.VISIBLE);
                break;
            case 4:
                hang4 = (ImageView) findViewById(R.id.hang4);
                hang4.setVisibility(View.VISIBLE);
                break;
        }
    }



   public void reiniciaJuego(View view){
        if(!intentos.isEmpty()){
            String statitiscs = "Juego " +(estadisticasAhorcado.size() + 1) +" : "+ "Canceló";
            estadisticasAhorcado.add(statitiscs);
        }
        iniciaJuego();
    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main_act,menu);
        return true;
    }

    public boolean onOptionsItemSelected(@NonNull MenuItem item){
        if(item.getItemId() == R.id.stadistics){
            Intent intent = new Intent(MainActivity.this, StatsActivity.class);
            intent.putExtra("estadisticas",estadisticasAhorcado);
            startActivity(intent);
        }
        return super.onOptionsItemSelected(item);
    }

}