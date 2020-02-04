package com.project.projectapplication;

import android.content.Context;
import android.content.Intent;
import android.content.res.Resources;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class RoundActivity extends AppCompatActivity {

    private TextView round_time, tv_time, tv_player1, tv_player2;
    private EditText player1, player2;
    private Button round_30, round_60, round_90, return_button, start;
    String value, category;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.round_screen);

        tv_player1 = (TextView)findViewById(R.id.tv_player1);
        tv_player2 = (TextView)findViewById(R.id.tv_player2);
        player1 = (EditText)findViewById(R.id.player1);
        player2 = (EditText)findViewById(R.id.player2);
        tv_time = (TextView)findViewById(R.id.tv_round_time);
        round_time = (TextView)findViewById(R.id.round_time);
        round_30 = (Button)findViewById(R.id.round30);
        round_60 = (Button)findViewById(R.id.round60);
        round_90 = (Button)findViewById(R.id.round90);
        return_button = (Button)findViewById(R.id.return_button4);
        start = (Button)findViewById(R.id.button4);

        Bundle extras = getIntent().getExtras();
        if (extras != null) {
            value = extras.getString("lang");
            category = extras.getString("category");
        }

        Context context = LocaleHelper.setLocale(RoundActivity.this, value.toString());
        Resources resources = context.getResources();
        return_button.setText(resources.getString(R.string.return_button));

        if(value.equals("en")){
            //context = LocaleHelper.setLocale(RoundActivity.this, "en");
            tv_player1.setText(resources.getString(R.string.first_player));
            tv_player2.setText(resources.getString(R.string.second_player));
            tv_time.setText(resources.getString(R.string.round_time));
            start.setText(resources.getString(R.string.play));
        }

        if(value.equals("mk")){
            //context = LocaleHelper.setLocale(RoundActivity.this, "mk");
            tv_player1.setText(resources.getString(R.string.first_player));
            tv_player2.setText(resources.getString(R.string.second_player));
            tv_time.setText(resources.getString(R.string.round_time));
            start.setText(resources.getString(R.string.play));
        }

        round_30.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                round_time.clearComposingText();
                round_time.setText("30");
            }
        });

        round_60.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                round_time.clearComposingText();
                round_time.setText("60");
            }
        });

        round_90.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                round_time.clearComposingText();
                round_time.setText("90");
            }
        });

        start.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent startIntent = new Intent(RoundActivity.this, WordActivity.class);
                startIntent.putExtra("time", Long.parseLong(round_time.getText().toString()));
                startIntent.putExtra("player1", player1.getText().toString());
                startIntent.putExtra("player2", player2.getText().toString());
                startIntent.putExtra("category", category);
                startIntent.putExtra("lang", value);
                startIntent.putExtra("round", "1");
                startActivity(startIntent);
            }
        });

        return_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent returnIntent = new Intent(RoundActivity.this, CategoryActivity.class);
                startActivity(returnIntent);
            }
        });
    }
}
