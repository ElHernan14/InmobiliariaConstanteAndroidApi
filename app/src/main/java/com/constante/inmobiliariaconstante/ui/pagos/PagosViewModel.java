package com.constante.inmobiliariaconstante.ui.pagos;

import android.app.Application;
import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.constante.inmobiliariaconstante.modelo.Contrato;
import com.constante.inmobiliariaconstante.modelo.Pago;
import com.constante.inmobiliariaconstante.request.ApiRetroFit;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class PagosViewModel extends AndroidViewModel {
    private MutableLiveData<ArrayList<Pago>> pagos;
    private SharedPreferences sp;
    private Context context;

    public PagosViewModel(@NonNull Application application) {
        super(application);
        context = application.getApplicationContext();
        sp = ApiRetroFit.conectar(context);
    }

    public MutableLiveData<ArrayList<Pago>> getPagos() {
        if(pagos == null){
            pagos = new MutableLiveData<>();
        }
        return pagos;
    }

    public void setPagos(Bundle bundle) {
        String token = sp.getString("Token", "Sin dato");
        Contrato contrato = (Contrato) bundle.getSerializable("contrato");
        Call<ArrayList<Pago>> call = ApiRetroFit.getMyApiClient().PagosXContrato(token,contrato.getIdContrato());
        call.enqueue(new Callback<ArrayList<Pago>>() {
            @Override
            public void onResponse(Call<ArrayList<Pago>> call, Response<ArrayList<Pago>> response) {
                if (response.isSuccessful()){
                    pagos.postValue(response.body());
                }else {
                    Toast.makeText(context,"OBTENER PAGOS NOT SUCCESSFUL:",Toast.LENGTH_LONG);
                }
            }

            @Override
            public void onFailure(Call<ArrayList<Pago>> call, Throwable t) {
                Toast.makeText(context,"ERROR OBTENER PAGOS: "+t.getMessage(),Toast.LENGTH_LONG);
            }
        });
    }
}