package com.example.eco_recicla;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class Datos_Personales extends AppCompatActivity {

    private EditText EditTextTelefono, EditTextAdress1, EditTextEmail1;
    private Button btnActualizar;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_datos_personales);

        EditTextTelefono = findViewById(R.id.EditTextTelefono);
        EditTextAdress1 = findViewById(R.id.EditTextAdress1);
        EditTextEmail1  = findViewById(R.id.EditTextEmail1);
        btnActualizar = findViewById(R.id.btnActualizar);

        //envia conkjunto de datos
        btnActualizar.setOnClickListener(view -> PersonalData());


    }
    private void PersonalData(){
        String email = EditTextEmail1.getText().toString();
        String telefono = EditTextTelefono.getText().toString();
        String adress = EditTextAdress1.getText().toString();

        if(email.isEmpty() || telefono.isEmpty() || adress.isEmpty()){
            Toast.makeText(this,"Complete los Campos",Toast.LENGTH_SHORT).show();
            return;
        }

        //guardar datos en sharepreference
        SharedPreferences preferences = getSharedPreferences("DataP",MODE_PRIVATE);
        //habilitar editor para modificar prefencias
        SharedPreferences.Editor editor = preferences.edit();

        int index = preferences.getInt("index",0);
        editor.putString("email" + index,email);
        editor.putString("telefono" + index,telefono);
        editor.putString("adress" + index,adress);

        editor.putInt("index",index+1);
        editor.apply();
        Toast.makeText(this,"Datos guardados",Toast.LENGTH_SHORT).show();

        finish();
    }



}
