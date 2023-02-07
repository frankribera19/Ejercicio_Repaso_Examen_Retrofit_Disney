package com.example.ejerciciorepasoexamenretrofitdisney;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.ejerciciorepasoexamenretrofitdisney.conexiones.ApiConexiones;
import com.example.ejerciciorepasoexamenretrofitdisney.conexiones.RetrofitObject;
import com.example.ejerciciorepasoexamenretrofitdisney.modelos.Personaje;
import com.squareup.picasso.Picasso;

import java.net.HttpURLConnection;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

public class VerPersonajeActivity extends AppCompatActivity {

    private ImageView imgPersonaje;
    private TextView lblNombre;
    private TextView lblFilms;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ver_personaje);

        inicializar();

        if (getIntent().getExtras() != null && getIntent().getExtras().getString("ID") != null){
            cargarPersonaje(getIntent().getExtras().getString("ID"));
        }
    }

    private void cargarPersonaje(String id) {
        Retrofit retrofit = RetrofitObject.getConexion();
        ApiConexiones api = retrofit.create(ApiConexiones.class);

        Call<Personaje> getPersonaje = api.getPersonaje(id);

        getPersonaje.enqueue(new Callback<Personaje>() {
            @Override
            public void onResponse(Call<Personaje> call, Response<Personaje> response) {
                if (response.code() == HttpURLConnection.HTTP_OK){
                    lblNombre.setText(response.body().getName());
                    lblFilms.setText("");
                    for (String film:response.body().getFilms()) {
                        lblFilms.setText(lblFilms.getText()+"\n"+film);
                    }

                    Picasso.get()
                            .load(response.body().getImageUrl())
                            .into(imgPersonaje);
                }
            }

            @Override
            public void onFailure(Call<Personaje> call, Throwable t) {

            }
        });
    }

    private void inicializar() {
        imgPersonaje = findViewById(R.id.imgVerPersonaje);
        lblNombre = findViewById(R.id.lblNombreVerPersonaje);
        lblFilms = findViewById(R.id.lblFilmsVerPersonaje);
    }
}