package com.constante.inmobiliariaconstante.modelo;

import java.io.Serializable;

public class Inquilino implements Serializable {

    private int idInquilino;
    private Long dni;
    private String nombre;
    private String apellido;
    private String lugarDeTrabajo;
    private String email;
    private String telefono;
    private String nombreGarante;
    private String telefonoGarante;

    public Inquilino() {}

    public Inquilino(int idInquilino, Long dni, String nombre, String apellido, String lugarDeTrabajo, String email, String telefono, String nombreGarante, String telefonoGarante) {
        this.idInquilino = idInquilino;
        dni = dni;
        nombre = nombre;
        apellido = apellido;
        this.lugarDeTrabajo = lugarDeTrabajo;
        email = email;
        telefono = telefono;
        this.nombreGarante = nombreGarante;
        this.telefonoGarante = telefonoGarante;
    }

    public int getIdInquilino() {
        return idInquilino;
    }

    public void setIdInquilino(int idInquilino) {
        this.idInquilino = idInquilino;
    }

    public Long getDni() {
        return dni;
    }

    public void setDni(Long dni) {
        dni = dni;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        nombre = nombre;
    }

    public String getApellido() {
        return apellido;
    }

    public void setApellido(String apellido) {
        apellido = apellido;
    }

    public String getLugarDeTrabajo() {
        return lugarDeTrabajo;
    }

    public void setLugarDeTrabajo(String lugarDeTrabajo) {
        this.lugarDeTrabajo = lugarDeTrabajo;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        email = email;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        telefono = telefono;
    }

    public String getNombreGarante() {
        return nombreGarante;
    }

    public void setNombreGarante(String nombreGarante) {
        this.nombreGarante = nombreGarante;
    }

    public String getTelefonoGarante() {
        return telefonoGarante;
    }

    public void setTelefonoGarante(String telefonoGarante) {
        this.telefonoGarante = telefonoGarante;
    }
}
