package com.project.projectapplication;

import android.content.Context;
import android.content.Intent;
import android.content.res.Resources;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.util.ArrayList;

public class ResultActivity extends AppCompatActivity {

    private TextView name, result_pass, result_correct, category_name;
    private ArrayList<String> arrCorrect, arrPass;
    private static long TIME_IN_MILLIS = 0;
    int p,c,i;
    //int pa, ca;
    private String word_correct, word_pass, round, name1, name2, lang, category;
    private Button cont2, correct_num, pass_num;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.result_screen);

        name = (TextView)findViewById(R.id.name);
        result_pass = (TextView)findViewById(R.id.result_pass);
        result_correct = (TextView)findViewById(R.id.result_correct);
        category_name = (TextView)findViewById(R.id.category);
        cont2 = (Button)findViewById(R.id.cont2);
        correct_num = (Button)findViewById(R.id.number_correct);
        pass_num = (Button)findViewById(R.id.number_pass);

        Bundle extras = getIntent().getExtras();
        if (extras != null) {
            name1 = extras.getString("player1");
            name2 = extras.getString("player2");
            TIME_IN_MILLIS = extras.getLong("time");
            lang = extras.getString("lang");
            category = extras.getString("category");
            arrCorrect = extras.getStringArrayList("correct");
            arrPass = extras.getStringArrayList("pass");
            //p = extras.getInt("p");
            //pa = extras.getInt("pa");
            //ca = extras.getInt("ca");
            round = extras.getString("round");
            //pass = (ArrayList<String>) extras.getSerializable("pass");
        }

        Context context = LocaleHelper.setLocale(ResultActivity.this, lang);
        Resources resources = context.getResources();

        if(category.equals("food")) {
            category_name.setText(resources.getString(R.string.food).toUpperCase());
        }
        else if(category.equals("art")) {
            category_name.setText(resources.getString(R.string.art).toUpperCase());
        }
        else if(category.equals("history")){
            category_name.setText(resources.getString(R.string.history).toUpperCase());
        }
        else if(category.equals("science")){
            category_name.setText(resources.getString(R.string.science).toUpperCase());
        }
        else if(category.equals("sport")){
            category_name.setText(resources.getString(R.string.sport).toUpperCase());
        }
        else if(category.equals("summer")){
            category_name.setText(resources.getString(R.string.summer).toUpperCase());
        }
        else if(category.equals("winter")){
            category_name.setText(resources.getString(R.string.winter).toUpperCase());
        }
        else if(category.equals("geography")){
            category_name.setText(resources.getString(R.string.geography).toUpperCase());
        }

        for (int i= 0; i<arrCorrect.size(); i++){
            /*if (i==0)
                word_correct = arrCorrect.get(0).substring(4,arrCorrect.get(0).length()-1) + "\n";
            else*/
                word_correct = word_correct + arrCorrect.get(i) + "\n";
        }
        result_correct.setText(word_correct);
        correct_num.setText(String.valueOf(arrCorrect.size()));

        for (int i= 0; i<arrPass.size(); i++){
            /*if(i==0)
                word_pass = arrPass.get(0).substring(4,arrPass.get(0).length()-1) + "\n";
            else*/
                word_pass = word_pass + arrPass.get(i) + "\n";
        }
        result_pass.setText(word_pass);
        pass_num.setText(String.valueOf(arrPass.size()));

        if(round != null && round.equals("1")){
            if(lang.equals("en")){
                if(name1.substring(name1.length()-1) == "s"){
                    name.setText(name1.toUpperCase() + "' Score");
                }
                else if(name1.substring(name1.length()-1) != "s"){
                    name.setText(name1.toUpperCase() + "'s Score");
                }
                cont2.setText("Continue");
            }
            else if(lang.equals("mk")){
                name.setText("Резултат на " + name1.toUpperCase());
                cont2.setText("Продолжи");
            }
        }
        else if(round != null && round.equals("2")){
            if(lang.equals("en")){
                if(name2.substring(name2.length()-1) == "s"){
                    name.setText(name2.toUpperCase() + "' Score");
                }
                else if(name2.substring(name2.length()-1) != "s"){
                    name.setText(name2.toUpperCase() + "'s Score");
                }
                cont2.setText("Done!");
            }
            else if(lang.equals("mk")){
                name.setText("Резултат на " + name2.toUpperCase());
                cont2.setText("Направено!");
            }
        }
        else{
            name.setText("Error!!!!!!!");
        }
        
        /*for(int i=0; i<pass.size(); i++){
            result_pass.setText(pass.get(i)+"\n");
        }*/
        //result.setText("Hello " + name1 + "\nPass:\n" + pass.subList(0,pass.size()));

        cont2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent contIntent;

                if(round.equals("1")){
                    contIntent = new Intent(ResultActivity.this, WordActivity.class);
                    contIntent.putExtra("round", "2");
                    contIntent.putExtra("player1", name1);
                    contIntent.putExtra("player2", name2);
                    contIntent.putExtra("time", TIME_IN_MILLIS);
                    contIntent.putExtra("lang", lang);
                    contIntent.putExtra("category", category);
                    startActivity(contIntent);
                }
                else if(round.equals("2")){
                    contIntent = new Intent(ResultActivity.this, CategoryActivity.class);
                    startActivity(contIntent);
                }
            }
        });
    }

}
