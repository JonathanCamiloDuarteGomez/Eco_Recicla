package com.example.eco_recicla;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.util.Patterns;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import com.example.eco_recicla.Enums.TiposDeDocumentos;
import com.example.eco_recicla.back.UserManager;
import com.example.eco_recicla.back.Usuario;

public class CrearCuentaActivity extends AppCompatActivity {
    // Declaración de variables para los elementos de la interfaz de usuario
    private EditText editTextEmail, editTextPassword, editTextName, editTextApellido, editTextEdad, editTextTelefono, editTextAdress1, editTextAdress2;
    private Button btnIrAloginRegistrar;
    private UserManager userManager;
    private CheckBox checkBoxTerms;
    private Button btnRegistrar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_crear_cuenta);

        // Inicialización de los elementos de la interfaz de usuario
        editTextEmail = findViewById(R.id.EditTextIdentificacion1);
        editTextPassword = findViewById(R.id.EditTextContrasena);
        editTextApellido = findViewById(R.id.EditTextApellido);
        btnIrAloginRegistrar = findViewById(R.id.btnIrAloginRegistrar);
        checkBoxTerms = findViewById(R.id.checkBoxTratoDatos);
        btnRegistrar = findViewById(R.id.btnRegistrar);

        userManager = new UserManager(this); // Inicialización de la instancia de UserManager

        // Inicialización del Spinner
        Spinner spinnerTipoDeIdentificación = findViewById(R.id.spinnerTipoDeIdentificación);

        // Configuración de las opciones del Spinner
        String[] problemas = new String[TiposDeDocumentos.values().length + 1];
        problemas[0] = "Seleccione Tipo de documento";
        for (int i = 0; i < TiposDeDocumentos.values().length; i++) {
            problemas[i + 1] = TiposDeDocumentos.values()[i].getNombre();
        }

        // Configuración del adaptador para el Spinner
        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, problemas);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerTipoDeIdentificación.setAdapter(adapter);

        // Configuración del listener del Spinner
        spinnerTipoDeIdentificación.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                if (position == 0) {
                    Log.i("Tipo de documento", "Tipo de documento");
                } else {
                    TiposDeDocumentos tipo = TiposDeDocumentos.values()[position - 1];
                    Log.i("Tipo de documento", tipo.getNombre());
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                Log.i("Tipo de documento", "Seleccione Tipo de documento");
            }
        });

        // Configuración del botón para cambiar de pantalla
        cambioDePantalla(btnIrAloginRegistrar, MainActivity.class); //en la siguiente funcion se ingresa el boton y la clase hacia donde se va

        // Configuración del botón para registrar usuario
        btnRegistrar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View w) {
                String email = editTextEmail.getText().toString();
                String password = editTextPassword.getText().toString();
                String apellido = editTextApellido.getText().toString();

                // Validación de campos de entrada
                if (TextUtils.isEmpty(email)) {
                    Toast.makeText(CrearCuentaActivity.this, "Ingrese un Correo Electronico", Toast.LENGTH_SHORT).show();
                } else if (!isValidEmail(email)) {
                    Toast.makeText(CrearCuentaActivity.this, "Ingrese un Correo Electronico Valido", Toast.LENGTH_SHORT).show();
                } else if (TextUtils.isEmpty(password)) {
                    Toast.makeText(CrearCuentaActivity.this, "Ingrese una contraseña", Toast.LENGTH_SHORT).show();
                } else if (TextUtils.isEmpty(apellido)) {
                    Toast.makeText(CrearCuentaActivity.this, "Ingrese un apellido", Toast.LENGTH_SHORT).show();
                } else if (!checkBoxTerms.isChecked()) {
                    Toast.makeText(CrearCuentaActivity.this, "Debe aceptar terminos y condiciones", Toast.LENGTH_SHORT).show();
                } else {
                    // Creación de un nuevo usuario con los datos ingresados
                    Usuario usuario = new Usuario(TiposDeDocumentos.CEDULA, 100, "Camilo", apellido, 20, email, "3137787041", "direccion", "direccionAlternativa", password);
                    // Registro del nuevo usuario
                    userManager.registerUser(usuario);
                }
            }
        });
    }

    // Método para cambiar de pantalla
    private void cambioDePantalla(Button nombreBtn, final Class<?> clase) {
        nombreBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent next = new Intent(CrearCuentaActivity.this, clase);
                startActivity(next);
            }
        });
    }

    // Método para validar el formato del correo electrónico
    private boolean isValidEmail(CharSequence target) {
        return (!TextUtils.isEmpty(target) && Patterns.EMAIL_ADDRESS.matcher(target).matches());
    }

    // Método para registrar un usuario y mostrar un mensaje de éxito
    private void registrarUsuario(Usuario usuario) {
        userManager.registerUser(usuario);
        Toast.makeText(CrearCuentaActivity.this, "Registro exitoso", Toast.LENGTH_SHORT).show();
        finish(); // Finaliza la actividad actual
    }
}
