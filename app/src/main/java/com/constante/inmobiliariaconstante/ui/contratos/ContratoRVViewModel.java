package com.constante.inmobiliariaconstante.ui.contratos;

import android.app.Application;
import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.MutableLiveData;

import com.constante.inmobiliariaconstante.modelo.Contrato;
import com.constante.inmobiliariaconstante.modelo.Inmueble;
import com.constante.inmobiliariaconstante.request.ApiRetroFit;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ContratoRVViewModel extends AndroidViewModel {
    private SharedPreferences sp;
    private Context context;
    private MutableLiveData<ArrayList<Contrato>> lista;

    public ContratoRVViewModel(@NonNull Application application) {
        super(application);
        context = application.getApplicationContext();
        sp = ApiRetroFit.conectar(context);
    }

    public MutableLiveData<ArrayList<Contrato>> getLista() {
        if(lista == null){
            lista = new MutableLiveData<>();
        }
        return lista;
    }

    public void obtenerContratos(Bundle bundle){
        String token = sp.getString("Token", "Sin dato");
        Inmueble id = (Inmueble) bundle.getSerializable("Inmueble");
        Log.e("llegue", id.getId()+"");
        Call<ArrayList<Contrato>> call = ApiRetroFit.getMyApiClient().ContratosXInmueble(token,id.getId());
        call.enqueue(new Callback<ArrayList<Contrato>>() {
            @Override
            public void onResponse(Call<ArrayList<Contrato>> call, Response<ArrayList<Contrato>> response) {
                if (response.isSuccessful()){
                    lista.postValue(response.body());
                    Log.e("llegue", "is successfull de contratos");
                }else {
                    Log.e("llegue", "is NOT successfull de contratos");
                }
            }

            @Override
            public void onFailure(Call<ArrayList<Contrato>> call, Throwable t) {
                Toast.makeText(context,"ERROR OBTENER CONTRATOS:"+t.getLocalizedMessage(),Toast.LENGTH_LONG);
                Log.e("llegue", "ierror obtener contratos");
            }
        });
    }
}