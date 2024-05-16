package com.example.myaplitfg.api;

import com.example.myaplitfg.Entity.GenericResponse;
import com.example.myaplitfg.Entity.service.Prendas;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;

public interface AtuendosApi {
    String base = "api/atuendos";

    @GET(base)
    Call<GenericResponse<List<Prendas>>> listarAtuendosActivos();
}
