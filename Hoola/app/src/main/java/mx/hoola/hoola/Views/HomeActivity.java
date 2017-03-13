package mx.hoola.hoola.Views;

import android.graphics.drawable.Drawable;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;

import com.miguelcatalan.materialsearchview.MaterialSearchView;

import org.json.JSONArray;

import java.util.ArrayList;
import java.util.HashMap;

import it.neokree.materialtabs.MaterialTab;
import it.neokree.materialtabs.MaterialTabHost;
import it.neokree.materialtabs.MaterialTabListener;
import mx.hoola.hoola.Controller.Interfaces.VolleyCallback;
import mx.hoola.hoola.Models.Inmueble;
import mx.hoola.hoola.R;
import mx.hoola.hoola.Views.Adapters.ViewPagerAdapter;
import mx.hoola.hoola.Views.Fragments.ConfiguracionFragment;
import mx.hoola.hoola.Views.Fragments.ContactoFragment;
import mx.hoola.hoola.Views.Fragments.FiltrosFragment;
import mx.hoola.hoola.Views.Fragments.InicioFragment;

public class HomeActivity extends AppCompatActivity implements MaterialTabListener, VolleyCallback {

    //private MaterialTabHost tabHost;
    private TabLayout tabHost;
    private ViewPager viewPager;
    private ViewPagerAdapter androidAdapter;
    private Toolbar toolBar;
    private MaterialSearchView searchView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        //android toolbar
        toolBar = (android.support.v7.widget.Toolbar) this.findViewById(R.id.toolbarHome);
        this.setSupportActionBar(toolBar);
        this.getSupportActionBar().setTitle("hoola");

        //tab host
        tabHost =  (TabLayout) findViewById(R.id.tabHost);
        viewPager = (ViewPager) this.findViewById(R.id.viewPager);
        setupViewPager(viewPager);

        tabHost.setupWithViewPager(viewPager);
        setupTabIcons();

        searchView = (MaterialSearchView) findViewById(R.id.search_view);
        searchView.setVoiceSearch(true); //or false
        searchView.setOnQueryTextListener(new MaterialSearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                //Do some magic
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                //Do some magic
                return false;
            }
        });

        searchView.setOnSearchViewListener(new MaterialSearchView.SearchViewListener() {
            @Override
            public void onSearchViewShown() {
                //Do some magic
            }

            @Override
            public void onSearchViewClosed() {
                //Do some magic
            }
        });

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.base, menu);

        MenuItem item = menu.findItem(R.id.action_search);
        searchView.setMenuItem(item);

        return true;
    }

    @Override
    public void onTabSelected(MaterialTab tab) {
        viewPager.setCurrentItem(tab.getPosition());

    }

    @Override
    public void onTabReselected(MaterialTab tab) {

    }

    @Override
    public void onTabUnselected(MaterialTab tab) {

    }

    /**
     * Adding custom view to tab
     */
    private void setupTabIcons() {

        TextView tabOne = (TextView) LayoutInflater.from(this).inflate(R.layout.custom_tab, null);
        tabOne.setText("Inicio");
        tabOne.setCompoundDrawablesWithIntrinsicBounds(0, R.drawable.home, 0, 0);
        tabHost.getTabAt(0).setCustomView(tabOne);

        TextView tabTwo = (TextView) LayoutInflater.from(this).inflate(R.layout.custom_tab, null);
        tabTwo.setText("Filtros");
        tabTwo.setCompoundDrawablesWithIntrinsicBounds(0, R.drawable.filter, 0, 0);
        tabHost.getTabAt(1).setCustomView(tabTwo);

        TextView tabThree = (TextView) LayoutInflater.from(this).inflate(R.layout.custom_tab, null);
        tabThree.setText("Configuración");
        tabThree.setCompoundDrawablesWithIntrinsicBounds(0, R.drawable.settings, 0, 0);
        tabHost.getTabAt(2).setCustomView(tabThree);
    }

    /**
     * Adding fragments to ViewPager
     * @param viewPager
     */
    private void setupViewPager(ViewPager viewPager) {
        ViewPagerAdapter adapter = new ViewPagerAdapter(getSupportFragmentManager());
        adapter.addFrag(new InicioFragment(), "Inicio");
        adapter.addFrag(new FiltrosFragment(), "Filtros");
        adapter.addFrag(new ConfiguracionFragment(), "Settings");
        viewPager.setAdapter(adapter);
    }


    @Override
    public void onBackPressed() {
        if (searchView.isSearchOpen()) {
            searchView.closeSearch();
        } else {
            super.onBackPressed();
        }
    }


    @Override
    public void onSuccessResponse(JSONArray json) {

    }
}
