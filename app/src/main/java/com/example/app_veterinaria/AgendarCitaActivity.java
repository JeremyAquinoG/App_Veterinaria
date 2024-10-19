package com.example.app_veterinaria;

import android.app.DatePickerDialog;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import java.util.Calendar;
import java.util.HashMap;
import java.util.Map;

public class AgendarCitaActivity extends AppCompatActivity {

    EditText etMascota, etFechaCita, etMotivo;
    Spinner spinnerVeterinario, spinnerHoraCita;
    Button btnAgendar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_agendar_cita); // Asegúrate que este layout sea el correcto

        // Inicializar campos
        etFechaCita = findViewById(R.id.et_fecha_cita);
        etMascota = findViewById(R.id.et_mascota);
        etMotivo = findViewById(R.id.et_motivo);
        spinnerVeterinario = findViewById(R.id.spinner_veterinario);
        spinnerHoraCita = findViewById(R.id.spinner_hora_cita); // Nuevo Spinner para la hora
        btnAgendar = findViewById(R.id.btn_agendar);

        // Configurar el Spinner con las horas predefinidas
        String[] horasDisponibles = {
                "10:00 AM - 11:00 AM", "11:00 AM - 12:00 PM", "12:00 PM - 13:00 PM",
                "14:00 PM - 15:00 PM", "15:00 PM - 16:00 PM",
                "16:00 PM - 17:00 PM", "17:00 PM - 18:00 PM"
        };
        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_dropdown_item, horasDisponibles);
        spinnerHoraCita.setAdapter(adapter);

        // Evento de selección de la fecha
        etFechaCita.setOnClickListener(v -> {
            // Obtener la fecha actual
            final Calendar calendario = Calendar.getInstance();
            int dia = calendario.get(Calendar.DAY_OF_MONTH);
            int mes = calendario.get(Calendar.MONTH);
            int anio = calendario.get(Calendar.YEAR);

            // Crear un DatePickerDialog y mostrarlo
            DatePickerDialog datePickerDialog = new DatePickerDialog(AgendarCitaActivity.this,
                    (view, year, monthOfYear, dayOfMonth) -> {
                        // Establecer la fecha seleccionada en el EditText
                        etFechaCita.setText(dayOfMonth + "/" + (monthOfYear + 1) + "/" + year);
                    }, anio, mes, dia);
            datePickerDialog.show();
        });

        // Evento de clic en el botón Agendar Cita
        btnAgendar.setOnClickListener(v -> {
            String mascota = etMascota.getText().toString().trim();
            String fechaCita = etFechaCita.getText().toString().trim();
            String motivo = etMotivo.getText().toString().trim();
            String veterinario = spinnerVeterinario.getSelectedItem().toString();
            String horaCita = spinnerHoraCita.getSelectedItem().toString();  // Obtener la hora seleccionada

            // Verifica si todos los campos están completos
            if (!mascota.isEmpty() && !fechaCita.isEmpty() && !horaCita.isEmpty() && !motivo.isEmpty()) {
                agendarCita(mascota, veterinario, fechaCita, horaCita, motivo);
            } else {
                Toast.makeText(AgendarCitaActivity.this, "Por favor, complete todos los campos", Toast.LENGTH_SHORT).show();
            }
        });
    }

    // Función para enviar los datos al servidor y agendar la cita
    private void agendarCita(String mascota, String veterinario, String fechaCita, String horaCita, String motivo) {
        String url = "http://10.0.2.2/veterinaria_api/agendar_cita.php"; // Cambia la URL según tu configuración

        // Realiza la solicitud POST
        StringRequest request = new StringRequest(Request.Method.POST, url,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        Toast.makeText(AgendarCitaActivity.this, "Cita agendada con éxito", Toast.LENGTH_SHORT).show();
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Toast.makeText(AgendarCitaActivity.this, "Error al agendar la cita: " + error.getMessage(), Toast.LENGTH_SHORT).show();
                    }
                }) {
            @Override
            protected Map<String, String> getParams() {
                Map<String, String> params = new HashMap<>();
                params.put("id_mascota", mascota); // Cambiado de "nombre_mascota"
                params.put("id_veterinario", veterinario); // Cambiado de "veterinario"
                params.put("fecha_cita", fechaCita);
                params.put("hora_cita", horaCita);  // Agregar el campo de la hora seleccionada
                params.put("motivo", motivo);
                return params;
            }

        };

        // Añadir la solicitud a la cola de Volley
        RequestQueue queue = Volley.newRequestQueue(AgendarCitaActivity.this);
        queue.add(request);
    }
}
