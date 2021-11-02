package com.constante.inmobiliariaconstante;

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

import com.constante.inmobiliariaconstante.modelo.Propietario;
import com.constante.inmobiliariaconstante.request.ApiClient;
import com.constante.inmobiliariaconstante.request.ApiRetroFit;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class LoginViewModel extends AndroidViewModel {
    public MutableLiveData<Integer> getMensaje() {
        if (mensaje == null) {
            mensaje = new MutableLiveData<>();
        }
        return mensaje;
    }

    private MutableLiveData<Integer> mensaje;
    private Context context;
    private SharedPreferences sp;

    public LoginViewModel(@NonNull Application application) {
        super(application);
        this.context = application.getApplicationContext();
        sp=ApiRetroFit.conectar(context);
    }

    public void iniciarSesion(String m, String c) {
        Call<String> token = ApiRetroFit.getMyApiClient().Login(m,c);
        token.enqueue(new Callback<String>() {
            @Override
            public void onResponse(Call<String> call, Response<String> response) {
                if (response.isSuccessful()){
                    SharedPreferences.Editor editor=sp.edit();
                    editor.putString("Token","Bearer "+response.body());
                    editor.commit();
                    mensaje.setValue(View.INVISIBLE);
                    Intent intent = new Intent(context, MainActivity.class);
                    intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                    context.startActivity(intent);
                }else {
                    mensaje.setValue(View.VISIBLE);
                }
            }

            @Override
            public void onFailure(Call<String> call, Throwable t) {
                Toast.makeText(context,"Error Login:"+t.getLocalizedMessage(),Toast.LENGTH_LONG);
            }
        });
    }
}
