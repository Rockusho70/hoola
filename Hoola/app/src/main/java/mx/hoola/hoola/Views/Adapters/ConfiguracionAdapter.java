package mx.hoola.hoola.Views.Adapters;

import android.content.Context;
import android.content.res.Resources;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.fasterxml.jackson.databind.JsonNode;

import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import mx.hoola.hoola.R;
import mx.hoola.hoola.Tools.StringTools;

/**
 * Created by Isaac on 07/03/2017.
 */

public class ConfiguracionAdapter extends RecyclerView.Adapter<ConfiguracionAdapter.AnimeViewHolder> {

    private List<String> items;
    private Context mContext;

    public ConfiguracionAdapter(Context mContext,List<String> items){
        this.items=items;
        this.mContext=mContext;
    }

    @Override
    public ConfiguracionAdapter.AnimeViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        View v = LayoutInflater.from(viewGroup.getContext())
                .inflate(R.layout.config_list, viewGroup, false);
        return new ConfiguracionAdapter.AnimeViewHolder(v);
    }

    @Override
    public void onBindViewHolder(ConfiguracionAdapter.AnimeViewHolder holder, int i) {
        holder.imagen.setImageResource(R.drawable.ic_menu_manage);
        holder.titulo.setText(items.get(i));
    }

    public Resources getResources() {
        Resources resources = null;
        return resources;
    }

    @Override
    public int getItemCount() {
        return items.size();
    }

    public static class AnimeViewHolder extends RecyclerView.ViewHolder {
        // Campos respectivos de un item
        public ImageView imagen;
        public TextView titulo;

        public AnimeViewHolder(View v) {
            super(v);
            imagen = (ImageView) v.findViewById(R.id.configImg);
            titulo = (TextView) v.findViewById(R.id.configTxt);

        }
    }

}
