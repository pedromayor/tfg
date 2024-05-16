package com.example.myaplitfg.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.myaplitfg.Entity.service.Condicionesmeteorologicas;
import com.example.myaplitfg.R;
import com.squareup.picasso.Picasso;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

public class CondicionesmeteorologicasAdapter extends RecyclerView.Adapter<CondicionesmeteorologicasAdapter.ViewHolder>{

    private Context context;
    private ArrayList<Condicionesmeteorologicas> CondicionesmeteorologicasArrayList;

    public CondicionesmeteorologicasAdapter(Context context, ArrayList<Condicionesmeteorologicas> condicionesmeteorologicasArrayList) {
        this.context = context;
        CondicionesmeteorologicasArrayList = condicionesmeteorologicasArrayList;
    }

    @NonNull
    @Override
    public CondicionesmeteorologicasAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
       View view = LayoutInflater.from(context).inflate(R.layout.condicionesmeteorologicas_item, parent, false);
       return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull CondicionesmeteorologicasAdapter.ViewHolder holder, int position) {

        Condicionesmeteorologicas modal = CondicionesmeteorologicasArrayList.get(position);
        holder.temperatureTV.setText(modal.getTemperature() + "ºc");
        Picasso.get().load("http:".concat(modal.getIcon())).into(holder.conditionTV);
        holder.windTV.setText(modal.getWindSpeed() + "km/h");
        SimpleDateFormat input = new SimpleDateFormat("yyyy-MM-dd hh:mm");
        SimpleDateFormat output = new SimpleDateFormat("hh:mm aa");
        try{
            Date t = input.parse(modal.getTime());
            holder.timeTV.setText(output.format(t));
        }catch (ParseException e){
            e.printStackTrace();
        }
    }

    @Override
    public int getItemCount() {
        return CondicionesmeteorologicasArrayList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{
        private TextView windTV, temperatureTV, timeTV;
        private ImageView conditionTV;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            windTV = itemView.findViewById(R.id.idTVWindSpeed);
            temperatureTV = itemView.findViewById(R.id.idTVTemperature);
            timeTV = itemView.findViewById(R.id.idTVTime);
            conditionTV = itemView.findViewById(R.id.idIVCondition);
        }
    }
}
