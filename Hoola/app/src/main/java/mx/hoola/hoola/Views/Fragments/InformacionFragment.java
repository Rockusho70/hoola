package mx.hoola.hoola.Views.Fragments;

import android.content.Intent;
import android.graphics.Color;
import android.graphics.DashPathEffect;
import android.graphics.Paint;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.yqritc.recyclerviewflexibledivider.HorizontalDividerItemDecoration;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import mx.hoola.hoola.Controller.DAO.InmuebleController;
import mx.hoola.hoola.Controller.Interfaces.VolleyCallback;
import mx.hoola.hoola.Models.Caracteristica;
import mx.hoola.hoola.Models.List.Caracteristicas;
import mx.hoola.hoola.R;
import mx.hoola.hoola.Tools.RecyclerItemClickListener;
import mx.hoola.hoola.Views.Adapters.CaracteristicasAdapter;
import mx.hoola.hoola.Views.Adapters.CasaAdapter;
import mx.hoola.hoola.Views.DetalleActivity;

/**
 * Created by Isaac on 23/02/2017.
 */

public class InformacionFragment extends Fragment {

    private RecyclerView recycler;
    private CaracteristicasAdapter adapter;
    private RecyclerView.LayoutManager lManager;
    private TextView direccion;
    private InmuebleController controller = new InmuebleController();
    private String json="";
    private JSONObject jsonObj;
    private ArrayList<Caracteristica> items = new ArrayList<>();
    public static String caracteristica="";
    public boolean flag= false;

    public InformacionFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View v =inflater.inflate(R.layout.info_fragment,container,false);
        // Inflate the layout for this fragment

        json = getArguments().getString("info");

        try {
            jsonObj = new JSONObject(json);
        } catch (JSONException e) {
            e.printStackTrace();
        }

        direccion = (TextView)v.findViewById(R.id.direccionTxt);

        try {
            direccion.setText(jsonObj.getJSONObject("direccion").getString("calle"));
        } catch (JSONException e) {
            e.printStackTrace();
        }

        recycler = (RecyclerView) v.findViewById(R.id.infoRecycler);
        recycler.setHasFixedSize(true);

        // Usar un administrador para LinearLayout
        lManager = new LinearLayoutManager(getActivity());
        recycler.setLayoutManager(lManager);
        recycler.addOnItemTouchListener(
                new RecyclerItemClickListener(getActivity() ,new RecyclerItemClickListener.OnItemClickListener() {
                    @Override
                    public void onItemClick(View view, int position) {
                        if(flag==false){
                            caracteristica = adapter.getItemSelected(position).getNombre();
                        }
                    }
                })
        );
        adapter = new CaracteristicasAdapter(getActivity(),items);
        try {
            getItems(jsonObj);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        recycler.setAdapter(adapter);
        adapter.notifyDataSetChanged();

        return v;
    }

    public void getItems(JSONObject obj) throws JSONException {
        int size = obj.getJSONArray("caracteristicas").length();

        if(items!=null)
            items.clear();

        Caracteristica car[] = new Caracteristica[size];
        for(int i =0; i<size; i++){
            car[i]=new Caracteristica();
            car[i].setNombre(obj.getJSONArray("caracteristicas").getJSONObject(i).getString("nombre"));
            car[i].setValor(obj.getJSONArray("caracteristicas").getJSONObject(i).getString("valor"));
            car[i].setFoto(obj.getJSONArray("caracteristicas").getJSONObject(i).getString("image_src"));
            items.add(i,car[i]);
        }
        adapter.notifyDataSetChanged();
    }

}
