package com.constante.inmobiliariaconstante.ui.Inmueble;

import android.app.Application;
import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.constante.inmobiliariaconstante.modelo.Inmueble;
import com.constante.inmobiliariaconstante.request.ApiRetroFit;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class InmuebleDetalleViewModel extends AndroidViewModel {
    private MutableLiveData<Inmueble> inmueble;
    private SharedPreferences sp;
    private Context context;
    private Inmueble i;

    public InmuebleDetalleViewModel(@NonNull Application application) {
        super(application);
        context = application.getApplicationContext();
        inmueble = new MutableLiveData<>();
        sp = ApiRetroFit.conectar(context);
    }


    public MutableLiveData<Inmueble> getInmueble() {
        if(inmueble == null){
            inmueble = new MutableLiveData<>();
        }
        return inmueble;
    }

    public void setInmueble(Bundle bundle) {
        i = (Inmueble) bundle.getSerializable("inmueble");
        inmueble.setValue(i);
    }

    public void actualizarInmueble(boolean estado){
        String token = sp.getString("Token", "Sin dato");
        Call<Inmueble> inmuebleCall = ApiRetroFit.getMyApiClient().ModificarEstado(token, i.getId());
        inmuebleCall.enqueue(new Callback<Inmueble>() {
            @Override
            public void onResponse(Call<Inmueble> call, Response<Inmueble> response) {
                if (response.isSuccessful()){
                    inmueble.setValue(response.body());
                    i.setEstado(estado);
                    Toast.makeText(context,"INMUEBLE ACTUALIZADO CORRECTAMENTE",Toast.LENGTH_LONG);
                }else{
                    Toast.makeText(context,"UPDATE NOT SUCCESSFUL:",Toast.LENGTH_LONG);
                }
            }

            @Override
            public void onFailure(Call<Inmueble> call, Throwable t) {
                Toast.makeText(context,"Error Al Actualizar Inmueble:"+t.getLocalizedMessage(),Toast.LENGTH_LONG);
            }
        });
    }
}