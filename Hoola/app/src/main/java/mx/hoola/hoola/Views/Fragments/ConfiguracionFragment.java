package mx.hoola.hoola.Views.Fragments;

import android.graphics.Color;
import android.graphics.DashPathEffect;
import android.graphics.Paint;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.fasterxml.jackson.databind.JsonNode;
import com.yqritc.recyclerviewflexibledivider.HorizontalDividerItemDecoration;

import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Map;

import mx.hoola.hoola.R;
import mx.hoola.hoola.Tools.RecyclerItemClickListener;
import mx.hoola.hoola.Views.Adapters.ConfiguracionAdapter;
import mx.hoola.hoola.Views.Adapters.ContactoAdapter;

/**
 * Created by Isaac on 03/03/2017.
 */

public class ConfiguracionFragment extends Fragment {

    private RecyclerView recycler;
    private ConfiguracionAdapter adapter;
    private RecyclerView.LayoutManager lManager;
    private ArrayList<String> items = new ArrayList<>();
    private String[] options = new String[]{"Aviso de privacidad","Condiciones de uso","Publicidad",
            "Registrarse", "hoola", "Reportar un problema"};
    private Button conectar;

    public ConfiguracionFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View v =inflater.inflate(R.layout.configuracion_fragment,container,false);

        conectar = (Button)v.findViewById(R.id.conectar);

        recycler = (RecyclerView) v.findViewById(R.id.configRecycler);
        recycler.setHasFixedSize(true);

        // Usar un administrador para LinearLayout
        lManager = new LinearLayoutManager(getActivity());
        recycler.setLayoutManager(lManager);
        recycler.addOnItemTouchListener(
                new RecyclerItemClickListener(getActivity() ,new RecyclerItemClickListener.OnItemClickListener() {
                    @Override
                    public void onItemClick(View view, int position) {

                    }
                })
        );
        adapter = new ConfiguracionAdapter(getActivity(),items);
        fillList(options);
        recycler.setAdapter(adapter);

        Paint paint = new Paint();
        paint.setStrokeWidth(10);
        paint.setColor(Color.parseColor("#eef1f5"));
        paint.setAntiAlias(true);
        paint.setPathEffect(new DashPathEffect(new float[]{25.0f, 25.0f}, 0));

        recycler.addItemDecoration(new HorizontalDividerItemDecoration.Builder(getActivity()).paint(paint).build());

        return v;
    }

    public void fillList(String[] array){
        if(items!=null){
            items.clear();
        }
        for(int i=0; i<array.length; i++){
            items.add(i,array[i]);
        }
        adapter.notifyDataSetChanged();
    }
}
