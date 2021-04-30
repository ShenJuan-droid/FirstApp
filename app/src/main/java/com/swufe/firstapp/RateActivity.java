package com.swufe.firstapp;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

public class RateActivity extends AppCompatActivity implements Runnable{

    private static final String TAG = "Rate";
    private float dollarRate = 0.0f;
    private float euroRate = 0.1266f;
    private float wonRate = 170.2708f;

    EditText rmb;
    TextView result;

    Handler handler;

    @SuppressLint("WrongViewCast")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_rate);

        rmb = findViewById(R.id.input_rmb);
        result = findViewById(R.id.result);

        //
        SharedPreferences sharedPreferences = getSharedPreferences("myrate", Activity.MODE_PRIVATE);

        dollarRate = sharedPreferences.getFloat("dollar_rate",0.0f);
        euroRate = sharedPreferences.getFloat("euro_rate",0.0f);
        wonRate = sharedPreferences.getFloat("won_rate",0.0f);

        Log.i(TAG,"onCreate: sp dollarRate" + dollarRate);
        Log.i(TAG,"onCreate: sp euroRate" + euroRate);
        Log.i(TAG,"onCreate: sp wonRate" + wonRate);

        //开启线程
        Thread t = new Thread(this);
        t.start();

        //创建handler对象
        handler = new Handler(){
            public void handleMessage(Message msg){
                if(msg.what==5){
                    String str = (String) msg.obj;
                    Log.i(TAG,"handleMessage:getMessage msg=" + str);
                    result.setText(str);
                }
                super.handleMessage(msg);
            }
        };


    }

    public void onClick(View btn){
        //获取用户输入内容
        String str = rmb.getText().toString();
        float r = 0;
        if(str.length()>0){
            r = Float.parseFloat(str);
        }else{
            //用户没有输入内容
            Toast.makeText(this,"请输入内容",Toast.LENGTH_SHORT).show();
        }

        Log.i(TAG,"onclick:r=" + r);

        //计算
        if(btn.getId()==R.id.btn_dollar){
            result.setText(String.format("%.2f,r*dollarRate"));
        }else if(btn.getId() == R.id.btn_euro){
            result.setText(String.format("%.2f,r*euroRate"));
        }else{
            result.setText(String.format("%.2f,r*wonRate"));
        }
    }

    //打开一个页面Activity
    public void openOne(View btn){
        Intent config = new Intent(this,ConfigActivity.class);
        config.putExtra("dollar_rate_key",dollarRate);
        config.putExtra("euro_rate_key",euroRate);
        config.putExtra("won_rate_key",wonRate);

        Log.i(TAG,"openOne:dollarRate=" + dollarRate);
        Log.i(TAG,"openOne:euroRate=" + euroRate);
        Log.i(TAG,"openOne:wonRate=" + wonRate);

        //startActivity(config);
        startActivityForResult(config,1);
    }
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        if(requestCode==1 && resultCode==2){
            Bundle bundle = data.getExtras();
            assert bundle != null;
            dollarRate = bundle.getFloat("key_dollar",0.1f);
            euroRate = bundle.getFloat("key_euro",0.1f);
            wonRate = bundle.getFloat("key_won",0.1f);
            Log.i(TAG, "onActivityResult: dollarRate=" + dollarRate);
            Log.i(TAG, "onActivityResult: euroRate=" + euroRate);
            Log.i(TAG, "onActivityResult: wonRate=" + wonRate);

            //
            SharedPreferences sp = getSharedPreferences("myrate", Activity.MODE_PRIVATE);
            SharedPreferences.Editor editor = sp.edit();
            editor.putFloat("dollar_rate",dollarRate);
            editor.putFloat("euro_rate",euroRate);
            editor.putFloat("won_rate",wonRate);
            editor.apply();
        }

        super.onActivityResult(requestCode, resultCode, data);
    }

    public void run(){
        Log.i(TAG,"run:......");
        for(int i=1;i<3;i++) {
            Log.i(TAG,"run:i=" + i);
            //耗时操作
            try {
                Thread.sleep(3000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

        //返回消息到主线程
        Message msg = handler.obtainMessage(5);
        msg.obj = "Hello from run()";
        handler.sendMessage(msg);

        //获取网络数据
        URL url = null;
        try{
            url = new URL("Http://www.usd-cny.com/icbc.htm");
            HttpURLConnection http = (HttpURLConnection) url.openConnection();
            InputStream in = http.getInputStream();

            String html = inputStream2String(in);
            Log.i(TAG,"run: html=" + html);
        }catch(MalformedURLException e){
            e.printStackTrace();
        }catch (IOException e){
            e.printStackTrace();
        }
    }

    private String inputStream2String(InputStream inputStream) throws IOException {
        final int bufferSize = 1024;
        final char[] buffer = new char[bufferSize];
        final StringBuilder out = new StringBuilder();
        Reader in = new InputStreamReader(inputStream,"UTF-8");
        for(; ;){
            int rsz = in.read(buffer,0,buffer.length);
            if(rsz<0)
                break;
            out.append(buffer,0,rsz);
        }
        return out.toString();
    }
}
