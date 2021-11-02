package com.constante.inmobiliariaconstante.modelo;

import java.io.Serializable;
import java.util.Objects;

public class Contrato implements Serializable {

    private int id;
    private String fechaDesde;
    private String fechaHasta;
    private Inquilino inquilino;
    private Inmueble inmueble;
    private Boolean estado;
    private int cuotas;

    public Contrato() {}
    public Contrato(int idContrato, String fechaInicio, String fechaFin, Inquilino inquilino, Inmueble inmueble) {
        this.id = idContrato;
        this.fechaDesde = fechaInicio;
        this.fechaHasta = fechaFin;
        this.inquilino = inquilino;
        this.inmueble = inmueble;
    }

    public int getIdContrato() {
        return id;
    }

    public void setIdContrato(int idContrato) {
        this.id = id;
    }

    public String getFechaInicio() {
        return fechaDesde;
    }

    public void setFechaInicio(String FechaDesde) {
        this.fechaDesde = FechaDesde;
    }

    public String getFechaFin() {
        return fechaHasta;
    }

    public void setFechaFin(String FechaHasta) {
        this.fechaHasta = FechaHasta;
    }

    public Inquilino getInquilino() {
        return inquilino;
    }

    public void setInquilino(Inquilino inquilino) {
        this.inquilino = inquilino;
    }

    public Inmueble getInmueble() {
        return inmueble;
    }

    public void setInmueble(Inmueble inmueble) {
        this.inmueble = inmueble;
    }

    public Boolean getEstado() {
        return estado;
    }

    public void setEstado(Boolean estado) {
        this.estado = estado;
    }

    public int getCuotas() {
        return cuotas;
    }

    public void setCuotas(int cuotas) {
        this.cuotas = cuotas;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Contrato contrato = (Contrato) o;
        return id == contrato.id;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}
