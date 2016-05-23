package com.brianroper.tattome;

import android.app.Fragment;
import android.content.Intent;
import android.content.res.Configuration;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.NavigationView;
import android.support.design.widget.Snackbar;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Base64;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import com.firebase.client.Firebase;

import java.io.ByteArrayOutputStream;

public class ListActivity extends AppCompatActivity {

    private DrawerLayout mDrawerLayout;
    private ActionBarDrawerToggle mActionBarDrawerToggle;
    private NavigationView mNavigationView;
    private Toolbar mToolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list);

        mToolbar = (Toolbar) findViewById(R.id.toolbar);
        mDrawerLayout = (DrawerLayout)findViewById(R.id.drawer_layout);
        mNavigationView = (NavigationView)findViewById(R.id.nav_view);

        setUpDrawerContent(mNavigationView);

        mActionBarDrawerToggle= setupDrawerToggle();
        mDrawerLayout.addDrawerListener(mActionBarDrawerToggle);
        mDrawerLayout.setBackgroundColor(getResources().getColor(R.color.colorPrimary, null));

        setSupportActionBar(mToolbar);
        getSupportActionBar().setTitle(getApplicationContext()
                        .getResources()
                        .getString(R.string.app_title));

        final Firebase ref = new Firebase("https://tattoome.firebaseio.com/");
    }

    @Override
    protected void onPostCreate(Bundle savedInstanceState) {
        super.onPostCreate(savedInstanceState);

        mActionBarDrawerToggle.syncState();
    }

    @Override
    public void onConfigurationChanged(Configuration newConfig) {

        mActionBarDrawerToggle.onConfigurationChanged(newConfig);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()){

            case android.R.id.home:
                mDrawerLayout.openDrawer(GravityCompat.START);
                //mDrawerLayout.isDrawerOpen(GravityCompat.START);
                return true;

        }

        if(mActionBarDrawerToggle.onOptionsItemSelected(item)){

            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    public void setUpDrawerContent(NavigationView navigationView){

        navigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(MenuItem item) {
                selectDrawerItem(item);
                return true;
            }
        });
    }

    public void selectDrawerItem(MenuItem menuItem){

        Bundle args = new Bundle();
        Intent intent = new Intent(this, ListActivity.class);

        switch(menuItem.getItemId()){

            case R.id.nav_first_item:

                args.putString("category", "featured");

                startActivity(intent);

                break;

            case R.id.nav_second_item:

                args.putString("category", "animal-tattoos");

                startActivity(intent);

                break;

            case R.id.nav_third_item:

                //start favorites activity here
                Toast.makeText(getApplicationContext(), "Not yet implemented, be patient please",
                        Toast.LENGTH_LONG).show();

                break;

            case R.id.nav_fourth_item:

                //start settings activity here
                Toast.makeText(getApplicationContext(), "Not yet implemented, be patient please",
                        Toast.LENGTH_LONG).show();

                break;

            case R.id.nav_fifth_item:

                //logout the user
                Toast.makeText(getApplicationContext(), "Not yet implemented, be patient please",
                        Toast.LENGTH_LONG).show();

                break;
        }
    }

    public ActionBarDrawerToggle setupDrawerToggle(){
            return new ActionBarDrawerToggle(this,
                    mDrawerLayout,
                    mToolbar,
                    R.string.drawer_open,
                    R.string.drawer_closed);

    }
}
