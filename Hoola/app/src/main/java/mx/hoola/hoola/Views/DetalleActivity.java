package mx.hoola.hoola.Views;

import android.app.Activity;
import android.content.Intent;
import android.support.design.widget.Snackbar;
import android.support.design.widget.TabLayout;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.ActivityOptionsCompat;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.daimajia.slider.library.SliderLayout;
import com.daimajia.slider.library.SliderTypes.BaseSliderView;
import com.daimajia.slider.library.SliderTypes.DefaultSliderView;
import com.daimajia.slider.library.SliderTypes.TextSliderView;
import com.daimajia.slider.library.Tricks.ViewPagerEx;

import org.json.JSONException;
import org.json.JSONObject;

import java.text.DecimalFormat;

import mx.hoola.hoola.Models.Inmueble;
import mx.hoola.hoola.Models.List.Caracteristicas;
import mx.hoola.hoola.R;
import mx.hoola.hoola.Views.Adapters.ViewPagerAdapter;
import mx.hoola.hoola.Views.Fragments.ContactoFragment;
import mx.hoola.hoola.Views.Fragments.InformacionFragment;
import mx.hoola.hoola.Views.Fragments.MapaFragment;

public class DetalleActivity extends AppCompatActivity implements BaseSliderView.OnSliderClickListener, ViewPagerEx.OnPageChangeListener {

    private Toolbar toolbar;
    private TabLayout tabLayout;
    private ViewPager pageTabs;
    private TextView precioTxt;
    private SliderLayout sliderShow;
    private JSONObject inmueble = null;
    private Caracteristicas car[];
    private int imageArray=0;
    private String json="";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detalle);

        Intent i = getIntent();

        json = i.getStringExtra("inmueble");
        try {
            inmueble = new JSONObject(json);
        } catch (JSONException e) {
            e.printStackTrace();
        }

        toolbar = (Toolbar) findViewById(R.id.toolbarDetalle);
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle("Información");

        //Tabs
        pageTabs = (ViewPager) findViewById(R.id.viewPagerTabs);
        setupViewPager(pageTabs);

        tabLayout = (TabLayout) findViewById(R.id.tabHost);
        tabLayout.setupWithViewPager(pageTabs);

        Double precio = null;
        try {
            precio = Double.parseDouble(inmueble.getJSONObject("precio").getString("precio"));
        } catch (JSONException e) {
            e.printStackTrace();
        }
        DecimalFormat df = new DecimalFormat("#,###");
        precioTxt = (TextView)findViewById(R.id.tipoTxt);
        precioTxt.setText("$ "+df.format(precio));

        //Slider images
        sliderShow = (SliderLayout) findViewById(R.id.slider);

        try {
            imageArray=inmueble.getJSONObject("media").getJSONArray("imagenes").length();
            for(int o=0; o< imageArray; o++){
                DefaultSliderView sliderView = new DefaultSliderView(this);
                sliderView
                        .setOnSliderClickListener(this)
                        .image(inmueble.getJSONObject("media").getJSONArray("imagenes").getJSONObject(o).getString("imagenLink"));

                sliderShow.addSlider(sliderView);
            }
            sliderShow.stopAutoCycle();
            sliderShow.addOnPageChangeListener(this);

        } catch (JSONException e) {
            e.printStackTrace();
        }

    }
    private void setupViewPager(ViewPager viewPager) {
        ViewPagerAdapter adapter = new ViewPagerAdapter(getSupportFragmentManager());
        try {
            Bundle bundle = new Bundle();
            bundle.putString("info", json);
            InformacionFragment frag = new InformacionFragment();
            frag.setArguments(bundle);
            adapter.addFrag(frag, inmueble.getString("tipoPropiedad").toLowerCase());
            ContactoFragment contacto = new ContactoFragment();
            contacto.setArguments(bundle);
            adapter.addFrag(contacto, "Contacto".toLowerCase());

            MapaFragment mapa = new MapaFragment();
            mapa.setArguments(bundle);
            adapter.addFrag(mapa, "Mapa".toLowerCase());
        } catch (JSONException e) {
            e.printStackTrace();
        }

        viewPager.setAdapter(adapter);
    }

    @Override
    protected void onStop() {
        sliderShow.stopAutoCycle();
        super.onStop();
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // handle arrow click here
        if (item.getItemId() == android.R.id.home) {
            finish(); // close this activity and return to preview activity (if there is any)
            startActivity(new Intent(DetalleActivity.this, HomeActivity.class));
            overridePendingTransition(R.anim.fade_in, R.anim.fade_out);
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onSliderClick(BaseSliderView slider) {
        Toast.makeText(this,"Listo" + "",Toast.LENGTH_SHORT).show();
        int index = sliderShow.getCurrentPosition();
        Intent setIntent = new Intent(DetalleActivity.this, ImagenPopUpActivity.class);
        setIntent.putExtra("fotos",inmueble.toString());
        setIntent.putExtra("index",index);
        startActivity(setIntent);
        overridePendingTransition(R.anim.fade_in, R.anim.fade_out);
        //launch(this,);
    }

    @Override
    public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

    }

    @Override
    public void onPageSelected(int position) {

    }

    @Override
    public void onPageScrollStateChanged(int state) {

    }

    public void launch(Activity activity, View transitionView, String url, int index) {
        ActivityOptionsCompat options =
                ActivityOptionsCompat.makeSceneTransitionAnimation(
                        activity, transitionView, "fotos");
        Intent intent = new Intent(activity, ImagenPopUpActivity.class);
        intent.putExtra("fotos", url);
        intent.putExtra("index", index);
        ActivityCompat.startActivity(activity, intent, options.toBundle());
    }
}
