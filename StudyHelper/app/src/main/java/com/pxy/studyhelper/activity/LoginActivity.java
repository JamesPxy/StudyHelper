package com.pxy.studyhelper.activity;

import android.app.Activity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.Toast;

import com.pxy.studyhelper.R;

import org.xutils.view.annotation.ContentView;
import org.xutils.view.annotation.Event;
import org.xutils.view.annotation.ViewInject;
import org.xutils.x;

import cn.bmob.v3.BmobUser;
import cn.bmob.v3.listener.SaveListener;

@ContentView(value = R.layout.activity_login)
public class LoginActivity extends Activity {
    @ViewInject(value = R.id.username)
    private AutoCompleteTextView mUsername;
    @ViewInject(value = R.id.password)
    private AutoCompleteTextView  mPassword;
    @ViewInject(value = R.id.btn_login)
    private Button  mBtnLogin;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_login2);
        x.view().inject(this);
    }

    @Event(value = R.id.btn_login,type = View.OnClickListener.class)
    private  void  login(View  v){
        String  userName=mUsername.getText().toString().trim();
        String password=mPassword.getText().toString().trim();
        if(TextUtils.isEmpty(userName)||
                TextUtils.isEmpty(password)){
            Toast.makeText(LoginActivity.this, "用户名和密码不能为空...", Toast.LENGTH_SHORT).show();
            return;
        }
        BmobUser bu = new BmobUser();
        bu.setUsername(userName);
        bu.setPassword(password);
//注意：不能用save方法进行注册
        bu.signUp(this, new SaveListener() {
            @Override
            public void onSuccess() {
                Toast.makeText(LoginActivity.this, "注册成功...", Toast.LENGTH_SHORT).show();
            }
            @Override
            public void onFailure(int code, String msg) {
                Toast.makeText(LoginActivity.this, "注册失败..."+code+"---"+msg, Toast.LENGTH_SHORT).show();
            }
        });
    }


}
