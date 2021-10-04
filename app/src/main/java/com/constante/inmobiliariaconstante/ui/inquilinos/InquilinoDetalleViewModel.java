package com.constante.inmobiliariaconstante.ui.inquilinos;

import android.os.Bundle;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.constante.inmobiliariaconstante.modelo.Inmueble;
import com.constante.inmobiliariaconstante.modelo.Inquilino;

public class InquilinoDetalleViewModel extends ViewModel {
    private MutableLiveData<Inquilino> inquilino;
    private Inquilino i;

    public InquilinoDetalleViewModel(){
        inquilino = new MutableLiveData<>();
    }
    public MutableLiveData<Inquilino> getInquilino() {
        return inquilino;
    }

    public void setInquilino(Bundle bundle) {
        i = (Inquilino) bundle.getSerializable("inquilino");
        inquilino.setValue(i);
    }
}