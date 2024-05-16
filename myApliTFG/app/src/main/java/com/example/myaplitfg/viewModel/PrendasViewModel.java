package com.example.myaplitfg.viewModel;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import com.example.myaplitfg.Entity.GenericResponse;
import com.example.myaplitfg.Entity.service.Prendas;
import com.example.myaplitfg.repository.PrendasRepository;

import java.util.List;


public class PrendasViewModel extends AndroidViewModel {
    private final PrendasRepository repository;

    public PrendasViewModel(@NonNull Application application) {
        super(application);
        repository = PrendasRepository.getInstance();
    }
    public LiveData<GenericResponse<List<Prendas>>> listarPrendasRecomendadas(){
        return this.repository.listarPrendasRecomendadas();
    }
    public LiveData<GenericResponse<List<Prendas>>> listarPrendasPorCategoria(int idC){
        return this.repository.listarPrendasPorCategoria(idC);
    }
}
