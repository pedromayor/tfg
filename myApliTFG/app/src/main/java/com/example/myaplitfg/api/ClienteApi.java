package com.example.myaplitfg.api;

import com.example.myaplitfg.Entity.GenericResponse;
import com.example.myaplitfg.Entity.service.Cliente;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.POST;

public interface ClienteApi {

    String base = "/cliente";
    @POST(base)
    Call<GenericResponse<Cliente>> guardarCliente(@Body Cliente c);
}
