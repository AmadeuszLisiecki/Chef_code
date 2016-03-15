package com.example.amadeusz.chef_cook;

import android.content.Intent;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.text.method.ScrollingMovementMethod;
import android.util.Base64;
import android.view.Menu;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;
import android.view.animation.Animation;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.ViewFlipper;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

public class RecipeActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {

    private ViewFlipper swapSteps;
    private float lastX;
    private RadioButton[] dots;
    private TextView stepText;
    private TextView dishName;
    private ImageView step1image;
    private ImageView step2image;
    private ImageView step3image;
    private ImageView step4image;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recipe);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        if(getSupportActionBar() != null) {
            getSupportActionBar().setTitle(null);
        }
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout_recipe);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();
        dishName = (TextView) findViewById(R.id.text_recipe);
        Intent intent = getIntent();
        final String dishText = intent.getStringExtra("dish name");
        dishName.setText(dishText + " - krok 1");
        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view_recipe);
        navigationView.setNavigationItemSelectedListener(this);
        swapSteps = (ViewFlipper)findViewById(R.id.steps);
        final String[] steps = Base.getStepsForRecepture(dishText);
        step1image = (ImageView)findViewById(R.id.step1image);
        step2image = (ImageView)findViewById(R.id.step2image);
        step3image = (ImageView)findViewById(R.id.step3image);
        step4image = (ImageView)findViewById(R.id.step4image);
        stepText = (TextView)findViewById(R.id.step_text);
        stepText.setMovementMethod(new ScrollingMovementMethod());
        if(steps != null) {
            step1image.setImageResource(R.drawable.no_internet);
            step2image.setImageResource(R.drawable.no_internet);
            step3image.setImageResource(R.drawable.no_internet);
            step4image.setImageResource(R.drawable.no_internet);
            stepText.setText(steps[0]);
        }
        dots = new RadioButton[4];
        dots[0] = (RadioButton)findViewById(R.id.dot1);
        dots[1] = (RadioButton)findViewById(R.id.dot2);
        dots[2] = (RadioButton)findViewById(R.id.dot3);
        dots[3] = (RadioButton)findViewById(R.id.dot4);
        swapSteps.setDisplayedChild(0);
        swapSteps.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View view, MotionEvent touchevent) {
                switch (touchevent.getAction()) {
                    case MotionEvent.ACTION_DOWN:
                        lastX = touchevent.getX();
                        return true;
                    case MotionEvent.ACTION_UP:
                        float currentX = touchevent.getX();
                        // Handling left to right screen swap.
                        if (lastX < currentX) {
                            // If there aren't any other children, just break.
                            if (swapSteps.getDisplayedChild() == 0)
                                break;
                            // Next screen comes in from left.
                            swapSteps.setInAnimation(getApplication(), R.anim.slide_in_from_left);

                            // Current screen goes out from right.
                            swapSteps.setOutAnimation(getApplication(), R.anim.slide_out_to_right);

                            // Display next screen.
                            swapSteps.showNext();
                        }
                        // Handling right to left screen swap.
                        if (lastX > currentX) {
                            // If there is a child (to the left), kust break.
                            if (swapSteps.getDisplayedChild() == 1)
                                break;
                            // Next screen comes in from right.
                            swapSteps.setInAnimation(getApplication(), R.anim.slide_in_from_right);
                            // Current screen goes out from left.
                            swapSteps.setOutAnimation(getApplication(), R.anim.slide_out_to_left);
                            // Display previous screen.
                            swapSteps.showPrevious();
                        }
                        swapSteps.getOutAnimation().setAnimationListener(new Animation.AnimationListener() {
                            public void onAnimationStart(Animation animation) {
                            }

                            public void onAnimationRepeat(Animation animation) {
                            }

                            public void onAnimationEnd(Animation animation) {
                               switch(swapSteps.getDisplayedChild()) {
                                   case 0:
                                       dots[1].setChecked(false);
                                       dots[0].setChecked(true);
                                       dishName.setText(dishText + " - krok 1");
                                       if(steps != null) {
                                           stepText.setText(steps[0]);
                                       }
                                       return;
                                   case 3:
                                       (dots[0].isChecked() ? dots[0]: dots[2]).setChecked(false);
                                       dots[1].setChecked(true);
                                       dishName.setText(dishText + " - krok 2");
                                       if(steps != null) {
                                           stepText.setText(steps[1]);
                                       }
                                       return;
                                   case 2:
                                       (dots[0].isChecked() ? dots[0]: dots[2]).setChecked(false);
                                       dots[2].setChecked(true);
                                       dishName.setText(dishText + " - krok 3");
                                       if(steps != null) {
                                           stepText.setText(steps[2]);
                                       }
                                       return;
                                   default:
                                       dots[2].setChecked(false);
                                       dots[3].setChecked(true);
                                       dishName.setText(dishText + " - krok 4");
                                       if(steps != null) {
                                           stepText.setText(steps[3]);
                                       }
                               }
                            }
                        });
                }
                return true;
            }
        });
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("http://chef.cba.pl")
                .build();
        Get service = retrofit.create(Get.class);
        Call<ResponseBody> result;
        switch(dishText) {
            case "Muszle z łososiem": {
                result = service.getStepsPhotosForSalmoNudle();
                if (result != null) {
                    result.enqueue(new Callback<ResponseBody>() {

                        @Override
                        public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                            try {
                                String responseString = response.body().string();
                                //Toast.makeText(getApplicationContext(), "ACAB", Toast.LENGTH_SHORT).show();
                                dispalyImages(responseString);
                            } catch (IOException e) {
                                Toast.makeText(getApplicationContext(), e.toString(), Toast.LENGTH_SHORT).show();
                            }
                        }

                        @Override
                        public void onFailure(Call<ResponseBody> call, Throwable t) {
                            //Toast.makeText(getApplicationContext(), "ACAB 1312", Toast.LENGTH_SHORT).show();
                            t.printStackTrace();
                        }
                    });
                }
            }
        }
    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout_recipe);
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


        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        int id = item.getItemId();

        Intent swapScreen = null;
        if (id == R.id.random) {
            swapScreen = new Intent(RecipeActivity.this, RandomActivity.class);
        } else if (id == R.id.predictors) {
            swapScreen = new Intent(RecipeActivity.this, PredictorActivity.class);
        } else if (id == R.id.about_app) {
            swapScreen = new Intent(RecipeActivity.this, AboutAppActivity.class);
        }
        RecipeActivity.this.startActivity(swapScreen);

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout_recipe);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    private void dispalyImages(String responseString) {
        try {
            JSONObject  jsonRootObject = new JSONObject(responseString);
            JSONArray jsonArray = jsonRootObject.optJSONArray("ZdjeciaEtapow");
            int stepNr;
            String photoString;
            JSONObject jsonObject;
            byte[] decodedString;
            Bitmap decodedByte;
            for(int i = 0; i < jsonArray.length(); i++){
                jsonObject = jsonArray.getJSONObject(i);
                stepNr = Integer.parseInt(jsonObject.optString("nrEtapu"));
                photoString = jsonObject.optString("zdjecie");
                if(stepNr != -1) {
                    decodedString = Base64.decode(photoString, Base64.NO_WRAP);
                    decodedByte = BitmapFactory.decodeByteArray(decodedString, 0, decodedString.length);
                    switch(stepNr) {
                        case 1:
                            step1image.setImageBitmap(decodedByte);
                            break;
                        case 2:
                            step4image.setImageBitmap(decodedByte);
                            break;
                        case 3:
                            step3image.setImageBitmap(decodedByte);
                            break;
                        case 4:
                            step2image.setImageBitmap(decodedByte);
                    }
                }
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

}
