package com.example.app_veterinaria;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.LinearLayout;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import android.widget.Button;
import com.example.app_veterinaria.AgendarCitaActivity;



public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Botones en lugar de la barra de navegación
        LinearLayout btnCitas = findViewById(R.id.btn_citas);
        LinearLayout btnAgenda = findViewById(R.id.btn_agenda);
        LinearLayout btnCuenta = findViewById(R.id.btn_cuenta);

        // Mostrar por defecto el fragmento de Citas
        if (savedInstanceState == null) {
            getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, new CitasFragment()).commit();
        }

        // Manejar clic en el botón "Citas"
        btnCitas.setOnClickListener(v -> {
            Fragment selectedFragment = new CitasFragment();
            getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, selectedFragment).commit();
        });

        // Manejar clic en el botón "Agenda"
        btnAgenda.setOnClickListener(v -> {
            Fragment selectedFragment = new AgentaFragment();
            getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, selectedFragment).commit();
        });

        // Manejar clic en el botón "Cuenta"
        btnCuenta.setOnClickListener(v -> {
            Fragment selectedFragment = new CuentaFragment();
            getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, selectedFragment).commit();
        });

        // Redirigir a la actividad para agendar una cita
        Button btnAgendarCita = findViewById(R.id.btn_agendar_cita);
        btnAgendarCita.setOnClickListener(v -> {
            // Crear un Intent para iniciar la actividad AgendarCitaActivity
            Intent intent = new Intent(MainActivity.this, AgendarCitaActivity.class);
            startActivity(intent);
        });

    }
}
