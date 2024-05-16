package com.example.myaplitfg.api;


import com.example.myaplitfg.Entity.GenericResponse;

import com.example.myaplitfg.Entity.service.Prendas;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

public interface PrendasApi {
    String base = "api/prendas";

    @GET(base)
    Call<GenericResponse<List<Prendas>>> listarPrendas();

    @GET(base + "/{idC}")
    Call<GenericResponse<List<Prendas>>> listarPrendasPorCategoria(@Path("idC") int idC);
}
