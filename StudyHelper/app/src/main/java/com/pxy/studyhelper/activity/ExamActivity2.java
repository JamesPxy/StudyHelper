package com.pxy.studyhelper.activity;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Chronometer;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

import com.pxy.studyhelper.MyApplication;
import com.pxy.studyhelper.R;
import com.pxy.studyhelper.dao.TestDao;
import com.pxy.studyhelper.entity.FavoriteQuestion;
import com.pxy.studyhelper.entity.Question;
import com.pxy.studyhelper.utils.DialogUtil;
import com.pxy.studyhelper.utils.Tools;

import org.xutils.common.util.LogUtil;
import org.xutils.ex.DbException;
import org.xutils.view.annotation.ContentView;
import org.xutils.view.annotation.Event;
import org.xutils.view.annotation.ViewInject;
import org.xutils.x;

import java.util.ArrayList;
import java.util.List;


/**
 * 试题测试Activity
 */
@ContentView(value = R.layout.activity_exam)
public class ExamActivity2 extends AppCompatActivity {
    @ViewInject(value = R.id.question)
    private TextView tvQuestion;
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
    private ImageView ivPreQ;
    @ViewInject(R.id.iv_add_note)
    private ImageView  ivAddNote;
    @ViewInject(value = R.id.iv_add_collection)
    private ImageView  ivAddCollection;
    @ViewInject(value = R.id.iv_nextQ)
    private ImageView  ivNextQ;

    @ViewInject(value = R.id.chronometer)
    private Chronometer chronometer;
    @ViewInject(value = R.id.iv_time)
    private ImageView  ivTime;

    @ViewInject(value = R.id.viewPager)
    private ViewPager  mViewPager;

    private TestDao mTestDao;
    private List<Question> mQuestionList;
    //当前问题游标
    private int mCurrentIndex=0;
    private int mTotalQusestion;
    private Question  mCurrentQuestion;
    private String  mTime;
    private boolean  isRuning=true;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        x.view().inject(this);

        String name=getIntent().getStringExtra("dbName");
        LogUtil.i("dbName--" + name);
        mTestDao=new TestDao(this,name);

        mQuestionList=mTestDao.getQuestions();

        mTotalQusestion=mQuestionList.size();

        if(mTotalQusestion>0){
            showQuestion(mCurrentIndex);
        }else{
            Tools.ToastShort("获取试题失败...");
            this.finish();
            return;
        }

        initView();
    }

    private void initView() {

        View  view=View.inflate(this,R.layout.item_exam,null);
        tvQuestion= (TextView) view.findViewById(R.id.question);
        List<View>  list=new ArrayList<>();
        for(int i=0;i<mTotalQusestion;i++){
            list.add(view);
        }

        mViewPager.setAdapter();
        mViewPager.


        chronometer.start();
        mTime=mTotalQusestion+":00";
        LogUtil.e(mTime);

        chronometer.setOnChronometerTickListener(new Chronometer.OnChronometerTickListener() {
            @Override
            public void onChronometerTick(Chronometer chronometer) {
//                重置时间  chronometer.setBase(SystemClock.elapsedRealtime());
                if(mTime.equals(chronometer.getText())){
                    Tools.ToastShort("测试时间到....");
                    DialogUtil.showProgressDialog(ExamActivity.this, "测试时间到");
                }
            }
        });

        mRadioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                int checked = -1;
                switch (checkedId) {
                    case R.id.answerA:
                        checked = 0;
                        break;
                    case R.id.answerB:
                        checked = 1;
                        break;
                    case R.id.answerC:
                        checked = 2;
                        break;
                    case R.id.answerD:
                        checked = 3;
                        break;
                    case R.id.answerE:
                        checked = 4;
                        break;
                }
                //标记选中答案
                mCurrentQuestion.setSelectedAnswer(checked);
                if (mCurrentQuestion.getSelectedAnswer() != -1) {
                    //检查答案
                    if (!CheckAnswer()) {//答错  显示正确答案
                        showRightAnswer();
                    } else {
                        Tools.ToastShort("回答正确..");
                    }
                } else {
                    tvExplaination.setVisibility(View.GONE);
                }
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
            mCurrentIndex=0;
        }else if(index<mTotalQusestion){
            mCurrentQuestion=mQuestionList.get(index);
            tvQuestion.setText(index + 1 + ". " + mCurrentQuestion.getQuestion());
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
//            mRadioGroup.setSelected(false);
            //  记住用户选中答案 并显示
            switch (mCurrentQuestion.getSelectedAnswer()){
                case 0:mAnswerA.setChecked(true);break;
                case 1:mAnswerB.setChecked(true); break;
                case 2:mAnswerC.setChecked(true); break;
                case 3:mAnswerD.setChecked(true); break;
                case 4:mAnswerE.setChecked(true); break;
                default: mRadioGroup.clearCheck();break;
            }
            if(isFav()) {
                ivAddCollection.setImageResource(R.drawable.icon_favor2);
            }else {
                ivAddCollection.setImageResource(R.drawable.icon_favor1);
            }
        }else {
            mCurrentIndex=mTotalQusestion-1;
            new AlertDialog.Builder(ExamActivity.this)
                    .setIcon(R.drawable.ic_luncher)
                    .setMessage("已经是最后一题,是否退出")
                    .setTitle("提示")
                    .setCancelable(false)
                    .setNegativeButton("取消", null)
                    .setPositiveButton("退出", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            ExamActivity.this.finish();
                        }
                    })
                    .show();
        }
    }
    @Event(value ={R.id.iv_preQ,R.id.iv_add_note,R.id.iv_add_collection,R.id.iv_nextQ,R.id.iv_back,R.id.iv_menu,R.id.iv_time},
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
                addToFav();
                break;
            case R.id.iv_nextQ:
                showQuestion(++mCurrentIndex);
                break;
            case R.id.iv_back:
                finish();
                break;
            case R.id.iv_menu:
                //todo  showMenu;
                break;
            case R.id.iv_time:
                PauseTime();
                break;
        }
    }

    private void PauseTime() {
        if(isRuning){
            chronometer.stop();
            Tools.ToastShort("先休息一下,让脑子飞一会吧...");
            ivTime.setImageResource(android.R.drawable.ic_media_play);
            isRuning=false;
        }else{
            chronometer.start();
            Tools.ToastShort("继续开始奋战...");
            ivTime.setImageResource(android.R.drawable.ic_media_pause);
            isRuning=true;
        }
    }

    /**
     * 收藏题目
     */
    private void addToFav() {
        //todo  判断是否已被收藏
        FavoriteQuestion favQ=new FavoriteQuestion();
        if(isFav()) {
            try {
                if(favQs!=null){
                    MyApplication.dbManager.delete(favQs);LogUtil.e("favq  null");}
                Tools.ToastShort("取消收藏成功");
                ivAddCollection.setImageResource(R.drawable.icon_favor1);
            } catch (DbException e) {
                LogUtil.e(e.getMessage());
            }
            return;
        }
        favQ.setQuestion(mCurrentQuestion.getQuestion());
        favQ.setAnswerA(mCurrentQuestion.getAnswerA());
        favQ.setAnswerB(mCurrentQuestion.getAnswerB());
        favQ.setAnswerC(mCurrentQuestion.getAnswerC());
        favQ.setAnswerD(mCurrentQuestion.getAnswerD());
        favQ.setAnswerE(mCurrentQuestion.getAnswerE());
        favQ.setRightAnswer(mCurrentQuestion.getRightAnswer());
        favQ.setExplaination(mCurrentQuestion.getExplaination());
        favQ.setIsWrong(mCurrentQuestion.isWrong);
        try {
            MyApplication.dbManager.save(favQ);
            ivAddCollection.setImageResource(R.drawable.icon_favor2);
            Tools.ToastShort("收藏成功");
        } catch (DbException e) {
            LogUtil.e(e.getMessage());
        }
    }

    FavoriteQuestion favQs=new FavoriteQuestion();
    private boolean  isFav(){
        favQs=null;
        try {
            favQs=MyApplication.dbManager.selector(FavoriteQuestion.class).where("answerA","LIKE",mCurrentQuestion.getAnswerA()).findFirst();
        } catch (DbException e) {
            LogUtil.e(e.getMessage());
        }
        if(favQs!=null) {
            LogUtil.e(favQs.toString());
            return true;
        }
        return false;
    }

    /**
     * 显示答案
     */
    private void showRightAnswer() {
        tvExplaination.setVisibility(View.VISIBLE);
        if(mCurrentQuestion==null) return;
        String str=null;
        switch (mCurrentQuestion.getRightAnswer()){
            case 0:str="A";break;
            case 1:str="B";break;
            case 2:str="C";break;
            case 3:str="D";break;
            case 4:str="E";break;
        }
        tvExplaination.setText("正确答案: "+str+"\n"+"解析: "+mCurrentQuestion.getExplaination());
    }


    private boolean CheckAnswer(){
        if(mCurrentQuestion.getRightAnswer()==mCurrentQuestion.getSelectedAnswer())
        {
            mTestDao.updateQuestion(mCurrentQuestion.getAnswerA(),0);
            return  true;
        }else {//答错
            mTestDao.updateQuestion(mCurrentQuestion.getAnswerA(),1);
        }
        return  false;
    }

}
