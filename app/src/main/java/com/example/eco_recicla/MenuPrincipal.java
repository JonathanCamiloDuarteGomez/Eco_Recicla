package com.example.eco_recicla;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.eco_recicla.back.UserManager;
import com.example.eco_recicla.back.Usuario;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

public class MenuPrincipal extends AppCompatActivity {
    private TextView textviewDate;

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
        setContentView(R.layout.activity_menu_principal);

        ImageButton btnGestionDeRe = findViewById(R.id.buttonGestionDeRe);
        ImageButton btnIncentivosRecompensas = findViewById(R.id.buttonIncentivosRecompensas);
        ImageButton btnEstadisticasDeReciclaje = findViewById(R.id.buttonEstadisticasDeReciclaje);
        ImageButton btnDatosPersonales = findViewById(R.id.buttonInformacionDeUsuario);
        ImageButton btnSalir = findViewById(R.id.floatingActionButton2);
        TextView textviewDate =findViewById(R.id.data);
        //inicializar sharePreference
        SharedPreferences preferences = getSharedPreferences("DailyTips",MODE_PRIVATE);
        SharedPreferences.Editor editor = preferences.edit();

        //Verificar los consejos ya guardados

        boolean isInitialized = preferences.getBoolean("isInitialized",false);

        if(!isInitialized){
            for(int i=0; i < Tips.length;i ++){
                editor.putString("tip"+ i,Tips[i]);
            }
            editor.putBoolean("isInitialized",true);
            editor.apply();
        }

//EEEE dia d Fecha  MMMM mes yyyy año
        SimpleDateFormat sdf = new SimpleDateFormat("EEEE,d 'de' MMMM 'de' yyyy", new Locale("es", "CO"));

//almacena la cadena de la fecha y formateda
        String currentDate = sdf.format(new Date());
        textviewDate.setText(currentDate);

        //en la siguiente funcion se ingresa el bton y la clase hacia donde se va

        cambioDePantalla(btnGestionDeRe, GestionDeReciclajeAgregarObjeto.class);
        cambioDePantalla(btnIncentivosRecompensas, Premios.class);
        cambioDePantalla(btnEstadisticasDeReciclaje, Estadisticas.class);
        cambioDePantalla(btnDatosPersonales, MenuInformacionUsuario.class);
        cambioDePantalla(btnSalir, MainActivity.class);//cambiar a login
        //no olvidar reemplazar la clase pa donde se dirije, no son HistorialActivity

        //cambiar el txt de bienvenida por el nombre del usuario
        //Bienvenido \n \nPepito Perez
        UserManager UserManager = new UserManager(this);
        Usuario usuario = UserManager.getUsuario();
        if (usuario != null) {
            TextView txtBienvenida = findViewById(R.id.txtBienvenida);
            TextView txtCoins = findViewById(R.id.txtCoins);
            txtBienvenida.setText("Bienvenido \n"+"\n"+usuario.getNombre()+" "+usuario.getApellido());
            txtCoins.setText("Coins = "+usuario.getCoins());
        }

    }

    private void cambioDePantalla(ImageButton nombreBtn, final Class<?> clase) {
        nombreBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent next = new Intent(MenuPrincipal.this, clase);
                startActivity(next);
                finish();
            }
        });
    }
}