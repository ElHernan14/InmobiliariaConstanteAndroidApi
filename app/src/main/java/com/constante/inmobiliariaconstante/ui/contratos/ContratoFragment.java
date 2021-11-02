package com.constante.inmobiliariaconstante.ui.contratos;


import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;


import com.constante.inmobiliariaconstante.databinding.ContratoFragmentBinding;
import com.constante.inmobiliariaconstante.modelo.Inmueble;

import java.util.ArrayList;

public class ContratoFragment extends Fragment {
    private ContratoViewModel mViewModel;
    private ContratoFragmentBinding binding;
    private ContratoAdapter adapter;

    public static ContratoFragment newInstance() {
        return new ContratoFragment();
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        mViewModel = new ViewModelProvider(this).get(ContratoViewModel.class);
        binding = ContratoFragmentBinding.inflate(inflater, container, false);
        View root = binding.getRoot();
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(
                getContext(),
                LinearLayoutManager.VERTICAL,
                false
        );
        mViewModel.getLista().observe(getViewLifecycleOwner(), new Observer<ArrayList<Inmueble>>() {
            @Override
            public void onChanged(ArrayList<Inmueble> inmuebles) {
                binding.RVContratos.setLayoutManager(linearLayoutManager);
                adapter = new ContratoAdapter(inmuebles,root);
                binding.RVContratos.setAdapter(adapter);
            }
        });
        mViewModel.obtenerInmuebles();
        return root;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        mViewModel = new ViewModelProvider(this).get(ContratoViewModel.class);
        // TODO: Use the ViewModel
    }
}
