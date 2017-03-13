package mx.hoola.hoola.Views.Adapters;

import android.app.LauncherActivity;
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
import com.fasterxml.jackson.databind.ObjectMapper;

import org.json.JSONObject;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import mx.hoola.hoola.Models.Header;
import mx.hoola.hoola.R;
import mx.hoola.hoola.Tools.StringTools;

/**
 * Created by Isaac on 03/03/2017.
 */

public class ContactoAdapter extends  RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private static final int TYPE_HEADER = 0;
    private static final int TYPE_ITEM = 1;

    private List<Map.Entry<String, JsonNode>> items;
    private ArrayList<JSONObject> myList;
    private Context mContext;
    private StringTools tool = new StringTools();
    private String nombre;
    Header header;
    private String head="";


    public ContactoAdapter(Context activity, List<Map.Entry<String, JsonNode>> items) {
        this.items = items;
        this.mContext = activity;
        setHasStableIds(true);
    }

    @Override
    public int getItemCount() {
        return items.size()+1;
    }

    @Override
    public int getItemViewType(int position) {
        if(isPositionHeader(position))
            return TYPE_HEADER;
        return TYPE_ITEM;
    }


    private boolean isPositionHeader(int position)
    {
        return position == 0;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        //View v = LayoutInflater.from(viewGroup.getContext())
          //      .inflate(R.layout.contacto_list, viewGroup, false);
        //return new ContactoAdapter.AnimeViewHolder(v);
        if(viewType == TYPE_HEADER)
        {
            View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.recycler_header, parent, false);
            return  new VHHeader(v);
        }
        else if(viewType == TYPE_ITEM)
        {
            View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.contacto_list, parent, false);
            return new VHItem(v);
        }
        throw new RuntimeException("there is no type that matches the type " + viewType + " + make sure your using types correctly");
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int i) {
        if(holder instanceof VHHeader)
        {
            VHHeader VHheader = (VHHeader)holder;
            VHheader.txtTitle.setText(head);
        }
        else if(holder instanceof VHItem)
        {
            VHItem VHitem = (VHItem)holder;
            if(items.get(i-1).getKey().equals("facebook")){
                ObjectMapper mapper = new ObjectMapper();
                JsonNode root;

                try {
                    root = mapper.readTree(String.valueOf(items.get(i-1).getValue()));
                    //holder.titulo.setText(root.get("url") + ": "+ root.get("nombre"));
                    VHitem.txtName.setText(root.get("nombre").asText());
                    VHitem.iv.setImageResource(R.drawable.facebook);
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            else{
                    VHitem.txtName.setText(items.get(i-1).getValue().asText());
                if(items.get(i-1).getKey().equals("telefono")){
                    VHitem.iv.setImageResource(R.drawable.phone);
                }
                else if(items.get(i-1).getKey().equals("email")){
                    VHitem.iv.setImageResource(R.drawable.email);
                }
                else if(items.get(i-1).getKey().equals("whatsapp")){
                    VHitem.iv.setImageResource(R.drawable.whatsapp);
                }
            }
        }

    }


    public Resources getResources() {
        Resources resources = null;
        return resources;
    }

    class VHHeader extends RecyclerView.ViewHolder{
        TextView txtTitle;
        public VHHeader(View itemView) {
            super(itemView);
            this.txtTitle = (TextView)itemView.findViewById(R.id.txtName);
        }
    }

    class VHItem extends RecyclerView.ViewHolder{
        TextView txtName;
        ImageView iv;
        public VHItem(View itemView) {
            super(itemView);
            this.txtName = (TextView)itemView.findViewById(R.id.caracTxt);
            this.iv = (ImageView)itemView.findViewById(R.id.caracImg);
        }
    }

    public void removeItem(int position) {
        items.remove(position);
        notifyItemRemoved(position);
    }

    public void setHeader(String header){
        this.head = header;
    }

    public List<Map.Entry<String, JsonNode>> getListItems(){
        return this.items;
    }

}
