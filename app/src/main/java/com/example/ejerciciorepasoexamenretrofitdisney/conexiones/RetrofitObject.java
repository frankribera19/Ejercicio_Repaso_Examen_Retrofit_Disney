package com.example.ejerciciorepasoexamenretrofitdisney.conexiones;

import com.example.ejerciciorepasoexamenretrofitdisney.configuraciones.Constantes;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class RetrofitObject {

    public static Retrofit getConexion(){
        return new Retrofit.Builder()
                .baseUrl(Constantes.URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
    }
}
