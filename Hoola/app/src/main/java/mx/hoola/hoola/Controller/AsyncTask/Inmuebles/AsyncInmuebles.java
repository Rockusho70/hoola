package mx.hoola.hoola.Controller.AsyncTask.Inmuebles;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import mx.hoola.hoola.Controller.DAO.InmuebleController;
import mx.hoola.hoola.Controller.Interfaces.OnDataSendInmuebles;
import mx.hoola.hoola.Controller.WebService;
import mx.hoola.hoola.Models.Inmueble;
import mx.hoola.hoola.Views.HomeActivity;

/**
 * Created by Isaac on 14/02/2017.
 */

public class AsyncInmuebles extends AsyncTask<String,Integer,String> {

    private ProgressDialog progressDialog;
    private Inmueble[] array;
    private Inmueble packs = new Inmueble();
    private String newsString;
    private WebService services = new WebService();
    private InmuebleController controller = new InmuebleController();

    public OnDataSendInmuebles SendToActivity;//Call back interface

    Context mContext;

    private boolean flag = false;


    public AsyncInmuebles(Context SendToActivity, OnDataSendInmuebles context) {
        super();
        this.SendToActivity = context;
        mContext = SendToActivity;
    }

    @Override
    protected String doInBackground(String... params) {


        return newsString;
    }

    @Override
    protected void onPreExecute() {
        super.onPreExecute();
        progressDialog = new ProgressDialog(mContext);
        progressDialog.setMessage("Procesando...");
        progressDialog.show();
        progressDialog.setCancelable(false);

    }

    @Override
    protected void onPostExecute(String result) {
        super.onPostExecute(result);
        if(progressDialog.isShowing()){
            progressDialog.dismiss();
        }
        if(result.equals("")){
            Toast.makeText(mContext, "Vuelve a intentarlo"+result, Toast.LENGTH_SHORT).show();
        }else{
            //     Toast.makeText(mContext, "Bienvenido "+brands, Toast.LENGTH_SHORT).show();

            try {
                JSONArray jsonarray = new JSONArray(result);
                array = new Inmueble[jsonarray.length()];

                for (int i = 0; i < jsonarray.length(); i++) {
                    array[i] = new Inmueble();
                    JSONObject jsonobject = jsonarray.getJSONObject(i);
                    array[i].setId(jsonobject.getString("_id"));
                    array[i].setTitulo(jsonobject.getString("tipoPropiedad")+ " - "+ jsonobject.getString("direccion.calle"));
                    array[i].setHabitacion(jsonobject.getString("caracteristicas.habitaciones"));
                    array[i].setPiso(jsonobject.getString("caracteristicas.pisos"));
                    array[i].setBanio(jsonobject.getString("caracteristicas.banos"));
                    array[i].setEstacionamiento(jsonobject.getString("caracteristicas.estacionamientos"));
                    array[i].setPrecio(jsonobject.getString("precio"));
                    array[i].setImagen(jsonobject.getString("media.imagenes.imagenLink"));
                    array[i].setTerreno(jsonobject.getString("m2"));
                }

            } catch (JSONException e) {
                e.printStackTrace();
            }
            Intent intent = new Intent(mContext, HomeActivity.class);
            SendToActivity.sendData(array);
            //Toast.makeText(mContext, ", Toast.LENGTH_SHORT).show();
//  intent.putExtra("objBrands",arrayBrands);

            //  mContext.startActivity(intent);



        }
    }

}
