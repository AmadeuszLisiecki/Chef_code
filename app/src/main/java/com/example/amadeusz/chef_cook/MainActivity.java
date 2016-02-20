package com.example.amadeusz.chef_cook;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.text.InputType;
import android.view.View;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.inputmethod.InputMethodManager;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.TextView;
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    private ArrayList<Row> toView;
    private ListView listView;
    private ArrayList<Row> rowTree;
    private TextView parentName;
    private EditText textSearch;
    private ImageButton back;
    private boolean serachedTxt = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        if(getSupportActionBar() != null) {
            getSupportActionBar().setTitle(null);
        }
        back = (ImageButton)findViewById(R.id.back_button);
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout_main);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view_main);
        navigationView.setNavigationItemSelectedListener(this);
        rowTree = new ArrayList<>();
        rowTree.add(new Row(R.drawable.meat, "Potrawy mięsne", null, "Kategoria"));
        rowTree.add(new Row(R.drawable.pork, "Wieprzowina", rowTree.get(0), "Kategoria"));
        rowTree.add(new Row(R.drawable.poultry, "Drób", rowTree.get(0), "Kategoria"));
        rowTree.add(new Row(R.drawable.chicken, "Kurczaki", rowTree.get(2), "Kategoria"));
        rowTree.add(new Row(R.drawable.turkey, "Indyki", rowTree.get(2), "Kategoria"));
        rowTree.add(new Row(R.drawable.goose, "Gęsi", rowTree.get(2), "Kategoria"));
        rowTree.add(new Row(R.drawable.duck, "Kaczki", rowTree.get(2), "Kategoria"));
        rowTree.add(new Row(R.drawable.beef, "Wołowina", rowTree.get(0), "Kategoria"));
        rowTree.add(new Row(R.drawable.sousage, "Kiełbasy", rowTree.get(0), "Kategoria"));
        rowTree.add(new Row(R.drawable.fishes, "Potrawy rybne", null, "Kategoria"));
        rowTree.add(new Row(R.drawable.herring, "Śledzie", rowTree.get(9), "Kategoria"));
        rowTree.add(new Row(R.drawable.salmon, "Łososie", rowTree.get(9), "Kategoria"));
        rowTree.add(new Row(R.drawable.trout, "Pstrągi", rowTree.get(9), "Kategoria"));
        rowTree.add(new Row(R.drawable.carp, "Karpie", rowTree.get(9), "Kategoria"));
        rowTree.add(new Row(R.drawable.seafood, "Owoce morza", rowTree.get(9), "Kategoria"));
        rowTree.add(new Row(R.drawable.meatless, "Potrawy bezmięsne", null, "Kategoria"));
        rowTree.add(new Row(R.drawable.vegetables, "Warzywne", rowTree.get(15), "Kategoria"));
        rowTree.add(new Row(R.drawable.mushrooms, "Grzybowe", rowTree.get(15), "Kategoria"));
        rowTree.add(new Row(R.drawable.eggs_, "Z mąki, mleka i jajek, ", rowTree.get(15), "Kategoria"));
        rowTree.add(new Row(R.drawable.noodles, "Makaronowe", rowTree.get(15), "Kategoria"));
        rowTree.add(new Row(R.drawable.rise, "Z ryżu", rowTree.get(15), "Kategoria"));
        rowTree.add(new Row(R.drawable.kasha, "Z kaszy", rowTree.get(15), "Kategoria"));
        rowTree.add(new Row(R.drawable.soup, "Zupy", null, "Kategoria"));
        rowTree.add(new Row(R.drawable.beet_soup, "Barszcze", rowTree.get(22), "Kategoria"));
        rowTree.add(new Row(R.drawable.broth, "Rosoły", rowTree.get(22), "Kategoria"));
        rowTree.add(new Row(R.drawable.barley_soup, "Krupniki", rowTree.get(22), "Kategoria"));
        rowTree.add(new Row(R.drawable.pottage, "Jarzynowe", rowTree.get(22), "Kategoria"));
        rowTree.add(new Row(R.drawable.tomato_soup, "Pomidorowe", rowTree.get(22), "Kategoria"));
        rowTree.add(new Row(R.drawable.salad, "Sałatki", null, "Kategoria"));
        rowTree.add(new Row(R.drawable.meat_salad, "Mięsne", rowTree.get(28), "Kategoria"));
        rowTree.add(new Row(R.drawable.meat_salad, "Warzywne", rowTree.get(28), "Kategoria"));
        rowTree.add(new Row(R.drawable.meat_salad, "Owocowe", rowTree.get(28), "Kategoria"));
        rowTree.add(new Row(R.drawable.egg_salad, "Jajeczne", rowTree.get(28), "Kategoria"));
        rowTree.add(new Row(R.drawable.snack, "Przekąski", null, "Kategoria"));
        rowTree.add(new Row(R.drawable.cold_snack, "Na zimno", rowTree.get(33), "Kategoria"));
        rowTree.add(new Row(R.drawable.hot_snack, "Na ciepło", rowTree.get(33), "Kategoria"));
        rowTree.add(new Row(R.drawable.dessert, "Desery", null, "Kategoria"));
        rowTree.add(new Row(R.drawable.pie, "Ciasta", rowTree.get(36), "Kategoria"));
        rowTree.add(new Row(R.drawable.cake, "Ciastka", rowTree.get(36), "Kategoria"));
        rowTree.add(new Row(R.drawable.kissel, "Kisiele", rowTree.get(36), "Kategoria"));
        rowTree.add(new Row(R.drawable.pudding, "Budynie", rowTree.get(36), "Kategoria"));
        rowTree.add(new Row(R.drawable.jelly, "Galaretki", rowTree.get(36), "Kategoria"));
        rowTree.add(new Row(R.drawable.health_food, "Zdrowa żywność", null, "Kategoria"));
        rowTree.add(new Row(R.drawable.salmo_nuddle, "Muszle z łososiem", rowTree.get(11), "Przepis"));
        fillDatabase();
        toView = new ArrayList<>();
        parentName = (TextView)findViewById(R.id.parent_text);
        showElementsForParent(null);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Row clickedRow = toView.get(position);
                if (clickedRow.getType().equals("Kategoria")) {
                    showElementsForParent(clickedRow);
                } else {
                    Intent swapScreen = new Intent(MainActivity.this, DishActivity.class);
                    swapScreen.putExtra("dish name", clickedRow.getDescription());
                    MainActivity.this.startActivity(swapScreen);
                }

            }
        });
        textSearch = (EditText)findViewById(R.id.search_text);

        textSearch.setInputType(InputType.TYPE_NULL);
        textSearch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
                imm.toggleSoftInput(InputMethodManager.SHOW_FORCED, 0);
                textSearch.requestFocus();
            }
        });
    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout_main);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        int id = item.getItemId();
        Intent swapScreen = null;
        if (id == R.id.random) {
            swapScreen = new Intent(MainActivity.this, RandomActivity.class);
        } else if (id == R.id.predictors) {
            swapScreen = new Intent(MainActivity.this, PredictorActivity.class);
        } else if (id == R.id.about_app) {
            swapScreen = new Intent(MainActivity.this, AboutAppActivity.class);
        }
        MainActivity.this.startActivity(swapScreen);

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout_main);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    private void showElementsForParent(Row clicked) {
        toView.clear();
        for(int i = 0; i < rowTree.size(); i++) {
            if(rowTree.get(i).getParent() == clicked) {
                toView.add(rowTree.get(i));
            }
        }
        RowAdapter adapter = new RowAdapter(this, R.layout.row, toView);
        listView = (ListView)findViewById(R.id.list);

        listView.setAdapter(adapter);
        if(clicked != null) {
            parentName.setText(clicked.getDescription());
            back.setVisibility(View.VISIBLE);
        }
        else {
            parentName.setText(R.string.chef);
            back.setVisibility(View.INVISIBLE);
        }
    }

    public void back1level(View button) {
        if(!serachedTxt) {
            for(int i = 0; i < rowTree.size(); i++) {
                if(rowTree.get(i).getDescription() == parentName.getText()) {
                    showElementsForParent(rowTree.get(i).getParent());
                }
            }
        }
        else {
            showElementsForParent(null);
            serachedTxt = false;
        }
    }

    private void fillDatabase() {
        Base.transformRows(rowTree);
        Ingredient conchiglioniShells = new Ingredient(20, "", "muszli conchiglioni", 6);
        Ingredient cannelloniNuddle = new Ingredient(20, "sztuk", "makaronu cannelloni", -1);
        Ingredient pubescentHam = new Ingredient(120, "g", "szynki dojrzewającej", -1);
        Ingredient porkHam = new Ingredient(105, "g", "szynki wieprzowej", -1);
        Ingredient turkeyHam = new Ingredient(300, "g", "szynki z indyka", -1);
        Ingredient smokedSalmon = new Ingredient(150, "g", "wędzonego łososia", 22.5);
        Ingredient sandwichCheese = new Ingredient(150, "g", "serka kanapkowego", -1);
        Ingredient naturalCremeCheese = new Ingredient(150, "g", "serka śmietankowego naturlanego", 2.5);
        Ingredient pressBanana = new Ingredient(3, "", "zgniecione banany", -1);
        Ingredient eggsOnHard = new Ingredient(3, "", "jajka ugotowane na twardo", 1.2);
        Ingredient homogenizedCheese = new Ingredient(4, "łyżeczki", "serka homogenizowanego", -1);
        Ingredient mayonnaise = new Ingredient(1, "łyżka", "majonezu", 0.3);
        Ingredient olivies = new Ingredient(1, "łyżeczka", "oliwek", -1);
        Ingredient mustard = new Ingredient(1, "łyżeczka", "musztardy", 0.05);
        Ingredient mulveriseMango = new Ingredient(1, "łyżeczka", "sproszkowanego mango", -1);
        Ingredient juiceWithLemon = new Ingredient(1, "łyżeczka", "soku z cytryny", 0.1);
        Ingredient garlic = new Ingredient(-1, "", "czosnek", -1);
        Ingredient saltAndPepper = new Ingredient(-1, "", "sól i pieprz", 0.17);
        Ingredient parsley = new Ingredient(-1, "", "natka pietruszki", -1);
        Ingredient chopChives = new Ingredient(-1, "", "posiekany szczypiorek", 0.1);
        Base.addIngredient(conchiglioniShells);
        Base.addIngredient(cannelloniNuddle);
        Base.addIngredient(pubescentHam);
        Base.addIngredient(porkHam);
        Base.addIngredient(turkeyHam);
        Base.addIngredient(smokedSalmon);
        Base.addIngredient(sandwichCheese);
        Base.addIngredient(pressBanana);
        Base.addIngredient(naturalCremeCheese);
        Base.addIngredient(eggsOnHard);
        Base.addIngredient(homogenizedCheese);
        Base.addIngredient(mayonnaise);
        Base.addIngredient(olivies);
        Base.addIngredient(mustard);
        Base.addIngredient(mulveriseMango);
        Base.addIngredient(juiceWithLemon);
        Base.addIngredient(garlic);
        Base.addIngredient(saltAndPepper);
        Base.addIngredient(parsley);
        Base.addIngredient(chopChives);
        Base.setSubstituteForIngredient(conchiglioniShells, cannelloniNuddle);
        Base.setSubstituteForIngredient(smokedSalmon, pubescentHam);
        Base.setSubstituteForIngredient(smokedSalmon, porkHam);
        Base.setSubstituteForIngredient(smokedSalmon, turkeyHam);
        Base.setSubstituteForIngredient(sandwichCheese, naturalCremeCheese);
        Base.setSubstituteForIngredient(pressBanana, eggsOnHard);
        Base.setSubstituteForIngredient(homogenizedCheese, mayonnaise);
        Base.setSubstituteForIngredient(olivies, mustard);
        Base.setSubstituteForIngredient(mulveriseMango, juiceWithLemon);
        Base.setSubstituteForIngredient(garlic, saltAndPepper);
        Base.setSubstituteForIngredient(parsley, chopChives);

        Base.match("Muszle z łososiem", conchiglioniShells);
        Base.match("Muszle z łososiem", smokedSalmon);
        Base.match("Muszle z łososiem", naturalCremeCheese);
        Base.match("Muszle z łososiem", eggsOnHard);
        Base.match("Muszle z łososiem", mayonnaise);
        Base.match("Muszle z łososiem", mustard);
        Base.match("Muszle z łososiem", juiceWithLemon);
        Base.match("Muszle z łososiem", saltAndPepper);
        Base.match("Muszle z łososiem", chopChives);

        Step[] nuddleSalmonSteps = new Step[]{
                new Step(
                        R.drawable.salmo_nuddle1,
                        "Muszle ugotować w osolonym wrzątku al dente, przecedzić, przelać zimną wodą i wyłożyć na talerz.",
                        1
                ),
                new Step(
                        R.drawable.salmo_nuddle2,
                        "W misce zmieszać ze sobą serek śmietankowy, pokrojonego łososia i jajka starte na tarce o małych oczkach.",
                        2
                ),
                new Step(
                        R.drawable.salmo_nuddle3,
                        "Doprawić pastę majonezem, musztardą sokiem z cytryny oraz solą i pieprzem. Ugotowane, ostudzone muszle" +
                                " nadziewać pastą.",
                        3
                ),
                new Step(
                        R.drawable.salmo_nuddle4,
                        "Ułożyć na talerz i posypać szczypiorkiem. Podawać jako przystawkę na zimno na wszelkich przyjęciach.",
                        4
                )
        };
        Base.addStep(nuddleSalmonSteps);
        Base.match("Muszle z łososiem", nuddleSalmonSteps);
        Base.getRecepture("Muszle z łososiem").setDetails("Dziś pomysł na pyszną przekąskę, która uświetni niejedno przyjęcie w gronie"
                        + " przyjaciół czy rodziny. Idealne na czas karnawału. Muszle przypominają swoim kształtem łódeczki.", 30, 50, 20, 2,
                "łatwe", 30, 250, 20, "Kolacja");
        ArrayList<Multimedia> nuddleSalmonMultimedia = new ArrayList<>();
        nuddleSalmonMultimedia.add(new Video("https://www.youtube.com/watch?v=sBgdm4tkB7I"));
        nuddleSalmonMultimedia.add(new Photo(R.drawable.salmo_nuddle_photo1, R.drawable.salmo_nuddle_photo1_big));
        nuddleSalmonMultimedia.add(new Photo(R.drawable.salmo_nuddle_photo2, R.drawable.salmo_nuddle_photo2_big));
        nuddleSalmonMultimedia.add(new Photo(R.drawable.salmo_nuddle_photo3, R.drawable.salmo_nuddle_photo3_big));
        nuddleSalmonMultimedia.add(new Photo(R.drawable.salmo_nuddle_photo4, R.drawable.salmo_nuddle_photo4_big));
        Base.getRecepture("Muszle z łososiem").setMultimedia(nuddleSalmonMultimedia);
        Base.addPredictor(new Predictor("Bulion", 250, 15, 5, R.drawable.bulion, "ml"));
        Base.addPredictor(new Predictor("Bułka tarta", 150, 8, 3, R.drawable.breadcrumbs, "g"));
        Base.addPredictor(new Predictor("Cukier", 220, 15, 5, R.drawable.sugar, "g"));
        Base.addPredictor(new Predictor("Cukier puder", 200, 10, 3, R.drawable.icing_sugar, "g"));
        Base.addPredictor(new Predictor("Cukier wnailiowy", 250, 15, 5, R.drawable.vanillin_sugar, "g"));
        Base.addPredictor(new Predictor("Cynamon", 175, 10.5, 3.5, R.drawable.cinnamon, "g"));
        Base.addPredictor(new Predictor("Czekolada w proszku", 175, 10.5, 3.5, R.drawable.chocolade, "g"));
        Base.addPredictor(new Predictor("Dżem", 300, 18, 6, R.drawable.jam, "g"));
        Base.addPredictor(new Predictor("Kakao", 125, 7, 2.5, R.drawable.cacao, "g"));
        Base.addPredictor(new Predictor("Kasza gryczana", 170, 10, 3.3, R.drawable.buckwheat, "g"));
        Base.addPredictor(new Predictor("Kasza jęczmienna", 180, 11, 3.5, R.drawable.barley, "g"));
        Base.addPredictor(new Predictor("Kasza manna", 180, 11, 3.5, R.drawable.semolina, "g"));
        Base.addPredictor(new Predictor("Ketchup", 280, 18, 6, R.drawable.ketchup, "ml"));
        Base.addPredictor(new Predictor("Koncentrat pomidorowy", 260, 17, 6.5, R.drawable.tomato_puree, "ml"));
        Base.addPredictor(new Predictor("Majonez", 225, 13.5, 4.5, R.drawable.mayonnaise, "g"));
        Base.addPredictor(new Predictor("Mak", 165, 8, 2.6, R.drawable.poppy, "g"));
        Base.addPredictor(new Predictor("Margaryna", 250, 15, 5, R.drawable.margarine, "g"));
        Base.addPredictor(new Predictor("Masło", 250, 15, 5, R.drawable.butter, "g"));
        Base.addPredictor(new Predictor("Mąka pszenna", 170, 10, 3, R.drawable.wheat_flour, "g"));
        Base.addPredictor(new Predictor("Migdały", 170, 10, 3, R.drawable.almonds, "g"));
        Base.addPredictor(new Predictor("Miód", 360, 22, 7, R.drawable.honey, "g"));
        Base.addPredictor(new Predictor("Mleko", 250, 15, 5, R.drawable.milk, "ml"));
        Base.addPredictor(new Predictor("Mleko w proszku", 175, 10.5, 3.5, R.drawable.milk_powder, "ml"));
        Base.addPredictor(new Predictor("Olej", 230, 14, 4.7, R.drawable.oil, "ml"));
        Base.addPredictor(new Predictor("Orzechy laskowe mielone", 250, 15, 5, R.drawable.hazelnuts, "g"));
        Base.addPredictor(new Predictor("Papryka mielona", 142, 8.5, 2.8, R.drawable.pepper, "g"));
        Base.addPredictor(new Predictor("Pieprz mielony", 117, 7, 2.3, R.drawable.spice, "g"));
        Base.addPredictor(new Predictor("Płatki owsiane", 90, 5.4, 1.8, R.drawable.oatmeal, "g"));
        Base.addPredictor(new Predictor("Proszek do pieczenia", 300, 18, 6, R.drawable.backpulver, "g"));
        Base.addPredictor(new Predictor("Rodzynki", 175, 10.5, 3.5, R.drawable.raisins, "g"));
        Base.addPredictor(new Predictor("Ryż", 225, 13.5, 4.5, R.drawable.rise2, "g"));
        Base.addPredictor(new Predictor("Smalec", 175, 10.5, 3.5, R.drawable.lard, "ml"));
        Base.addPredictor(new Predictor("Soda oczyszczona", 260, 15.5, 5.15, R.drawable.bicarbonate, "g"));
        Base.addPredictor(new Predictor("Śmietana", 220, 12, 4, R.drawable.cream, "g"));
        Base.addPredictor(new Predictor("Wiórki kokosowe", 180, 10, 3, R.drawable.coconut_shrimes, "g"));
        Base.addPredictor(new Predictor("Woda", 250, 15, 5, R.drawable.water, "ml"));
        Base.addPredictor(new Predictor("Żelatyna", 170, 10, 3, R.drawable.gelatine, "g"));
        Base.addPredictor(new Predictor("Żółty ser (starty)", 125, 8, 2.8, R.drawable.cheese, "g"));
    }

    public void search(View v) {
        String searchText = (textSearch.getText().toString().toLowerCase());
        if(!searchText.equals("")) {
            serachedTxt = true;
            String[] words = searchText.split(" ");
            ArrayList<Row> receptures = new ArrayList<>();
            for(int i = 0; i < rowTree.size(); i++) {
                if(rowTree.get(i).getType().equals("Przepis")) {
                    receptures.add(rowTree.get(i));
                }
            }
            toView.clear();
            for (String word : words) {
                for (int j = 0; j < receptures.size(); j++) {
                    switch (word.length()) {
                        case 1:
                            if (receptures.get(j).getDescription().contains(word.substring(1))) {
                                toView.add(receptures.get(j));
                            }
                            break;
                        default:
                            if (receptures.get(j).getDescription().contains(word.substring(1, Math.min(word.length(), 5)))) {
                                toView.add(receptures.get(j));
                            }
                    }
                }
            }
            RowAdapter adapter = new RowAdapter(this, R.layout.row, toView);
            listView.setAdapter(adapter);
            parentName.setText(R.string.results);
            back.setVisibility(View.VISIBLE);
        }
    }
}

