package com.constante.inmobiliariaconstante.ui.inquilinos;

import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.constante.inmobiliariaconstante.R;
import com.constante.inmobiliariaconstante.databinding.InquilinoFragmentBinding;
import com.constante.inmobiliariaconstante.databinding.InquilinosRVFragmentBinding;
import com.constante.inmobiliariaconstante.modelo.Inquilino;

import java.util.ArrayList;

public class InquilinosRV extends Fragment {

    private InquilinosRVViewModel mViewModel;
    private InquilinosRVFragmentBinding binding;
    private InquilinosRVAdapter adapter;

    public static InquilinosRV newInstance() {
        return new InquilinosRV();
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        binding = InquilinosRVFragmentBinding.inflate(inflater, container, false);
        mViewModel = new ViewModelProvider(this).get(InquilinosRVViewModel.class);
        View root = binding.getRoot();
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(
                getContext(),
                LinearLayoutManager.VERTICAL,
                false
        );
        mViewModel.getInquilinos().observe(getViewLifecycleOwner(), new Observer<Inquilino>() {
            @Override
            public void onChanged(Inquilino inquilino) {
                ArrayList<Inquilino> inquilinos = new ArrayList<>();
                inquilinos.add(inquilino);
                binding.RVInquilinosPerro.setLayoutManager(linearLayoutManager);
                adapter = new InquilinosRVAdapter(inquilinos,root);
                binding.RVInquilinosPerro.setAdapter(adapter);
            }
        });
        mViewModel.obtenerInquilinos(getArguments());
        return root;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        mViewModel = new ViewModelProvider(this).get(InquilinosRVViewModel.class);
        // TODO: Use the ViewModel
    }

}