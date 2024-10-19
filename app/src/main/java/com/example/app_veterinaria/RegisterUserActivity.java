package com.example.app_veterinaria;

import android.os.AsyncTask;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;

import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;

public class RegisterUserActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register_user);

        // Inicializamos los campos
        EditText etName = findViewById(R.id.et_name);
        EditText etEmail = findViewById(R.id.et_email);
        EditText etPhone = findViewById(R.id.et_phone);
        EditText etAddress = findViewById(R.id.et_address);
        Spinner spinnerUserType = findViewById(R.id.spinner_user_type);
        EditText etPassword = findViewById(R.id.et_password);
        Button btnRegister = findViewById(R.id.btn_register);

        // Configuramos el evento del botón
        btnRegister.setOnClickListener(v -> {
            String name = etName.getText().toString();
            String email = etEmail.getText().toString();
            String phoneString = etPhone.getText().toString();
            String address = etAddress.getText().toString();
            String userType = spinnerUserType.getSelectedItem().toString();
            String password = etPassword.getText().toString();

            // Crear el objeto Usuarios
            Usuarios nuevoUsuario = new Usuarios(name, email, phoneString, address, userType, password);

            // Verificar si todos los datos son válidos
            if (nuevoUsuario.isDataComplete()) {
                // Llamada a la tarea asíncrona para registrar el usuario
                new RegisterUserTask().execute(nuevoUsuario);
            } else {
                Toast.makeText(RegisterUserActivity.this, "Por favor, complete todos los campos", Toast.LENGTH_SHORT).show();
            }
        });
    }

    // Tarea asíncrona para manejar la solicitud de registro
    private class RegisterUserTask extends AsyncTask<Usuarios, Void, String> {

        @Override
        protected String doInBackground(Usuarios... params) {
            Usuarios usuario = params[0]; // Obtenemos el usuario

            String response = "";
            try {
                // Crear la URL de la API
                URL url = new URL("http://10.0.2.2/veterinaria_api/register_user.php");

                // Abrir la conexión HTTP
                HttpURLConnection conn = (HttpURLConnection) url.openConnection();
                conn.setRequestMethod("POST");
                conn.setDoOutput(true);

                // Construir los datos del formulario
                String postData = "nombre=" + URLEncoder.encode(usuario.getNombre(), "UTF-8")
                        + "&correo=" + URLEncoder.encode(usuario.getCorreo(), "UTF-8")
                        + "&telefono=" + URLEncoder.encode(usuario.getTelefono(), "UTF-8")
                        + "&direccion=" + URLEncoder.encode(usuario.getDireccion(), "UTF-8")
                        + "&tipo_usuario=" + URLEncoder.encode(usuario.getTipoUsuario(), "UTF-8")
                        + "&contraseña=" + URLEncoder.encode(usuario.getContrasena(), "UTF-8");

                // Enviar los datos
                OutputStream os = conn.getOutputStream();
                os.write(postData.getBytes());
                os.flush();
                os.close();

                // Leer la respuesta
                if (conn.getResponseCode() == HttpURLConnection.HTTP_OK) {
                    response = "Usuario registrado con éxito";
                } else {
                    response = "Error al registrar usuario";
                }

            } catch (Exception e) {
                response = "Exception: " + e.getMessage();
            }
            return response;
        }

        @Override
        protected void onPostExecute(String result) {
            // Mostrar la respuesta en un Toast
            Toast.makeText(RegisterUserActivity.this, result, Toast.LENGTH_SHORT).show();
        }
    }
}
