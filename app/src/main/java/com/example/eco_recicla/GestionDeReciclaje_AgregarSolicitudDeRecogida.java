package com.example.eco_recicla;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.DialogFragment;

import com.example.eco_recicla.PickerFragment.DatePickerFragment;
import com.example.eco_recicla.PickerFragment.TimePickerFragment;

public class GestionDeReciclaje_AgregarSolicitudDeRecogida extends AppCompatActivity {
    private EditText etDate;
    private Button btnDate;
    private Button btnIrAGestionDeReciclajeAgregarObjeto;
    private ImageButton btnCancelar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_gestion_de_reciclaje_agregar_solicitud_de_recogida);
        etDate = findViewById(R.id.etDate);
        btnDate = findViewById(R.id.datePickerButton);
        btnIrAGestionDeReciclajeAgregarObjeto = findViewById(R.id.btnIrAGestionDeReciclajeAgregarObjeto);
        btnCancelar = findViewById(R.id.btnCancelar);

        //
        //navegacion entre pantallas
        btnIrAGestionDeReciclajeAgregarObjeto.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent next = new Intent(GestionDeReciclaje_AgregarSolicitudDeRecogida.this, GestionDeReciclajeAgregarObjeto.class);
                startActivity(next);
                finish();
            }
        });
        btnCancelar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent next = new Intent(GestionDeReciclaje_AgregarSolicitudDeRecogida.this, MenuPrincipal.class);
                startActivity(next);
                finish();
            }
        });

        // Configurar el OnClickListener para el botón de los picker
        btnDate.setOnClickListener(v ->
                showDatePickerDialog());

        Button timePickerButton = findViewById(R.id.timePickerButton);
        timePickerButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showTimePickerDialog();
            }
        });

    }

    //Funciones de los picker
    public void showDatePickerDialog() {
        DialogFragment newFragment = new DatePickerFragment();
        newFragment.show(getSupportFragmentManager(), "datePicker");
    }
    public void showTimePickerDialog() {
        DialogFragment newFragment = new TimePickerFragment();
        newFragment.show(getSupportFragmentManager(), "timePicker");
    }

    public void processDatePickerResult(int year, int month, int day) {
        String month_string = Integer.toString(month + 1);
        String day_string = Integer.toString(day);
        String year_string = Integer.toString(year);
        String dateMessage = (month_string + "/" + day_string + "/" + year_string);
        TextView dateTextView = findViewById(R.id.etDate);
        dateTextView.setText( dateMessage);
    }
    public void processTimePickerResult(int hourOfDay, int minute) {
        String hour_string = Integer.toString(hourOfDay);
        String minute_string = Integer.toString(minute);
        String timeMessage = (hour_string + ":" + minute_string);
        TextView timeTextView = findViewById(R.id.timeTextView);
        timeTextView.setText( timeMessage);
    }



}