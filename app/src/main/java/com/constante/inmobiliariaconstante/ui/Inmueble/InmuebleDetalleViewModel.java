package com.constante.inmobiliariaconstante.ui.Inmueble;

import android.os.Bundle;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.constante.inmobiliariaconstante.modelo.Inmueble;
import com.constante.inmobiliariaconstante.request.ApiClient;

public class InmuebleDetalleViewModel extends ViewModel {
    private MutableLiveData<Inmueble> inmueble;
    private ApiClient apiClient;
    private Inmueble i;

    public InmuebleDetalleViewModel(){
        inmueble = new MutableLiveData<>();
        apiClient = ApiClient.getApi();
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
        i.setEstado(estado);
        apiClient.actualizarInmueble(i);
        inmueble.setValue(i);
    }
}