package com.example.eco_recicla;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

public class MenuPrincipal extends AppCompatActivity {
    private TextView textviewDate;

    private String[] Tips= {
            "Reserva un espacio: Destina un lugar específico para depositar los materiales reciclables.",
            "Separa los residuos: Clasifica los materiales según su tipo: papel y cartón, vidrio, plástico, metal, orgánico.",
            "Limpia los envases: Antes de reciclar, enjuaga bien los envases para eliminar restos de comida o líquidos.",
            "Aplasta y compacta: Para ahorrar espacio, aplasta las botellas de plástico y las latas de aluminio.",
            "Reutiliza: Antes de reciclar, busca darle una segunda vida a algunos objetos, como frascos de vidrio o cajas de cartón.",
            "Reduce y reutiliza: Antes de reciclar, trata de reducir la cantidad de residuos que generas y reutiliza los objetos siempre que sea posible.",
            "Usa el aire acondicionado con moderación.",
            "Compra productos reciclados: Al adquirir nuevos productos, busca aquellos hechos con materiales reciclados.",
            "Educa a los demás: Difunde la importancia del reciclaje entre tus familiares y amigos.",
            
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
