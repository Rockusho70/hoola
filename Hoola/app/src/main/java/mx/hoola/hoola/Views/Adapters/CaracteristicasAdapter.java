package mx.hoola.hoola.Views.Adapters;

import android.content.Context;
import android.content.res.Resources;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.fasterxml.jackson.databind.JsonNode;
import com.squareup.picasso.Picasso;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import mx.hoola.hoola.Models.Caracteristica;
import mx.hoola.hoola.Models.Inmueble;
import mx.hoola.hoola.Models.List.Caracteristicas;
import mx.hoola.hoola.R;
import mx.hoola.hoola.Tools.StringTools;

/**
 * Created by Isaac on 23/02/2017.
 */

public class CaracteristicasAdapter extends RecyclerView.Adapter<CaracteristicasAdapter.AnimeViewHolder> {

    private List<Caracteristica> items;
    private Context mContext;
    private StringTools tool = new StringTools();

    public CaracteristicasAdapter(Context activity,List<Caracteristica> items) {
        this.items = items;
        this.mContext = activity;
    }

    @Override
    public int getItemCount() {
        return items.size();
    }

    @Override
    public CaracteristicasAdapter.AnimeViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        View v = LayoutInflater.from(viewGroup.getContext())
                .inflate(R.layout.info_list, viewGroup, false);
        return new CaracteristicasAdapter.AnimeViewHolder(v);
    }

    @Override
    public void onBindViewHolder(AnimeViewHolder holder, int i) {
        holder.titulo.setText(items.get(i).getNombre()+ ": "+ items.get(i).getValor());
        Picasso.with(mContext).load("http://hoola.mx"+items.get(i).getFoto()).fit().into(holder.imagen);
        Log.d("Titulo", items.get(i).getNombre()+ ": "+ items.get(i).getValor());
        Log.d("Items size", String.valueOf(i));
    }

    public Resources getResources() {
        Resources resources = null;
        return resources;
    }

    public static class AnimeViewHolder extends RecyclerView.ViewHolder {
        // Campos respectivos de un item
        public ImageView imagen;
        public TextView titulo;
        public Button boton;

        public AnimeViewHolder(View v) {
            super(v);
            imagen = (ImageView) v.findViewById(R.id.caracImg);
            titulo = (TextView) v.findViewById(R.id.caracTxt);
            boton = (Button) v.findViewById(R.id.filtroBtn);

        }
    }

    public Caracteristica getItemSelected(int position){
        return items.get(position);
    }

}
