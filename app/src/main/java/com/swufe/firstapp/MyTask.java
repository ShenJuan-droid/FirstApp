package com.swufe.firstapp;

import androidx.appcompat.app.AppCompatActivity;

import android.R.layout;
import android.os.Bundle;
import android.os.Handler;
import android.widget.ArrayAdapter;
import android.widget.ListAdapter;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;

public class MyTask extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_task);

        ListView listView = findViewById(R.id.mylist);
        List list1 = new ArrayList<String>();
        for(int i = 1;i<100;i++){
            list1.add("stu"+i);
        }

        ListAdapter adapter = new ArrayAdapter<String>(this,android.R.layout.simple_list_item_1,list1);
    }

    public void setHandler(Handler handler) {

    }
}
