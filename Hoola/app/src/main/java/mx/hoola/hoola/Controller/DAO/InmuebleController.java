package mx.hoola.hoola.Controller.DAO;


import android.content.Context;
import android.os.Build;
import android.support.annotation.RequiresApi;
import android.util.Log;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.Volley;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import mx.hoola.hoola.Controller.Interfaces.VolleyCallback;

/**
 * Created by Isaac on 14/02/2017.
 */

public class InmuebleController {

    public String res = "";

    public void getInmuebles(Context context, final VolleyCallback callback ) throws JSONException {
        String url = "http://hoola.mx/api/propiedades";
        RequestQueue queue = Volley.newRequestQueue(context);  // this = context

        JsonArrayRequest req = new JsonArrayRequest(url,
                new Response.Listener<JSONArray>() {
                    @RequiresApi(api = Build.VERSION_CODES.N)
                    @Override
                    public void onResponse(JSONArray response) {

                        callback.onSuccessResponse(response);

                    }
                }, new Response.ErrorListener() {

            @Override
            public void onErrorResponse(VolleyError error) {
                // TODO Auto-generated method stub
                Log.d("Error.Response", error.toString());
            }
        });

        queue.add(req);
    }
}
