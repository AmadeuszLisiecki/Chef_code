package com.example.amadeusz.chef_cook;

import android.content.Intent;
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
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

public class PredictorActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_predictor);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        if(getSupportActionBar() != null) {
            getSupportActionBar().setTitle(null);
        }
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout_predictor);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();
        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view_predictor);
        navigationView.setNavigationItemSelectedListener(this);
        final Spinner predictors = (Spinner) findViewById(R.id.spinner_predictor);
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this, R.array.predictors, R.layout.spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        predictors.setAdapter(adapter);
        final TextView glass = (TextView)findViewById(R.id.glass_measure);
        final TextView spoon = (TextView)findViewById(R.id.spoon_measure);
        final TextView smallSpoon = (TextView)findViewById(R.id.small_spoon_measure);
        final ImageView photo = (ImageView)findViewById(R.id.predictor_photo);
        predictors.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {

            public void onItemSelected(AdapterView<?> parent, View view, int pos, long id) {
                Predictor ingredient = Base.getPredictors().get(pos);
                double[] measures = Base.getPredictorsForIngedient(ingredient.getName());
                glass.setText((int) measures[0] + " " + ingredient.getMeasure());
                if(measures[1] == Math.floor(measures[1])) {
                    spoon.setText((int)measures[1] + " " + ingredient.getMeasure());
                }
                else {
                    spoon.setText(measures[1] + " " + ingredient.getMeasure());
                }
                if(measures[2] == Math.floor(measures[2])) {
                    smallSpoon.setText((int)measures[2] + " " + ingredient.getMeasure());
                }
                else {
                    smallSpoon.setText(measures[2] + " " + ingredient.getMeasure());
                }
                photo.setBackgroundResource((int) measures[3]);
            }

            public void onNothingSelected(AdapterView<?> parent) {

            }

        });
    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout_predictor);
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

        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        int id = item.getItemId();

        if (id == R.id.random) {
            Intent swapScreen = new Intent(PredictorActivity.this, RandomActivity.class);
            PredictorActivity.this.startActivity(swapScreen);
        } else if (id == R.id.about_app) {
            Intent swapScreen = new Intent(PredictorActivity.this, AboutAppActivity.class);
            PredictorActivity.this.startActivity(swapScreen);
        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout_predictor);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

}
