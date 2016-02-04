package com.pxy.studyhelper.activity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.pxy.studyhelper.R;
import com.pxy.studyhelper.biz.GetExamDataBiz;

import java.util.ArrayList;

public class TestActivity extends AppCompatActivity {

    private ListView  mListView;
    private ArrayList<String> mArrayList;
    private ArrayAdapter<String>  adapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test);
        mArrayList=getIntent().getStringArrayListExtra("data");
        mListView= (ListView) findViewById(R.id.listView_test);
        Log.i("tag","data---"+mArrayList.toString());
        if(mArrayList!=null){
            adapter=new ArrayAdapter<>(this,android.R.layout.simple_list_item_1,mArrayList);
            mListView.setAdapter(adapter);
        }

        mListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                GetExamDataBiz.getExamData(TestActivity.this,"question2.db");
            }
        });
    }
}
