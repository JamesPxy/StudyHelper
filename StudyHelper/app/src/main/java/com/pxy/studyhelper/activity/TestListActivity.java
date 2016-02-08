package com.pxy.studyhelper.activity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ListView;

import com.pxy.studyhelper.R;
import com.pxy.studyhelper.adapter.DownloadAdapter;
import com.pxy.studyhelper.biz.GetExamDataBiz;
import com.pxy.studyhelper.entity.Test;
import com.pxy.studyhelper.utils.DialogUtil;
import com.pxy.studyhelper.utils.LoadingDialog;

import java.util.List;

public class TestListActivity extends AppCompatActivity {

    private ListView  mListView;
    private DownloadAdapter mDownloadAdapter;

    private int sort1,sort2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test_list);
        mListView= (ListView) findViewById(R.id.listView_test_list);
        sort1=getIntent().getIntExtra("position1",0);
        sort2=getIntent().getIntExtra("position2",0);
        LoadingDialog.showLoadingDialog(this);
        //请求获取试题内容
        GetExamDataBiz.getExamData(this,sort1,sort2);
    }

    public void setListView(List<Test>  mTestArrayList) {

        mDownloadAdapter=new DownloadAdapter(this,mTestArrayList);
        mListView.setAdapter(mDownloadAdapter);
        LoadingDialog.dissmissDialog();
    }

}
