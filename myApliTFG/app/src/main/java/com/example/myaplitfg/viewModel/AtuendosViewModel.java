package com.example.myaplitfg.viewModel;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.MutableLiveData;

import com.example.myaplitfg.Entity.GenericResponse;

import com.example.myaplitfg.Entity.service.Prendas;
import com.example.myaplitfg.repository.AtuendosRepository;

import java.util.List;


public class AtuendosViewModel extends AndroidViewModel {
    private final AtuendosRepository repository;


    public AtuendosViewModel(@NonNull Application application) {
        super(application);
        this.repository = AtuendosRepository.getInstance();
    }

    public MutableLiveData<GenericResponse<List<Prendas>>> listarAtuendosActivos(){
        return this.repository.listarAtuendosActivos();
    }
}
