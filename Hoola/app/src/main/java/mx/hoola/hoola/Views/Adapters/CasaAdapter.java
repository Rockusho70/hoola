package mx.hoola.hoola.Views.Adapters;

import android.content.Context;
import android.content.res.Resources;
import android.support.annotation.UiThread;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import org.json.JSONException;
import org.json.JSONObject;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;

import mx.hoola.hoola.Models.Inmueble;
import mx.hoola.hoola.Models.List.ListInmuebles;
import mx.hoola.hoola.R;
import mx.hoola.hoola.Tools.StringTools;

/**
 * Created by Isaac on 10/02/2017.
 */

public class CasaAdapter extends RecyclerView.Adapter<CasaAdapter.AnimeViewHolder>{

    private List<JSONObject> items;
    private ArrayList<JSONObject> myList;
    private Context mContext;
    private StringTools tool = new StringTools();

    public CasaAdapter(Context activity, List<JSONObject> items) {
        this.items = items;
        this.mContext = activity;
        setHasStableIds(true);
    }

    @Override
    public int getItemCount() {
        return items.size();
    }

    @Override
    public CasaAdapter.AnimeViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        View v = LayoutInflater.from(viewGroup.getContext())
                .inflate(R.layout.casa_list, viewGroup, false);
        return new CasaAdapter.AnimeViewHolder(v);
    }

    public Resources getResources() {
        Resources resources = null;
        return resources;
    }

    public static class AnimeViewHolder extends RecyclerView.ViewHolder {
        // Campos respectivos de un item
        public ImageView imagen,habimg,pisoimg,banioimg,estaimg;
        public TextView titulo;
        public TextView habitaciones;
        public TextView pisos;
        public TextView banios;
        public TextView estacionamientos;
        public TextView precio;
        public TextView tipo;
        public TextView moneda;

        public AnimeViewHolder(View v) {
            super(v);
            imagen = (ImageView) v.findViewById(R.id.imagenCasa);
            habimg = (ImageView) v.findViewById(R.id.habitacionImagen);
            pisoimg = (ImageView) v.findViewById(R.id.pisoImagen);
            banioimg = (ImageView) v.findViewById(R.id.banioImagen);
            estaimg = (ImageView) v.findViewById(R.id.estacionamientoImagen);
            titulo = (TextView) v.findViewById(R.id.titulo);
            habitaciones = (TextView) v.findViewById(R.id.habitacionTxt);
            pisos = (TextView) v.findViewById(R.id.pisoTxt);
            banios = (TextView) v.findViewById(R.id.baniosTxt);
            estacionamientos = (TextView) v.findViewById(R.id.estacionamientoTxt);
            precio = (TextView) v.findViewById(R.id.precioTxt);
            tipo = (TextView) v.findViewById(R.id.tipoTxt);
            moneda = (TextView) v.findViewById(R.id.mxnTxt);

        }
    }

    @Override
    public void onBindViewHolder(CasaAdapter.AnimeViewHolder viewHolder, int i) {
        try {
            if(items.get(i).getString("tipoPropiedad").equals("Terreno")){
                viewHolder.titulo.setText(items.get(i).getJSONObject("direccion").getString("calle"));
                viewHolder.tipo.setText("Terreno");
                viewHolder.habitaciones.setText("Terreno : "+items.get(i).getJSONArray("caracteristicas").getJSONObject(0).getString("valor")+ " m2");
                //viewHolder.habimg.setImageResource(R.drawable.m2);
                Picasso.with(mContext).load("http://hoola.mx"+items.get(i).getJSONArray("caracteristicas").getJSONObject(0).getString("image_src")).fit().into(viewHolder.habimg);

                Double precio = Double.parseDouble(items.get(i).getJSONObject("precio").getString("precio"));
                DecimalFormat df = new DecimalFormat("#,###");

                viewHolder.precio.setText("$ "+df.format(precio));

                Picasso.with(mContext).load(items.get(i).getJSONObject("media").getJSONArray("imagenes").getJSONObject(0).getString("imagenLink")).fit().into(viewHolder.imagen);
                viewHolder.moneda.setText(items.get(i).getJSONObject("precio").getString("moneda"));
                viewHolder.pisoimg.setVisibility(View.INVISIBLE);
                viewHolder.banioimg.setVisibility(View.INVISIBLE);
                viewHolder.estaimg.setVisibility(View.INVISIBLE);
                viewHolder.pisos.setVisibility(View.INVISIBLE);
                viewHolder.banios.setVisibility(View.INVISIBLE);
                viewHolder.estacionamientos.setVisibility(View.INVISIBLE);
            }
            else if(items.get(i).getString("tipoPropiedad").equals("Bodega")){
                viewHolder.titulo.setText(items.get(i).getJSONObject("direccion").getString("calle"));
                viewHolder.tipo.setText("Bodega");
                viewHolder.habitaciones.setText("Terreno : "+items.get(i).getJSONArray("caracteristicas").getJSONObject(0).getString("valor")+ " m2");
                //viewHolder.habimg.setImageResource(R.drawable.m2);
                Picasso.with(mContext).load("http://hoola.mx"+items.get(i).getJSONArray("caracteristicas").getJSONObject(0).getString("image_src")).fit().into(viewHolder.habimg);

                Double precio = Double.parseDouble(items.get(i).getJSONObject("precio").getString("precio"));
                DecimalFormat df = new DecimalFormat("#,###");

                viewHolder.precio.setText("$ "+df.format(precio));

                Picasso.with(mContext).load(items.get(i).getJSONObject("media").getJSONArray("imagenes").getJSONObject(0).getString("imagenLink")).fit().into(viewHolder.imagen);
                viewHolder.moneda.setText(items.get(i).getJSONObject("precio").getString("moneda"));
                viewHolder.pisoimg.setVisibility(View.INVISIBLE);
                viewHolder.banioimg.setVisibility(View.INVISIBLE);
                viewHolder.estaimg.setVisibility(View.INVISIBLE);
                viewHolder.pisos.setVisibility(View.INVISIBLE);
                viewHolder.banios.setVisibility(View.INVISIBLE);
                viewHolder.estacionamientos.setVisibility(View.INVISIBLE);
            }
            else if (items.get(i).getString("tipoPropiedad").equals("casa") || items.get(i).getString("tipoPropiedad").equals("Local")
                    || items.get(i).getString("tipoPropiedad").equals("Departamento")
                    || items.get(i).getString("tipoPropiedad").equals("Casa")){
                if(items.get(i).getJSONArray("caracteristicas").getJSONObject(3).getString("nombre").equals("Estacionamientos")){
                    viewHolder.titulo.setText(items.get(i).getJSONObject("direccion").getString("calle"));
                    viewHolder.tipo.setText(tool.upperCaseFirst(items.get(i).getString("tipoPropiedad")));
                    viewHolder.habitaciones.setText("Habitaciones: "+items.get(i).getJSONArray("caracteristicas").getJSONObject(0).getString("valor"));
                    viewHolder.banios.setText("Baños: "+items.get(i).getJSONArray("caracteristicas").getJSONObject(1).getString("valor"));
                    viewHolder.pisos.setText("Pisos: "+items.get(i).getJSONArray("caracteristicas").getJSONObject(2).getString("valor"));
                    viewHolder.estacionamientos.setText("Estacionamientos: "+items.get(i).getJSONArray("caracteristicas").getJSONObject(3).getString("valor"));

                    Picasso.with(mContext).load("http://hoola.mx"+items.get(i).getJSONArray("caracteristicas").getJSONObject(0).getString("image_src")).fit().into(viewHolder.habimg);
                    Picasso.with(mContext).load("http://hoola.mx"+items.get(i).getJSONArray("caracteristicas").getJSONObject(1).getString("image_src")).fit().into(viewHolder.pisoimg);
                    Picasso.with(mContext).load("http://hoola.mx"+items.get(i).getJSONArray("caracteristicas").getJSONObject(2).getString("image_src")).fit().into(viewHolder.banioimg);
                    Picasso.with(mContext).load("http://hoola.mx"+items.get(i).getJSONArray("caracteristicas").getJSONObject(3).getString("image_src")).fit().into(viewHolder.estaimg);

                    Double precio = Double.parseDouble(items.get(i).getJSONObject("precio").getString("precio"));
                    DecimalFormat df = new DecimalFormat("#,###");

                    viewHolder.precio.setText("$ "+df.format(precio));

                    Picasso.with(mContext).load(items.get(i).getJSONObject("media").getJSONArray("imagenes").getJSONObject(0).getString("imagenLink")).fit().into(viewHolder.imagen);

                    viewHolder.moneda.setText(items.get(i).getJSONObject("precio").getString("moneda"));
                }
                else{
                    viewHolder.titulo.setText(items.get(i).getJSONObject("direccion").getString("calle"));
                    viewHolder.tipo.setText(items.get(i).getString("tipoPropiedad"));
                    viewHolder.habitaciones.setText("Habitaciones: "+items.get(i).getJSONArray("caracteristicas").getJSONObject(0).getString("valor"));
                    Log.d("Habitaciones:",items.get(i).getJSONArray("caracteristicas").getJSONObject(0).getString("valor"));
                    viewHolder.banios.setText("Baños: "+items.get(i).getJSONArray("caracteristicas").getJSONObject(1).getString("valor"));
                    viewHolder.pisos.setText("Pisos: "+items.get(i).getJSONArray("caracteristicas").getJSONObject(2).getString("valor"));

                    Picasso.with(mContext).load("http://hoola.mx"+items.get(i).getJSONArray("caracteristicas").getJSONObject(0).getString("image_src")).fit().into(viewHolder.habimg);
                    Picasso.with(mContext).load("http://hoola.mx"+items.get(i).getJSONArray("caracteristicas").getJSONObject(1).getString("image_src")).fit().into(viewHolder.pisoimg);
                    Picasso.with(mContext).load("http://hoola.mx"+items.get(i).getJSONArray("caracteristicas").getJSONObject(2).getString("image_src")).fit().into(viewHolder.banioimg);

                    Double precio = Double.parseDouble(items.get(i).getJSONObject("precio").getString("precio"));
                    DecimalFormat df = new DecimalFormat("#,###");

                    viewHolder.precio.setText("$ "+df.format(precio));

                    Picasso.with(mContext).load(items.get(i).getJSONObject("media").getJSONArray("imagenes").getJSONObject(0).getString("imagenLink")).fit().into(viewHolder.imagen);
                    viewHolder.moneda.setText(items.get(i).getJSONObject("precio").getString("moneda"));

                    viewHolder.estacionamientos.setVisibility(View.INVISIBLE);
                    viewHolder.estaimg.setVisibility(View.INVISIBLE);
                }

            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    public JSONObject getItem(int position){
        return items.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public int getItemViewType(int position) {
        return position;
    }
}
