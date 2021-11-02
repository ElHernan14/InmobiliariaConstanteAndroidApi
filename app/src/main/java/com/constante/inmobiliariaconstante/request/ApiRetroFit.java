package com.constante.inmobiliariaconstante.request;

import android.content.Context;
import android.content.SharedPreferences;

import com.constante.inmobiliariaconstante.modelo.Contrato;
import com.constante.inmobiliariaconstante.modelo.Inmueble;
import com.constante.inmobiliariaconstante.modelo.Inquilino;
import com.constante.inmobiliariaconstante.modelo.Pago;
import com.constante.inmobiliariaconstante.modelo.Propietario;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.http.Body;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Path;

public class ApiRetroFit {
    private static final String URLBASE="http://192.168.0.5:45465/api/";
    private static final String URLIMAGEN="http://192.168.0.5:45465/";
    private static PostInterface postInterface;
    private static SharedPreferences sp;

    public static String getURLBASE() {
        return URLIMAGEN;
    }

    public static SharedPreferences conectar(Context context)
    {
        if(sp==null)
        {
            sp=context.getSharedPreferences("token.dat",0);
        }
        return sp;
    }

    public static PostInterface getMyApiClient(){
        Gson gson = new GsonBuilder().setLenient().create();
        Retrofit retrofit=new Retrofit.Builder()
                .baseUrl(URLBASE)
                .addConverterFactory(GsonConverterFactory.create(gson))
                .build();

        postInterface=retrofit.create(PostInterface.class);

        return postInterface;
    }

    public interface PostInterface{

        //PROPIETARIOS

        @FormUrlEncoded
        @POST("Propietarios/login")
        Call<String> Login(@Field("Usuario") String usuario, @Field("Clave") String clave);

        @GET("Propietarios")
        Call<Propietario> Perfil(@Header("Authorization") String token);

        @PUT("Propietarios")
        Call<Propietario> EditarPerfil(@Header("Authorization") String token, @Body Propietario propietario);

        //INMUEBLES

        @GET("Inmuebles")
        Call<ArrayList<Inmueble>> InmueblesPropietario(@Header("Authorization") String token);

        @PUT("Inmuebles/{id}")
        Call<Inmueble> ModificarEstado(@Header("Authorization") String token,@Path("id") int idInmueble);

        //INQUILINOS

        @GET("Inquilinos/Contratos")
        Call<ArrayList<Inmueble>> InmueblesContrato(@Header("Authorization") String token);

        @GET("Inquilinos/inqui/{id}")
        Call<Inquilino> InquilinosXInmueble(@Header("Authorization") String token,@Path("id") int idInmueble);

        //CONTRATOS

        @GET("Contratos/inmuebles")
        Call<ArrayList<Inmueble>> ContratoInmuebles(@Header("Authorization") String token);

        @GET("Contratos/{id}")
        Call<ArrayList<Contrato>> ContratosXInmueble(@Header("Authorization") String token, @Path("id") int idInmueble);

        @GET("Contratos/pagos/{id}")
        Call<ArrayList<Pago>> PagosXContrato(@Header("Authorization") String token, @Path("id") int idContrato);
    }
}
