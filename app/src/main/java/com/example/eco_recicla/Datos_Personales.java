package com.example.eco_recicla;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import com.example.eco_recicla.back.UserManager;
import com.example.eco_recicla.back.Usuario;

public class Datos_Personales extends AppCompatActivity {

    private EditText telefono,adress,adress2;
    private TextView cc,nombre,apellido,edad,email;
    private Button btnActualizar;
    private Button btnIrAmenuInformacionUsuario;
    private ImageButton imageButtonHome;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_datos_personales);

        cc = (TextView) findViewById(R.id.TextViewNumIdentificacion);
        nombre = (TextView) findViewById(R.id.TextViewNombre);
        apellido = (TextView) findViewById(R.id.TextViewApellido);
        edad = (TextView) findViewById(R.id.TextViewEdad);
        email = (TextView) findViewById(R.id.EditTextEmail1);
        telefono = findViewById(R.id.EditTextTelefono1);
        adress =  findViewById(R.id.EditTextAdress1);
        adress2 =  findViewById(R.id.EditTextAdress2);

        btnActualizar = findViewById(R.id.btnActualizar);
        btnIrAmenuInformacionUsuario = findViewById(R.id.btnIrAmenuInformacionUsuario);
        imageButtonHome = findViewById(R.id.imageButtonHome);


        //cambiar de pantalla
        cambioDePantalla(imageButtonHome , MenuPrincipal.class);

        btnIrAmenuInformacionUsuario.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent next = new Intent(Datos_Personales.this, MenuInformacionUsuario.class);
                startActivity(next);
                finish();
            }
        });
        //traer los datos de Shell preferences del usuario segun el email
        //Agregar factura al usuario
        UserManager userManager = new UserManager(this);
        //obtener el usuario
        Usuario usuario = userManager.getUsuario();
        //mostrar los datos personales del usuario
        mostrarDatosPersonales(usuario,cc,nombre,apellido,edad,email,telefono,adress,adress2);

        //envia conkjunto de datos
        btnActualizar.setOnClickListener(view -> PersonalData(userManager,usuario));



    }
    private void PersonalData(UserManager userManager, Usuario usuario){

        if(telefono.getText().toString().isEmpty() || adress.getText().toString().isEmpty() || adress2.getText().toString().isEmpty()){
            Toast.makeText(this,"Complete los Campos",Toast.LENGTH_SHORT).show();
            if(adress.equals(adress2)){
                Toast.makeText(this,"Las direcciones deben ser diferentes",Toast.LENGTH_SHORT).show();
            }
            return;
        }else{
            //actualizar los datos del usuario
            usuario.setTelefono(telefono.getText().toString());
            usuario.setDireccion(adress.getText().toString());
            usuario.setDireccionAlternativa(adress2.getText().toString());
            userManager.updateUser(usuario);
            Toast.makeText(this,"Datos Actualizados",Toast.LENGTH_SHORT).show();
            return;
        }

    }
    private void cambioDePantalla(ImageButton nombreBtn, final Class<?> clase) {
        nombreBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent next = new Intent(Datos_Personales.this, clase);
                startActivity(next);
                finish();
            }
        });

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


    //metodo para mostrar los datos personales del usuario
    private void mostrarDatosPersonales(Usuario usuario, TextView cc, TextView nombre, TextView apellido, TextView edad, TextView email, TextView telefono, TextView adress, TextView adress2){

        cc.setText(usuario.getIdUsuario().toString());
        nombre.setText(usuario.getNombre());
        apellido.setText(usuario.getApellido());
        edad.setText(usuario.getEdad().toString());
        email.setText(usuario.getEmail());
        telefono.setText(usuario.getTelefono());
        adress.setText(usuario.getDireccion());
        adress2.setText(usuario.getDireccionAlternativa());

    }

     /*
        //aquie en ves de crear un nuevo archivo de preferences, toca traer la informacion del usuario y con un set
        //modificar la informacion del usuario que se puede modificar en los editText

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

        finish();*/



