package com.project.projectapplication;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.res.Resources;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageView;

public class CategoryActivity extends AppCompatActivity {

    private ImageView food, history, science, sport, summer, winter, geography, art, settings, name;
    String value = "en";
    SharedPreferences sharedPreferences;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.category_screen);

        //setTitle(R.string.title);

        name = (ImageView) findViewById(R.id.name);
        settings = (ImageView) findViewById(R.id.settings);
        food = (ImageView) findViewById(R.id.food_button);
        art = (ImageView)findViewById(R.id.art_button);
        history = (ImageView)findViewById(R.id.history_button);
        science = (ImageView)findViewById(R.id.science_button);
        sport = (ImageView)findViewById(R.id.sport_button);
        summer = (ImageView)findViewById(R.id.summer_button);
        winter = (ImageView)findViewById(R.id.winter_button);
        geography = (ImageView)findViewById(R.id.geography_button);

        sharedPreferences = getSharedPreferences("launch", Context.MODE_PRIVATE);

        Bundle extras = getIntent().getExtras();
        if (extras != null) {
            value = extras.getString("lang");
        }
        else {
            value = sharedPreferences.getString("language", value);
        }

        Context context = LocaleHelper.setLocale(CategoryActivity.this, value);
        Resources resources = context.getResources();
        //return_button.setText(resources.getString(R.string.return_button));

        if(value.equals("en")){
            food.setImageResource(R.drawable.food);
            history.setImageResource(R.drawable.history);
            science.setImageResource(R.drawable.science);
            sport.setImageResource(R.drawable.sport_en);
            summer.setImageResource(R.drawable.summer);
            winter.setImageResource(R.drawable.winter);
            geography.setImageResource(R.drawable.geography);
            art.setImageResource(R.drawable.art_en);
        }

        if(value.equals("mk")){
            food.setImageResource(R.drawable.hrana);
            history.setImageResource(R.drawable.istorija);
            science.setImageResource(R.drawable.nauka);
            sport.setImageResource(R.drawable.sport_mk);
            summer.setImageResource(R.drawable.leto);
            winter.setImageResource(R.drawable.zima);
            geography.setImageResource(R.drawable.geografija);
            art.setImageResource(R.drawable.art_mk);
        }

        settings.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent returnIntent = new Intent(CategoryActivity.this, MainActivity.class);
                returnIntent.putExtra("lang", value);
                startActivity(returnIntent);
            }
        });

        food.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent roundIntent = new Intent(CategoryActivity.this, RoundActivity.class);
                roundIntent.putExtra("lang", value);
                roundIntent.putExtra("category", "food");
                startActivity(roundIntent);
            }
        });

        art.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent roundIntent = new Intent(CategoryActivity.this, RoundActivity.class);
                roundIntent.putExtra("lang", value);
                roundIntent.putExtra("category", "art");
                startActivity(roundIntent);
            }
        });

        history.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent roundIntent = new Intent(CategoryActivity.this, RoundActivity.class);
                roundIntent.putExtra("lang", value);
                roundIntent.putExtra("category", "history");
                startActivity(roundIntent);
            }
        });

        science.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent roundIntent = new Intent(CategoryActivity.this, RoundActivity.class);
                roundIntent.putExtra("lang", value);
                roundIntent.putExtra("category", "science");
                startActivity(roundIntent);
            }
        });

        sport.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent roundIntent = new Intent(CategoryActivity.this, RoundActivity.class);
                roundIntent.putExtra("lang", value);
                roundIntent.putExtra("category", "sport");
                startActivity(roundIntent);
            }
        });

        summer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent roundIntent = new Intent(CategoryActivity.this, RoundActivity.class);
                roundIntent.putExtra("lang", value);
                roundIntent.putExtra("category", "summer");
                startActivity(roundIntent);
            }
        });

        winter.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent roundIntent = new Intent(CategoryActivity.this, RoundActivity.class);
                roundIntent.putExtra("lang", value);
                roundIntent.putExtra("category", "winter");
                startActivity(roundIntent);
            }
        });

        geography.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent roundIntent = new Intent(CategoryActivity.this, RoundActivity.class);
                roundIntent.putExtra("lang", value);
                roundIntent.putExtra("category", "geography");
                startActivity(roundIntent);
            }
        });
    }
}
