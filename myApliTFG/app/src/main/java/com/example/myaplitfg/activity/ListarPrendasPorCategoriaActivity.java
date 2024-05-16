package com.example.myaplitfg.activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;


import android.os.Bundle;
import com.example.myaplitfg.Entity.service.Prendas;
import com.example.myaplitfg.R;
import com.example.myaplitfg.adapter.PrendasPorCategoriaAdapter;
import com.example.myaplitfg.viewModel.PrendasViewModel;
import java.util.ArrayList;
import java.util.List;
import cn.pedant.SweetAlert.SweetAlertDialog;

public class ListarPrendasPorCategoriaActivity extends AppCompatActivity {
    private PrendasViewModel prendasViewModel;
    private PrendasPorCategoriaAdapter adapter;
    private List<Prendas> prendas = new ArrayList<>();
    private RecyclerView rcvPrendasPorCategoria;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_listarprendasporcategoria);
        init();
        initViewModel();
        initAdapter();
        loadData();
    }

    private void init() {
        Toolbar toolbar = this.findViewById(R.id.toolbar);
        toolbar.setNavigationIcon(R.drawable.ic_volver_atras);
        toolbar.setNavigationOnClickListener(v -> {
            this.finish();
            this.overridePendingTransition(R.anim.rigth_in, R.anim.rigth_out);
        });
    }

    private void initViewModel() {
        final ViewModelProvider vmp = new ViewModelProvider(this);
        this.prendasViewModel = vmp.get(PrendasViewModel.class);
    }

    private void initAdapter() {
        adapter = new PrendasPorCategoriaAdapter(prendas);
        rcvPrendasPorCategoria = findViewById(R.id.rcvPrendasPorCategoria);
        rcvPrendasPorCategoria.setAdapter(adapter);
        rcvPrendasPorCategoria.setLayoutManager(new LinearLayoutManager(this));
       }

    private void loadData() {
        int idC = getIntent().getIntExtra("idC", 0);//Recibimos el idCategoria
        prendasViewModel.listarPrendasPorCategoria(idC).observe(this, response -> {
            adapter.updateItems(response.getBody());
        });
    }


    public void successMessage(String message) {
        new SweetAlertDialog(this,
                SweetAlertDialog.SUCCESS_TYPE).setTitleText("Buen Trabajo!")
                .setContentText(message).show();
    }
}