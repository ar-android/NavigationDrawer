package id.ocittwo.navigationdrawer;

import android.app.Fragment;
import android.content.res.Configuration;
import android.os.Handler;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;

import id.ocittwo.navigationdrawer.fragment.FragmentGallery;
import id.ocittwo.navigationdrawer.fragment.FragmentProfile;
import id.ocittwo.navigationdrawer.fragment.FragmentSchool;

public class MainActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    private DrawerLayout drawerLayout;
    private Toolbar toolbar;
    private int activeMenu;
    private NavigationView navigationView;
    private ActionBarDrawerToggle toggle;

    private FragmentProfile fProfile = new FragmentProfile();
    private FragmentGallery fGallery = new FragmentGallery();
    private FragmentSchool fSchool = new FragmentSchool();

    private Handler handlerSaveId = new Handler();
    private long DRAWER_CLOSE_DELAY = 350;
    private String ID_MENU_ACTIVE = "IdMenuActive";

    @Override
    protected void onCreate(final Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        drawerLayout = (DrawerLayout)findViewById(R.id.drawerLayout);
        toolbar = (Toolbar)findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        if(null == savedInstanceState){
            activeMenu = R.id.profile;
        }else{
            activeMenu = savedInstanceState.getInt(ID_MENU_ACTIVE);
        }

        navigationView = (NavigationView)findViewById(R.id.navigationView);
        navigationView.setNavigationItemSelectedListener(this);
        navigationView.getMenu().findItem(activeMenu).setChecked(true);

        toggle = new ActionBarDrawerToggle(this, drawerLayout, toolbar, R.string.open, R.string.close);
        drawerLayout.setDrawerListener(toggle);
        toggle.syncState();

        switchFragment(activeMenu);

    }

    /**
     * for switch active fragment by sevedInstanState
     * @param activeMenu
     */
    private void switchFragment(int activeMenu) {
        switch (activeMenu){
            case R.id.profile:
                setFragment(fProfile);
                break;
            case R.id.explore:
                setFragment(fGallery);
                break;
            case R.id.School:
                setFragment(fSchool);
            default:
                //Default
                    break;
        }
    }

    /**
     * replace fragment by activemenu id
     * @param fragment
     */
    private void setFragment(Fragment fragment){
        getFragmentManager()
                .beginTransaction()
                .replace(R.id.container, fragment)
                .commit();
    }

    @Override
    public boolean onNavigationItemSelected(final MenuItem menuItem) {

        menuItem.setChecked(true);
        activeMenu = menuItem.getItemId();
        drawerLayout.closeDrawer(GravityCompat.START);
        handlerSaveId.postDelayed(new Runnable() {
            @Override
            public void run() {
                switchFragment(menuItem.getItemId());
            }
        }, DRAWER_CLOSE_DELAY);

        return true;
    }

    @Override
    public void onConfigurationChanged(Configuration newConfig) {
        super.onConfigurationChanged(newConfig);
        toggle.onConfigurationChanged(newConfig);
    }

    @Override
    public void onBackPressed() {
        if (drawerLayout.isDrawerOpen(GravityCompat.START)){
            drawerLayout.closeDrawer(GravityCompat.START);
        }else {
            super.onBackPressed();
        }
    }

    /**
     * to case active id menu
     * @param outState
     */
    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putInt(ID_MENU_ACTIVE, activeMenu);
    }
}
