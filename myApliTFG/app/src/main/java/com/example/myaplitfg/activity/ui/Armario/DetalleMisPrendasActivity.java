package com.example.myaplitfg.activity.ui.Armario;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import android.os.Bundle;

import com.example.myaplitfg.Entity.service.Prendas;
import com.example.myaplitfg.R;
import com.example.myaplitfg.utils.DateSerializer;
import com.example.myaplitfg.utils.TimeSerializer;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;

import java.sql.Date;
import java.sql.Time;
import java.util.ArrayList;
import java.util.List;

public class DetalleMisPrendasActivity extends AppCompatActivity {
    private RecyclerView rcvDetalleMisPrendas;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detalle_mis_prendas);
        init();
        initAdapter();
        loadData();
    }

    private void init() {
        rcvDetalleMisPrendas = findViewById(R.id.rcvDetalleMisPrendas);
        rcvDetalleMisPrendas.setLayoutManager(new GridLayoutManager(this, 1));
        Toolbar toolbar = this.findViewById(R.id.toolbar);
        toolbar.setNavigationIcon(R.drawable.ic_volver_atras);
        toolbar.setNavigationOnClickListener(v -> {
            this.onBackPressed();
        });
    }

    private void initAdapter() {

    }

    private void loadData() {
        final String detalleString = this.getIntent().getStringExtra("detailsPurchases");
        if (detalleString != null) {
            final Gson g = new GsonBuilder()
                    .registerTypeAdapter(Date.class, new DateSerializer())
                    .registerTypeAdapter(Time.class, new TimeSerializer())
                    .create();
            List<Prendas> detalles = g.fromJson(detalleString,
                    new TypeToken<List<Prendas>>() {
                    }.getType());
                   }
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        this.finish();
        this.overridePendingTransition(R.anim.down_in, R.anim.down_out);
    }
}