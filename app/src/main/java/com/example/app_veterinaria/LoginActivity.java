package com.example.app_veterinaria;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

public class LoginActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // Enlazamos el layout de la actividad
        setContentView(R.layout.activity_login);

        // Aquí puedes inicializar cualquier elemento como botones, textos, etc.
        Button loginButton = findViewById(R.id.btn_other_methods);
        loginButton.setOnClickListener(v -> {
            Intent intent = new Intent(LoginActivity.this, UserLoginActivity.class);
            startActivity(intent);
        });
        // Inicializamos el botón de "Crear Cuenta"
        Button registerButton = findViewById(R.id.btn_create_account);
        registerButton.setOnClickListener(v -> {
            // Redirige a la actividad de registrar usuario
            Intent intent = new Intent(LoginActivity.this, RegisterUserActivity.class);
            startActivity(intent);
        });



    }
}
