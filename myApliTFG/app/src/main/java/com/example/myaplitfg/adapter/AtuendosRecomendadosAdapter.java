package com.example.myaplitfg.adapter;

import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import com.example.myaplitfg.Entity.service.Prendas;
import com.example.myaplitfg.R;
import com.example.myaplitfg.activity.ElTiempoActivity;
import com.example.myaplitfg.api.ConfigApi;
import com.example.myaplitfg.communication.Communication;
import com.example.myaplitfg.utils.DateSerializer;
import com.example.myaplitfg.utils.TimeSerializer;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.squareup.picasso.OkHttp3Downloader;
import com.squareup.picasso.Picasso;
import java.sql.Date;
import java.sql.Time;
import java.util.List;
import cn.pedant.SweetAlert.SweetAlertDialog;

public class AtuendosRecomendadosAdapter extends RecyclerView.Adapter<AtuendosRecomendadosAdapter.ViewHolder> {
    private List<Prendas> prendas;
    private final Communication communication;


    public AtuendosRecomendadosAdapter(List<Prendas> prendas, Communication communication) {
        this.prendas = prendas;
        this.communication = communication;

    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        final View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_atuendos, parent, false);
        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.setItem(this.prendas.get(position));
    }

    @Override
    public int getItemCount() {
        return this.prendas.size();
    }

    public void updateItems(List<Prendas> prendas) {
        this.prendas.clear();
        this.prendas.addAll(prendas);
        this.notifyDataSetChanged();
    }

    protected class ViewHolder extends RecyclerView.ViewHolder {

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
        }

        public void setItem(final Prendas p) {
            ImageView imgPrendas = itemView.findViewById(R.id.imgPrenda);
            TextView namePrendas = itemView.findViewById(R.id.namePrenda);
            Button btnOrdenar = itemView.findViewById(R.id.btnOrdenar);

            String url = ConfigApi.baseUrlE + "/api/documento-almacenado/download/" + p.getImagenUrl();

            Picasso picasso = new Picasso.Builder(itemView.getContext())
                    .downloader(new OkHttp3Downloader(ConfigApi.getClient()))
                    .build();
            picasso.load(url)
                    //.networkPolicy(MemoryPolicy.NO_CACHE, MemoryPolicy.NO_STORE)
                    .error(R.drawable.image_not_found)
                    .into(imgPrendas);
            namePrendas.setText(p.getTipo());
            btnOrdenar.setOnClickListener(v -> {
                Prendas detallePrendas = new Prendas();

            });

            //Inicializar la vista del detalle del platillo
            itemView.setOnClickListener(v -> {
                final Intent i = new Intent(itemView.getContext(), ElTiempoActivity.class);
                final Gson g = new GsonBuilder()
                        .registerTypeAdapter(Date.class, new DateSerializer())
                        .registerTypeAdapter(Time.class, new TimeSerializer())
                        .create();
                i.putExtra("detallePlatillo", g.toJson(p));
                communication.showDetails(i);
            });
        }

        public void successMessage(String message) {
            new SweetAlertDialog(itemView.getContext(),
                    SweetAlertDialog.SUCCESS_TYPE).setTitleText("Buen Trabajo!")
                    .setContentText(message).show();
        }
    }
}
