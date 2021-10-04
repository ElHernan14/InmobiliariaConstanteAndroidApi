package com.constante.inmobiliariaconstante;

import android.app.Application;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.MutableLiveData;

import com.constante.inmobiliariaconstante.modelo.Propietario;
import com.constante.inmobiliariaconstante.request.ApiClient;

public class LoginViewModel extends AndroidViewModel {
    public MutableLiveData<Integer> getMensaje() {
        if (mensaje == null) {
            mensaje = new MutableLiveData<>();
        }
        return mensaje;
    }

    private MutableLiveData<Integer> mensaje;
    private Context context;

    public LoginViewModel(@NonNull Application application) {
        super(application);
        this.context = application.getApplicationContext();
    }

    public void iniciarSesion(String m, String c) {
        ApiClient api = ApiClient.getApi();
        Propietario p = api.login(m, c);
        if (p != null) {
            mensaje.setValue(View.INVISIBLE);
            Intent intent = new Intent(context, MainActivity.class);
            intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            context.startActivity(intent);

        } else {
            mensaje.setValue(View.VISIBLE);
        }
    }
}
