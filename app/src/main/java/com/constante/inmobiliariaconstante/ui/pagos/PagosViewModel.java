package com.constante.inmobiliariaconstante.ui.pagos;

import android.os.Bundle;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.constante.inmobiliariaconstante.modelo.Pago;

import java.util.ArrayList;

public class PagosViewModel extends ViewModel {
    private MutableLiveData<ArrayList<Pago>> pagos;

    public MutableLiveData<ArrayList<Pago>> getPagos() {
        if(pagos == null){
            pagos = new MutableLiveData<>();
        }
        return pagos;
    }

    public void setPagos(Bundle bundle) {
        pagos.setValue((ArrayList<Pago>) bundle.getSerializable("pagos"));
    }
}