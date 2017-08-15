package com.jasmin.myrandgame;

import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.util.Random;

public class MainActivity extends AppCompatActivity {

    TextView txtHighScore,txtScore;
    Button btnplay,btnreset;

    SharedPreferences pref;
    SharedPreferences.Editor editor; //aita diye file a value change kore parbo

    private final String PREF_GAME="com.jasmin.myrandgame.game";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        txtHighScore=(TextView)findViewById(R.id.txtHighScore);
        txtScore=(TextView)findViewById(R.id.txtScore);

        btnplay=(Button)findViewById(R.id.btnplay);
        btnreset=(Button)findViewById(R.id.btnreset);

        pref=getSharedPreferences(PREF_GAME,MODE_PRIVATE);
        editor=pref.edit();

        final int highScore=pref.getInt("highScore",0);
        txtHighScore.setText("High Score:"+highScore);

        btnplay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Random random=new Random();
                int score=random.nextInt(1000);
                txtScore.setText(String.valueOf(score));

                int getSavedScore=pref.getInt("highScore",0);

                if(score>getSavedScore)
                {
                    txtHighScore.setText("High Score: " + score);
                    editor.putInt("highScore", score);
                    editor.commit();
                }
            }
        });

        btnreset.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                editor.putInt("highScore",0);
                editor.commit();

                txtHighScore.setText("highScore: "+0);
                txtScore.setText(String.valueOf(0));
            }
        });
    }
}
