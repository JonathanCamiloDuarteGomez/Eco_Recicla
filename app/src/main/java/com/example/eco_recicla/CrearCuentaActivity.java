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
import com.example.eco_recicla.PickerFragment.UserManager;

public class CrearCuentaActivity extends AppCompatActivity {
    private EditText editTextEmail, editTextPassword;
    //asignacion de variables
    private Button btnIrAloginRegistrar;
    private UserManager userManager;
    private CheckBox checkBoxTerms;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_crear_cuenta);
        editTextEmail = findViewById(R.id.EditTextIdentificacion1);
        editTextPassword = findViewById(R.id.EditTextContrasena);
        btnIrAloginRegistrar = findViewById(R.id.btnIrAloginRegistrar);
        checkBoxTerms = findViewById(R.id.checkBoxTratoDatos);

        userManager = new UserManager(this);


        Spinner spinnerTipoDeIdentificación = findViewById(R.id.spinnerTipoDeIdentificación);
        //opciones del spinner
        String[] problemas = new String[TiposDeDocumentos.values().length+1];
        problemas[0] = "Seleccione Tipo de documento";
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

}
