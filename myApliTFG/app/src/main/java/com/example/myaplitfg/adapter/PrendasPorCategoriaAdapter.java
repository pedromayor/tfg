package com.example.myaplitfg.adapter;

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
import com.example.myaplitfg.api.ConfigApi;
import com.squareup.picasso.OkHttp3Downloader;
import com.squareup.picasso.Picasso;

import java.util.List;
import java.util.Locale;

public class PrendasPorCategoriaAdapter extends RecyclerView.Adapter<PrendasPorCategoriaAdapter.ViewHolder> {
    private List<Prendas> listadoPrendasPorCategoria;


    public PrendasPorCategoriaAdapter(List<Prendas> listadoPrendasPorCategoria) {
        this.listadoPrendasPorCategoria = listadoPrendasPorCategoria;

    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_prendasporcategoria, parent, false);
        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.setItem(this.listadoPrendasPorCategoria.get(position));
    }

    @Override
    public int getItemCount() {
        return this.listadoPrendasPorCategoria.size();
    }
    public void updateItems(List<Prendas> prendasByCategoria){
        this.listadoPrendasPorCategoria.clear();
        this.listadoPrendasPorCategoria.addAll(prendasByCategoria);
        this.notifyDataSetChanged();
    }

    protected class ViewHolder extends RecyclerView.ViewHolder {
        private final ImageView imgPrenda;
        private final TextView namePrenda;
        private final Button btnOrdenarPC;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            this.imgPrenda = itemView.findViewById(R.id.imgPrenda);
            this.namePrenda = itemView.findViewById(R.id.namePrenda);
            this.btnOrdenarPC = itemView.findViewById(R.id.btnOrdenar);
        }

        public void setItem(final Prendas p) {
            String url = ConfigApi.baseUrlE + "/api/documento-almacenado/download/" + p.getImagenUrl();

            Picasso picasso = new Picasso.Builder(itemView.getContext())
                    .downloader(new OkHttp3Downloader(ConfigApi.getClient()))
                    .build();
            picasso.load(url)
                    //.networkPolicy(MemoryPolicy.NO_CACHE, MemoryPolicy.NO_STORE) //No lo almacena el la caché ni en el disco
                    .error(R.drawable.image_not_found)
                    .into(imgPrenda);
            namePrenda.setText(p.getTipo());
            btnOrdenarPC.setOnClickListener(v -> {
                Prendas prendas = new Prendas();
               prendas.setPrendaId();
            });
        }
    }
}
