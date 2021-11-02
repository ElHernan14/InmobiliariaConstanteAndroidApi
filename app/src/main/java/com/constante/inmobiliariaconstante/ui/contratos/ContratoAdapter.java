package com.constante.inmobiliariaconstante.ui.contratos;

import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.constante.inmobiliariaconstante.R;
import com.constante.inmobiliariaconstante.modelo.Contrato;
import com.constante.inmobiliariaconstante.modelo.Inmueble;
import com.constante.inmobiliariaconstante.modelo.Inquilino;
import com.constante.inmobiliariaconstante.request.ApiClient;
import com.constante.inmobiliariaconstante.request.ApiRetroFit;


import java.util.ArrayList;

public class ContratoAdapter extends RecyclerView.Adapter<ContratoAdapter.ViewHolder>{
    private ArrayList<Inmueble> inmuebles;
    private View root;
    private Context context;
    private LayoutInflater layoutInflater;
    private ApiClient apiClient;

    public ContratoAdapter(ArrayList<Inmueble> inmuebles, View root) {
        this.inmuebles = inmuebles;
        this.root = root;
        this.layoutInflater = LayoutInflater.from(root.getContext());
        this.context = root.getContext();
        this.apiClient = ApiClient.getApi();
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = layoutInflater.inflate(R.layout.item_contratos,parent,false);
        return new ContratoAdapter.ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Inmueble inmueble = inmuebles.get(position);
        Glide.with(root.getContext())
                .load(ApiRetroFit.getURLBASE() +inmuebles.get(position).getImagen())
                .diskCacheStrategy(DiskCacheStrategy.ALL)
                .into(holder.imagen);
        holder.direccion.setText(inmuebles.get(position).getDireccion());
        holder.ver.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Log.e("llegue", "EVENTO BOTON CONTRATOADAPTER");
                Bundle bundle = new Bundle();
                bundle.putSerializable("Inmueble",inmueble);
                Navigation.findNavController(root).navigate(R.id.contratoRV,bundle);
            }
        });
    }

    @Override
    public int getItemCount() {
        return inmuebles.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        private ImageView imagen;
        private TextView direccion;
        private Button ver;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            imagen = itemView.findViewById(R.id.IVInmuebleContrato);
            direccion = itemView.findViewById(R.id.TVDireccionContrato);
            ver = itemView.findViewById(R.id.BTVerContrato);

        }
    }
}
