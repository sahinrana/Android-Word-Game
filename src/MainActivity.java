package com.project.projectapplication;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.res.Resources;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.NumberPicker;
import android.widget.PopupWindow;
import android.widget.TextView;

//import android.widget.ImageView;

public class MainActivity extends AppCompatActivity {

    private TextView desc, lang, howtoplay, description;
    //private ImageView mk, en, tr;
    private Button cont;
    private PopupWindow popupWindow;
    private NumberPicker langPicker;
    String l=null;

    SharedPreferences sharedPreferences;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        description = (TextView)findViewById(R.id.desc_text);
        lang = (TextView)findViewById(R.id.language_selection_text);
        howtoplay = (TextView)findViewById(R.id.description_text);
        desc = (TextView)findViewById(R.id.description_text);
        //mk = (ImageView)findViewById(R.id.macedonian_selection);
        //en = (ImageView)findViewById(R.id.english_selection);
        //tr = (ImageView)findViewById(R.id.turkish_selection);
        cont = (Button)findViewById(R.id.cont);
        langPicker = (NumberPicker) findViewById(R.id.langPicker);

        final Bundle extras = getIntent().getExtras();
        /*if (extras != null) {
            l = extras.getString("lang");
        }*/

        if(langPicker != null){
            final String[] values = {"English", "Македонски"};
            langPicker.setMinValue(0);
            langPicker.setMaxValue(values.length-1);
            langPicker.setValue(0);
            langPicker.setDisplayedValues(values);
            langPicker.setWrapSelectorWheel(false);

            if(langPicker.getValue() == 0){
                Context context = LocaleHelper.setLocale(MainActivity.this, "en");
                l = LocaleHelper.getLanguage(context);
                Resources resources = context.getResources();
                lang.setText(resources.getString(R.string.lang));
                desc.setText(resources.getString(R.string.desc));
                howtoplay.setText(resources.getString(R.string.how_to_play));
                cont.setText(resources.getString(R.string.cont));
            }
            else if(langPicker.getValue() == 1){
                Context context = LocaleHelper.setLocale(MainActivity.this, "mk");
                l = LocaleHelper.getLanguage(context);
                Resources resources = context.getResources();
                lang.setText(resources.getString(R.string.lang));
                desc.setText(resources.getString(R.string.desc));
                howtoplay.setText(resources.getString(R.string.how_to_play));
                cont.setText(resources.getString(R.string.cont));
            }
            /*else if(langPicker.getValue() == 2){
                Context context = LocaleHelper.setLocale(MainActivity.this, "tr");
                l = LocaleHelper.getLanguage(context);
                Resources resources = context.getResources();
                lang.setText(resources.getString(R.string.lang));
                desc.setText(resources.getString(R.string.desc));
                howtoplay.setText(resources.getString(R.string.how_to_play));
                cont.setText(resources.getString(R.string.cont));
            }*/

            langPicker.setOnValueChangedListener(new NumberPicker.OnValueChangeListener() {
                @Override
                public void onValueChange(NumberPicker picker, int oldVal, int newVal) {
                    //String text = "Language changed from " + values[oldVal] + " to " + values[newVal];
                    //Toast.makeText(MainActivity.this, text, Toast.LENGTH_SHORT).show();

                    if(values[newVal] == "English" || values[newVal] == null){
                        Context context = LocaleHelper.setLocale(MainActivity.this, "en");
                        l = LocaleHelper.getLanguage(context);
                        Resources resources = context.getResources();
                        lang.setText(resources.getString(R.string.lang));
                        desc.setText(resources.getString(R.string.desc));
                        howtoplay.setText(resources.getString(R.string.how_to_play));
                        cont.setText(resources.getString(R.string.cont));
                        //startActivity(enIntent);
                    }
                    /*else if(values[newVal] == "Türkçe"){
                        Context context = LocaleHelper.setLocale(MainActivity.this, "tr");
                        l = LocaleHelper.getLanguage(context);
                        Resources resources = context.getResources();
                        lang.setText(resources.getString(R.string.lang));
                        desc.setText(resources.getString(R.string.desc));
                        howtoplay.setText(resources.getString(R.string.how_to_play));
                        cont.setText(resources.getString(R.string.cont));
                        //startActivity(enIntent);
                    }*/
                    else if(values[newVal] == "Македонски"){
                        Context context = LocaleHelper.setLocale(MainActivity.this, "mk");
                        l = LocaleHelper.getLanguage(context);
                        Resources resources = context.getResources();
                        lang.setText(resources.getString(R.string.lang));
                        desc.setText(resources.getString(R.string.desc));
                        howtoplay.setText(resources.getString(R.string.how_to_play));
                        cont.setText(resources.getString(R.string.cont));
                        //startActivity(mkIntent);
                    }
                }
            });
        }

        desc.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent descIntent = new Intent(MainActivity.this, HowToPlayActivity.class);
                descIntent.putExtra("lang", l);
                startActivity(descIntent);
            }
        });

        cont.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent contIntent = new Intent(MainActivity.this, CategoryActivity.class);
                if(l==null){
                    langPicker.setValue(0);
                    //en.performClick();
                }
                contIntent.putExtra("lang", l);
                SharedPreferences preferences = getSharedPreferences("launch", MODE_PRIVATE);
                preferences.edit().putBoolean("activity_executed",true).apply();
                preferences.edit().putString("language", l).apply();
                startActivity(contIntent);
            }
        });

        /*mk.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Context context = LocaleHelper.setLocale(MainActivity.this, "mk");
                l = LocaleHelper.getLanguage(context);
                Resources resources = context.getResources();
                lang.setText(resources.getString(R.string.lang));
                desc.setText(resources.getString(R.string.desc));
                howtoplay.setText(resources.getString(R.string.how_to_play));
                cont.setText(resources.getString(R.string.cont));
                //startActivity(mkIntent);
            }
        });

        en.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Context context = LocaleHelper.setLocale(MainActivity.this, "en");
                l = LocaleHelper.getLanguage(context);
                Resources resources = context.getResources();
                lang.setText(resources.getString(R.string.lang));
                desc.setText(resources.getString(R.string.desc));
                howtoplay.setText(resources.getString(R.string.how_to_play));
                cont.setText(resources.getString(R.string.cont));
                //startActivity(enIntent);
            }
        });

        tr.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Context context = LocaleHelper.setLocale(MainActivity.this, "tr");
                l = LocaleHelper.getLanguage(context);
                Resources resources = context.getResources();
                lang.setText(resources.getString(R.string.lang));
                desc.setText(resources.getString(R.string.desc));
                howtoplay.setText(resources.getString(R.string.how_to_play));
                cont.setText(resources.getString(R.string.cont));
                //startActivity(enIntent);
            }
        });*/
    }

    @Override
    protected void attachBaseContext(Context newBase) {
        super.attachBaseContext(LocaleHelper.onAttach(newBase));
    }
}

