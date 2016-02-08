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

public class TestBigActivity extends AppCompatActivity {

    private ListView  mListView;
    private ArrayAdapter adapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.test_layout);
        mListView= (ListView) findViewById(R.id.listView);

        setListView();
    }

    private void setListView() {
        adapter=new ArrayAdapter<>(this,android.R.layout.simple_list_item_1, Constant.TEST_BIG_SORT);
        mListView.setAdapter(adapter);

        mListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id){
                Intent  mIntent=new Intent(TestBigActivity.this,TestActivity.class);
                mIntent.putExtra("position1",position);
                startActivity(mIntent);
            }
        });
    }


}
