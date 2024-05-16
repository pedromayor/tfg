package com.example.myaplitfg.repository;


import androidx.lifecycle.MutableLiveData;
import com.example.myaplitfg.Entity.GenericResponse;
import com.example.myaplitfg.Entity.service.Prendas;
import com.example.myaplitfg.api.AtuendosApi;
import com.example.myaplitfg.api.ConfigApi;
import java.util.List;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class AtuendosRepository {
    private final AtuendosApi api;
    private static AtuendosRepository repository;

    public AtuendosRepository() {
        this.api = ConfigApi.getAtuendosApi();
    }
    public static AtuendosRepository getInstance(){
        if(repository == null){
            repository = new AtuendosRepository();
        }
        return repository;
    }
    public MutableLiveData<GenericResponse<List<Prendas>>> listarAtuendosActivos(){
        final MutableLiveData<GenericResponse<List<Prendas>>> mld = new MutableLiveData<>();
        this.api.listarAtuendosActivos().enqueue(new Callback<GenericResponse<List<Prendas>>>() {
            @Override
            public void onResponse(Call<GenericResponse<List<Prendas>>> call, Response<GenericResponse<List<Prendas>>> response) {
                if (response.isSuccessful() && response.body() != null) {
                    mld.setValue(response.body());
                } else {
                    // Aquí podrías manejar diferentes códigos de error
                    System.out.println("Error en la respuesta: " + response.code() + " - " + response.message());
                }
            }

            @Override
            public void onFailure(Call<GenericResponse<List<Prendas>>> call, Throwable t) {
                System.out.println("Error al obtener los Atuendos: " + t.getMessage());
                t.printStackTrace();
            }
        });
        return mld;
    }
}
