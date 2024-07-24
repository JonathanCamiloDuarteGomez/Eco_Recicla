package com.example.eco_recicla;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Spinner;
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
    private Spinner spinnerSeleccionDeEmpresa;
    private Spinner spinnerPlacaVehiculo;
    private Spinner spinnerConductor;
    private String[] nombreEmpresas = new String[3];
    private String[] nombreVehiculos = new String[3];
    private String[] nombreConductores = new String[3];

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_gestion_de_reciclaje_agregar_solicitud_de_recogida);
        etDate = findViewById(R.id.etDate);
        btnDate = findViewById(R.id.datePickerButton);
        btnIrAGestionDeReciclajeAgregarObjeto = findViewById(R.id.btnIrAGestionDeReciclajeAgregarObjeto);
        btnCancelar = findViewById(R.id.btnCancelar);
        spinnerSeleccionDeEmpresa = findViewById(R.id.spinnerSeleccionDeEmpresa);
        nombreEmpresas[0] = "Seleccione Empresa";nombreEmpresas[1] = "Empresa 1";nombreEmpresas[2] = "Empresa 2";
        spinnerPlacaVehiculo = findViewById(R.id.spinnerPlacaVehiculo);
        nombreVehiculos[0] = "Seleccione Vehículo";nombreVehiculos[1] = "Vehículo 1";nombreVehiculos[2] = "Vehículo 2";
        spinnerConductor = findViewById(R.id.spinnerConductor);
        nombreConductores[0] = "Seleccione Conductor";nombreConductores[1] = "Conductor 1";nombreConductores[2] = "Conductor 2";


        // Navegación entre pantallas
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
        btnDate.setOnClickListener(v -> showDatePickerDialog());

        Button timePickerButton = findViewById(R.id.timePickerButton);
        timePickerButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showTimePickerDialog();
            }
        });

        // Configurar el spinnerSeleccionDeEmpresa
        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, nombreEmpresas);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerSeleccionDeEmpresa.setAdapter(adapter);

        // Configurar el listener del spinner
        spinnerSeleccionDeEmpresa.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                if (position != 0) {
                    obtenerInformacionSegunSpinner();
                }
            }
            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                obtenerInformacionSegunSpinner();
            }
        });
        // Configurar el spinnerPlacaVehiculo
        ArrayAdapter<String> adapter1 = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, nombreVehiculos);
        adapter1.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerPlacaVehiculo.setAdapter(adapter1);

        //configuracion del listener spinnerPlacaVehiculo
        spinnerPlacaVehiculo.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                if (position != 0) {
                    obtenerInformacionSegunSpinner();
                };
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                obtenerInformacionSegunSpinner();
            }
        });

        //configurar el spinnerConductor
        ArrayAdapter<String> adapter2 = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, nombreConductores);
        adapter2.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerConductor.setAdapter(adapter2);
        //configuracion del listener spinnerConductor
        spinnerConductor.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                if (position != 0) {
                    obtenerInformacionSegunSpinner();
                };
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                obtenerInformacionSegunSpinner();
            }
        });



    }

    // Funciones de los picker
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
        dateTextView.setText(dateMessage);
    }

    public void processTimePickerResult(int hourOfDay, int minute) {
        String hour_string = Integer.toString(hourOfDay);
        String minute_string = Integer.toString(minute);
        String timeMessage = (hour_string + ":" + minute_string);
        TextView timeTextView = findViewById(R.id.timeTextView);
        timeTextView.setText(timeMessage);
    }

    // Función para obtener la información basada en el spinner activo
    private void obtenerInformacionSegunSpinner() {
        String empresaSeleccionada = spinnerSeleccionDeEmpresa.getSelectedItem().toString();
        String vehiculoSeleccionado = spinnerPlacaVehiculo.getSelectedItem().toString();
        String conductorSeleccionado = spinnerConductor.getSelectedItem().toString();

        // Verificar cuál spinner tiene la información seleccionada
        if (!empresaSeleccionada.equals("Seleccione Empresa")) {
            Log.i("Info Empresa", empresaSeleccionada);
        } else if (!vehiculoSeleccionado.equals("Seleccione Vehículo")) {
            Log.i("Info Vehículo", vehiculoSeleccionado);
        } else if (!conductorSeleccionado.equals("Seleccione Conductor")) {
            Log.i("Info Conductor", conductorSeleccionado);
        } else {
            Log.i("Info", "No se ha seleccionado ninguna opción válida");
        }
    }
}
