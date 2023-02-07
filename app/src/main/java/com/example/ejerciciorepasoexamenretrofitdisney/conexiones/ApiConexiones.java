package com.example.ejerciciorepasoexamenretrofitdisney.conexiones;

import retrofit2.Call;

import com.example.ejerciciorepasoexamenretrofitdisney.modelos.Personaje;
import com.example.ejerciciorepasoexamenretrofitdisney.modelos.Respuesta;

import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface ApiConexiones {
    //Obtener pagina inicial
    @GET("/characters")
    Call<Respuesta> getPersonajes();

    //Obtener siguiente pagina
    @GET("/characters?")
    Call<Respuesta> getPage(@Query("page") String page);

    //Obtener un personaje
    @GET("/characters/{id}")
    Call<Personaje> getPersonaje(@Path("id") String id);

}
