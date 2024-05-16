package com.example.myaplitfg.repository;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.example.myaplitfg.Entity.GenericResponse;
import com.example.myaplitfg.Entity.service.Cliente;
import com.example.myaplitfg.api.ClienteApi;
import com.example.myaplitfg.api.ConfigApi;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ClienteRepository {
    private static ClienteRepository clienteRepository;
    private final ClienteApi api;

    public static ClienteRepository getInstance(){
        if(clienteRepository == null){
            clienteRepository= new ClienteRepository();
        }
        return clienteRepository;
    }

    private ClienteRepository(){
        api = ConfigApi.getClienteApi();
    }

    public LiveData<GenericResponse<Cliente>> guardarCliente(Cliente c){
        final MutableLiveData<GenericResponse<Cliente>> mld = new MutableLiveData<>();
        this.api.guardarCliente(c).enqueue(new Callback<GenericResponse<Cliente>>() {
            @Override
            public void onResponse(Call<GenericResponse<Cliente>> call, Response<GenericResponse<Cliente>> response) {
                mld.setValue(response.body());
            }

            @Override
            public void onFailure(Call<GenericResponse<Cliente>> call, Throwable t) {
                mld.setValue(new GenericResponse<>());
                System.out.println("Se ha producido un error : " + t.getMessage());
                t.printStackTrace();
            }
        });
        return mld;
    }
}
