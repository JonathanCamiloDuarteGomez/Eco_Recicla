package com.example.eco_recicla;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.eco_recicla.back.UserManager;
import com.example.eco_recicla.back.Usuario;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;
import java.util.Random;

public class MainActivity extends AppCompatActivity {
    private Button btnInicioSesion;
    private Button btnRegistrar;
    private EditText editTextemail;
    private EditText getEditTextPassword;
    private UserManager userManager;

    private String[] Tips = {
            "Reserva un espacio: Destina un lugar específico para depositar los materiales reciclables.",
            "Separa los residuos: Clasifica los materiales según su tipo: papel y cartón, vidrio, plástico, metal, orgánico.",
            "Limpia los envases: Antes de reciclar, enjuaga bien los envases para eliminar restos de comida o líquidos.",
            "Aplasta y compacta: Para ahorrar espacio, aplasta las botellas de plástico y las latas de aluminio.",
            "Reutiliza: Antes de reciclar, busca darle una segunda vida a algunos objetos, como frascos de vidrio o cajas de cartón.",
            "Reduce y reutiliza: Antes de reciclar, trata de reducir la cantidad de residuos que generas y reutiliza los objetos siempre que sea posible.",
            "Usa el aire acondicionado con moderación.",
            "Compra productos reciclados: Al adquirir nuevos productos, busca aquellos hechos con materiales reciclados.",
            "Educa a los demás: Difunde la importancia del reciclaje entre tus familiares y amigos."
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btnInicioSesion = findViewById(R.id.btnInicia_sesion);
        btnRegistrar = findViewById(R.id.btnRegistrar);
        editTextemail = findViewById(R.id.EditTextCorreo);
        getEditTextPassword = findViewById(R.id.EditTextPassword);

        SharedPreferences preferences = getSharedPreferences("Tips", MODE_PRIVATE);
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

        btnInicioSesion.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String email = editTextemail.getText().toString();
                String password = getEditTextPassword.getText().toString();

                boolean isInitialized = preferences.getBoolean("isInitialized", false);

                if (!isInitialized) {
                    for (int i = 0; i < Tips.length; i++) {
                        editor.putString("tip_" + i, Tips[i]);
                    }
                    editor.putBoolean("isInitialized", true);
                    editor.apply();
                }

                SimpleDateFormat sdf = new SimpleDateFormat("EEEE, d 'de' MMMM 'de' yyyy", new Locale("es", "CO"));
                String currentDate = sdf.format(new Date());
                if (!currentDate.equals(savedDate)) {
                    Random random = new Random();
                    int randomIndex = random.nextInt(Tips.length);
                    String newTip = Tips[randomIndex];
                    editor.putString("currentTip", newTip);
                    editor.putString("savedDate", currentDate);
                    editor.apply();
                }

                if (userManager.loginUser(email, password)) {
                    Usuario usuario = userManager.getUsuario();
                    if(usuario != null){
                        Intent intent = new Intent(MainActivity.this, Consejos.class);
                        startActivity(intent);
                        finish();
                    }
                } else {
                    Toast.makeText(MainActivity.this, "Email o Password Invalidos", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
}