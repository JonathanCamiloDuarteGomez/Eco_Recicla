package com.example.eco_recicla;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;

import androidx.appcompat.app.AppCompatActivity;

import com.example.eco_recicla.Enums.TiposDeDocumentos;

public class CrearCuentaActivity extends AppCompatActivity {
    //asignacion de variables
    Button btnIrAloginRegistrar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_crear_cuenta);

        btnIrAloginRegistrar = findViewById(R.id.btnIrAloginRegistrar);

        Spinner spinnerTipoDeIdentificación = findViewById(R.id.spinnerTipoDeIdentificación);
        //opciones del spinner
        String[] problemas = new String[TiposDeDocumentos.values().length+1];
<<<<<<< HEAD
        problemas[0] = "Seleccione la Problematica";
=======
        problemas[0] = "Seleccione Tipo de documento";
>>>>>>> c1ca808d4fcd8c882b4233beef56e3f12c9ffffa
        for (int i=0; i<TiposDeDocumentos.values().length; i++){//mostrar uno a uno
            problemas[i+1] = TiposDeDocumentos.values()[i].getNombre();
        }

        //configurar el Spiner con los nombres de los problemas
        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, problemas);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerTipoDeIdentificación.setAdapter(adapter);
        //configurar el listener del spinner
        spinnerTipoDeIdentificación.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                if(position==0){
                    Log.i("Tipo de documento", "Tipo de documento");
                }else {
                    TiposDeDocumentos tipo = TiposDeDocumentos.values()[position-1];
                    Log.i("Tipo de documento", tipo.getNombre());

                }

            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                Log.i("Tipo de documento", "Seleccione Tipo de documento");
            }
        });


        //llamado de funciones

        cambioDePantalla(btnIrAloginRegistrar, MainActivity.class); //en la siguiente funcion se ingresa el bton y la clase hacia donde se va

        //faltan botones de registrar y ir a login codigo
    }
    private void cambioDePantalla(Button nombreBtn, final Class<?> clase) {
        nombreBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent next = new Intent(CrearCuentaActivity.this, clase);
                startActivity(next);
            }
        });
    }

}
