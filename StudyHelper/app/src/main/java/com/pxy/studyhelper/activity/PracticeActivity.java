package com.pxy.studyhelper.activity;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

import com.pxy.studyhelper.MyApplication;
import com.pxy.studyhelper.R;
import com.pxy.studyhelper.dao.TestDao;
import com.pxy.studyhelper.entity.FavoriteQuestion;
import com.pxy.studyhelper.entity.Question;
import com.pxy.studyhelper.utils.Tools;

import org.xutils.common.util.LogUtil;
import org.xutils.ex.DbException;
import org.xutils.view.annotation.ContentView;
import org.xutils.view.annotation.Event;
import org.xutils.view.annotation.ViewInject;
import org.xutils.x;

import java.util.List;

/**
 * 试题练习Activity
 */
@ContentView(value = R.layout.activity_exam)
public class PracticeActivity extends Activity {

    @ViewInject(value = R.id.question)
    private TextView   tvQuestion;
    @ViewInject(value = R.id.radioGroup)
    private RadioGroup mRadioGroup;
    @ViewInject(value = R.id.answerA)
    private RadioButton mAnswerA;
    @ViewInject(value = R.id.answerB)
    private RadioButton   mAnswerB;
    @ViewInject(value = R.id.answerC)
    private RadioButton   mAnswerC;
    @ViewInject(value = R.id.answerD)
    private RadioButton   mAnswerD;
    @ViewInject(value = R.id.answerE)
    private RadioButton   mAnswerE;
    @ViewInject(value = R.id.explaination)
    private TextView  tvExplaination;
    @ViewInject(value = R.id.iv_preQ)
    private ImageView  ivPreQ;
    @ViewInject(R.id.iv_add_note)
    private ImageView  ivAddNote;
    @ViewInject(value = R.id.iv_add_collection)
    private ImageView  ivAddCollection;
    @ViewInject(value = R.id.iv_nextQ)
    private ImageView  ivNextQ;

    private TestDao  mTestDao;
    private List<Question>  mQuestionList;
    //当前问题游标
    private int mCurrentIndex=0;
    private int mTotalQusestion;
    private Question  mCurrentQuestion;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        x.view().inject(this);

        String name=getIntent().getStringExtra("dbName");
        LogUtil.i("dbName--"+name);
        mTestDao=new TestDao(this,name);

        mQuestionList=mTestDao.getQuestions();
        mTotalQusestion=mQuestionList.size();

        if(mTotalQusestion<0){
            Tools.ToastShort("获取试题失败...");
            this.finish();
            return;
        }else{
            showQuestion(mCurrentIndex);
        }

        mRadioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                if(mAnswerA.isChecked())mCurrentQuestion.setSelectedAnswer(0);
                if(mAnswerB.isChecked())mCurrentQuestion.setSelectedAnswer(1);
                if(mAnswerC.isChecked())mCurrentQuestion.setSelectedAnswer(2);
                if(mAnswerD.isChecked())mCurrentQuestion.setSelectedAnswer(3);
                if(mAnswerE.isChecked())mCurrentQuestion.setSelectedAnswer(4);
                //检查答案
                CheckAnswer();
            }
        });
    }

    /**
     * 显示问题
     * @param index
     */
    private void showQuestion(int index) {
        tvExplaination.setVisibility(View.GONE);
        if(index<0){
            Tools.ToastShort("当前是第一题...");
        }else if(index<mTotalQusestion){
            mCurrentQuestion=mQuestionList.get(index);
            tvQuestion.setText(index+1+"."+mCurrentQuestion.getQuestion());
            mAnswerA.setText(mCurrentQuestion.getAnswerA());
            mAnswerB.setText(mCurrentQuestion.getAnswerB());
            mAnswerC.setText(mCurrentQuestion.getAnswerC());
            mAnswerD.setText(mCurrentQuestion.getAnswerD());
            if(mCurrentQuestion.getAnswerE().length()>0){
                mAnswerE.setVisibility(View.VISIBLE);
                mAnswerE.setText(mCurrentQuestion.getAnswerE());
            }else{
                mAnswerE.setVisibility(View.GONE);
            }
            mRadioGroup.setSelected(false);
            //todo  选中记忆
            int select=mCurrentQuestion.getSelectedAnswer();

            switch (select){
                case 0:mAnswerA.setChecked(true); break;
                case 1:mAnswerB.setChecked(true); break;
                case 2:mAnswerC.setChecked(true); break;
                case 3:mAnswerD.setChecked(true); break;
                case 4:mAnswerE.setChecked(true); break;
                default: mRadioGroup.clearCheck();break;
            }
        }else {
            Tools.ToastShort("当前是最后一题...");
        }
    }
    @Event(value ={R.id.iv_preQ,R.id.iv_add_note,R.id.iv_add_collection,R.id.iv_nextQ},
            type = View.OnClickListener.class)
    private  void  doClick(View view){
        switch (view.getId()){
            case R.id.iv_preQ:
                showQuestion(--mCurrentIndex);
                break;
            case R.id.iv_add_note:
                showRightAnswer();
                break;
            case R.id.iv_add_collection:
                //todo 加入收藏
                FavoriteQuestion  favoriteQuestion=new FavoriteQuestion();
                favoriteQuestion.setQuestion(mCurrentQuestion.getQuestion());
                favoriteQuestion.setAnswerA(mCurrentQuestion.getAnswerA());
                favoriteQuestion.setAnswerA(mCurrentQuestion.getAnswerA());
                favoriteQuestion.setAnswerA(mCurrentQuestion.getAnswerA());
                favoriteQuestion.setAnswerA(mCurrentQuestion.getAnswerA());
                favoriteQuestion.setAnswerA(mCurrentQuestion.getAnswerA());
                favoriteQuestion.setRightAnswer(mCurrentQuestion.getRightAnswer());
                favoriteQuestion.setExplaination(mCurrentQuestion.getExplaination());
                favoriteQuestion.setIsWrong(mCurrentQuestion.isWrong);
                try {
                    MyApplication.dbManager.save(favoriteQuestion);
                } catch (DbException e) {
                    e.printStackTrace();
                }
                break;
            case R.id.iv_nextQ:
                showQuestion(++mCurrentIndex);
                break;
        }

    }
    /**
     * 显示答案
     */
    private void showRightAnswer() {

        tvExplaination.setVisibility(View.VISIBLE);
        tvExplaination.setText("正确答案:"+mCurrentQuestion.getRightAnswer());
    }


    private void CheckAnswer4Exam(){
        Question  mQuestion;
        int  score=0;
        for(int i=0;i<mQuestionList.size();i++){
            mQuestion=mQuestionList.get(i);
            if(mQuestion.getSelectedAnswer()==mQuestion.getRightAnswer()){
                score++;
            }else{
                //TODO 答错题目  存入错题库  修改 isWrong  return  boolean
                mTestDao.updateQuestion(mCurrentQuestion.getAnswerA(),1);

            }
        }
    }

    private boolean CheckAnswer(){
        if(mCurrentQuestion.getRightAnswer()==mCurrentQuestion.getSelectedAnswer())
        {
            mTestDao.updateQuestion(mCurrentQuestion.getAnswerA(),0);
            return  true;
        }else {//答错
            mTestDao.updateQuestion(mCurrentQuestion.getAnswerA(),1);
            //显示正确答案
            showRightAnswer();
        }
        return  false;
    }


}
