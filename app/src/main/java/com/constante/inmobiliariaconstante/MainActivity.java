package com.constante.inmobiliariaconstante;

import android.content.DialogInterface;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.Menu;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.load.model.GlideUrl;
import com.bumptech.glide.load.model.LazyHeaders;
import com.constante.inmobiliariaconstante.modelo.Propietario;
import com.constante.inmobiliariaconstante.request.ApiClient;
import com.constante.inmobiliariaconstante.request.ApiRetroFit;
import com.google.android.material.snackbar.Snackbar;
import com.google.android.material.navigation.NavigationView;

import androidx.appcompat.app.AlertDialog;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.appcompat.app.AppCompatActivity;

import com.constante.inmobiliariaconstante.databinding.ActivityMainBinding;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {

    private AppBarConfiguration mAppBarConfiguration;
    private ActivityMainBinding binding;
    private SharedPreferences sp;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        setSupportActionBar(binding.appBarMain.toolbar);
        binding.appBarMain.fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });
        sp = ApiRetroFit.conectar(this);
        DrawerLayout drawer = binding.drawerLayout;
        NavigationView navigationView = binding.navView;
        inicializar(navigationView);
        // Passing each menu ID as a set of Ids because each
        // menu should be considered as top level destinations.
        mAppBarConfiguration = new AppBarConfiguration.Builder(
                R.id.mapaFragment, R.id.nav_perfil, R.id.nav_inmueble, R.id.nav_contrato, R.id.nav_inquilino, R.id.nav_logout)
                .setOpenableLayout(drawer)
                .build();
        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment_content_main);
        NavigationUI.setupActionBarWithNavController(this, navController, mAppBarConfiguration);
        NavigationUI.setupWithNavController(navigationView, navController);
    }

    private void inicializar(NavigationView navigationView){
        View header = navigationView.getHeaderView(0);
        ImageView foto = header.findViewById(R.id.IvFotoPerfil);
        TextView usuario = header.findViewById(R.id.TvNombrePropietario);
        TextView correo = header.findViewById(R.id.TvCorreoPropietario);
        String token = sp.getString("Token", "Sin dato");
        Call<Propietario> propietarioCall = ApiRetroFit.getMyApiClient().Perfil(token);
        propietarioCall.enqueue(new Callback<Propietario>() {
            @Override
            public void onResponse(Call<Propietario> call, Response<Propietario> response) {
                if (response.isSuccessful()){
                    Propietario p = response.body();
                    GlideUrl url = new GlideUrl(ApiRetroFit.getURLBASE()+p.getAvatar(), new LazyHeaders.Builder()
                            .addHeader("User-Agent", "your-user-agent")
                            .build());
                    Glide.with(MainActivity.this)//contexto
                            .load(url)//url de la imagen
                            .error(R.drawable.ic_launcher_background)
                            .diskCacheStrategy(DiskCacheStrategy.ALL)// guarda en el cache
                            .encodeFormat(Bitmap.CompressFormat.JPEG)
                            .into(foto);
                    //foto.setImageResource("http://192.168.0.5:45457/"+p.getAvatar());
                    usuario.setText(p.getNombre());
                    correo.setText(p.getEmail());
                }else{
                    Toast.makeText(MainActivity.this,"PERFIL NOT SUCCESSFUL:",Toast.LENGTH_LONG);
                }
            }

            @Override
            public void onFailure(Call<Propietario> call, Throwable t) {
                Toast.makeText(MainActivity.this,"ERROR CARGAR PERFIL: "+t.getMessage(),Toast.LENGTH_LONG);
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onSupportNavigateUp() {
        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment_content_main);
        return NavigationUI.navigateUp(navController, mAppBarConfiguration)
                || super.onSupportNavigateUp();
    }

    @Override
    public void onBackPressed() {
        new AlertDialog.Builder(this)
                .setTitle("Salir")
                .setMessage("¿Desea salir de la app Inmobiliaria?")
                .setPositiveButton("Sí", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        MainActivity.this.finishAffinity();
                    }
                })
                .setNegativeButton("No", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {

                    }
                })
                .show();
    }

}