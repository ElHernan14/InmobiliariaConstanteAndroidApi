package com.constante.inmobiliariaconstante.ui.inquilinos;

import android.app.Application;
import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.constante.inmobiliariaconstante.modelo.Inmueble;
import com.constante.inmobiliariaconstante.modelo.Inquilino;
import com.constante.inmobiliariaconstante.request.ApiRetroFit;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class InquilinosRVViewModel extends AndroidViewModel {
    private MutableLiveData<Inquilino> Inquilinos;
    private Context context;
    private SharedPreferences sp;

    public InquilinosRVViewModel(@NonNull Application application) {
        super(application);
        context = application.getApplicationContext();
        sp = ApiRetroFit.conectar(context);
    }

    public MutableLiveData<Inquilino> getInquilinos() {
        if(Inquilinos == null){
            Inquilinos = new MutableLiveData<>();
        }
        return Inquilinos;
    }

    public void obtenerInquilinos(Bundle bundle){
        String token = sp.getString("Token", "Sin dato");
        Inmueble id = (Inmueble) bundle.getSerializable("Inmueble");
        Log.e("llegue", id.getId()+"");
        Call<Inquilino> inquilinoCall = ApiRetroFit.getMyApiClient().InquilinosXInmueble(token,id.getId());
        inquilinoCall.enqueue(new Callback<Inquilino>() {
            @Override
            public void onResponse(Call<Inquilino> call, Response<Inquilino> response) {
                if (response.isSuccessful()){
                    Inquilinos.postValue(response.body());
                    Log.e("llegue", "SUSCCESFULY");
                }else {
                    Toast.makeText(context,"InquilinosXInmueble NOT Successfuly",Toast.LENGTH_LONG);
                    Log.e("llegue", "InquilinosXInmueble NOT Successfuly");
                }
            }

            @Override
            public void onFailure(Call<Inquilino> call, Throwable t) {
                Toast.makeText(context,"Error al obtener InquilinosXInmueble"+t.getLocalizedMessage(),Toast.LENGTH_LONG);
                Log.e("llegue", "Error al obtener InquilinosXInmueble"+t.getMessage());
            }
        });
    }
}