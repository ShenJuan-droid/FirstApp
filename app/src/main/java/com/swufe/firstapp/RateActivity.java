package com.swufe.firstapp;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class RateActivity extends AppCompatActivity {

    EditText rmb;
    TextView result;

    @SuppressLint("WrongViewCast")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_rate);

        rmb = findViewById(R.id.input_rmb);
        result = findViewById(R.id.result);
    }

    public void onclick(View btn){
        //获取用户输入内容
        String str = rmb.getText().toString();
        float r = 0;
        if(str.length()>0){
            r = Float.parseFloat(str);
        }

        if(btn.getId()==R.id.btn_dollar){
            float val = r * 6.7f;
            result.setText(String.valueOf(val));
        }else if(btn.getId() == R.id.btn_euro){
            float val = r * 11;
            result.setText(val + "");
        }else{
            float val = r * 500;
            result.setText(String.valueOf(val));
        }
    }
}
