package com.constante.inmobiliariaconstante.modelo;

import java.io.Serializable;
import java.util.Objects;

public class Inmueble implements Serializable {

    private int id;
    private String direccion;
    private String uso;
    private String tipo;
    private int ambientes;
    private double precio;
    private Propietario duenio;
    //En falso significa que el innmueble no está disponible por alguna falla en el mismo.
    private boolean estado=true;
    private String avatar;
    private int superficie;
    private double latitud;
    private double longitud;

    public Inmueble(int id, String direccion, String uso, String tipo, int ambientes, double precio, Propietario duenio, boolean estado, String imagen, int superficie, double latitud, double longitud) {
        id = id;
        direccion = direccion;
        this.uso = uso;
        this.tipo = tipo;
        ambientes = ambientes;
        precio = precio;
        duenio = duenio;
        this.estado = estado;
        this.avatar = imagen;
        superficie = superficie;
        latitud = latitud;
        longitud = longitud;
    }

    public Inmueble(int id, String direccion, String uso, String tipo, int ambientes, double precio, Propietario duenio, boolean estado, String imagen) {
        id = id;
        direccion = direccion;
        this.uso = uso;
        this.tipo = tipo;
        precio = precio;
        duenio = duenio;
        this.estado = estado;
        this.avatar = imagen;
        ambientes = ambientes;
    }

    public Inmueble() {

    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        id = id;
    }

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        direccion = direccion;
    }

    public String getUso() {
        return uso;
    }

    public void setUso(String uso) {
        this.uso = uso;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public int getAmbientes() {
        return ambientes;
    }

    public void setAmbientes(int ambientes) {
        ambientes = ambientes;
    }

    public double getPrecio() {
        return precio;
    }

    public void setPrecio(double precio) {
        precio = precio;
    }

    public Propietario getDuenio() {
        return duenio;
    }

    public void setDuenio(Propietario duenio) {
        duenio = duenio;
    }

    public boolean isEstado() {
        return estado;
    }

    public void setEstado(boolean estado) {
        this.estado = estado;
    }

    public String getImagen() {
        return avatar;
    }

    public void setImagen(String imagen) {
        this.avatar = imagen;
    }

    public int getSuperficie() {
        return superficie;
    }

    public void setSuperficie(int superficie) {
        superficie = superficie;
    }

    public double getLatitud() {
        return latitud;
    }

    public void setLatitud(double latitud) {
        latitud = latitud;
    }

    public double getLongitud() {
        return longitud;
    }

    public void setLongitud(double longitud) {
        longitud = longitud;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Inmueble inmueble = (Inmueble) o;
        return id == inmueble.id;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}
