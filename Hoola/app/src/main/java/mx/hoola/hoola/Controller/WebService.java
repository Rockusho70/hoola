package mx.hoola.hoola.Controller;

import android.content.Context;

import mx.hoola.hoola.Controller.AsyncTask.Inmuebles.AsyncInmuebles;
import mx.hoola.hoola.Controller.Interfaces.OnDataSendInmuebles;

/**
 * Created by Isaac on 14/02/2017.
 */

public class WebService {

    public void getInmuebles(Context context,OnDataSendInmuebles SendToActivity){
        new AsyncInmuebles(context,SendToActivity).execute();
    }

}
