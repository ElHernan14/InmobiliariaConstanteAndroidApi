package com.constante.inmobiliariaconstante.ui.perfil;

import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.text.Editable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.constante.inmobiliariaconstante.R;
import com.constante.inmobiliariaconstante.databinding.PerfilFragmentBinding;
import com.constante.inmobiliariaconstante.modelo.Propietario;

public class PerfilFragment extends Fragment {

    private PerfilViewModel mViewModel;
    private PerfilFragmentBinding binding;

    public static PerfilFragment newInstance() {
        return new PerfilFragment();
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        mViewModel = new ViewModelProvider(this).get(PerfilViewModel.class);
        binding = PerfilFragmentBinding.inflate(inflater, container, false);
        inicializar();
        View root = binding.getRoot();
        return root;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        mViewModel = new ViewModelProvider(this).get(PerfilViewModel.class);
        // TODO: Use the ViewModel
    }

    private void inicializar(){
        mViewModel.getUsuario().observe(getViewLifecycleOwner(), new Observer<Propietario>() {
            @Override
            public void onChanged(Propietario propietario) {
                binding.EtApellido.setText(propietario.getApellido());
                binding.EtCodigo.setText(String.valueOf(propietario.getIdPropietario()));
                binding.EtDni.setText(propietario.getDni().toString());
                binding.EtEmail.setText(propietario.getEmail());
                binding.EtNombre.setText(propietario.getNombre());
                binding.EtTelefono.setText(propietario.getTelefono());
                binding.eTContraseA.setText(propietario.getClave());
            }
        });
        mViewModel.getEditables().observe(getViewLifecycleOwner(), new Observer<Boolean>() {
            @Override
            public void onChanged(Boolean aBoolean) {
                binding.EtApellido.setEnabled(aBoolean);
                binding.EtDni.setEnabled(aBoolean);
                binding.EtEmail.setEnabled(aBoolean);
                binding.EtNombre.setEnabled(aBoolean);
                binding.EtTelefono.setEnabled(aBoolean);
                binding.eTContraseA.setEnabled(aBoolean);
            }
        });
        mViewModel.getEditar().observe(getViewLifecycleOwner(), new Observer<Integer>() {
            @Override
            public void onChanged(Integer integer) {
                binding.BTEditar.setVisibility(integer);
            }
        });
        mViewModel.getGuardar().observe(getViewLifecycleOwner(), new Observer<Integer>() {
            @Override
            public void onChanged(Integer integer) {
                binding.BTGuardar.setVisibility(integer);
            }
        });
        binding.BTEditar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mViewModel.cambiarAEditar();
            }
        });
        binding.BTGuardar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Propietario p = new Propietario();
                p.setApellido(binding.EtApellido.getText().toString());
                p.setClave(binding.eTContraseA.getText().toString());
                p.setDni(Long.parseLong(binding.EtDni.getText().toString()));
                p.setEmail(binding.EtEmail.getText().toString());
                p.setIdPropietario(Integer.parseInt(binding.EtCodigo.getText().toString()));
                p.setTelefono(binding.EtTelefono.getText().toString());
                p.setNombre(binding.EtNombre.getText().toString());
                mViewModel.modificarDatos(p);
            }
        });
        mViewModel.usuarioActual();
    }
    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}