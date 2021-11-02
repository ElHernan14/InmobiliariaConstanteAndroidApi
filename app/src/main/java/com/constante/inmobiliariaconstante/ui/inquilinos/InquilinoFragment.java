package com.constante.inmobiliariaconstante.ui.inquilinos;

import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.constante.inmobiliariaconstante.R;
import com.constante.inmobiliariaconstante.databinding.InmuebleFragmentBinding;
import com.constante.inmobiliariaconstante.databinding.InquilinoFragmentBinding;
import com.constante.inmobiliariaconstante.modelo.Inmueble;
import com.constante.inmobiliariaconstante.ui.Inmueble.InmuebleAdapter;
import com.constante.inmobiliariaconstante.ui.Inmueble.InmuebleViewModel;

import java.util.ArrayList;

public class InquilinoFragment extends Fragment {
    private InquilinoViewModel mViewModel;
    private InquilinoFragmentBinding binding;
    private InquilinoAdapter adapter;

    public static InquilinoFragment newInstance() {
        return new InquilinoFragment();
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        Log.e("llegue", "HOLA INICIO");
        mViewModel = new ViewModelProvider(this).get(InquilinoViewModel.class);
        binding = InquilinoFragmentBinding.inflate(inflater, container, false);
        View root = binding.getRoot();
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(
                getContext(),
                LinearLayoutManager.VERTICAL,
                false
        );
        mViewModel.getInmuebles().observe(getViewLifecycleOwner(), new Observer<ArrayList<Inmueble>>() {
            @Override
            public void onChanged(ArrayList<Inmueble> inmuebles) {
                binding.RVInquilinos.setLayoutManager(linearLayoutManager);
                adapter = new InquilinoAdapter(inmuebles,root);
                binding.RVInquilinos.setAdapter(adapter);
            }
        });
        mViewModel.obtenerInmuebles();
        return root;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        mViewModel = new ViewModelProvider(this).get(InquilinoViewModel.class);
        // TODO: Use the ViewModel
    }

}