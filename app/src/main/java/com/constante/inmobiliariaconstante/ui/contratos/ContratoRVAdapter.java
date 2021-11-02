package com.constante.inmobiliariaconstante.ui.contratos;

import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.RecyclerView;


import com.constante.inmobiliariaconstante.R;
import com.constante.inmobiliariaconstante.modelo.Contrato;
import com.constante.inmobiliariaconstante.modelo.Pago;
import com.constante.inmobiliariaconstante.ui.inquilinos.InquilinosRVAdapter;

import java.util.ArrayList;

public class ContratoRVAdapter extends RecyclerView.Adapter<ContratoRVAdapter.ViewHolder>{
    private ArrayList<Contrato> contratos;
    private View root;
    private Context context;
    private LayoutInflater layoutInflater;

    public ContratoRVAdapter(ArrayList<Contrato> contratos, View root){
        this.contratos = contratos;
        this.root = root;
        this.layoutInflater = LayoutInflater.from(root.getContext());
        this.context = root.getContext();
    }
    @NonNull
    @Override
    public ContratoRVAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = layoutInflater.inflate(R.layout.item_contrato,parent,false);
        return new ContratoRVAdapter.ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ContratoRVAdapter.ViewHolder holder, int position) {
        Log.e("llegue", "EVENTO CONTRATORVADAPTER");
        Contrato contrato = contratos.get(position);
        Log.e("llegue", contrato.getInmueble()+"");
        holder.codigo.setText(contrato.getIdContrato()+"");
        holder.fechaInicio.setText(contrato.getFechaInicio());
        holder.fechaFin.setText(contrato.getFechaFin());
        holder.monto.setText(contrato.getInmueble().getPrecio()+"");
        holder.inquilino.setText(contrato.getInquilino().getNombre()+" "+contrato.getInquilino().getApellido());
        holder.inmueble.setText(contrato.getInmueble().getDireccion());
        holder.verPagos.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Bundle bundle = new Bundle();
                bundle.putSerializable("contrato",contrato);
                Navigation.findNavController(root).navigate(R.id.pagosFragment,bundle);
            }
        });
    }

    @Override
    public int getItemCount() {
        return contratos.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{
        private TextView codigo, fechaInicio, fechaFin, monto, inquilino, inmueble;
        private Button verPagos;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            codigo = itemView.findViewById(R.id.TVCodigoContrato2);
            fechaInicio = itemView.findViewById(R.id.TVFechaInicioContrato2);
            fechaFin = itemView.findViewById(R.id.TVFechaFinalContrato2);
            monto = itemView.findViewById(R.id.TVMontoContrato2);
            inquilino = itemView.findViewById(R.id.TVInquilinoContrato2);
            inmueble = itemView.findViewById(R.id.TVInmuebleContrato2);
            verPagos = itemView.findViewById(R.id.BTPagosContrato2);
        }
    }
}
