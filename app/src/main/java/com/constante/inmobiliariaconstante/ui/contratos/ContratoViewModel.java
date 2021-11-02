package com.constante.inmobiliariaconstante.ui.contratos;

import android.app.Application;
import android.content.Context;
import android.content.SharedPreferences;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.constante.inmobiliariaconstante.modelo.Inmueble;
import com.constante.inmobiliariaconstante.request.ApiClient;
import com.constante.inmobiliariaconstante.request.ApiRetroFit;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ContratoViewModel extends AndroidViewModel {
    private SharedPreferences sp;
    private Context context;
    private MutableLiveData<ArrayList<Inmueble>> lista;

    public ContratoViewModel(@NonNull Application application) {
        super(application);
        context = application.getApplicationContext();
        sp = ApiRetroFit.conectar(context);
    }


    public MutableLiveData<ArrayList<Inmueble>> getLista() {
        if(lista == null){
            lista = new MutableLiveData<>();
        }
        return lista;
    }
    public void obtenerInmuebles() {
        String token = sp.getString("Token", "Sin dato");
        Call<ArrayList<Inmueble>> call = ApiRetroFit.getMyApiClient().ContratoInmuebles(token);
        call.enqueue(new Callback<ArrayList<Inmueble>>() {
            @Override
            public void onResponse(Call<ArrayList<Inmueble>> call, Response<ArrayList<Inmueble>> response) {
                if (response.isSuccessful()){
                    lista.postValue(response.body());
                }else {
                    Toast.makeText(context,"CONTRATO INMUEBLES NOT SUCCESSFUL:",Toast.LENGTH_LONG);
                }
            }

            @Override
            public void onFailure(Call<ArrayList<Inmueble>> call, Throwable t) {
                Toast.makeText(context,"ERROR CONTRATO INMUEBLES:"+t.getLocalizedMessage(),Toast.LENGTH_LONG);
            }
        });
    }
}