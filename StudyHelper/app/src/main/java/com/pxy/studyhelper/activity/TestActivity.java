package com.pxy.studyhelper.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.pxy.studyhelper.R;
import com.pxy.studyhelper.utils.Constant;

public class TestActivity extends AppCompatActivity {

    private ListView  mListView;
    private ArrayAdapter<String>  adapter;
    private int position1;
    private String[]  data;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test);
        position1=getIntent().getIntExtra("position1",0);
        mListView= (ListView) findViewById(R.id.listView_test);
        data= Constant.TEST_SORTS[position1];

        adapter=new ArrayAdapter<>(this,android.R.layout.simple_list_item_1,data);
        mListView.setAdapter(adapter);

        mListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent=new Intent(TestActivity.this,TestListActivity.class);
                intent.putExtra("position1",position1);
                intent.putExtra("position2",position);
                startActivity(intent);
            }
        });
    }

}
