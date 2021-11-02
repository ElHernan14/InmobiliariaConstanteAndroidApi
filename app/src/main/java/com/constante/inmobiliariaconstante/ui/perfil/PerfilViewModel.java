package com.constante.inmobiliariaconstante.ui.perfil;

import android.app.Application;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.MutableLiveData;

import com.constante.inmobiliariaconstante.MainActivity;
import com.constante.inmobiliariaconstante.modelo.Propietario;
import com.constante.inmobiliariaconstante.request.ApiClient;
import com.constante.inmobiliariaconstante.request.ApiRetroFit;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class PerfilViewModel extends AndroidViewModel {
    private Context context;
    private MutableLiveData<Boolean> editables;
    private MutableLiveData<Integer> editar;
    private MutableLiveData<Integer> guardar;
    private MutableLiveData<Propietario> usuario;
    private SharedPreferences sp;

    public PerfilViewModel(@NonNull Application application) {
        super(application);
        this.context = application.getApplicationContext();
        sp = ApiRetroFit.conectar(context);
    }

    public void usuarioActual(){
        String token = sp.getString("Token", "Sin dato");
        Call<Propietario> propietarioCall = ApiRetroFit.getMyApiClient().Perfil(token);
        propietarioCall.enqueue(new Callback<Propietario>() {
            @Override
            public void onResponse(Call<Propietario> call, Response<Propietario> response) {
                if (response.isSuccessful()){
                    usuario.postValue(response.body());
                }else {
                    Toast.makeText(context,"Usuario Propietario NOT Successfuly",Toast.LENGTH_LONG);
                }
            }

            @Override
            public void onFailure(Call<Propietario> call, Throwable t) {
                Toast.makeText(context,"ERROR,USUARIO "+t.getLocalizedMessage(),Toast.LENGTH_LONG);
            }
        });
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
        String token = sp.getString("Token", "Sin dato");
        Call<Propietario> propietarioCall = ApiRetroFit.getMyApiClient().EditarPerfil(token,p);
        propietarioCall.enqueue(new Callback<Propietario>() {
            @Override
            public void onResponse(Call<Propietario> call, Response<Propietario> response) {
                usuario.setValue(p);
                editables.setValue(false);
                editar.setValue(View.VISIBLE);
                guardar.setValue(View.INVISIBLE);
            }

            @Override
            public void onFailure(Call<Propietario> call, Throwable t) {
                Toast.makeText(context,"Error al modificar Propietario",Toast.LENGTH_LONG);
            }
        });

    }

    public void cambiarAEditar(){
        editables.setValue(true);
        editar.setValue(View.INVISIBLE);
        guardar.setValue(View.VISIBLE);
    }

    // TODO: Implement the ViewModel
}