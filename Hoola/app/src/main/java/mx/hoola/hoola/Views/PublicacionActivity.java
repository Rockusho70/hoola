package mx.hoola.hoola.Views;

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

import it.neokree.materialtabs.MaterialTab;
import it.neokree.materialtabs.MaterialTabListener;
import mx.hoola.hoola.R;
import mx.hoola.hoola.Views.Adapters.ViewPagerAdapter;
import mx.hoola.hoola.Views.Fragments.ConfiguracionFragment;
import mx.hoola.hoola.Views.Fragments.FiltrosFragment;
import mx.hoola.hoola.Views.Fragments.InicioFragment;

public class PublicacionActivity extends AppCompatActivity implements MaterialTabListener {

    private Toolbar toolbar;
    private TabLayout tabHost;
    private ViewPager viewPager;
    private ViewPagerAdapter androidAdapter;
    private MaterialSearchView searchView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_publicacion);

        //android toolbar
        toolbar = (android.support.v7.widget.Toolbar) this.findViewById(R.id.toolbarPublicacion);
        this.setSupportActionBar(toolbar);
        this.getSupportActionBar().setTitle("Publicaciones");

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

    /**
     * Adding custom view to tab
     */
    private void setupTabIcons() {
        tabHost.getTabAt(0).setIcon(R.drawable.home_publish);
        tabHost.getTabAt(1).setIcon(R.drawable.camera);
        tabHost.getTabAt(2).setIcon(R.drawable.settings_publish);
    }

    /**
     * Adding fragments to ViewPager
     * @param viewPager
     */
    private void setupViewPager(ViewPager viewPager) {
        ViewPagerAdapter adapter = new ViewPagerAdapter(getSupportFragmentManager());
        adapter.addFrag(new InicioFragment(), "");
        adapter.addFrag(new FiltrosFragment(), "");
        adapter.addFrag(new ConfiguracionFragment(), "");
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
    public void onTabSelected(MaterialTab tab) {
        viewPager.setCurrentItem(tab.getPosition());
    }

    @Override
    public void onTabReselected(MaterialTab tab) {

    }

    @Override
    public void onTabUnselected(MaterialTab tab) {

    }
}
