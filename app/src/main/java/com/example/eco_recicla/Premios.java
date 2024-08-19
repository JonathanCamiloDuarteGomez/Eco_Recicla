package com.example.eco_recicla;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;

import com.example.eco_recicla.back.UserManager;
import com.example.eco_recicla.back.Usuario;

import java.util.ArrayList;
import java.util.List;

public class Premios extends AppCompatActivity {

    private ImageButton imageButtonHome;
    private Button buttonCanjear;
    private TextView textViewXCoins;
    private TextView textView1;
    private TextView textView2;
    private TextView textView3;
    private List<String> productos = new ArrayList<>();
    private Integer coins = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_premios);
        imageButtonHome = findViewById(R.id.imageButtonHome);
        buttonCanjear = findViewById(R.id.Button_canjear);
        textViewXCoins = findViewById(R.id.textViewXCoins);
        textView1 = findViewById(R.id.textView1);
        textView2 = findViewById(R.id.textView2);
        textView3 = findViewById(R.id.textView3);

        //Traer datos del usuario
        UserManager UserManager = new UserManager(this);
        Usuario usuario = UserManager.getUsuario();

        if (usuario != null) {
            textViewXCoins.setText("Tiene " +usuario.getCoins()+" Coins Disponibles");
        }
        //escuchar los textView
        textViewListener(textView1);
        textViewListener(textView2);
        textViewListener(textView3);

        //funcionalidad de canjear.
        buttonCanjear.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(usuario.getCoins() < coins){
                    Toast.makeText(Premios.this, "No tienes suficientes coins", Toast.LENGTH_LONG).show();
                    Intent next = new Intent(Premios.this, MenuPrincipal.class);
                    startActivity(next);
                    finish();
                }
                if(usuario.getCoins() >= coins){
                    Toast.makeText(Premios.this, "Productos canjeados : "+productos +"\n Total de coins Canjeados : "+coins, Toast.LENGTH_LONG).show();
                    usuario.setCoins(usuario.getCoins()-coins);
                    UserManager.updateCoins(usuario);
                    Intent next = new Intent(Premios.this, MenuPrincipal.class);
                    startActivity(next);
                    finish();
                }
                if(productos.isEmpty() || coins == 0){
                    Toast.makeText(Premios.this, "No has canjeado ningun producto", Toast.LENGTH_LONG).show();
                    Intent next = new Intent(Premios.this, MenuPrincipal.class);
                    startActivity(next);
                    finish();
                }
            }
        });


        imageButtonHome.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent next = new Intent(Premios.this, MenuPrincipal.class);
                startActivity(next);
            }
        });
    }
    //funcion para escuchar los textView
    private void textViewListener(TextView textView){

        textView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(textView.getText().toString().contains("EARBUDS")){
                    productos.add("EARBUDS");
                    coins += 1000;
                    Toast.makeText(Premios.this, "EARBUDS agregado al carrito", Toast.LENGTH_SHORT).show();
                    return;
                }else if(textView.getText().toString().contains("Smartwatch")){
                    productos.add("Smartwatch");
                    coins += 10000;
                    Toast.makeText(Premios.this, "Smartwatch agregado al carrito", Toast.LENGTH_SHORT).show();
                    return;
                }else if(textView.getText().toString().contains("Entradas a cine")){
                    productos.add("Entradas a cine");
                    coins += 200;
                    Toast.makeText(Premios.this, "Entradas a cine agregado al carrito", Toast.LENGTH_SHORT).show();
                    return;
                } else if (!textView.getText().toString().contains("Smartwatch") || !textView.getText().toString().contains("EARBUDS") || !textView.getText().toString().contains("Entradas a cine") ) {
                    Toast.makeText(Premios.this, "El producto no esta disponible", Toast.LENGTH_SHORT).show();
                }
            }
        });
  }
}