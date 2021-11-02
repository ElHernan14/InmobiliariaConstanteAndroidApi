package com.constante.inmobiliariaconstante.ui.inquilinos;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;


import com.constante.inmobiliariaconstante.R;
import com.constante.inmobiliariaconstante.modelo.Inquilino;

import java.util.ArrayList;

public class InquilinosRVAdapter extends RecyclerView.Adapter<InquilinosRVAdapter.ViewHolder>{
    private ArrayList<Inquilino> Inquilinos;
    private View root;
    private Context context;
    private LayoutInflater layoutInflater;

    public InquilinosRVAdapter(ArrayList<Inquilino> Inquilinos, View root){
        this.Inquilinos = Inquilinos;
        this.root = root;
        this.layoutInflater = LayoutInflater.from(root.getContext());
        this.context = root.getContext();
    }

    @NonNull
    @Override
    public InquilinosRVAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = layoutInflater.inflate(R.layout.item_inquilinosperro,parent,false);
        return new InquilinosRVAdapter.ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull InquilinosRVAdapter.ViewHolder holder, int position) {
        Inquilino inquilino = Inquilinos.get(position);
        holder.idInquilino.setText(inquilino.getIdInquilino()+"");
        holder.Dni.setText(inquilino.getDni().toString());
        holder.Apellido.setText(inquilino.getApellido());
        holder.Email.setText(inquilino.getEmail());
        holder.Nombre.setText(inquilino.getNombre());
        holder.Telefono.setText(inquilino.getTelefono());
    }

    @Override
    public int getItemCount() {
        return Inquilinos.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{
        private EditText idInquilino, Dni, Nombre, Apellido,Email, Telefono;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            idInquilino = itemView.findViewById(R.id.ETCodigoDetalle2);
            Nombre = itemView.findViewById(R.id.ETNombreDetalle2);
            Apellido = itemView.findViewById(R.id.ETApellidoDetalle2);
            Dni = itemView.findViewById(R.id.ETDniDetalle2);
            Telefono = itemView.findViewById(R.id.ETTelefonoDetalle2);
            Email = itemView.findViewById(R.id.ETEmailDetalle2);
        }
    }
}
