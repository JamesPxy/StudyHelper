package com.pxy.studyhelper.activity;

import android.app.Activity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.pxy.studyhelper.R;
import com.pxy.studyhelper.entity.User;

import org.xutils.view.annotation.ContentView;
import org.xutils.view.annotation.Event;
import org.xutils.view.annotation.ViewInject;
import org.xutils.x;

import cn.bmob.v3.listener.SaveListener;


@ContentView(value = R.layout.activity_register)
public class RegisterActivity extends Activity {

    @ViewInject(value = R.id.phone_layout)
    private LinearLayout  lv_phone;
    @ViewInject(value = R.id.email_layout)
    private LinearLayout  lv_email;
    @ViewInject(value = R.id.regUsr)
    private EditText edt_user;
    @ViewInject(value = R.id.regPwd)
    private EditText edt_pwd;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        x.view().inject(this);
    }
    @Event(value = R.id.btn_register,type = View.OnClickListener.class)
    private void doRegist(View view){
        User user = new User();
        String  userName=edt_user.getText().toString().trim();
        String password=edt_pwd.getText().toString().trim();
        //todo  校验  手机号 邮箱
        if(TextUtils.isEmpty(password)||TextUtils.isEmpty(userName)){
            Toast.makeText(RegisterActivity.this, "密码不能为空...", Toast.LENGTH_SHORT).show();
            return;
        }
        if(lv_email.isPressed()){
            user.setEmail(userName);
        }else{
            user.setMobilePhoneNumber(userName);
        }
        user.setUsername(userName);
        user.setPassword(password);
        user.signUp(this, new SaveListener() {
            @Override
            public void onSuccess() {
                // TODO Auto-generated method stub
                Toast.makeText(RegisterActivity.this, "注册成功...", Toast.LENGTH_SHORT).show();
            }
            @Override
            public void onFailure(int code, String msg) {
                // TODO Auto-generated method stub
                Toast.makeText(RegisterActivity.this, "注册失败..."+msg, Toast.LENGTH_SHORT).show();
            }
        });
    }
}
