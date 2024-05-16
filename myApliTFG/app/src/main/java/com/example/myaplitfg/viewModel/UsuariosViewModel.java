package com.example.myaplitfg.viewModel;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import com.example.myaplitfg.Entity.GenericResponse;
import com.example.myaplitfg.Entity.service.Usuarios;
import com.example.myaplitfg.repository.UsuariosRepository;

public class UsuariosViewModel extends AndroidViewModel {

    private final UsuariosRepository repository;
    public UsuariosViewModel(@NonNull Application application){
        super(application);
        this.repository =UsuariosRepository.getInstance();
    }

    public LiveData<GenericResponse<Usuarios>> login(String email, String password){
        return this.repository.login(email,password);
    }

    public LiveData<GenericResponse<Usuarios>> save(Usuarios u){
        return this.repository.save(u);
    }
}
