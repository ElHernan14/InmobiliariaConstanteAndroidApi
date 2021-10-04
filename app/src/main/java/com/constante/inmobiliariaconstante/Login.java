package com.constante.inmobiliariaconstante;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

public class Login extends AppCompatActivity {
    private LoginViewModel vm;
    private ImageView foto;
    private EditText usuario, contra;
    private TextView mensaje;
    private Button logear;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        inicializar();
        vm = ViewModelProvider.AndroidViewModelFactory.getInstance(getApplication()).create(LoginViewModel.class);
        vm.getMensaje().observe(this, new Observer<Integer>() {
            @Override
            public void onChanged(Integer integer) {
                mensaje.setVisibility(integer);
            }
        });
    }

    public void inicializar(){
        foto = findViewById(R.id.IvFoto);
        usuario = findViewById(R.id.EtCorreo);
        contra = findViewById(R.id.EtContraseña);
        mensaje = findViewById(R.id.TvIncorrecto);
        logear = findViewById(R.id.BtIniciarSesion);
        logear.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                vm.iniciarSesion(usuario.getText().toString(),contra.getText().toString());
            }
        });
    }
}