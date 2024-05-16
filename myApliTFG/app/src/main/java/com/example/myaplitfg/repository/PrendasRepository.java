package com.example.myaplitfg.repository;


import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import com.example.myaplitfg.Entity.GenericResponse;
import com.example.myaplitfg.Entity.service.Prendas;
import com.example.myaplitfg.api.ConfigApi;
import com.example.myaplitfg.api.PrendasApi;
import java.util.List;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class PrendasRepository {
    private final PrendasApi api;
    private static PrendasRepository repository;

    public PrendasRepository() {
        this.api = ConfigApi.getPrendasApi();
    }

    public static PrendasRepository getInstance() {
        if (repository == null) {
            repository = new PrendasRepository();
        }
        return repository;
    }

    public LiveData<GenericResponse<List<Prendas>>> listarPrendasRecomendadas(){
        final MutableLiveData<GenericResponse<List<Prendas>>> mld = new MutableLiveData<>();
        this.api.listarPrendas().enqueue(new Callback<GenericResponse<List<Prendas>>>() {
            @Override
            public void onResponse(Call<GenericResponse<List<Prendas>>> call, Response<GenericResponse<List<Prendas>>> response) {
                mld.setValue(response.body());
            }

            @Override
            public void onFailure(Call<GenericResponse<List<Prendas>>> call, Throwable t) {
                mld.setValue(new GenericResponse<>());
                t.printStackTrace();
            }
        });
        return mld;
    }

    public LiveData<GenericResponse<List<Prendas>>> listarPrendasPorCategoria(int idC){
        final MutableLiveData<GenericResponse<List<Prendas>>> mld = new MutableLiveData<>();
        this.api.listarPrendasPorCategoria(idC).enqueue(new Callback<GenericResponse<List<Prendas>>>() {
            @Override
            public void onResponse(Call<GenericResponse<List<Prendas>>> call, Response<GenericResponse<List<Prendas>>> response) {
                mld.setValue(response.body());
            }

            @Override
            public void onFailure(Call<GenericResponse<List<Prendas>>> call, Throwable t) {
                mld.setValue(new GenericResponse<>());
                t.printStackTrace();
            }
        });
        return mld;
    }
}
