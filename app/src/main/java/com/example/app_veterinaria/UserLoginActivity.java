package com.example.app_veterinaria;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import java.util.HashMap;
import java.util.Map;

import org.json.JSONObject;
import org.json.JSONException;


public class UserLoginActivity extends AppCompatActivity {

    EditText etCorreo, etPassword;
    Button btnLogin;
    String strCorreo, strPassword;
    String url = "http://10.0.2.2/veterinaria_api/login_user.php";  // Cambia si tu URL es diferente

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_login);  // Enlazamos el layout

        // Inicializamos los elementos de la vista
        etCorreo = findViewById(R.id.et_email);
        etPassword = findViewById(R.id.et_password);
        btnLogin = findViewById(R.id.btn_login);

        // Configuramos el evento del botón de inicio de sesión
        btnLogin.setOnClickListener(v -> {
            strCorreo = etCorreo.getText().toString();
            strPassword = etPassword.getText().toString();

            if (strCorreo.isEmpty() || strPassword.isEmpty()) {
                Toast.makeText(UserLoginActivity.this, "Por favor, complete todos los campos", Toast.LENGTH_SHORT).show();
            } else {
                loginUser();  // Llamar a la función de inicio de sesión
            }
        });
    }
    private void loginUser() {
        ProgressDialog progressDialog = new ProgressDialog(this);
        progressDialog.setMessage("Iniciando sesión...");
        progressDialog.show();

        StringRequest request = new StringRequest(Request.Method.POST, url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                progressDialog.dismiss();
                try {
                    // Procesar la respuesta JSON
                    JSONObject jsonResponse = new JSONObject(response);
                    String status = jsonResponse.getString("status");

                    if (status.equals("success")) {
                        // Redirigir a MainActivity
                        Intent intent = new Intent(UserLoginActivity.this, MainActivity.class);
                        startActivity(intent);
                        finish();
                    } else {
                        // Mostrar mensaje de error desde el backend
                        String message = jsonResponse.getString("message");
                        Toast.makeText(UserLoginActivity.this, message, Toast.LENGTH_SHORT).show();
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                    Toast.makeText(UserLoginActivity.this, "Error en la respuesta del servidor", Toast.LENGTH_SHORT).show();
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                progressDialog.dismiss();
                Toast.makeText(UserLoginActivity.this, "Error en la conexión", Toast.LENGTH_SHORT).show();
            }
        }) {
            @Override
            protected Map<String, String> getParams() {
                Map<String, String> params = new HashMap<>();
                params.put("correo", strCorreo);
                params.put("contraseña", strPassword);
                return params;
            }
        };

        RequestQueue requestQueue = Volley.newRequestQueue(UserLoginActivity.this);
        requestQueue.add(request);
    }

}
