package com.constante.inmobiliariaconstante.ui.pagos;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.constante.inmobiliariaconstante.R;
import com.constante.inmobiliariaconstante.modelo.Pago;
import com.constante.inmobiliariaconstante.request.ApiClient;
import com.constante.inmobiliariaconstante.ui.contratos.ContratoAdapter;

import java.util.ArrayList;

public class PagosAdapter extends RecyclerView.Adapter<PagosAdapter.ViewHolder>{
    private ArrayList<Pago> pagos;
    private View root;
    private Context context;
    private LayoutInflater layoutInflater;

    public PagosAdapter(ArrayList<Pago> pagos, View root) {
        this.pagos = pagos;
        this.root = root;
        this.layoutInflater = LayoutInflater.from(root.getContext());
        this.context = root.getContext();
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = layoutInflater.inflate(R.layout.item_pagos,parent,false);
        return new PagosAdapter.ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Pago pago = pagos.get(position);
        holder.codigo.setText(String.valueOf(pago.getId()));
        holder.codContrato.setText(String.valueOf(pago.getContrato().getIdContrato()));
        holder.fechaPago.setText(pago.getFechaDePago());
        holder.importe.setText(String.valueOf(pago.getMonto()));
        holder.numero.setText(String.valueOf(pago.getNumeroDePago()));
    }

    @Override
    public int getItemCount() {
        return pagos.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        private TextView codigo,numero,codContrato,importe,fechaPago;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            codigo = itemView.findViewById(R.id.TVCodPago);
            numero = itemView.findViewById(R.id.TVNumPago);
            codContrato = itemView.findViewById(R.id.TVCodContratoPago);
            importe = itemView.findViewById(R.id.TVImportePago);
            fechaPago = itemView.findViewById(R.id.TVFechaPago);
        }
    }
}
