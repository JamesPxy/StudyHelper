package com.pxy.studyhelper.adapter;

import android.content.Context;
import android.support.v4.view.PagerAdapter;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.pxy.studyhelper.R;
import com.pxy.studyhelper.entity.Question;

import java.util.ArrayList;
import java.util.List;

/**
 * User: Pxy(15602269883@163.com)
 * Date: 2016-02-14
 * Time: 23:34
 * FIXME
 */
public class MyPagerAdapter  extends PagerAdapter {
    private Context  context;
private List<Question>  mQuestionList;
    public MyPagerAdapter(Context   context,List<Question>  mQuestionList) {
        this.context=context;
        if(mQuestionList!=null){
            this.mQuestionList=mQuestionList;
        }else{
            this.mQuestionList=new ArrayList<Question>();
        }
    }

    @Override
    public int getCount() {
        return mQuestionList.size();
    }

    @Override
    public boolean isViewFromObject(View view, Object object) {
        return view==object;
    }

    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
//        container.removeView(mQuestionList.get(position));
        super.destroyItem(container, position, object);
    }

    @Override
    public Object instantiateItem(ViewGroup container, int position) {
        LayoutInflater  inflater=LayoutInflater.from(context);
        View view=inflater.inflate(R.layout.item_exam,null);
        view.findViewById(R.id.l);
        return super.instantiateItem(container, position);
    }
}
