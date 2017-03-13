package mx.hoola.hoola.Views.Fragments;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.DashPathEffect;
import android.graphics.Paint;
import android.os.Looper;
import android.os.Parcelable;
import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.Volley;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.yqritc.recyclerviewflexibledivider.HorizontalDividerItemDecoration;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.logging.Handler;

import mx.hoola.hoola.Controller.DAO.InmuebleController;
import mx.hoola.hoola.Controller.Interfaces.OnDataSendInmuebles;
import mx.hoola.hoola.Controller.Interfaces.VolleyCallback;
import mx.hoola.hoola.Controller.WebService;
import mx.hoola.hoola.Models.Inmueble;
import mx.hoola.hoola.R;
import mx.hoola.hoola.Tools.RecyclerItemClickListener;
import mx.hoola.hoola.Views.Adapters.CasaAdapter;
import mx.hoola.hoola.Views.DetalleActivity;

/**
 * Created by Isaac on 13/02/2017.
 */

public class InicioFragment extends Fragment implements VolleyCallback{

    private RecyclerView recycler;
    private CasaAdapter adapter;
    private RecyclerView.LayoutManager lManager;
    private ArrayList<JSONObject> items = new ArrayList<>();

    private InmuebleController controller = new InmuebleController();
    private VolleyCallback call = new VolleyCallback() {

        @Override
        public void onSuccessResponse(JSONArray json) {
            try {
                getItems(json);
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }
    };


    public InicioFragment(){}

    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable final ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v =inflater.inflate(R.layout.inicio_fragment,container,false);

        try {
            controller.getInmuebles(getContext(),call);
        } catch (JSONException e) {
            e.printStackTrace();
        }


        recycler = (RecyclerView) v.findViewById(R.id.inicioList);
        recycler.setHasFixedSize(true);

        // Usar un administrador para LinearLayout
        lManager = new LinearLayoutManager(getActivity());
        recycler.setLayoutManager(lManager);
        //final RecyclerView.ItemDecoration itemDecoration = new SampleDivider(getActivity());
        //recycler.addItemDecoration(itemDecoration);
        recycler.addOnItemTouchListener(
                new RecyclerItemClickListener(getActivity() ,new RecyclerItemClickListener.OnItemClickListener() {
                    @Override
                    public void onItemClick(View view, int position) {

                        //Toast.makeText(getActivity(), "position = " +items.get(position).getId(), Toast.LENGTH_SHORT).show();
                        JSONObject inm = null;
                        inm = adapter.getItem(position);
                        //listener.sendData(inm);
                        Intent intent = new Intent(getActivity(), DetalleActivity.class);
                        intent.putExtra("inmueble", inm.toString());
                        getActivity().startActivity(intent);

                    }
                })
        );
        adapter = new CasaAdapter(getActivity(),items);
        recycler.setAdapter(adapter);

        Paint paint = new Paint();
        paint.setStrokeWidth(30);
        paint.setColor(Color.parseColor("#eef1f5"));
        paint.setAntiAlias(true);
        paint.setPathEffect(new DashPathEffect(new float[]{25.0f, 25.0f}, 0));

        recycler.addItemDecoration(new HorizontalDividerItemDecoration.Builder(getActivity()).paint(paint).build());


        return v;
    }

    public void getItems(JSONArray a) throws JSONException {
        if(items!=null){
            items.clear();
        }
        for(int i =0; i < a.length(); i++){
            JSONObject json = a.getJSONObject(i);
            items.add(i,json);
        }
        adapter.notifyDataSetChanged();
    }


    @Override
    public void onSuccessResponse(JSONArray json) {
        try {
            getItems(json);
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }
}
