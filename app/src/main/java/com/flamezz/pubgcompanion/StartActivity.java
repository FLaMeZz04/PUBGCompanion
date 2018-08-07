package com.flamezz.pubgcompanion;

import android.content.DialogInterface;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AlertDialog;
import android.view.View;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ImageView;

import com.bumptech.glide.Glide;

public class StartActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    private HomeFragment homeFragment;
    private ItemFragment itemFragment;
    private AlertDialog.Builder alertdialog;
    private MapFragment mapFragment;
    private TipsFragment tipsFragment;
    private NewsFragment newsFragment;
    private ImageView logoImage;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_start);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);



        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);
        final View header = navigationView.getHeaderView(0);
        logoImage = header.findViewById(R.id.logoImage);
        String url = "https://firebasestorage.googleapis.com/v0/b/pubg-companion-af4fc.appspot.com/o/pubg_icon.png?alt=media&token=0b7f480d-1c00-4fd2-b0ed-489a1eb99435";
        Glide.with(StartActivity.this).load(url).into(logoImage);
        Initlaize();
    }



    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.start, menu);
        return true;
    }


    private void Initlaize()
    {
        homeFragment = new HomeFragment();
        newsFragment = new NewsFragment();
        itemFragment =new ItemFragment();
        tipsFragment = new TipsFragment();
        mapFragment = new MapFragment();
        alertdialog = new AlertDialog.Builder(this);


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

    @Override
    public void onBackPressed() {
        DrawerLayout drawer =  findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }
    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();
            if (id == R.id.nav_home) {
                setFragmentNavigation(homeFragment);

            } else if (id == R.id.nav_items) {
                setFragmentNavigation(itemFragment);

            } else if (id == R.id.nav_map) {
                setFragmentNavigation(mapFragment);

            } else if (id == R.id.nav_tip) {
                setFragmentNavigation(tipsFragment);

            } else if (id == R.id.nav_news) {
                setFragmentNavigation(newsFragment);
            }




        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    private void setFragmentNavigation(Fragment fragment)
    {
        FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
        fragmentTransaction.replace(R.id.frameLayout,fragment);
        fragmentTransaction.commit();
    }
}
