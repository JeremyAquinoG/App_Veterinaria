package com.example.app_veterinaria;

public class Cita {
    private String nombreMascota;
    private String nombreVeterinario;
    private String fechaCita;
    private String motivo;
    private String estado;

    public Cita() {
    }

    public Cita(String nombreMascota, String nombreVeterinario, String fechaCita, String motivo, String estado) {
        this.nombreMascota = nombreMascota;
        this.nombreVeterinario = nombreVeterinario;
        this.fechaCita = fechaCita;
        this.motivo = motivo;
        this.estado = estado;
    }

    public String getNombreMascota() {
        return nombreMascota;
    }

    public void setNombreMascota(String nombreMascota) {
        this.nombreMascota = nombreMascota;
    }

    public String getNombreVeterinario() {
        return nombreVeterinario;
    }

    public void setNombreVeterinario(String nombreVeterinario) {
        this.nombreVeterinario = nombreVeterinario;
    }

    public String getFechaCita() {
        return fechaCita;
    }

    public void setFechaCita(String fechaCita) {
        this.fechaCita = fechaCita;
    }

    public String getMotivo() {
        return motivo;
    }

    public void setMotivo(String motivo) {
        this.motivo = motivo;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }
}
