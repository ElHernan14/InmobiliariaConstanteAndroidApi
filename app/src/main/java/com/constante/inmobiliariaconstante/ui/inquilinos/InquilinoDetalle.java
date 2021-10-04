package com.constante.inmobiliariaconstante.ui.inquilinos;

import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.constante.inmobiliariaconstante.R;
import com.constante.inmobiliariaconstante.databinding.InmuebleDetalleFragmentBinding;
import com.constante.inmobiliariaconstante.databinding.InquilinoDetalleFragmentBinding;
import com.constante.inmobiliariaconstante.modelo.Inquilino;
import com.constante.inmobiliariaconstante.ui.Inmueble.InmuebleDetalleViewModel;

public class InquilinoDetalle extends Fragment {
    private InquilinoDetalleViewModel mViewModel;
    private InquilinoDetalleFragmentBinding binding;

    public static InquilinoDetalle newInstance() {
        return new InquilinoDetalle();
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        mViewModel = new ViewModelProvider(this).get(InquilinoDetalleViewModel.class);
        binding = InquilinoDetalleFragmentBinding.inflate(inflater, container, false);
        View root = binding.getRoot();
        mViewModel.getInquilino().observe(getViewLifecycleOwner(), new Observer<Inquilino>() {
            @Override
            public void onChanged(Inquilino inquilino) {
                binding.ETApellidoDetalle.setText(inquilino.getApellido());
                binding.ETCodigoDetalle.setText(String.valueOf(inquilino.getIdInquilino()));
                binding.ETDniDetalle.setText(inquilino.getDNI().toString());
                binding.ETEmailDetalle.setText(inquilino.getEmail());
                binding.ETGaranteDetalle.setText(inquilino.getNombreGarante());
                binding.ETNombreDetalle.setText(inquilino.getNombre());
                binding.ETTelefonoDetalle.setText(inquilino.getTelefono());
                binding.ETTelGaranteDetalle.setText(inquilino.getApellido());
            }
        });
        mViewModel.setInquilino(getArguments());
        return root;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        mViewModel = new ViewModelProvider(this).get(InquilinoDetalleViewModel.class);
        // TODO: Use the ViewModel
    }

}