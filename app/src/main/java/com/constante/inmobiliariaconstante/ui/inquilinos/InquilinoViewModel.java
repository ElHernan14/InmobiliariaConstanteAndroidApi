package com.constante.inmobiliariaconstante.ui.inquilinos;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.constante.inmobiliariaconstante.modelo.Inmueble;
import com.constante.inmobiliariaconstante.request.ApiClient;

import java.util.ArrayList;

public class InquilinoViewModel extends ViewModel {

    private MutableLiveData<ArrayList<Inmueble>> inmuebles;
    private ApiClient apiClient;

    public InquilinoViewModel(){
        apiClient = ApiClient.getApi();
    }
    public MutableLiveData<ArrayList<Inmueble>> getInmuebles() {
        if(inmuebles == null){
            inmuebles = new MutableLiveData<>();
        }
        return inmuebles;
    }
    public void obtenerInquilinos(){
        inmuebles.setValue(apiClient.obtenerPropiedadesAlquiladas());
    }
}