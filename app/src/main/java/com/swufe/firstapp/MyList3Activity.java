package com.swufe.firstapp;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.SimpleAdapter;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.HashMap;

public class MyList3Activity extends AppCompatActivity implements AdapterView.OnItemClickListener {

    private static final String TAG = "MyList3Activity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_list3);

        final ListView listView = findViewById(R.id.mylist3);
        final ProgressBar progressBar = findViewById(R.id.progressBar);
       /* progressBar.setVisibility(View.GONE);

        ArrayList listItems = new ArrayList();
        for(int i=0;i<10;i++){
            HashMap<String,String> map = new HashMap<String,String>();
            map.put("ItemTitle","Rate:"+i);  //标题文字
            map.put("ItemDetail","Detail:"+i);  //详情描述
            listItems.add(map);
        }

        SimpleAdapter listItemAdapter = new SimpleAdapter(this,
                listItems,    //数据源
                R.layout.activity_list_item,  //ListItem的XML布局实现
                new String[]{"ItemTitle", "ItemDetail"},
                new int[]{R.id.itemTitle, R.id.itemDetail}
        );
        listView.setAdapter(listItemAdapter);
        listView.setVisibility(View.VISIBLE);
*/
        ListView.setOnItemClickListener(this);
        //获取网络数据
        Handler handler = new Handler(){
            public void handleMessage(Message msg){
                if(msg.what==9){
                    ArrayList list3 = (ArrayList) msg.obj;
                    ListAdapter adapter = new ArrayAdapter<String>(MyList3Activity.this,android.R.layout.simple_list_item_1,list3);
                    listView.setAdapter(adapter);

                    //显示隐藏
                    listView.setVisibility(View.VISIBLE);
                    progressBar.setVisibility(View.GONE);
                }
                super.handleMessage(msg);
            }
        };

        MyTask task = new MyTask();
        task.setHandler(handler);

        Thread t = new Thread((Runnable) task);
        t.start();
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        Object itemAtPosition = getListView().getItemAtPosition(position);
        HashMap map = (HashMap) itemAtPosition;
        String titleStr = (String) map.get("ItemTitle");
        String detailStr = (String) map.get("ItemDetail");
        Log.i(TAG,"OnItemClick:titleStr="+titleStr);
        Log.i(TAG,"OnItemClick:detailStr="+detailStr);

        TextView title = (TextView) view.findViewById(R.id.itemTitle);
        TextView detail = (TextView) view.findViewById(R.id.itemDetail);
        String title2 = String.valueOf(title.getText());
        String detail2 = String.valueOf(detail.getText());
        Log.i(TAG,"OnItemClick:title2="+title2);
        Log.i(TAG,"OnItemClick:detail2="+detail2);
    }

    public AdapterView getListView() {
        AdapterView listView = null;
        return listView;
    }
}
