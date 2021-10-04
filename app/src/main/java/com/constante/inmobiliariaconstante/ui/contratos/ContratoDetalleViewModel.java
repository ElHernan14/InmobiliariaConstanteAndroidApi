package com.constante.inmobiliariaconstante.ui.contratos;

import android.os.Bundle;
import android.view.View;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;
import androidx.navigation.Navigation;

import com.constante.inmobiliariaconstante.R;
import com.constante.inmobiliariaconstante.modelo.Contrato;
import com.constante.inmobiliariaconstante.modelo.Inquilino;
import com.constante.inmobiliariaconstante.modelo.Pago;
import com.constante.inmobiliariaconstante.request.ApiClient;

import java.util.ArrayList;

public class ContratoDetalleViewModel extends ViewModel {
    private MutableLiveData<Contrato> contrato;
    private Contrato c;
    private ApiClient apiClient;

    public ContratoDetalleViewModel(){
        contrato = new MutableLiveData<>();
        apiClient = ApiClient.getApi();
    }
    public MutableLiveData<Contrato> getContrato() {
        return contrato;
    }

    public void setContrato(Bundle bundle) {
        c = (Contrato) bundle.getSerializable("contrato");
        contrato.setValue(c);
    }

    public void verPagos(View root){
        Bundle bundle = new Bundle();
        ArrayList<Pago> p = apiClient.obtenerPagos(c);
        bundle.putSerializable("pagos",p);
        Navigation.findNavController(root).navigate(R.id.pagosFragment,bundle);
    }
}