package com.constante.inmobiliariaconstante.ui.contratos;

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
import com.constante.inmobiliariaconstante.databinding.ContratoDetalleFragmentBinding;
import com.constante.inmobiliariaconstante.databinding.InmuebleDetalleFragmentBinding;
import com.constante.inmobiliariaconstante.modelo.Contrato;
import com.constante.inmobiliariaconstante.ui.Inmueble.InmuebleDetalleViewModel;

public class ContratoDetalle extends Fragment {

    private ContratoDetalleViewModel mViewModel;
    private ContratoDetalleFragmentBinding binding;

    public static ContratoDetalle newInstance() {
        return new ContratoDetalle();
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        mViewModel = new ViewModelProvider(this).get(ContratoDetalleViewModel.class);
        binding = ContratoDetalleFragmentBinding.inflate(inflater, container, false);
        View root = binding.getRoot();
        mViewModel.getContrato().observe(getViewLifecycleOwner(), new Observer<Contrato>() {
            @Override
            public void onChanged(Contrato contrato) {
                binding.TVCodigoContrato.setText(String.valueOf(contrato.getIdContrato()));
                binding.TVFechaFinalContrato.setText(contrato.getFechaFin());
                binding.TVFechaInicioContrato.setText(contrato.getFechaInicio());
                binding.TVInmuebleContrato.setText(contrato.getInmueble().getDireccion());
                binding.TVMontoContrato.setText(String.valueOf(contrato.getMontoAlquiler()));
                binding.TVInquilinoContrato.setText(contrato.getInquilino().getNombre()+" "+contrato.getInquilino().getApellido());
                binding.BTPagosContrato.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        mViewModel.verPagos(root);
                    }
                });
            }
        });
        mViewModel.setContrato(getArguments());
        return root;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        mViewModel = new ViewModelProvider(this).get(ContratoDetalleViewModel.class);
        // TODO: Use the ViewModel
    }

}