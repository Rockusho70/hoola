package mx.hoola.hoola.Views.Fragments;

import android.content.Intent;
import android.net.Uri;
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

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Map;

import mx.hoola.hoola.Controller.DAO.InmuebleController;
import mx.hoola.hoola.Models.Header;
import mx.hoola.hoola.R;
import mx.hoola.hoola.Tools.RecyclerItemClickListener;
import mx.hoola.hoola.Views.Adapters.CasaAdapter;
import mx.hoola.hoola.Views.Adapters.ContactoAdapter;

/**
 * Created by Isaac on 17/02/2017.
 */

public class ContactoFragment extends Fragment {

    private RecyclerView recycler;
    private ContactoAdapter adapter;
    private RecyclerView.LayoutManager lManager;
    private ArrayList<Map.Entry<String, JsonNode>> items = new ArrayList<>();
    private String json="";
    private JSONObject jsonObj;
    private TextView nombre;
    Header header = new Header();

    public ContactoFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View v =inflater.inflate(R.layout.contacto_fragment,container,false);

        json = getArguments().getString("info");

        try {
            jsonObj = new JSONObject(json);
        } catch (JSONException e) {
            e.printStackTrace();
        }

        recycler = (RecyclerView) v.findViewById(R.id.contactoRecycler);
        recycler.setHasFixedSize(true);

        // Usar un administrador para LinearLayout
        lManager = new LinearLayoutManager(getActivity());
        recycler.setLayoutManager(lManager);
        recycler.addOnItemTouchListener(
                new RecyclerItemClickListener(getActivity() ,new RecyclerItemClickListener.OnItemClickListener() {
                    @Override
                    public void onItemClick(View view, int position) {
                        if(position == 1){
                            Intent phoneIntent = new Intent(Intent.ACTION_DIAL, Uri.fromParts(
                                    "tel", adapter.getListItems().get(0).getValue().asText(), null));
                            startActivity(phoneIntent);
                        }
                        else if(position == 2){
                            Intent emailIntent = new Intent(android.content.Intent.ACTION_SEND);
                            emailIntent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                            emailIntent.setType("vnd.android.cursor.item/email");
                            emailIntent.putExtra(android.content.Intent.EXTRA_EMAIL, new String[] {adapter.getListItems().get(1).getValue().asText()});
                            emailIntent.putExtra(android.content.Intent.EXTRA_SUBJECT, "My Email Subject");
                            emailIntent.putExtra(android.content.Intent.EXTRA_TEXT, "My email content");
                            startActivity(Intent.createChooser(emailIntent, "Send mail using..."));
                        }
                    }
                })
        );
        adapter = new ContactoAdapter(getActivity(),items);
        try {
            getItems();
        } catch (JSONException e) {
            e.printStackTrace();
        }
        adapter.removeItem(0);
        recycler.setAdapter(adapter);

        //nombre = (TextView)v.findViewById(R.id.direccionTxt);
        //nombre.setText(adapter.getName());

        return v;
    }

    public void getItems() throws JSONException {
        ObjectMapper mapper = new ObjectMapper();
        JsonNode root;
        if(items!=null)
            items.clear();

        try {
            root = mapper.readTree(json);

            JsonNode node = root.path("contacto");
            Iterator<Map.Entry<String, JsonNode>> fields = node.fields();
            while (fields.hasNext()) {
                Map.Entry<String, JsonNode> entry = fields.next();
                Log.d("contacto: ",entry.getKey() + ":" + entry.getValue());
                if(entry.getKey().equals("nombre")){
                    adapter.setHeader(entry.getValue().asText());
                }
                items.add(entry);
            }


        } catch (IOException e) {
            e.printStackTrace();
        }

        adapter.notifyDataSetChanged();
    }

}
