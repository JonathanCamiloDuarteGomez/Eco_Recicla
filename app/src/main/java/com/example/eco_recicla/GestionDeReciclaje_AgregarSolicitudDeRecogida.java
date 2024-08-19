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
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.DialogFragment;

import com.example.eco_recicla.PickerFragment.DatePickerFragment;
import com.example.eco_recicla.PickerFragment.TimePickerFragment;
import com.example.eco_recicla.back.ListadoDeProductos;

public class GestionDeReciclaje_AgregarSolicitudDeRecogida extends AppCompatActivity {
    private TextView dateTextView;
    private TextView timeTextView;
    private Button btnDate;
    private Button btnIrAGestionDeReciclajeAgregarObjeto;
    private Button btnIrACreacionConfirmacion;
    private ImageButton btnCancelar;
    private Spinner spinnerSeleccionDeEmpresa;
    private Spinner spinnerPlacaVehiculo;
    private Spinner spinnerConductor;
    private String[] nombreEmpresas = new String[3];
    private String[] nombreVehiculos = new String[3];
    private String[] nombreConductores = new String[3];

    public String empresaSeleccionada;
    public String vehiculoSeleccionado;
    public String conductorSeleccionado;
    public String date;
    public String time;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_gestion_de_reciclaje_agregar_solicitud_de_recogida);

        dateTextView = findViewById(R.id.etDate);
        timeTextView = findViewById(R.id.timeTextView);
        btnDate = findViewById(R.id.datePickerButton);
        btnIrAGestionDeReciclajeAgregarObjeto = findViewById(R.id.btnIrAGestionDeReciclajeAgregarObjeto);
        btnCancelar = findViewById(R.id.btnCancelar);
        btnIrACreacionConfirmacion = findViewById(R.id.btnIrACreacionConfirmacion);
        spinnerSeleccionDeEmpresa = findViewById(R.id.spinnerSeleccionDeEmpresa);
        nombreEmpresas[0] = "Seleccione Empresa";nombreEmpresas[1] = "Recicloplas S.A";nombreEmpresas[2] = "Lito S.A.S";
        spinnerPlacaVehiculo = findViewById(R.id.spinnerPlacaVehiculo);
        nombreVehiculos[0] = "Seleccione Vehículo";nombreVehiculos[1] = " CVD 152";nombreVehiculos[2] = " DFT 256";
        spinnerConductor = findViewById(R.id.spinnerConductor);
        nombreConductores[0] = "Seleccione Conductor";nombreConductores[1] = " Maria Fernandez";nombreConductores[2] = " Pedro Linares";
        empresaSeleccionada = "";vehiculoSeleccionado = "";conductorSeleccionado = "";date = "";time = "";

        //Obtener el Intent que inició esta actividad
        Intent intent = getIntent();
        // Obtener los datos del Intent
        ListadoDeProductos listadoDeProductos = (ListadoDeProductos) intent.getSerializableExtra("listadoDeProductos");
        String direccion = intent.getStringExtra("direccion");


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
        btnIrACreacionConfirmacion.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(obtenerInformacionSegunSpinner()==true){
                    Intent next = new Intent(GestionDeReciclaje_AgregarSolicitudDeRecogida.this, GestionDeReciclaje_CreacionYConfirmacionDeRecogida.class);
                    //enviar datos capturados a la siguiente pantalla
                    //esta pantalla
                    next.putExtra("date", date.toString());
                    next.putExtra("time", time.toString());
                    next.putExtra("empresa", empresaSeleccionada.toString());
                    next.putExtra("conductor", conductorSeleccionado.toString());
                    next.putExtra("vehiculo", vehiculoSeleccionado.toString());
                    //pantalla de gestion de reciclaje agregar objeto
                    next.putExtra("direccion", direccion.toString());
                    next.putExtra("listadoDeProductos", listadoDeProductos);
                    startActivity(next);
                    finish();
                }else{
                    Toast.makeText(GestionDeReciclaje_AgregarSolicitudDeRecogida.this, "Datos ingresados incorrectamente", Toast.LENGTH_SHORT).show();
                }

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

        dateTextView.setText(dateMessage);
    }

    public void processTimePickerResult(int hourOfDay, int minute) {
        String hour_string = Integer.toString(hourOfDay);
        String minute_string = Integer.toString(minute);
        String timeMessage = (hour_string + ":" + minute_string);
        timeTextView.setText(timeMessage);
    }

    // Función para obtener la información basada en el spinner activo
    private boolean obtenerInformacionSegunSpinner() {
        String empresaSeleccionada = spinnerSeleccionDeEmpresa.getSelectedItem().toString();
        String vehiculoSeleccionado = spinnerPlacaVehiculo.getSelectedItem().toString();
        String conductorSeleccionado = spinnerConductor.getSelectedItem().toString();

        // Verificar si se ha seleccionado alguna opción válida
        boolean isTimeAndDateValid = !timeTextView.getText().toString().equals("") && !dateTextView.getText().toString().equals("");
        boolean isEmpresaSeleccionada = !empresaSeleccionada.equals("Seleccione Empresa");
        boolean isVehiculoSeleccionado = !vehiculoSeleccionado.equals("Seleccione Vehículo");
        boolean isConductorSeleccionado = !conductorSeleccionado.equals("Seleccione Conductor");

        if (isTimeAndDateValid) {
            this.time = timeTextView.getText().toString();
            this.date = dateTextView.getText().toString();
            Log.i("Info Tiempo", this.time + "\nFecha: " + this.date);
        } else {
            Log.i("Info Tiempo y Fecha", "Tiempo y fecha no seleccionados");
        }

        if (isEmpresaSeleccionada) {
            this.empresaSeleccionada = empresaSeleccionada;
            Log.i("Info Empresa", this.empresaSeleccionada);
        } else {
            Log.i("Info Empresa", "Empresa no seleccionada");
        }

        if (isVehiculoSeleccionado) {
            this.vehiculoSeleccionado = vehiculoSeleccionado;
            Log.i("Info Vehículo", this.vehiculoSeleccionado);
        } else {
            Log.i("Info Vehículo", "Vehículo no seleccionado");
        }

        if (isConductorSeleccionado) {
            this.conductorSeleccionado = conductorSeleccionado;
            Log.i("Info Conductor", this.conductorSeleccionado);
        } else {
            Log.i("Info Conductor", "Conductor no seleccionado");
        }

        // Verificar que todas las condiciones se cumplan
        return isTimeAndDateValid && isEmpresaSeleccionada && isVehiculoSeleccionado && isConductorSeleccionado;
    }

}