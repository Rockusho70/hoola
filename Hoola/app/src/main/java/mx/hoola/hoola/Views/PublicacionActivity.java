package mx.hoola.hoola.Views;

import android.content.Intent;
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
import com.nex3z.notificationbadge.NotificationBadge;

import it.neokree.materialtabs.MaterialTab;
import it.neokree.materialtabs.MaterialTabListener;
import mx.hoola.hoola.R;
import mx.hoola.hoola.Views.Adapters.NonSwipeableViewPager;
import mx.hoola.hoola.Views.Adapters.ViewPagerAdapter;
import mx.hoola.hoola.Views.Fragments.ConfiguracionFragment;
import mx.hoola.hoola.Views.Fragments.InicioFragment;

public class PublicacionActivity extends AppCompatActivity implements MaterialTabListener {

    private Toolbar toolbar;
    private TabLayout tabHost;
    private NonSwipeableViewPager viewPager;
    private MaterialSearchView searchView;

    private NotificationBadge mBadge;
    private int mCount = 7;

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

        viewPager = (NonSwipeableViewPager) this.findViewById(R.id.viewPager);
        setupViewPager(viewPager);

        tabHost.setOnTabSelectedListener(
                new TabLayout.ViewPagerOnTabSelectedListener(viewPager) {
                    @Override
                    public void onTabSelected(TabLayout.Tab tab) {
                        super.onTabSelected(tab);
                        if(tab.getPosition() == 1){
                            Intent intent = new Intent(PublicacionActivity.this, CamaraActivity.class);
                            startActivity(intent);
                            overridePendingTransition(R.anim.fade_in, R.anim.fade_out);
                        }
                    }

                    @Override
                    public void onTabReselected(TabLayout.Tab tab) {
                        super.onTabSelected(tab);
                        if(tab.getPosition() == 1){
                            Intent intent = new Intent(PublicacionActivity.this, CamaraActivity.class);
                            startActivity(intent);
                            overridePendingTransition(R.anim.fade_in, R.anim.fade_out);
                        }
                    }
                });

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

        mBadge = (NotificationBadge) findViewById(R.id.badge);
        mBadge.setNumber(mCount);

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
        TextView home = (TextView) LayoutInflater.from(this).inflate(R.layout.custom_tab_publish, null);
        home.setCompoundDrawablesWithIntrinsicBounds(0, R.drawable.home_publish, 0, 0);
        home.setText("Inicio");
        tabHost.getTabAt(0).setCustomView(home);
        tabHost.getTabAt(1).setIcon(R.drawable.camera);
        TextView conf = (TextView) LayoutInflater.from(this).inflate(R.layout.custom_tab_publish, null);
        conf.setCompoundDrawablesWithIntrinsicBounds(0, R.drawable.settings_publish, 0, 0);
        conf.setText("Configuración");
        tabHost.getTabAt(2).setCustomView(conf);
    }

    /**
     * Adding fragments to ViewPager
     * @param viewPager
     */
    private void setupViewPager(ViewPager viewPager) {
        ViewPagerAdapter adapter = new ViewPagerAdapter(getSupportFragmentManager());
        adapter.addFrag(new InicioFragment(), "");
        adapter.addFrag(new InicioFragment(), "");
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
