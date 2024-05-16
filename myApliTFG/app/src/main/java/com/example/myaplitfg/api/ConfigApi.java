package com.example.myaplitfg.api;

import com.example.myaplitfg.utils.DateSerializer;
import com.example.myaplitfg.utils.TimeSerializer;
import com.facebook.stetho.okhttp3.StethoInterceptor;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.sql.Time;
import java.util.Date;
import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class ConfigApi {
    public static final String baseUrlE = "http://10.0.2.2:9090";
    private static Retrofit retrofit;
    private static String token="";

    private static UsuariosApi usuariosApi;
    private static ClienteApi clienteApi;
    private static DocumentoAlmacenadoApi documentoAlmacenadoApi;
    private static AtuendosApi atuendosApi;
    private static PrendasApi prendasApi;

    static {
        initClient();
    }

    private static void initClient() {
        Gson gson = new GsonBuilder()
                .registerTypeAdapter(Date.class, new DateSerializer())
                .registerTypeAdapter(Time.class, new TimeSerializer())
                .create();
        retrofit = new Retrofit.Builder()
                .baseUrl(baseUrlE)//Si quieren ejecutar la app desde su móvil, cambiar aquí con la ip de su ordenador
                .addConverterFactory(GsonConverterFactory.create(gson))
                .client(getClient())
                .build();
    }

    public static OkHttpClient getClient() {
        HttpLoggingInterceptor loggin = new HttpLoggingInterceptor();
        loggin.level(HttpLoggingInterceptor.Level.BODY);

        StethoInterceptor stetho = new StethoInterceptor();

        OkHttpClient.Builder builder = new OkHttpClient.Builder();

        builder.addInterceptor(loggin)
                .connectTimeout(60, TimeUnit.SECONDS)
                .readTimeout(60, TimeUnit.SECONDS)
                .writeTimeout(60, TimeUnit.SECONDS)
                .addNetworkInterceptor(stetho);
        return builder.build();
    }

    public static void setToken(String value) {
        token = value;
        initClient();
    }

    public static UsuariosApi getUsuariosApi() {
        if (usuariosApi == null) {
            usuariosApi = retrofit.create(UsuariosApi.class);
        }
        return usuariosApi;
    }

    public static ClienteApi getClienteApi() {
        if (clienteApi == null) {
            clienteApi = retrofit.create(ClienteApi.class);
        }
        return clienteApi;
    }

    public static DocumentoAlmacenadoApi getDocumentoAlmacenadoApi() {
        if (documentoAlmacenadoApi == null) {
            documentoAlmacenadoApi = retrofit.create(DocumentoAlmacenadoApi.class);
        }
        return documentoAlmacenadoApi;
    }

    public static AtuendosApi getAtuendosApi() {
        if (atuendosApi == null) {
            atuendosApi = retrofit.create(AtuendosApi.class);
        }
        return atuendosApi;
    }

    public static PrendasApi getPrendasApi() {
        if (prendasApi == null) {
            prendasApi = retrofit.create(PrendasApi.class);
        }
        return prendasApi;
    }

}
