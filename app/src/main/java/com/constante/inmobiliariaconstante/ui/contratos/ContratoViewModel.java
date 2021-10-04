package com.constante.inmobiliariaconstante.ui.contratos;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.constante.inmobiliariaconstante.modelo.Inmueble;
import com.constante.inmobiliariaconstante.request.ApiClient;

import java.util.ArrayList;

public class ContratoViewModel extends ViewModel {
    private MutableLiveData<ArrayList<Inmueble>> lista;
    private ApiClient apiClient;

    public ContratoViewModel(){
        apiClient = ApiClient.getApi();
    }

    public MutableLiveData<ArrayList<Inmueble>> getLista() {
        if(lista == null){
            lista = new MutableLiveData<>();
        }
        return lista;
    }
    public void obtenerInmuebles() {
        lista.setValue(apiClient.obtenerPropiedadesAlquiladas());
    }
}