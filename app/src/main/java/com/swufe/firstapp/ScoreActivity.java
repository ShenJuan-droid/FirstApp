package com.swufe.firstapp;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import android.icu.text.BreakIterator;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

public class ScoreActivity extends AppCompatActivity {

    TextView scoreA;
    TextView scoreB;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_score);

        scoreA = (TextView)findViewById(R.id.score);
        scoreB = (TextView)findViewById(R.id.score1);

    }
    public void btnAdd1(View btn){
        showScore(1);
    }
    public void btnAdd2(View btn){
        showScore(2);
    }
    public void btnAdd3(View btn){
        showScore(3);
    }

    public void btnAdd4(View btn){
        showScore1(1);
    }
    public void btnAdd5(View btn){
        showScore1(2);
    }
    public void btnAdd6(View btn){
        showScore1(3);
    }


    public void btnReset(View btn){
        scoreA.setText("0");
        scoreB.setText("0");
    }

    private void showScore(int inc){
        Log log = null;
        log.i("show","inc="+inc);
        String oldScore = (String)scoreA.getText();
        int newScore = Integer.parseInt(oldScore) + inc;
        scoreA.setText(""+newScore);
    }

    private void showScore1(int inc){
        Log log = null;
        log.i("show","inc="+inc);
        String oldScore = (String)scoreB.getText();
        int newScore = Integer.parseInt(oldScore) + inc;
        scoreB.setText(""+newScore);
    }

}
