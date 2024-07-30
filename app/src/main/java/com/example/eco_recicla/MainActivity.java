package com.example.eco_recicla;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;


import androidx.appcompat.app.AppCompatActivity;

import com.example.eco_recicla.PickerFragment.UserManager;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;
import java.util.Random;

public class MainActivity extends AppCompatActivity {
    //variables
    private Button btnInicioSesion;
    private Button btnRegistrar;
    private EditText editTextemail;
    private EditText getEditTextPassword;
    private UserManager userManager;

    private String[] Tips= {
            "Ahorra energía apagando las luces que no necesitas.",
            "Desconecta los electrodomésticos que no estás usando.",
            "Utiliza bombillas LED para reducir el consumo de energía.",
            "Apaga tu computadora cuando no la estés usando.",
            "Aprovecha la luz natural durante el día.",
            "Mantén el refrigerador bien cerrado para ahorrar energía.",
            "Usa el aire acondicionado con moderación.",
            "Lava tu ropa con agua fría para ahorrar energía.",
            "Revisa tus aparatos eléctricos para evitar fugas de energía.",
            "Plancha tu ropa en una sola sesión para ahorrar energía."
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //asignacion de variables
        btnInicioSesion = findViewById(R.id.btnInicia_sesion);
        btnRegistrar = findViewById(R.id.btnRegistrar);

        editTextemail = findViewById(R.id.EditTextCorreo);
        getEditTextPassword = findViewById(R.id.EditTextPassword);

       // btnOlvContra = findViewById(R.id.btnOlvContra);
        SharedPreferences preferences = getSharedPreferences("savedDate",MODE_PRIVATE);
        String savedDate = preferences.getString("savedDate", "");
        SharedPreferences.Editor editor = preferences.edit();

        userManager = new UserManager(this);
        btnRegistrar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, CrearCuentaActivity.class);
                startActivity(intent);
            }

        });
        btnInicioSesion.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                String email = editTextemail.getText().toString();
                String password = getEditTextPassword.getText().toString();

                boolean isInitialized = preferences.getBoolean("isInitialized",false);

                if(!isInitialized){
                    for(int i=0; i < Tips.length;i ++){
                        editor.putString("tip"+ i,Tips[i]);
                    }
                    editor.putBoolean("isInitialized",true);
                    editor.apply();
                }

                //comparar la fecha actual con la guardada

                SimpleDateFormat sdf = new SimpleDateFormat("EEEE,d 'de' MMMM 'de' yyyy", new Locale("es", "CO"));
                String currentDate = sdf.format(new Date());
                if(!currentDate.equals(savedDate)){
                    Random random = new Random();
                    int randomIndex = random.nextInt(9);
                    String newTip = Tips[randomIndex];
                    //actualzar editor
                    editor.putString("currenTip",newTip);
                    editor.putString("saveDate",currentDate);
                    editor.apply();
                }

                if(userManager.loginUser(email,password)){
                    Intent intent = new Intent(MainActivity.this, Consejos.class);
                    startActivity(intent);
                    finish();
                }else {
                    Toast.makeText(MainActivity.this,"Email o Password Invalidos",Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

}