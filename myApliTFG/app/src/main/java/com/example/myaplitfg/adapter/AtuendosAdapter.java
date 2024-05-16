package com.example.myaplitfg.adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import com.example.myaplitfg.Entity.service.Atuendos;
import com.example.myaplitfg.R;
import com.example.myaplitfg.activity.ListarPrendasPorCategoriaActivity;
import com.example.myaplitfg.api.ConfigApi;
import com.squareup.picasso.OkHttp3Downloader;
import com.squareup.picasso.Picasso;

import java.util.List;


public class AtuendosAdapter extends ArrayAdapter<Atuendos> {
    private final String url = ConfigApi.baseUrlE + "/api/documento-almacenado/download/";


    public AtuendosAdapter(@NonNull Context context, int resource, @NonNull List<Atuendos> objects) {
        super(context, resource, objects);
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        if(convertView == null){
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.item_atuendos, parent, false);
        }
        Atuendos c = this.getItem(position);
        ImageView imgAtuendos = convertView.findViewById(R.id.imgAtuendo);
        TextView txtNombreAtuendo= convertView.findViewById(R.id.txtNombreAtuendo);

        Picasso picasso = new Picasso.Builder(convertView.getContext())
                .downloader(new OkHttp3Downloader(ConfigApi.getClient()))
                .build();
        picasso.load(url + c.getImagenUrl())
                //.networkPolicy(MemoryPolicy.NO_CACHE, MemoryPolicy.NO_STORE)
                .error(R.drawable.image_not_found)
                .into(imgAtuendos);
        txtNombreAtuendo.setText(c.getNombre());
        convertView.setOnClickListener(v -> {
            Intent i = new Intent(getContext(), ListarPrendasPorCategoriaActivity.class);
            i.putExtra("idC", c.getAtuendoId());//Obtenenmos el id de la Categoria
            getContext().startActivity(i);
        });
        return convertView;
    }
}
