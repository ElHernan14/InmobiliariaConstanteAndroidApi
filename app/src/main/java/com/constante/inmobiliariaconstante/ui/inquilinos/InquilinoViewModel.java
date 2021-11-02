package com.constante.inmobiliariaconstante.ui.inquilinos;

import android.app.Application;
import android.content.Context;
import android.content.SharedPreferences;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.MutableLiveData;

import com.constante.inmobiliariaconstante.modelo.Inmueble;
import com.constante.inmobiliariaconstante.request.ApiRetroFit;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class InquilinoViewModel extends AndroidViewModel {
    private SharedPreferences sp;
    private MutableLiveData<ArrayList<Inmueble>> inmuebles;
    private Context context;

    public InquilinoViewModel(@NonNull Application application) {
        super(application);
        context = application.getApplicationContext();
        sp = ApiRetroFit.conectar(context);
    }

    public MutableLiveData<ArrayList<Inmueble>> getInmuebles() {
        if(inmuebles == null){
            inmuebles = new MutableLiveData<>();
        }
        return inmuebles;
    }
    public void obtenerInmuebles(){
        String token = sp.getString("Token", "Sin dato");
        Call<ArrayList<Inmueble>> inmuebleCall = ApiRetroFit.getMyApiClient().InmueblesContrato(token);
        inmuebleCall.enqueue(new Callback<ArrayList<Inmueble>>() {
            @Override
            public void onResponse(Call<ArrayList<Inmueble>> call, Response<ArrayList<Inmueble>> response) {
                if (response.isSuccessful()){
                    inmuebles.postValue(response.body());
                }else {
                    Toast.makeText(context,"InmueblesXInquilino Not Successfuly",Toast.LENGTH_LONG);
                }
            }

            @Override
            public void onFailure(Call<ArrayList<Inmueble>> call, Throwable t) {
                Toast.makeText(context,"Error al obtener InmueblesXContrato"+t.getLocalizedMessage(),Toast.LENGTH_LONG);
            }
        });

    }
}