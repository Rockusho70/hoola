package mx.hoola.hoola.Views;

import android.animation.Animator;
import android.animation.ObjectAnimator;
import android.content.Intent;
import android.graphics.Point;
import android.support.constraint.ConstraintLayout;
import android.support.v4.content.ContextCompat;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Display;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewConfiguration;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ImageView;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

import mx.hoola.hoola.R;
import mx.hoola.hoola.Views.Adapters.FullScreenImageAdapter;
import mx.hoola.hoola.Views.Adapters.HackyViewPager;


public class ImagenPopUpActivity extends AppCompatActivity {

    private String json="";
    private int index=0;
    private JSONObject fotos = null;
    private int imageArray=0;
    private ViewPager slide;
    private FullScreenImageAdapter img;
    private ArrayList<String> images= new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        Window window = this.getWindow();
        // clear FLAG_TRANSLUCENT_STATUS flag:
        window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);

        // add FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS flag to the window
        window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);

        // finally change the color
        window.setStatusBarColor(ContextCompat.getColor(this, R.color.black_overlay));

        setContentView(R.layout.activity_imagen_pop_up);

        Intent i = getIntent();

        index = i.getIntExtra("index", 0);

        json = i.getStringExtra("fotos");
        try {
            fotos = new JSONObject(json);
            int size= fotos.getJSONObject("media").getJSONArray("imagenes").length();
            for(int x=0; x<size; x++){
                images.add(x, fotos.getJSONObject("media").getJSONArray("imagenes").getJSONObject(x).getString("imagenLink"));
            }

        } catch (JSONException e) {
            e.printStackTrace();
        }

        slide = (HackyViewPager)findViewById(R.id.pager);
        img = new FullScreenImageAdapter(this,images);
        slide.setAdapter(img);
        slide.setCurrentItem(index);


    }

}
