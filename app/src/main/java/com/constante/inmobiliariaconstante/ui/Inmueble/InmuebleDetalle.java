package com.constante.inmobiliariaconstante.ui.Inmueble;

import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CompoundButton;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.constante.inmobiliariaconstante.R;
import com.constante.inmobiliariaconstante.databinding.InmuebleDetalleFragmentBinding;
import com.constante.inmobiliariaconstante.databinding.InmuebleFragmentBinding;
import com.constante.inmobiliariaconstante.modelo.Inmueble;
import com.constante.inmobiliariaconstante.request.ApiRetroFit;

public class InmuebleDetalle extends Fragment {

    private InmuebleDetalleViewModel mViewModel;
    private InmuebleDetalleFragmentBinding binding;

    public static InmuebleDetalle newInstance() {
        return new InmuebleDetalle();
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        mViewModel = new ViewModelProvider(this).get(InmuebleDetalleViewModel.class);
        binding = InmuebleDetalleFragmentBinding.inflate(inflater, container, false);
        View root = binding.getRoot();
        binding.CBDisponible.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                mViewModel.actualizarInmueble(b);
            }
        });
        mViewModel.getInmueble().observe(getViewLifecycleOwner(), new Observer<Inmueble>() {
            @Override
            public void onChanged(Inmueble inmueble) {
                binding.TVAmbientes.setText(String.valueOf(inmueble.getAmbientes()));
                binding.TVCodigo.setText(String.valueOf(inmueble.getId()));
                binding.TVDireccionDetalles.setText(inmueble.getDireccion());
                binding.TVPrecioDetalles.setText(String.valueOf(inmueble.getPrecio()));
                binding.TVTipo.setText(inmueble.getTipo());
                binding.TVUso.setText(inmueble.getUso());
                Glide.with(root.getContext())
                        .load(ApiRetroFit.getURLBASE()+inmueble.getImagen())
                        .diskCacheStrategy(DiskCacheStrategy.ALL)
                        .into(binding.imageView);
                binding.CBDisponible.setChecked(inmueble.isEstado());
            }
        });
        mViewModel.setInmueble(getArguments());
        return root;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        mViewModel = new ViewModelProvider(this).get(InmuebleDetalleViewModel.class);
        // TODO: Use the ViewModel
    }

}