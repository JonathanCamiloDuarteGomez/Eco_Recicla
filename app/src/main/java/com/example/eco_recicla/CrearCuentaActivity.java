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

import java.util.regex.Pattern;

public class CrearCuentaActivity extends AppCompatActivity {
    // Declaración de variables para los elementos de la interfaz de usuario
    private EditText editTextEmail, editTextPassword, editTextName, editTextApellido, editTextEdad, editTextTelefono, editTextAdress1, editTextAdress2, editTextIdentificacion, editTextPassword1;
    private Button btnIrAloginRegistrar;
    private UserManager userManager;
    private CheckBox checkBoxTerms;
    private Button btnRegistrar;
    private String tipoDeDocumento;

    // Expresión regular para validar el formato de una dirección en Bogotá
    private static final String BOGOTA_ADDRESS_REGEX = "^(Calle|Carrera|Avenida|Transversal|Diagonal|Circular)\\s+\\d+[A-Za-z]?(\\s+(Bis|Sur|Este|Oeste))?\\s+#\\s+\\d+-\\d+(\\s+[A-Za-z]+)?$";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_crear_cuenta);


        // Inicialización de los elementos de la interfaz de usuario
        editTextIdentificacion = findViewById(R.id.EditTextIdentificacion1);
        editTextName = findViewById(R.id.EditTextName);
        editTextApellido = findViewById(R.id.EditTextApellido);
        editTextEdad = findViewById(R.id.EditTextEdad);
        editTextEmail = findViewById(R.id.EditTextEmail);
        editTextTelefono = findViewById(R.id.EditTextTelefono);
        editTextAdress1 = findViewById(R.id.EditTextAdress1);
        editTextAdress2 = findViewById(R.id.EditTextAdress2);

        editTextPassword = findViewById(R.id.EditTextContrasena);
        editTextPassword1 = findViewById(R.id.EditTextContras_Repeticion1);

        btnIrAloginRegistrar = findViewById(R.id.btnIrAloginRegistrar);
        checkBoxTerms = findViewById(R.id.checkBoxTratoDatos);
        btnRegistrar = findViewById(R.id.btnRegistrar);



        userManager = new UserManager(this); // Inicialización de la instancia de UserManager

        // Inicialización del Spinner
        Spinner spinnerTipoDeIdentificación = findViewById(R.id.spinnerTipoDeIdentificación);

        // Configuración de las opciones del Spinner
        String[] tiposDD = new String[TiposDeDocumentos.values().length + 1];
        tiposDD[0] = "Seleccione Tipo de documento";
        for (int i = 0; i < TiposDeDocumentos.values().length; i++) {
            tiposDD[i + 1] = TiposDeDocumentos.values()[i].getNombre();
        }

        // Configuración del adaptador para el Spinner
        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, tiposDD);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerTipoDeIdentificación.setAdapter(adapter);

        // Configuración del listener del Spinner
        spinnerTipoDeIdentificación.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                if (position == 0) {
                    // No se seleccionó ningún tipo de documento
                    Toast.makeText(CrearCuentaActivity.this, "Seleccione un tipo de documento", Toast.LENGTH_SHORT).show();
                } else {
                    TiposDeDocumentos tipo = TiposDeDocumentos.values()[position - 1];
                    tipoDeDocumento = tipo.getNombre();
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
                // Obtención de los valores ingresados por el usuario
                String identificacion = editTextIdentificacion.getText().toString();
                String name = editTextName.getText().toString();
                String apellido = editTextApellido.getText().toString();
                String edad = editTextEdad.getText().toString();
                String email = editTextEmail.getText().toString();
                String telefono = editTextTelefono.getText().toString();
                String adress1 = editTextAdress1.getText().toString();
                String adress2 = editTextAdress2.getText().toString();
                String password = editTextPassword.getText().toString();
                String password1 = editTextPassword1.getText().toString();



                // Validación de campos de entrada
                if(tipoDeDocumento==null || tipoDeDocumento.equals("Seleccione Tipo de documento")){
                    Toast.makeText(CrearCuentaActivity.this, "Seleccione un tipo de documento", Toast.LENGTH_SHORT).show();
                }else if (TextUtils.isEmpty(identificacion) || identificacion.length() < 5) {
                    Toast.makeText(CrearCuentaActivity.this, "Ingrese una identificación", Toast.LENGTH_SHORT).show();
                }else if (TextUtils.isEmpty(name)) {
                    Toast.makeText(CrearCuentaActivity.this, "Ingrese un nombre", Toast.LENGTH_SHORT).show();
                }else if (TextUtils.isEmpty(edad) || Integer.parseInt(edad) < 15) {
                    Toast.makeText(CrearCuentaActivity.this, "Ingrese una edad", Toast.LENGTH_SHORT).show();
                }else if (TextUtils.isEmpty(telefono)) {
                    Toast.makeText(CrearCuentaActivity.this, "Ingrese un teléfono", Toast.LENGTH_SHORT).show();
                }else if ((TextUtils.isEmpty(adress1) || !isValidBogotaAddress(adress1)) || (TextUtils.isEmpty(adress2) || !isValidBogotaAddress(adress2)))  {
                    Toast.makeText(CrearCuentaActivity.this, "Ingrese una dirección válida", Toast.LENGTH_SHORT).show();
                } else if (TextUtils.isEmpty(email)) {
                    Toast.makeText(CrearCuentaActivity.this, "Ingrese un Correo Electronico", Toast.LENGTH_SHORT).show();
                } else if (!isValidEmail(email)) {
                    Toast.makeText(CrearCuentaActivity.this, "Ingrese un Correo Electronico Valido", Toast.LENGTH_SHORT).show();
                } else if (TextUtils.isEmpty(password) || TextUtils.isEmpty(password1) || !password.equals(password1) ) {
                    Toast.makeText(CrearCuentaActivity.this, "Ingrese una contraseña válida", Toast.LENGTH_SHORT).show();
                } else if (TextUtils.isEmpty(apellido)) {
                    Toast.makeText(CrearCuentaActivity.this, "Ingrese un apellido", Toast.LENGTH_SHORT).show();
                } else if (!checkBoxTerms.isChecked()) {
                    Toast.makeText(CrearCuentaActivity.this, "Debe aceptar terminos y condiciones", Toast.LENGTH_SHORT).show();
                } else {
                    registrarUsuario(tipoDeDocumento, identificacion, name, apellido, edad, email, telefono, adress1, adress2, password, password1);
                }
            }
        });
    }

    // Método para cambiar de pantalla
    private void cambioDePantalla(Button nombreBtn, final Class<?> clase) {
        nombreBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String email = editTextEmail.getText().toString();
                String password = editTextPassword.getText().toString();


                if(TextUtils.isEmpty(email)){
                    Toast.makeText(CrearCuentaActivity.this,"Ingrese un Correo Electronico",Toast.LENGTH_SHORT).show();
                } else if (!isValidEmail(email)) {
                    Toast.makeText(CrearCuentaActivity.this,"Ingrese un Correo Electronico Valido",Toast.LENGTH_SHORT).show();
                } else if (TextUtils.isEmpty(password)) {
                    Toast.makeText(CrearCuentaActivity.this,"Ingrese una contraseña",Toast.LENGTH_SHORT).show();
                } else if (!checkBoxTerms.isChecked()) {
                    Toast.makeText(CrearCuentaActivity.this,"Debe aceptar terminos y condiciones",Toast.LENGTH_SHORT).show();
                }else {
                    registrarUsuario(email,password);
                }

                Intent next = new Intent(CrearCuentaActivity.this, clase);
                startActivity(next);
            }

        });
    }
        private boolean isValidEmail(CharSequence target) {
            return (!TextUtils.isEmpty(target) && Patterns.EMAIL_ADDRESS.matcher(target).matches());
        }

        private void registrarUsuario(String email, String password) {
            userManager.RegisterUser(email, password);
            Toast.makeText(CrearCuentaActivity.this, "Registro exitoso", Toast.LENGTH_SHORT).show();
            finish();
        }

    // Método para validar el formato del correo electrónico
    private boolean isValidEmail(CharSequence target) {
        return (!TextUtils.isEmpty(target) && Patterns.EMAIL_ADDRESS.matcher(target).matches());
    }
    //para validar direccion
    public boolean isValidBogotaAddress(CharSequence target) {
        if (target == null || target.length() == 0) {
            return false;
        }
        return Pattern.compile(BOGOTA_ADDRESS_REGEX, Pattern.CASE_INSENSITIVE).matcher(target).matches();
    }

    // Método para registrar un usuario y mostrar un mensaje de éxito
    private void registrarUsuario(String tipoDeDocumento, String identificacion, String name, String apellido,String edad, String email,String telefono, String adress1, String adress2, String password, String password1) {
        TiposDeDocumentos tipoDocumento = TiposDeDocumentos.valueOf(tipoDeDocumento.toString());
        // Creación de un nuevo usuario con los datos ingresados
        Usuario usuario = new Usuario(tipoDocumento, Integer.parseInt(identificacion), name,  apellido, Integer.parseInt(edad), email, telefono, adress1, adress2, password, password1);
        // Registro del nuevo usuario
        userManager.registerUser(usuario);
        Toast.makeText(CrearCuentaActivity.this, "Registro exitoso", Toast.LENGTH_SHORT).show();
        finish(); // Finaliza la actividad actual
    }
}

