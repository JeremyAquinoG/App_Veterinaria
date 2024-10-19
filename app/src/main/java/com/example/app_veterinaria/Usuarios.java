package com.example.app_veterinaria;

public class Usuarios {

    private String nombre;
    private String correo;
    private String telefono;
    private String direccion;
    private String tipoUsuario;
    private String contrasena;

    // Constructor
    public Usuarios(String nombre, String correo, String telefono, String direccion, String tipoUsuario, String contrasena) {
        this.nombre = nombre;
        this.correo = correo;
        this.telefono = telefono;
        this.direccion = direccion;
        this.tipoUsuario = tipoUsuario;
        this.contrasena = contrasena;
    }

    // Getters y Setters
    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getCorreo() {
        return correo;
    }

    public void setCorreo(String correo) {
        this.correo = correo;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    public String getTipoUsuario() {
        return tipoUsuario;
    }

    public void setTipoUsuario(String tipoUsuario) {
        this.tipoUsuario = tipoUsuario;
    }

    public String getContrasena() {
        return contrasena;
    }

    public void setContrasena(String contrasena) {
        this.contrasena = contrasena;
    }

    // Método para validar si todos los datos están completos
    public boolean isDataComplete() {
        return !nombre.isEmpty() && !correo.isEmpty() && !telefono.isEmpty() && !direccion.isEmpty() && !tipoUsuario.isEmpty() && !contrasena.isEmpty();
    }
}
