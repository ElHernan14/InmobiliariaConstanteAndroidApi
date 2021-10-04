package com.constante.inmobiliariaconstante.ui.perfil;

import android.app.Application;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.MutableLiveData;

import com.constante.inmobiliariaconstante.MainActivity;
import com.constante.inmobiliariaconstante.modelo.Propietario;
import com.constante.inmobiliariaconstante.request.ApiClient;

public class PerfilViewModel extends AndroidViewModel {
    private Context context;
    private MutableLiveData<Boolean> editables;
    private MutableLiveData<Integer> editar;
    private MutableLiveData<Integer> guardar;
    private MutableLiveData<Propietario> usuario;
    private ApiClient apiClient;

    public PerfilViewModel(@NonNull Application application) {
        super(application);
        this.context = application.getApplicationContext();
        this.apiClient = ApiClient.getApi();
    }

    public void usuarioActual(){
        Propietario p = apiClient.obtenerUsuarioActual();

        usuario.setValue(apiClient.obtenerUsuarioActual());
    }


    public MutableLiveData<Integer> getEditar() {
        if (editar == null){
            editar = new MutableLiveData<>();
        }
        return editar;
    }

    public MutableLiveData<Boolean> getEditables() {
        if (editables == null){
            editables = new MutableLiveData<>();
        }
        return editables;
    }

    public MutableLiveData<Integer> getGuardar() {
        if (guardar == null){
            guardar = new MutableLiveData<>();
        }
        return guardar;
    }

    public MutableLiveData<Propietario> getUsuario() {
        if (usuario == null){
            usuario = new MutableLiveData<>();
        }
        return usuario;
    }

    public void modificarDatos(Propietario p){
        apiClient.actualizarPerfil(p);
        usuario.setValue(p);
        editables.setValue(false);
        editar.setValue(View.VISIBLE);
        guardar.setValue(View.INVISIBLE);
    }

    public void cambiarAEditar(){
        editables.setValue(true);
        editar.setValue(View.INVISIBLE);
        guardar.setValue(View.VISIBLE);
    }

    // TODO: Implement the ViewModel
}