package com.example.myaplitfg.activity.ui.inicio;

import android.graphics.Color;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.myaplitfg.Entity.SliderItem;
import com.example.myaplitfg.R;
import com.example.myaplitfg.adapter.SliderAdapter;
import com.smarteist.autoimageslider.IndicatorView.animation.type.IndicatorAnimationType;
import com.smarteist.autoimageslider.SliderAnimations;
import com.smarteist.autoimageslider.SliderView;

import java.util.ArrayList;
import java.util.List;

public class InicioFragment extends Fragment {

    private SliderView svCarrusel;
    private SliderAdapter sliderAdapter;

    // Método llamado cuando se crea la vista del fragmento
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // Inflar el diseño del fragmento
        return inflater.inflate(R.layout.fragment_inicio, container, false);
    }

    // Método llamado después de que la vista haya sido creada
    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        // Inicializar las vistas
        init(view);
        // Inicializar el adaptador del carrusel
        initAdapter();
        // Cargar los datos del carrusel
        loadData();
    }

    // Inicializar las vistas
    private void init(View v){
        svCarrusel = v.findViewById(R.id.svCarrusel);
    }

    // Inicializar el adaptador del carrusel
    private void initAdapter() {
        // Crear un nuevo adaptador para el carrusel
        sliderAdapter = new SliderAdapter(getContext());
        // Configurar el adaptador del carrusel
        svCarrusel.setSliderAdapter(sliderAdapter);
        svCarrusel.setIndicatorAnimation(IndicatorAnimationType.WORM);
        svCarrusel.setSliderTransformAnimation(SliderAnimations.SIMPLETRANSFORMATION);
        svCarrusel.setAutoCycleDirection(SliderView.AUTO_CYCLE_DIRECTION_BACK_AND_FORTH);
        svCarrusel.setIndicatorSelectedColor(Color.WHITE);
        svCarrusel.setIndicatorUnselectedColor(Color.GRAY);
        svCarrusel.setScrollTimeInSec(4);
        svCarrusel.startAutoCycle();
    }

    // Cargar los datos del carrusel
    private void loadData() {
        // Crear una lista de elementos del carrusel (aún no implementado)
        List<SliderItem> lista = new ArrayList<>();
    }
}
