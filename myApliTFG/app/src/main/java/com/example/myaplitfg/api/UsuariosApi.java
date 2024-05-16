package com.example.myaplitfg.api;


import com.example.myaplitfg.Entity.GenericResponse;
import com.example.myaplitfg.Entity.service.Usuarios;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;

public interface UsuariosApi {
    //RUTA DEL CONTROLADOR USUARIO
    String base = "/usuarios";

    //RUTA DEL CONTROLADOR USUARIO + LA RUTA DEL MÉTODO
    @FormUrlEncoded
    @POST(base + "/login")
    Call<GenericResponse<Usuarios>> login(@Field("email") String email, @Field("password") String contrasenia);

    @POST(base)
    Call<GenericResponse<Usuarios>> save (@Body Usuarios u);
}
