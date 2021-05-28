package com.swufe.firstapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.widget.TextView;

public class CalcActivity extends AppCompatActivity {

    private static final String TAG = "CalcActivity";
    float rate;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_calc);
        Intent intent = getIntent();
        TextView title = findViewById(R.id.calc_title);
        TextView input = findViewById(R.id.calc_inp);
        final TextView out = findViewById(R.id.calc_out);

        title.setText(intent.getStringExtra("title"));
        rate = Float.parseFloat(intent.getStringExtra("rate"));
        Log.i(TAG,"onCreate:rate="+rate);
        input.addTextChangedListener(new TextWatcher(){

            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                //获取用户输入数据
                String str = s.toString();
                float r = Float.parseFloat(str)*rate;
                out.setText("Result="+s);

            }
        });
    }
}
