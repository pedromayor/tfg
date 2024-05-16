package com.example.myaplitfg.repository;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.example.myaplitfg.Entity.GenericResponse;
import com.example.myaplitfg.Entity.service.Usuarios;
import com.example.myaplitfg.api.ConfigApi;
import com.example.myaplitfg.api.UsuariosApi;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class UsuariosRepository {
    private static UsuariosRepository repository;
    private final UsuariosApi api;

    public UsuariosRepository(){
        this.api= ConfigApi.getUsuariosApi();
    }

    public static UsuariosRepository getInstance(){
        if(repository == null){
            repository = new UsuariosRepository();
        }
        return repository;
    }

    public LiveData<GenericResponse<Usuarios>> login (String email, String password){
        final MutableLiveData<GenericResponse<Usuarios>> mdl = new MutableLiveData<>();
        this.api.login(email, password).enqueue(new Callback<GenericResponse<Usuarios>>() {

            @Override
            public void onResponse(Call<GenericResponse<Usuarios>> call, Response<GenericResponse<Usuarios>> response) {
                mdl.setValue(response.body());
            }

            @Override
            public void onFailure(Call<GenericResponse<Usuarios>> call, Throwable t) {
                mdl.setValue(new GenericResponse());
                System.out.println("Se ha producido un error al iniciar sesion: " +t.getMessage());
                t.printStackTrace();
            }
        });
        return mdl;
    }

    public LiveData<GenericResponse<Usuarios>> save (Usuarios u){
        final MutableLiveData<GenericResponse<Usuarios>> mdl = new MutableLiveData<>();
        this.api.save(u).enqueue(new Callback<GenericResponse<Usuarios>>() {
            @Override
            public void onResponse(Call<GenericResponse<Usuarios>> call, Response<GenericResponse<Usuarios>> response) {
                mdl.setValue(response.body());
            }

            @Override
            public void onFailure(Call<GenericResponse<Usuarios>> call, Throwable t) {
                mdl.setValue(new GenericResponse<>());
                System.out.println("Se ha producido un error: " + t.getMessage());
                t.printStackTrace();
            }
        });
        return mdl;
    }
}
