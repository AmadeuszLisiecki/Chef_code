package com.example.amadeusz.chef_cook;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;

public class MultimediaActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_multimedia);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        if(getSupportActionBar() != null) {
            getSupportActionBar().setTitle(null);
        }
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout_multimedia);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();
        TextView dishName = (TextView) findViewById(R.id.text_multimedia);
        Intent intent = getIntent();
        final String dishText = intent.getStringExtra("dish name");
        dishName.setText(dishText + " - multimedia");
        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view_multimedia);
        navigationView.setNavigationItemSelectedListener(this);
        final ArrayList<Multimedia> multimedia = Base.getRecepture(dishText).getMultimedia();
        MultimediaAdapter adapter = new MultimediaAdapter(this, multimedia);
        ListView listMultimedia = (ListView)findViewById(R.id.list_multimedia);
        listMultimedia.setAdapter(adapter);
        listMultimedia.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Multimedia clicked = multimedia.get(position);
                if(clicked.getType().equals("Wideo")) {
                    Intent i = new Intent(Intent.ACTION_VIEW);
                    i.setData(Uri.parse(clicked.getReference()));
                    startActivity(i);
                }
                else {
                    Intent swapScreen = new Intent(MultimediaActivity.this, PhotoActivity.class);
                    swapScreen.putExtra("position", position);
                    swapScreen.putExtra("biggers", Base.getRecepture(dishText).getBiggerPhotos());
                    MultimediaActivity.this.startActivity(swapScreen);
                }
            }

        });
    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout_multimedia);
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
        //int id = item.getItemId();

        //noinspection SimplifiableIfStatement
       /* if (id == R.id.action_settings) {
            return true;
        }*/

        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        int id = item.getItemId();
        Intent swapScreen = null;
        if (id == R.id.random) {
            swapScreen = new Intent(MultimediaActivity.this, RandomActivity.class);
        } else if (id == R.id.predictors) {
            swapScreen = new Intent(MultimediaActivity.this, PredictorActivity.class);
        } else if (id == R.id.about_app) {
            swapScreen = new Intent(MultimediaActivity.this, AboutAppActivity.class);
        }
        MultimediaActivity.this.startActivity(swapScreen);
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout_multimedia);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

}
