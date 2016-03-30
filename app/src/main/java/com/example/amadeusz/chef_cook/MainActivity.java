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
        //rowTree.add(new Row(R.drawable.pork, "Wieprzowina", rowTree.get(0), "Kategoria"));
        //rowTree.add(new Row(R.drawable.poultry, "Drób", rowTree.get(0), "Kategoria"));
        //rowTree.add(new Row(R.drawable.chicken, "Kurczaki", rowTree.get(2), "Kategoria"));
        //rowTree.add(new Row(R.drawable.turkey, "Indyki", rowTree.get(2), "Kategoria"));
        //rowTree.add(new Row(R.drawable.goose, "Gęsi", rowTree.get(2), "Kategoria"));
        //rowTree.add(new Row(R.drawable.duck, "Kaczki", rowTree.get(2), "Kategoria"));
        //rowTree.add(new Row(R.drawable.beef, "Wołowina", rowTree.get(0), "Kategoria"));
        //rowTree.add(new Row(R.drawable.sousage, "Kiełbasy", rowTree.get(0), "Kategoria"));
        rowTree.add(new Row(R.drawable.fishes, "Potrawy rybne", null, "Kategoria"));
        //rowTree.add(new Row(R.drawable.herring, "Śledzie", rowTree.get(9), "Kategoria"));
        //rowTree.add(new Row(R.drawable.salmon, "Łososie", rowTree.get(9), "Kategoria"));
        //rowTree.add(new Row(R.drawable.trout, "Pstrągi", rowTree.get(9), "Kategoria"));
        //rowTree.add(new Row(R.drawable.carp, "Karpie", rowTree.get(9), "Kategoria"));
        //rowTree.add(new Row(R.drawable.seafood, "Owoce morza", rowTree.get(9), "Kategoria"));
        rowTree.add(new Row(R.drawable.meatless, "Potrawy bezmięsne", null, "Kategoria"));
        //rowTree.add(new Row(R.drawable.vegetables, "Warzywne", rowTree.get(15), "Kategoria"));
        //rowTree.add(new Row(R.drawable.mushrooms, "Grzybowe", rowTree.get(15), "Kategoria"));
        //rowTree.add(new Row(R.drawable.eggs_, "Z mąki, mleka i jajek, ", rowTree.get(15), "Kategoria"));
        //rowTree.add(new Row(R.drawable.noodles, "Makaronowe", rowTree.get(15), "Kategoria"));
        //rowTree.add(new Row(R.drawable.rise, "Z ryżu", rowTree.get(15), "Kategoria"));
        //rowTree.add(new Row(R.drawable.kasha, "Z kaszy", rowTree.get(15), "Kategoria"));
        rowTree.add(new Row(R.drawable.soup, "Zupy", null, "Kategoria"));
        //rowTree.add(new Row(R.drawable.beet_soup, "Barszcze", rowTree.get(22), "Kategoria"));
        //rowTree.add(new Row(R.drawable.broth, "Rosoły", rowTree.get(22), "Kategoria"));
        //rowTree.add(new Row(R.drawable.barley_soup, "Krupniki", rowTree.get(22), "Kategoria"));
        //rowTree.add(new Row(R.drawable.pottage, "Jarzynowe", rowTree.get(22), "Kategoria"));
        //rowTree.add(new Row(R.drawable.tomato_soup, "Pomidorowe", rowTree.get(22), "Kategoria"));
        rowTree.add(new Row(R.drawable.salad, "Sałatki", null, "Kategoria"));
        //rowTree.add(new Row(R.drawable.meat_salad, "Mięsne", rowTree.get(28), "Kategoria"));
        //rowTree.add(new Row(R.drawable.meat_salad, "Warzywne", rowTree.get(28), "Kategoria"));
        //rowTree.add(new Row(R.drawable.meat_salad, "Owocowe", rowTree.get(28), "Kategoria"));
        //rowTree.add(new Row(R.drawable.egg_salad, "Jajeczne", rowTree.get(28), "Kategoria"));
        rowTree.add(new Row(R.drawable.snack, "Przekąski", null, "Kategoria"));
        //rowTree.add(new Row(R.drawable.cold_snack, "Na zimno", rowTree.get(33), "Kategoria"));
        //rowTree.add(new Row(R.drawable.hot_snack, "Na ciepło", rowTree.get(33), "Kategoria"));
        rowTree.add(new Row(R.drawable.dessert, "Desery", null, "Kategoria"));
        //rowTree.add(new Row(R.drawable.pie, "Ciasta", rowTree.get(36), "Kategoria"));
        //rowTree.add(new Row(R.drawable.cake, "Ciastka", rowTree.get(36), "Kategoria"));
        //rowTree.add(new Row(R.drawable.kissel, "Kisiele", rowTree.get(36), "Kategoria"));
        //rowTree.add(new Row(R.drawable.pudding, "Budynie", rowTree.get(36), "Kategoria"));
        //rowTree.add(new Row(R.drawable.jelly, "Galaretki", rowTree.get(36), "Kategoria"));
        rowTree.add(new Row(R.drawable.health_food, "Zdrowa żywność", null, "Kategoria"));
        rowTree.add(new Row(R.drawable.salmo_nuddle, "Muszle z łososiem", rowTree.get(1), "Przepis"));
        rowTree.add(new Row(R.drawable.coconut_stew_with_pineapple, "Kokosowa potrawka z ananasem", rowTree.get(0), "Przepis"));
        rowTree.add(new Row(R.drawable.sweet_pampuchy, "Słodkie pampuchy", rowTree.get(2), "Przepis"));
        rowTree.add(new Row(R.drawable.tomato_soup, "Zupa pomidorowa", rowTree.get(3), "Przepis"));
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

        // MUSZLE Z ŁOSOSIEM

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
        Ingredient mayonnaise = new Ingredient(1, " łyżka", "majonezu", 0.3);
        Ingredient olivies = new Ingredient(1, "łyżeczka", "oliwek", -1);
        Ingredient mustard = new Ingredient(1, " łyżeczka", "musztardy", 0.05);
        Ingredient mulveriseMango = new Ingredient(1, "łyżeczka", "sproszkowanego mango", -1);
        Ingredient juiceWithLemon = new Ingredient(1, " łyżeczka", "soku z cytryny", 0.1);
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
        String[] nuddleSalmonSteps = new String[]{
                "Muszle ugotować w osolonym wrzątku al dente, przecedzić, przelać zimną wodą i wyłożyć na talerz.",
                "W misce zmieszać ze sobą serek śmietankowy, pokrojonego łososia i jajka starte na tarce o małych oczkach.",
                "Doprawić pastę majonezem, musztardą sokiem z cytryny oraz solą i pieprzem. Ugotowane, ostudzone muszle nadziewać pastą.",
                "Ułożyć na talerz i posypać szczypiorkiem. Podawać jako przystawkę na zimno na wszelkich przyjęciach."
        };
        Base.addStep(nuddleSalmonSteps);
        Base.match("Muszle z łososiem", nuddleSalmonSteps);
        Base.getRecepture("Muszle z łososiem").setDetails("Dziś pomysł na pyszną przekąskę, która uświetni niejedno przyjęcie w gronie"
                        + " przyjaciół czy rodziny. Idealne na czas karnawału. Muszle przypominają swoim kształtem łódeczki.", 30, 66, 12, 26,
                "łatwe", 260, 542, 4, "Kolacja");
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

        // KOKOSOWA POTRAWKA Z ANANASEM

        Ingredient chicken_breast = new Ingredient(1, " pojedyncza", "pierś z kurczaka", 2.1);
        Ingredient other_meat = new Ingredient(200, "g", "innego mięsa (np. wątróbk, ryby)", -1);
        Ingredient pineapple = new Ingredient(4, " plastry", "ananasa", 0.12);
        Ingredient other_fruit = new Ingredient(4, " plastry", "innego owocu (np. jabłka)", -1);
        Ingredient pineappple_syrup = new Ingredient(83, "ml", "syropu z ananasa", 0.4);
        Ingredient other_syrup = new Ingredient(83, " ml", "soku lub syropu z innego owocu (np. jabłka)", -1);
        Ingredient cherry_tomatoes = new Ingredient(6, "", "pomidorków koktajlowych", 1.5);
        Ingredient tomatoe = new Ingredient(1, "", "pomidor", -1);
        Ingredient yellow_pepper = new Ingredient(4, " plastry", "żółtej papryki", 0.2);
        Ingredient other_pepper = new Ingredient(4, " plastry", "innej papryki (np. zielonej)", -1);
        Ingredient red_pepper = new Ingredient(4, " plastry", "czerwonej papryki", 0.2);
        Ingredient coconut_milk = new Ingredient(125, "ml", "mleka kokosowego", 7.7);
        Ingredient milk = new Ingredient(125, " ml", "mleka krowiego", -1);
        Ingredient water = new Ingredient(125, "ml", "wody", 0.05);
        Ingredient cocnut_oil = new Ingredient(1, " łyżka", "oleju kokosowego", 1.37);
        Ingredient oil = new Ingredient(1, " łyżka", "oleju słonecznikowego", -1);
        Ingredient grape_seed_oil = new Ingredient(2, " łyżki", "oleju z pestek winogron", 1.35);
        Ingredient oils = new Ingredient(2, " łyżki", "oleju słonecznikowego", -1);
        // 1 łyżeczka soku z cytryny
        Ingredient grated_lemon_peel = new Ingredient(1, " szczypta", "utartej skórki z cytryny", 0.34);
        Ingredient other_citrus = new Ingredient(1, " szczypta", "utartej skórki z innego owoca cytrusowego (np. grapefruita)", -1);
        Ingredient sugar_cane = new Ingredient(1, " szczypta", "cukru trzcionowego", 0.04);
        Ingredient badian = new Ingredient(1, "", "badian", 0.2);
        Ingredient pepper = new Ingredient(1, " szczypta", "pieprzu", -1);
        Ingredient curry3 = new Ingredient(1, " łyżeczka", "curry", 0.4);
        Ingredient tropical_fruit_tea = new Ingredient(1, " saszetka", "herbaty o smaku owoców tropikalnych", 0.45);
        Ingredient other_tea = new Ingredient(1, " saszetka", "innej herbaty", -1);
        Ingredient peach_mango_tea = new Ingredient(2, " saszetki", "herbaty o smaku brzoskwinia-mango", 0.8);
        //sól i pieprz
        Ingredient kafir = new Ingredient(1, " liść", "kafiru", 0.06);
        Ingredient lemon_peel = new Ingredient(1, "", "kawałek skórki cytryny", -1);
        Ingredient cut_parsley = new Ingredient(2, " łyżki", "pokrojonej natki pietruszki", 0.42);
        Ingredient sugar = new Ingredient(1, " szczypta", "cukru buraczanego", -1);
        Base.addIngredient(chicken_breast);
        Base.addIngredient(pineapple);
        Base.addIngredient(pineappple_syrup);
        Base.addIngredient(cherry_tomatoes);
        Base.addIngredient(yellow_pepper);
        Base.addIngredient(red_pepper);
        Base.addIngredient(coconut_milk);
        Base.addIngredient(water);
        Base.addIngredient(cocnut_oil);
        Base.addIngredient(grape_seed_oil);
        //Base.addIngredient(juiceWithLemon);
        Base.addIngredient(grated_lemon_peel);
        //Base.addIngredient(saltAndPepper);
        Base.addIngredient(sugar_cane);
        Base.addIngredient(badian);
        Base.addIngredient(curry3);
        Base.addIngredient(oil);
        Base.addIngredient(tropical_fruit_tea);
        Base.addIngredient(peach_mango_tea);
        Base.addIngredient(kafir);
        Base.addIngredient(cut_parsley);
        Base.addIngredient(other_meat);
        Base.addIngredient(other_fruit);
        Base.addIngredient(cut_parsley);
        Base.addIngredient(tomatoe);
        Base.addIngredient(other_pepper);
        Base.addIngredient(milk);
        Base.addIngredient(oil);
        Base.addIngredient(oils);
        Base.addIngredient(other_citrus);
        Base.addIngredient(sugar_cane);
        Base.addIngredient(pepper);
        Base.addIngredient(other_syrup);
        Base.addIngredient(other_tea);
        Base.addIngredient(lemon_peel);

        Base.setSubstituteForIngredient(chicken_breast, other_meat);
        Base.setSubstituteForIngredient(pineapple, other_fruit);
        Base.setSubstituteForIngredient(pineappple_syrup, other_syrup);
        Base.setSubstituteForIngredient(cherry_tomatoes, tomatoe);
        Base.setSubstituteForIngredient(yellow_pepper, other_pepper);
        Base.setSubstituteForIngredient(red_pepper, other_pepper);
        Base.setSubstituteForIngredient(coconut_milk, milk);
        Base.setSubstituteForIngredient(cocnut_oil, oil);
        Base.setSubstituteForIngredient(grape_seed_oil, oils);
        Base.setSubstituteForIngredient(grated_lemon_peel, other_citrus);
        Base.setSubstituteForIngredient(sugar_cane, sugar);
        Base.setSubstituteForIngredient(badian, pepper);
        Base.setSubstituteForIngredient(curry3, pepper);
        Base.setSubstituteForIngredient(tropical_fruit_tea, other_tea);
        Base.setSubstituteForIngredient(peach_mango_tea, other_tea);
        Base.setSubstituteForIngredient(kafir, lemon_peel);
        Base.setSubstituteForIngredient(cut_parsley, chopChives);

        Base.match("Kokosowa potrawka z ananasem", chicken_breast);
        Base.match("Kokosowa potrawka z ananasem", pineapple);
        Base.match("Kokosowa potrawka z ananasem", pineappple_syrup);
        Base.match("Kokosowa potrawka z ananasem", cherry_tomatoes);
        Base.match("Kokosowa potrawka z ananasem", yellow_pepper);
        Base.match("Kokosowa potrawka z ananasem", red_pepper);
        Base.match("Kokosowa potrawka z ananasem", coconut_milk);
        Base.match("Kokosowa potrawka z ananasem", water);
        Base.match("Kokosowa potrawka z ananasem", cocnut_oil);
        Base.match("Kokosowa potrawka z ananasem", grape_seed_oil);
        Base.match("Kokosowa potrawka z ananasem", juiceWithLemon);
        Base.match("Kokosowa potrawka z ananasem", grated_lemon_peel);
        Base.match("Kokosowa potrawka z ananasem", saltAndPepper);
        Base.match("Kokosowa potrawka z ananasem", sugar_cane);
        Base.match("Kokosowa potrawka z ananasem", badian);
        Base.match("Kokosowa potrawka z ananasem", curry3);
        Base.match("Kokosowa potrawka z ananasem", grape_seed_oil);
        Base.match("Kokosowa potrawka z ananasem", tropical_fruit_tea);
        Base.match("Kokosowa potrawka z ananasem", peach_mango_tea);
        Base.match("Kokosowa potrawka z ananasem", kafir);
        Base.match("Kokosowa potrawka z ananasem", cut_parsley);

        String[] coconutStewWithPineappleSteps = new String[]{
                "Pokroić w cienkie paseczki pierś z kurczaka. Wymieszać w miseczce z olejem, płatkami paprykowymi, szczyptą soli i połową curry. Na głębokiej patelni lub w woku rozgrzać olej kokosowy.",
                "Włożyć paski kurczaka i obsmażyć na rumiano. Odcedzić ananas z zalewy. Zmiksować papryki i ananasa (nie za drobno) w rozdrabniaczu. Przełożyć pastę na patelnię.",
                "Dorzucić listek kafiru, curry, kurkumę, skórkę cytrynową, badian, pieprz kolorowy, cukier, sok z ananasa. Zalać wodą i mlekiem kokosowym. Zagotować. Włożyć herbaty. Dusić na małym gazie 20 minut.",
                "Usunąć saszetki herbaty i listek kafiru. Doprawić do smaku solą , pieprzem i sokiem z cytryny. Pomidorki przekroić na pół i usunąć pestki. Miąższ pokroić w cienkie paski. Wrzucić na patelnię i dusić pod przykryciem 5 minut."
        };
        Base.addStep(coconutStewWithPineappleSteps);
        Base.match("Kokosowa potrawka z ananasem", coconutStewWithPineappleSteps);
        Base.getRecepture("Kokosowa potrawka z ananasem").setDetails("Szybki sposób na tajską potrawkę. Herbaty smakowe świetnie sprawdzają się jako przyprawy. Wystarczy kilkadziesiąt minut i obiad gotowy", 20, 28, 24, 89,
                "łatwe", 549, 1014, 2, "Obiad");

        //Słodkie pampuchy

        Ingredient yeast = new Ingredient(20, "g", "drożdży w kostce", 0.14);
        Ingredient flour = new Ingredient(625, "g", "mąki", 1.05);
        Ingredient egg = new Ingredient(1, "", "jajko", 0.5);
        Ingredient butter = new Ingredient(2, " łyżki", "roztopionego masła", 0.75);
        Ingredient salt = new Ingredient(1, " szczypta", "soli", 0.03);
        Ingredient sugar30 = new Ingredient(2, " łyżki", "cukru", 0.08);
        Ingredient milk250 = new Ingredient(1, " szklanka", "mleka", 0.7);
        Ingredient yeast_other = new Ingredient(7, "g", "drożdży sproszkowanych", -1);
        Ingredient flour_other = new Ingredient(625, "g", "mąki innej, niż pszenna", -1);
        Ingredient egg_other = new Ingredient(5, "", "jajek przepiórczych", -1);
        Ingredient margarine = new Ingredient(2, " łyżki", "margaryny", -1);
        Ingredient oil30 = new Ingredient(2, " łyżki", "oleju", -1);
        Ingredient sugar_other = new Ingredient(2, " łyżki", "innego cukru (np. trzcinowego)", -1);
        Ingredient milk_other = new Ingredient(1, " szklanka", "innego mleka (np. koziego)", -1);

        Base.addIngredient(yeast);
        Base.addIngredient(flour);
        Base.addIngredient(egg);
        Base.addIngredient(sugar30);
        Base.addIngredient(milk250);
        Base.addIngredient(salt);
        Base.addIngredient(butter);

        Base.addIngredient(yeast_other);
        Base.addIngredient(flour_other);
        Base.addIngredient(egg_other);
        Base.addIngredient(margarine);
        Base.addIngredient(oil30);
        Base.addIngredient(sugar_other);
        Base.addIngredient(butter);
        Base.addIngredient(milk_other);

        Base.match("Słodkie pampuchy", yeast);
        Base.match("Słodkie pampuchy", flour);
        Base.match("Słodkie pampuchy", egg);
        Base.match("Słodkie pampuchy", salt);
        Base.match("Słodkie pampuchy", sugar30);
        Base.match("Słodkie pampuchy", milk250);
        Base.match("Słodkie pampuchy", butter);

        Base.setSubstituteForIngredient(yeast, yeast_other);
        Base.setSubstituteForIngredient(flour, flour_other);
        Base.setSubstituteForIngredient(egg, egg_other);
        Base.setSubstituteForIngredient(butter, margarine);
        Base.setSubstituteForIngredient(butter, oil30);
        Base.setSubstituteForIngredient(sugar30, sugar_other);
        Base.setSubstituteForIngredient(milk250, milk_other);

        String[] sweetPapmpuchySteps = new String[]{
                "Drożdże rozpuszczamy w odrobince ciepłego mleka ze szczyptą mąki i soli. Odstawiam do wyrośnięcia a gdy są już gotowe mieszam je z mąką, resztą mleka, cukrem, jajkiem, rozpuszczonym masłem.",
                "Wyrabiam ciasto ręką przez chwilę po czym przykrywam ściereczką i odstawiam w cieple miejsce do wyrośnięcia. Po około godzinie z wyrośniętego ciasta formuję okrągłe pyzy - nie za duże bo w trakcie gotowania urosną jeszcze.",
                "Pyzy pozostawiam jeszcze na trochę czasu do wyrośnięcia. Do garnka (do połowy wysokości) wlewam wodę i zagotowuje ją. Następnie gotuję pyzy na parze na tej wodzie pod przykryciem przez około 7-10 minut.",
                "Pyzy podaję z serkiem, dżemem, cukrem, cynamonem lub z rozpuszczonym masełkiem - wg upodobania."
        };
        Base.addStep(sweetPapmpuchySteps);
        Base.match("Słodkie pampuchy", sweetPapmpuchySteps);
        Base.getRecepture("Słodkie pampuchy").setDetails("Przepis na pyzy drożdżowe jest bardzo popularny i dosyć łatwy. Mnie pyzy drożdżowe kojarzą się z dzieciństwem - moja mama często podawała na obiad pyzy na słodko, którymi uwielbiałem się zajadać.", 35, 71, 41, 490, "łatwe", 422, 2611, 2, "Obiad");

        // Zupa pomidorowa

        Ingredient soupVegetables = new Ingredient(20, "dag", "włoszczyzny", 1.5);
        Ingredient water750 = new Ingredient(750, "ml", "wody", 0.6);
        //salt
        Ingredient butter20 = new Ingredient(20, "g", "rozpuszczonego masła", 0.5);
        Ingredient tomatoes = new Ingredient(60, "dag", "pomidorów", 3.6);
        Ingredient onion = new Ingredient(10, "dag", "szynki wieprzowej", 0.28);
        Ingredient flour30 = new Ingredient(3, "dag", "mąki", 0.54);
        Ingredient cream = new Ingredient(30, "g", "śmietany", 0.27);
        Ingredient sugarPositive = new Ingredient(1, " szczypta", "cukru", 0.05);
        Ingredient parsley15 = new Ingredient(15, "g", "posiekanej natki pietruszki", 0.4);
        Ingredient otherVegetables = new Ingredient(20, "dag", "warzyw, pokrojonych w paski (np. marchewki)", -1);
        Ingredient margarine20 = new Ingredient(20, "g", "margaryny", -1);
        Ingredient tomatoPaste = new Ingredient(40, "g", "koncentratu pomidorowego", -1);
        //garlic
        Ingredient naturalYogurt = new Ingredient(2, "łyżki", "jogurtu naturalnego", -1);
        Ingredient flour_other30 = new Ingredient(30, "g", "mąki innej, niż pszenna", -1);
        Ingredient otherSugar = new Ingredient(1, " szczypta", "cukru innego, niż buraczany", -1);
        Ingredient chopChives15 = new Ingredient(1, " łyżka", "posiekanego szczypiorku", -1);

        Base.addIngredient(soupVegetables);
        Base.addIngredient(water750);
        Base.addIngredient(butter20);
        Base.addIngredient(tomatoes);
        Base.addIngredient(onion);
        Base.addIngredient(flour30);
        Base.addIngredient(cream);
        Base.addIngredient(parsley15);
        Base.addIngredient(sugarPositive);

        Base.addIngredient(otherVegetables);
        Base.addIngredient(margarine20);
        Base.addIngredient(tomatoPaste);
        Base.addIngredient(naturalYogurt);
        Base.addIngredient(flour_other30);
        Base.addIngredient(otherSugar);
        Base.addIngredient(chopChives15);

        Base.setSubstituteForIngredient(soupVegetables, otherVegetables);
        Base.setSubstituteForIngredient(butter20, margarine20);
        Base.setSubstituteForIngredient(tomatoes, tomatoPaste);
        Base.setSubstituteForIngredient(onion, garlic);
        Base.setSubstituteForIngredient(flour30, flour_other30);
        Base.setSubstituteForIngredient(cream, naturalYogurt);
        Base.setSubstituteForIngredient(sugarPositive, otherSugar);
        Base.setSubstituteForIngredient(chopChives15, parsley15);

        Base.match("Zupa pomidorowa", yeast);
        Base.match("Zupa pomidorowa", water750);
        Base.match("Zupa pomidorowa", butter20);
        Base.match("Zupa pomidorowa", tomatoes);
        Base.match("Zupa pomidorowa", onion);
        Base.match("Zupa pomidorowa", flour30);
        Base.match("Zupa pomidorowa", cream);
        Base.match("Zupa pomidorowa", parsley15);
        Base.match("Zupa pomidorowa", sugarPositive);
        Base.match("Zupa pomidorowa", salt);

        String[] tomatoSoupSteps = new String[]{
                "Z włoszczyzny ugotować wywar. Pomidory opłukać, podzielić na częścii razem z pokrojoną w krążki cebulą szybko ugotować w małej ilości wody z tłuszczem.",
                "Przetrzeć przez sito, połączyć z przecedzonym wywarem. Podprawić mąką wymieszaną z 1/8 l zimnej wody.",
                "Zagotować, dodać śmietanę, przyprawić do smaku solą, cukrem. Dodać zieleninę.",
                "Podawać z grzankami, pęczakiem, ryżem gotowanym na sypko, z groszkiem ptysiowym, z naleśnikami pokrojonymi na makaron."
        };
        Base.addStep(tomatoSoupSteps);
        Base.match("Zupa pomidorowa", tomatoSoupSteps);
        Base.getRecepture("Zupa pomidorowa").setDetails(
                "Zapach domu i wspomnienie dzieciństwa przywodzi na myśl zupa pomidorowa. Przepisy na to danie, nawet jeśli wzbogacone są o zmyślne składniki, zawsze opierają się na jednym, wspólnym elemencie - pomidorach. W sezonie mogą być to pomidory świeże, sparzone i obrane ze skórek, ale równie dobrze możesz użyć koncentrat pomidorowy, dzięki czemu zupę można przygotowywać o każdej porze roku.",
                80, 13, 22, 53, "łatwe", 490, 453, 3, "Obiad");

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

