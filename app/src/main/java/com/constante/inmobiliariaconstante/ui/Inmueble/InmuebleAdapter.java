package com.constante.inmobiliariaconstante.ui.Inmueble;

import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.constante.inmobiliariaconstante.R;
import com.constante.inmobiliariaconstante.modelo.Inmueble;

import java.util.ArrayList;

public class InmuebleAdapter extends RecyclerView.Adapter<InmuebleAdapter.ViewHolder>{
    private ArrayList<Inmueble> inmuebles;
    private View root;
    private Context context;
    private LayoutInflater layoutInflater;

    public InmuebleAdapter(ArrayList<Inmueble> inmuebles, View root) {
        this.inmuebles = inmuebles;
        this.root = root;
        this.layoutInflater = LayoutInflater.from(root.getContext());
        this.context = root.getContext();
    }

    public InmuebleAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        Log.e("llegue", "LLEGUE CREANDOO");
        View view = layoutInflater.inflate(R.layout.item_inmuebles,parent,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull InmuebleAdapter.ViewHolder holder, int position) {
        Log.e("llegue", "LLEGUE ACAA ESTOYY VIEWHOLDERR");
        Inmueble i = inmuebles.get(position);
        Glide.with(root.getContext())
                .load(inmuebles.get(position).getImagen())
                .diskCacheStrategy(DiskCacheStrategy.ALL)
                .into(holder.imagen);
        holder.tvDireccion.setText(inmuebles.get(position).getDireccion());
        holder.precio.setText(" $"+(int) inmuebles.get(position).getPrecio());
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Bundle bundle = new Bundle();
                bundle.putSerializable("inmueble",i);
                Navigation.findNavController(root).navigate(R.id.inmuebleDetalle,bundle);
            }
        });
    }

    @Override
    public int getItemCount() {
        return inmuebles.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{
        private TextView tvDireccion, precio;
        private ImageView imagen;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            tvDireccion = itemView.findViewById(R.id.TVDireccion);
            precio = itemView.findViewById(R.id.TVPrecio);
            imagen = itemView.findViewById(R.id.IVFoto);
        }
    }
}
