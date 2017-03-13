package mx.hoola.hoola.Controller.Interfaces;

import org.json.JSONArray;

import java.util.ArrayList;
import java.util.HashMap;

import mx.hoola.hoola.Models.Inmueble;

/**
 * Created by Isaac on 21/02/2017.
 */

public interface VolleyCallback {
    void onSuccessResponse(JSONArray json);
}
