package com.example.jimmy.areyoukittenme;

import android.os.Bundle;
import android.app.Fragment;
import android.app.FragmentManager;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;

import com.example.jimmy.areyoukittenme.Fragments.FactsFragment;
import com.example.jimmy.areyoukittenme.Fragments.FavFactsFragment;
import com.example.jimmy.areyoukittenme.Fragments.MapsFragment;
import com.example.jimmy.areyoukittenme.Fragments.MovementFragment;

public class MainActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    private DrawerLayout drawer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

        // Load any fact fragment first
        FragmentManager manager = getFragmentManager();
        FactsFragment factsFragment = FactsFragment.newInstance();
        manager.beginTransaction().replace(R.id.flcontent, factsFragment).commit();

        this.setTitle("Cat Facts");
    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handles navigation view item clicks
        Class fragmentClass;
        Fragment page = null;
        switch (item.getItemId()) {
            case R.id.nav_facts:
                fragmentClass = FactsFragment.class;
                break;
            case R.id.nav_movement:
                fragmentClass = MovementFragment.class;
                break;
            case R.id.nav_maps:
                fragmentClass = MapsFragment.class;
                break;
            case R.id.nav_favs:
                fragmentClass = FavFactsFragment.class;
                break;
            default:
                fragmentClass = null;
        }

        try {
            page = (Fragment) fragmentClass.newInstance();
        } catch (Exception e) {
            e.printStackTrace();
        }

        // Replace any existing fragment
        FragmentManager manager = getFragmentManager();
        manager.beginTransaction().replace(R.id.flcontent, page).commit();

        item.setChecked(true);
        this.setTitle(item.getTitle());

        drawer.closeDrawer(GravityCompat.START);
        return true;
    }


}
