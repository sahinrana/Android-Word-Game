package com.project.projectapplication;

import android.content.Intent;
import android.graphics.Color;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.media.MediaPlayer;
import android.os.Build;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.support.annotation.RequiresApi;
import android.support.constraint.ConstraintLayout;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;

public class WordActivity extends AppCompatActivity implements SensorEventListener{

    private TextView timer, word;
    private CountDownTimer mCountDownTimer;
    private ConstraintLayout layout;
    private ImageButton back;
    String name1, name2, category, lang, round;
    private static long TIME_IN_MILLIS = 0;

    private Sensor sensor;
    private SensorManager sensorManager;

    private MediaPlayer mediaPlayer;

    private int azimuth, pitch, roll, pass, correct;

    private float[] accelerometerReading = new float[3];
    private float[] magnetometerReading = new float[3];
    private float[] values = new float[3];

    private float[] rotationMatrix = new float[9];
    private float[] inclinationMatrix = new float[3];

    Scanner scanner;
    ArrayList<String> wordList = new ArrayList<String>();
    ArrayList<String> arrCorrect = new ArrayList<String>();
    ArrayList<String> arrPass = new ArrayList<String>();
    //int pa, ca;

    int a = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.word_screen);

        timer = (TextView)findViewById(R.id.count_down_timer);
        word = (TextView)findViewById(R.id.word);
        back = (ImageButton) findViewById(R.id.back_button);

        layout = (ConstraintLayout)findViewById(R.id.layout);

        sensorManager = (SensorManager)getSystemService(SENSOR_SERVICE);

        final Bundle extras = getIntent().getExtras();
        if (extras != null) {
            TIME_IN_MILLIS = extras.getLong("time");
            name1 = extras.getString("player1");
            name2 = extras.getString("player2");
            category = extras.getString("category");
            lang = extras.getString("lang");
            round = extras.getString("round");
        }

        if(lang.equals("en")){
            word.setText("Ready!");
        }
        else if(lang.equals("mk")){
            word.setText("Подготвени!");
        }

        arrCorrect.clear();
        arrPass.clear();

        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(round.equals("1")){
                    Intent backIntent = new Intent(WordActivity.this, RoundActivity.class);
                    startActivity(backIntent);
                }
                else if(round.equals("2")){
                    Intent returnIntent = new Intent(WordActivity.this, ResultActivity.class);
                    startActivity(returnIntent);
                }
            }
        });

        mCountDownTimer = new CountDownTimer(TIME_IN_MILLIS*1000, 1000){

            @Override
            public void onTick(long millisUntilFinished) {
                timer.setText(String.valueOf(millisUntilFinished/1000));
            }

            @Override
            public void onFinish() {
                Intent finishIntent = new Intent(WordActivity.this, ResultActivity.class);
                    finishIntent.putExtra("player1", name1);
                    finishIntent.putExtra("player2", name2);
                    finishIntent.putExtra("time", TIME_IN_MILLIS);
                    finishIntent.putExtra("lang", lang);
                    finishIntent.putExtra("category", category);
                    finishIntent.putStringArrayListExtra("correct", arrCorrect);
                    finishIntent.putStringArrayListExtra("pass", arrPass);
                    //finishIntent.putExtra("pa", arrPass.size());
                    //finishIntent.putExtra("ca", arrCorrect.size());
                    //finishIntent.putExtra("pa", pa);
                    //finishIntent.putStringArrayListExtra("ca", ca);
                finishIntent.putExtra("round", round);

                startActivity(finishIntent);
                //extras.putSerializable("pass", arrPass);
            }
        }.start();
    }

    public static String getRandomWord(ArrayList list){
        int randomNumber = new Random().nextInt(list.size());
        return String.valueOf(list.get(randomNumber));
    }

    @Override
    public void onSensorChanged(SensorEvent event) {
        if(event.sensor.getType() == Sensor.TYPE_ACCELEROMETER){
            System.arraycopy(event.values, 0, accelerometerReading,
                    0, accelerometerReading.length);
        }
        else if(event.sensor.getType() == Sensor.TYPE_MAGNETIC_FIELD){
            System.arraycopy(event.values, 0, magnetometerReading,
                    0, magnetometerReading.length);
        }

        boolean success = SensorManager.getRotationMatrix(rotationMatrix, inclinationMatrix,
                accelerometerReading, magnetometerReading);

        if(success){
            SensorManager.getOrientation(rotationMatrix, values);
            azimuth = (int) (values[0] * (180 / Math.PI));
            pitch = (int) (values[1] * (180 / Math.PI));
            roll = (int) (values[2] * (180 / Math.PI));

            if(category.equals("food") && lang.equals("en")) {
                scanner = new Scanner(getResources().openRawResource(R.raw.food_words_en));
            }
            else if(category.equals("food") && lang.equals("mk")){
                scanner = new Scanner(getResources().openRawResource(R.raw.food_words_mk));
            }
            else if(category.equals("art") && lang.equals("en")) {
                scanner = new Scanner(getResources().openRawResource(R.raw.art_words_en));
            }
            else if(category.equals("art") && lang.equals("mk")) {
                scanner = new Scanner(getResources().openRawResource(R.raw.art_words_mk));
            }
            else if(category.equals("history") && lang.equals("en")){
                scanner = new Scanner(getResources().openRawResource(R.raw.history_words_en));
            }
            else if(category.equals("history") && lang.equals("mk")){
                scanner = new Scanner(getResources().openRawResource(R.raw.history_words_mk));
            }
            else if(category.equals("science") && lang.equals("en")){
                scanner = new Scanner(getResources().openRawResource(R.raw.science_words_en));
            }
            else if(category.equals("science") && lang.equals("mk")){
                scanner = new Scanner(getResources().openRawResource(R.raw.science_words_mk));
            }
            else if(category.equals("sport") && lang.equals("en")){
                scanner = new Scanner(getResources().openRawResource(R.raw.sport_words_en));
            }
            else if(category.equals("sport") && lang.equals("mk")){
                scanner = new Scanner(getResources().openRawResource(R.raw.sport_words_mk));
            }
            else if(category.equals("summer") && lang.equals("en")){
                scanner = new Scanner(getResources().openRawResource(R.raw.summer_words_en));
            }
            else if(category.equals("summer") && lang.equals("mk")){
                scanner = new Scanner(getResources().openRawResource(R.raw.summer_words_mk));
            }
            else if(category.equals("winter") && lang.equals("en")){
                scanner = new Scanner(getResources().openRawResource(R.raw.winter_words_en));
            }
            else if(category.equals("winter") && lang.equals("mk")){
                scanner = new Scanner(getResources().openRawResource(R.raw.winter_words_mk));
            }
            else if(category.equals("geography") && lang.equals("en")){
                scanner = new Scanner(getResources().openRawResource(R.raw.geography_words_en));
            }
            else if(category.equals("geography") && lang.equals("mk")){
                scanner = new Scanner(getResources().openRawResource(R.raw.geography_words_mk));
            }

                wordList = new ArrayList<String>();

                try{
                    while (scanner.hasNextLine()){
                        wordList.add(scanner.nextLine());
                    }
                }
                finally {
                    scanner.close();
                }
            //}

            if (roll>-50) {
                if (a != 1 && a!=0) {
                    a = 1;
                    pass++;
                    if(word.getText() != null && word.getText() != "Pass" && word.getText() != "Correct"){
                        arrPass.add(String.valueOf(word.getText()));
                        //arrPass.add((String) word.getText());
                    }
                    word.setText("Pass");
                    layout.setBackgroundColor(Color.parseColor("#ffce00"));
                    mediaPlayer = MediaPlayer.create(this, R.raw.pass_sound);
                    mediaPlayer.start();
                    //word.setText(getRandomWord(wordList));
                    //word.setText(getRandomWord(wordList));
                }
            }
            else if (roll<-150){
                if(a != 2 && a != 0) {
                    a = 2;
                    correct++;
                    if(word.getText() != null && word.getText() != "Pass" && word.getText() != "Correct"){
                        arrCorrect.add(String.valueOf(word.getText()));
                        //arrCorrect.add((String) word.getText());
                    }
                    mediaPlayer = MediaPlayer.create(this, R.raw.correct_sound);
                    mediaPlayer.start();
                    word.setText("Correct");
                    layout.setBackgroundColor(Color.parseColor("#2A9B3F"));
                    //word.setText(getRandomWord(wordList));
                }
            }
            else if (roll>-120 && roll<-70) {
                if (a != 3) {

                    a = 3;

                    //if (word.getText() == "Pass" || word.getText() == "Correct") {
                    /*new Handler().postDelayed(new Runnable() {
                        @Override
                        public void run() {
                            word.setText(getRandomWord(wordList));
                        }
                    },3000);*/
                        /*try {
                            timer.setEnabled(false);
                            Thread.sleep(3000);

                        } catch (Exception e) {
                            e.printStackTrace();
                        }*/
                    layout.setBackgroundColor(Color.parseColor("#063244"));
                        timer.setEnabled(true);
                        word.setText(getRandomWord(wordList));
                    //}
                }
            }
        }
        /*if(event.sensor.getType() == Sensor.TYPE_ACCELEROMETER){
            float x = event.values[0];
            float y = event.values[1];

            if(Math.abs(y)>Math.abs(x)){
                //when the device is tilt down
                if(x>92){
                    word.setText("Correct");
                }
                //when the device is tilt up
                if(x<88 && x>0){
                    word.setText("Pass");
                }
                word.setText(String.valueOf((int)x*10));
            }
            if(x>(88) && x<(92)){
                word.setText("no tilt");
            }
        }*/
    }

    @Override
    public void onAccuracyChanged(Sensor sensor, int accuracy) { }

    @RequiresApi(api = Build.VERSION_CODES.KITKAT)
    @Override
    protected void onResume() {
        super.onResume();

        Sensor accelerometer = sensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER);
        if (accelerometer != null) {
            sensorManager.registerListener(this, accelerometer,
                    SensorManager.SENSOR_DELAY_NORMAL, SensorManager.SENSOR_DELAY_UI);
        }
        Sensor magneticField = sensorManager.getDefaultSensor(Sensor.TYPE_MAGNETIC_FIELD);
        if (magneticField != null) {
            sensorManager.registerListener(this, magneticField,
                    SensorManager.SENSOR_DELAY_NORMAL, SensorManager.SENSOR_DELAY_UI);
        }
    }

    @Override
    protected void onPause() {
        super.onPause();
        sensorManager.unregisterListener(this);
    }
}