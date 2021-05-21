package com.swufe.firstapp;

import androidx.appcompat.app.AppCompatActivity;

import android.app.ListActivity;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.widget.ArrayAdapter;
import android.widget.ListAdapter;

import java.util.ArrayList;
import java.util.List;

public class FirstListActivity extends ListActivity{

    private static final String TAG ="FirstListActivity" ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //setContentView(R.layout.activity_first_list);   //该句一定不要
        List list1 = new ArrayList();
        for(int i=1;i<100;i++){
            list1.add("item" + i);
        }

        String[] list_data = {"Chinese","math","English","physics"};
        ListAdapter adapter = new ArrayAdapter<String>(this,android.R.layout.simple_list_item_1,list_data);
        setListAdapter(adapter);

        Handler handler = new Handler(){
            public void handleMessage(Message msg){
                if(msg.what==9){
                    Log.i(TAG,"handleMessage: get msg.what"+msg.what);
                    ArrayList list2 = (ArrayList) msg.obj;
                    ListAdapter adapter = new ArrayAdapter<String>(FirstListActivity.this,android.R.layout.simple_list_item_1,list2);
                    setListAdapter(adapter);
                }
                super.handleMessage(msg);
            }
        };

        MyTask task = new MyTask();
        task.setHandler(handler);

        RateActivity rate = new RateActivity();

        Thread t = new Thread((Runnable) task);
        t.start();
    }
}
