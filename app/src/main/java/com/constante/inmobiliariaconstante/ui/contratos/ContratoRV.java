package com.constante.inmobiliariaconstante.ui.contratos;

import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.constante.inmobiliariaconstante.R;
import com.constante.inmobiliariaconstante.databinding.ContratoRVFragmentBinding;
import com.constante.inmobiliariaconstante.databinding.InquilinosRVFragmentBinding;
import com.constante.inmobiliariaconstante.modelo.Contrato;
import com.constante.inmobiliariaconstante.modelo.Inmueble;
import com.constante.inmobiliariaconstante.ui.inquilinos.InquilinosRVViewModel;

import java.util.ArrayList;

public class ContratoRV extends Fragment {

    private ContratoRVViewModel mViewModel;
    private ContratoRVFragmentBinding binding;
    private ContratoRVAdapter adapter;

    public static ContratoRV newInstance() {
        return new ContratoRV();
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        binding = ContratoRVFragmentBinding.inflate(inflater, container, false);
        mViewModel = new ViewModelProvider(this).get(ContratoRVViewModel.class);
        View root = binding.getRoot();
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(
                getContext(),
                LinearLayoutManager.VERTICAL,
                false
        );
        mViewModel.getLista().observe(getViewLifecycleOwner(), new Observer<ArrayList<Contrato>>() {
            @Override
            public void onChanged(ArrayList<Contrato> contratos) {
                Log.e("llegue", "OBSERVE CONTRATOS");
                binding.RVContratoPerro.setLayoutManager(linearLayoutManager);
                adapter=new ContratoRVAdapter(contratos,root);
                binding.RVContratoPerro.setAdapter(adapter);
            }
        });
        mViewModel.obtenerContratos(getArguments());
        return root;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        mViewModel = new ViewModelProvider(this).get(ContratoRVViewModel.class);
        // TODO: Use the ViewModel
    }

}