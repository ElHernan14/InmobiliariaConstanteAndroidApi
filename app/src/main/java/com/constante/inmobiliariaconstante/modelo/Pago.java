package com.constante.inmobiliariaconstante.modelo;

import java.io.Serializable;

public class Pago implements Serializable {

    private int id;
    private int numeroDePago;
    private Contrato contrato;
    private double monto;
    private String fechaDePago;

    public Pago() {}

    public Pago(int id, int numeroDePago, Contrato contrato, double monto, String fechaDePago) {
        id = id;
        numeroDePago = numeroDePago;
        this.contrato = contrato;
        monto = monto;
        fechaDePago = fechaDePago;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        id = id;
    }

    public int getNumeroDePago() {
        return numeroDePago;
    }

    public void setNumeroDePago(int numeroDePago) {
        numeroDePago = numeroDePago;
    }

    public Contrato getContrato() {
        return contrato;
    }

    public void setContrato(Contrato contrato) {
        this.contrato = contrato;
    }

    public double getMonto() {
        return monto;
    }

    public void setMonto(double monto) {
        monto = monto;
    }

    public String getFechaDePago() {
        return fechaDePago;
    }

    public void setFechaDePago(String fechaDePago) {
        fechaDePago = fechaDePago;
    }
}
